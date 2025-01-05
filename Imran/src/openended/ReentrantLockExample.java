package openended;

import java.util.LinkedList;
import java.util.Queue;

class SimpleBuffer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int CAPACITY = 5;

    public synchronized void produce(int value) throws InterruptedException {
        while (queue.size() == CAPACITY) {
            System.out.println("Buffer full. Producer waiting...");
            wait();
        }
        queue.add(value);
        System.out.println("Produced: " + value);
        notifyAll(); // Notify consumer threads
    }

    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("Buffer empty. Consumer waiting...");
            wait();
        }
        int value = queue.poll();
        System.out.println("Consumed: " + value);
        notifyAll(); // Notify producer threads
        return value;
    }
}

public class ReentrantLockExample {
    public static void main(String[] args) {
        SimpleBuffer buffer = new SimpleBuffer();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    buffer.produce(i);
                    Thread.sleep(500); // Simulate production delay
                }
            } catch (InterruptedException e) {
                System.out.println("Producer interrupted");
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    buffer.consume();
                    Thread.sleep(1000); // Simulate consumption delay
                }
            } catch (InterruptedException e) {
                System.out.println("Consumer interrupted");
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }

        System.out.println("Producer-Consumer simulation complete.");
    }
}
