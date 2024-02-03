package javatest.threadtest;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author JC
 */
public class ThreadPoolManager {

    private ThreadPoolTaskExecutor taskExecutor;

    public ThreadPoolManager() {
        taskExecutor = new ThreadPoolTaskExecutor();
        //最大线程数
        taskExecutor.setMaxPoolSize(5);
        //核心线程数
        taskExecutor.setCorePoolSize(2);
//            taskExecutor.setQueueCapacity(3);
        taskExecutor.initialize();
    }

    public void executeTask(String taskId) {
        taskExecutor.execute(new ThreadPoolTask(taskId));
    }

    public void cancelTask(String taskId) {
//            System.out.println("Task " + taskId + " is ready to cancel.");
        System.out.println("taskExecutor size :" + taskExecutor.getThreadPoolExecutor().getQueue().size());
        // 模拟取消任务，只能取消在queue队列中的任务
        for (Runnable runnable : taskExecutor.getThreadPoolExecutor().getQueue()) {
//                if (runnable instanceof ThreadPoolTask) {
            ThreadPoolTask threadPoolTask = (ThreadPoolTask) runnable;
            if (threadPoolTask.getTaskId().equals(taskId)) {
                System.out.println("Task " + taskId + " is requested to cancel.");
                threadPoolTask.cancel();
                break;
            }
//                }
        }
    }

    public void shutDown() {
        taskExecutor.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolManager threadPoolManager = new ThreadPoolManager();
        for (int i = 0; i < 10; i++) {
            threadPoolManager.executeTask("task" + i);
        }
        System.out.println("main thread end sleep");
        for (int i = 0; i < 10; i++) {
            threadPoolManager.cancelTask("task" + i);
        }
        Thread.sleep(5000);

        /*
         * 总结来说就是：线程池默认创建的Worker线程是“非守护线程”，thread.setDaemon(false)，
         * JDK1.5的时候，就规定了当所有非守护线程退出时，JVM才会退出，Main方法主线程和Worker线程都是非守护线程，所以不关掉线程池，进程不会结束。
         */
        threadPoolManager.shutDown();
    }

}
