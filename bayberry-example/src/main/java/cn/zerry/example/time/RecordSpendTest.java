package cn.zerry.example.time;

import org.springframework.util.StopWatch;

/**
 * xxx
 * @author linzengrui
 * @date 2020年01月07日 15:26
 */
public class RecordSpendTest {

	public static void main(String[] args) {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start("method");
		System.out.println("do something");
		stopWatch.stop();
		System.out.println(stopWatch.prettyPrint());

	}
}
