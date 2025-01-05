package openended;

class MyThread extends Thread {
    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " thread starting...");
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println(name + " executing step " + i);
                Thread.sleep(1000); // Simulate task delay
            }
        } catch (InterruptedException e) {
            System.out.println(name + " interrupted!");
        }
        System.out.println(name + " thread finished.");
    }
}

public class ThreadLifecycleDemo {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread("Thread-1");
        MyThread thread2 = new MyThread("Thread-2");

        thread1.start();
        thread2.start();

        try {
            thread1.join(); // Wait for thread1 to finish
            thread2.join(); // Wait for thread2 to finish
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }

        System.out.println("All threads completed.");
    }
}

