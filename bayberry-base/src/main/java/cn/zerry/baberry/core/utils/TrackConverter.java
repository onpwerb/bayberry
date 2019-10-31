package cn.zerry.baberry.core.utils;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import cn.zerry.baberry.core.constant.TrackConsts;
import org.slf4j.MDC;

/**
 * xxx
 * @author linzengrui
 * @date 2019年10月31日 17:58
 */
public class TrackConverter extends ClassicConverter {

	@Override
	public String convert(ILoggingEvent event) {
		return MDC.get(TrackConsts.TRACK_KEY) == null ? TrackConsts.NON_TRACK_VALUE : MDC.get(TrackConsts.TRACK_KEY);
	}
}
