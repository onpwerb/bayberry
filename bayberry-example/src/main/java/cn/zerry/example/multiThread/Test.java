package cn.zerry.example.multiThread;

import java.util.Random;
import java.util.concurrent.*;

public class Test {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Callable<Integer> task = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Random random = new Random();
                int i = random.nextInt(Math.round(10));
                return i;
            }
        };
        Future<Integer> result = executorService.submit(task);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();



    }

}
