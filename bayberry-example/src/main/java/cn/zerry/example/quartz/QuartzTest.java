package cn.zerry.example.quartz;

import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTest {
    public static void main(String[] args) {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            scheduler.start();



            scheduler.shutdown();

        }catch (Exception e){

        }
    }
}
