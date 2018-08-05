package com.hencoder.javatests;

class SingleMan {
    private static SingleMan ourInstance;

    private SingleMan() {
    }

    static SingleMan newInstance() {
        if (ourInstance == null) {
            synchronized (SingleMan.class) {
                if (ourInstance == null) {
                    ourInstance = new SingleMan();
                }
            }
        }
        return ourInstance;
    }
}
