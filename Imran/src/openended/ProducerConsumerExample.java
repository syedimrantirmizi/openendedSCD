package openended;

import java.util.LinkedList;
import java.util.Queue;

class SharedBuffer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int CAPACITY = 5;

    public synchronized void produce(int value) throws InterruptedException {
        while (queue.size() == CAPACITY) {
            System.out.println("Buffer full. Producer waiting...");
            wait();
        }
        queue.add(value);
        System.out.println("Produced: " + value);
        notifyAll();
    }

    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("Buffer empty. Consumer waiting...");
            wait();
        }
        int value = queue.poll();
        System.out.println("Consumed: " + value);
        notifyAll();
        return value;
    }
}

class Producer extends Thread {
    private final SharedBuffer buffer;

    public Producer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                buffer.produce(i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Producer interrupted");
        }
    }
}

class Consumer extends Thread {
    private final SharedBuffer buffer;

    public Consumer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                buffer.consume();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Consumer interrupted");
        }
    }
}

public class ProducerConsumerExample {
    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer();

        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);

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

