package cn.zerry.bayberry.core.config;

import com.baomidou.mybatisplus.core.toolkit.*;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlUtils;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Statement;
import java.util.*;

@Intercepts({@Signature(
		type = StatementHandler.class,
		method = "query",
		args = {Statement.class, ResultHandler.class}
), @Signature(
		type = StatementHandler.class,
		method = "update",
		args = {Statement.class}
), @Signature(
		type = StatementHandler.class,
		method = "batch",
		args = {Statement.class}
)})
/**
 * 性能分析，sql打印
 */
public class HyperformanceInterceptor extends PerformanceInterceptor {
	private static final String DruidPooledPreparedStatement = "com.alibaba.druid.pool.DruidPooledPreparedStatement";
	private static final String T4CPreparedStatement = "oracle.jdbc.driver.T4CPreparedStatement";
	private static final String OraclePreparedStatementWrapper = "oracle.jdbc.driver.OraclePreparedStatementWrapper";
	private long maxTime = 0L;
	private boolean format = false;
	private boolean writeInLog = false;
	private Method oracleGetOriginalSqlMethod;
	private Method druidGetSQLMethod;

	public HyperformanceInterceptor() {
	}

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object firstArg = invocation.getArgs()[0];
		String invocationMethod = invocation.getMethod().getName();
		Statement statement;
		if (Proxy.isProxyClass(firstArg.getClass())) {
			statement = (Statement) SystemMetaObject.forObject(firstArg).getValue("h.statement");
		} else {
			statement = (Statement)firstArg;
		}

		MetaObject stmtMetaObj = SystemMetaObject.forObject(statement);

		try {
			statement = (Statement)stmtMetaObj.getValue("stmt.statement");
		} catch (Exception var20) {
		}

		if (stmtMetaObj.hasGetter("delegate")) {
			try {
				statement = (Statement)stmtMetaObj.getValue("delegate");
			} catch (Exception var19) {
			}
		}

		String originalSql = null;
		String stmtClassName = statement.getClass().getName();
		Class clazz;
		Object stmtSql;
		if ("com.alibaba.druid.pool.DruidPooledPreparedStatement".equals(stmtClassName)) {
			try {
				if (this.druidGetSQLMethod == null) {
					clazz = Class.forName("com.alibaba.druid.pool.DruidPooledPreparedStatement");
					this.druidGetSQLMethod = clazz.getMethod("getSql");
				}

				stmtSql = this.druidGetSQLMethod.invoke(statement);
				if (stmtSql instanceof String) {
					originalSql = (String)stmtSql;
				}
			} catch (Exception var18) {
				var18.printStackTrace();
			}
		} else if ("oracle.jdbc.driver.T4CPreparedStatement".equals(stmtClassName) || "oracle.jdbc.driver.OraclePreparedStatementWrapper".equals(stmtClassName)) {
			try {
				if (this.oracleGetOriginalSqlMethod != null) {
					stmtSql = this.oracleGetOriginalSqlMethod.invoke(statement);
					if (stmtSql instanceof String) {
						originalSql = (String)stmtSql;
					}
				} else {
					clazz = Class.forName(stmtClassName);
					this.oracleGetOriginalSqlMethod = this.getMethodRegular(clazz, "getOriginalSql");
					if (this.oracleGetOriginalSqlMethod != null) {
						this.oracleGetOriginalSqlMethod.setAccessible(true);
						if (null != this.oracleGetOriginalSqlMethod) {
							stmtSql = this.oracleGetOriginalSqlMethod.invoke(statement);
							if (stmtSql instanceof String) {
								originalSql = (String)stmtSql;
							}
						}
					}
				}
			} catch (Exception var17) {
			}
		}

		if (originalSql == null) {
			originalSql = statement.toString();
		}

		originalSql = originalSql.replaceAll("[\\s]+", " ");
		int index = this.indexOfSqlStart(originalSql);
		if (index > 0) {
			originalSql = originalSql.substring(index);
		}

		long start = SystemClock.now();
		Object result = invocation.proceed();
		long timing = SystemClock.now() - start;
		Object target = PluginUtils.realTarget(invocation.getTarget());
		MetaObject metaObject = SystemMetaObject.forObject(target);
		MappedStatement ms = (MappedStatement)metaObject.getValue("delegate.mappedStatement");
		String clazzMethod = ms.getId();
		StringBuilder formatSql = (new StringBuilder()).append("[").append(invocationMethod).append("] query ").append(timing).append(" times: ").append(
				SqlUtils.sqlFormat(originalSql, this.format)).append(" [").append(clazzMethod).append("]");
		Logger logger = LoggerFactory.getLogger(clazzMethod);
		if (this.isWriteInLog()) {
			if (this.getMaxTime() >= 1L && timing > this.getMaxTime()) {
				logger.info(formatSql.toString());
			} else {
				logger.info(formatSql.toString());
			}
		} else {
			logger.info(formatSql.toString());
			Assert.isFalse(this.getMaxTime() >= 1L && timing > this.getMaxTime(), " The SQL execution time is too large, please optimize ! ", new Object[0]);
		}

		return result;
	}

	@Override
	public Object plugin(Object target) {
		return target instanceof StatementHandler ? Plugin.wrap(target, this) : target;
	}

	@Override
	public void setProperties(Properties prop) {
		String maxTime = prop.getProperty("maxTime");
		String format = prop.getProperty("format");
		if (StringUtils.isNotEmpty(maxTime)) {
			this.maxTime = Long.parseLong(maxTime);
		}

		if (StringUtils.isNotEmpty(format)) {
			this.format = Boolean.valueOf(format);
		}

	}
	@Override
	public Method getMethodRegular(Class<?> clazz, String methodName) {
		if (Object.class.equals(clazz)) {
			return null;
		} else {
			Method[] var3 = clazz.getDeclaredMethods();
			int var4 = var3.length;

			for(int var5 = 0; var5 < var4; ++var5) {
				Method method = var3[var5];
				if (method.getName().equals(methodName)) {
					return method;
				}
			}

			return this.getMethodRegular(clazz.getSuperclass(), methodName);
		}
	}

	private int indexOfSqlStart(String sql) {
		String upperCaseSql = sql.toUpperCase();
		Set<Integer> set = new HashSet();
		set.add(upperCaseSql.indexOf("SELECT "));
		set.add(upperCaseSql.indexOf("UPDATE "));
		set.add(upperCaseSql.indexOf("INSERT "));
		set.add(upperCaseSql.indexOf("DELETE "));
		set.remove(-1);
		if (CollectionUtils.isEmpty(set)) {
			return -1;
		} else {
			int createTableIndex = upperCaseSql.indexOf("CREATE TABLE");
			if(createTableIndex != -1){
				return createTableIndex;
			}
			List<Integer> list = new ArrayList(set);
			list.sort(Comparator.naturalOrder());
			return (Integer)list.get(0);
		}
	}

	@Override
	public HyperformanceInterceptor setMaxTime(final long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	@Override
	public long getMaxTime() {
		return this.maxTime;
	}

	@Override
	public HyperformanceInterceptor setFormat(final boolean format) {
		this.format = format;
		return this;
	}

	@Override
	public boolean isFormat() {
		return this.format;
	}

	@Override
	public HyperformanceInterceptor setWriteInLog(final boolean writeInLog) {
		this.writeInLog = writeInLog;
		return this;
	}

	@Override
	public boolean isWriteInLog() {
		return this.writeInLog;
	}
}
