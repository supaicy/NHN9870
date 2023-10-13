package com.nhnacademy.node;

public class ActiveNode extends Node implements Runnable{
    Thread thread;
    boolean stopped = false;
    private int startTime;
    long interval;
    long defaultInterval;

    protected ActiveNode() {
        interval = defaultInterval;
        thread = new Thread(this);
    }

    protected ActiveNode(String id) {
        super(id);
        thread = new Thread(this);
    }

    protected void main() {

    }
    public void start() {
        thread = new Thread(this);
        thread.start();
        stopped = false;
    }

    public void stop() {
        thread.interrupt();
        stopped = true;
    }

    private int getStartTime() {
        return startTime;
    }

    public double getInterval() {
        return interval;
    }

    public double getDefaultInterval() {
        return defaultInterval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    protected void preprocess() {}
    protected void postprocess() {}

    public void setDefaultInterval(int defaultInterval) {
        this.defaultInterval = defaultInterval;
    }

    public boolean isAlive() {
        return !stopped;
    }
    public void run() {
        preprocess();
        while (isAlive()) {
            try {
                main();
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                stop();
            }
        }
        postprocess();
    }
}
