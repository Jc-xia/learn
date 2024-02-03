package javatest.threadtest;

/**
 * @author JC
 * @create 2024/1/30
 */
public class ThreadPoolTask implements Runnable {
    private String taskId;
    private boolean interruptedFlag = false;

    ThreadPoolTask(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
//        System.out.println(Thread.interrupted());
//        System.out.println(Thread.currentThread().isInterrupted());
        // 判断任务是否被取消
//        if (Thread.currentThread().isInterrupted()) {
        if (interruptedFlag) {
            System.out.println("Task " + taskId + " is canceled.");
            return;
        }

        // 执行任务逻辑
        System.out.println("Executing task " + taskId);
        try {
            System.out.println(Thread.currentThread().getName() + " Task " + taskId + " start sleep");
            Thread.sleep(5000);  // 模拟耗时操作
            System.out.println(Thread.currentThread().getName() + " Task " + taskId + " end sleep");

        } catch (InterruptedException e) {
            System.out.println("Task " + taskId + " is interrupted.");
            return;
        }
        System.out.println("Task " + taskId + " completed.");
    }

    public String getTaskId() {
        return taskId;
    }


    public void cancel() {
        System.out.println(Thread.currentThread().getName() + " current state is " + Thread.currentThread().getState());
        System.out.println(Thread.interrupted());

//        Thread.currentThread().interrupt();
//        interruptedFlag = true;
        System.out.println(Thread.currentThread().getName() + " interrupted result : " + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + " current state is " + Thread.currentThread().getState());
//        System.out.println(Thread.interrupted());
//        System.out.println(Thread.currentThread().isInterrupted());
//        System.out.println(Thread.interrupted());

    }
}
