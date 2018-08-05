package com.hencoder.javatests;

public class Synchronized3Demo implements TestDemo {

    private int x = 0;
    private int y = 0;
    private String name;
    private final Object monitor1 = new Object();
    private final Object monitor2 = new Object();

    private void count(int newValue) {
        synchronized (monitor1) {
            synchronized (monitor2) {
                name = x + "-" + y;
            }
        }
    }

    private void printNameAndPoint() {
        synchronized (monitor2) {
            synchronized (monitor1) {
                System.out.println("name: " + name + ", point: " + x + ", ");
            }
        }
    }

    private void setName(String newName) {
        synchronized (monitor2) {
            name = newName;
        }
    }

    private void printName() {
        synchronized (monitor2) {
            System.out.println("name: " + name);
        }
    }

    @Override
    public void runTest() {
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1_000_000_000; i++) {
                    count(i);
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1_000_000_000; i++) {
                    count(i);
                }
            }
        }.start();
    }
}
