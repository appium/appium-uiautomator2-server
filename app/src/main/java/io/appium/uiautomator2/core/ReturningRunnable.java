package io.appium.uiautomator2.core;

public abstract class ReturningRunnable<T> implements Runnable {

    public T result;

    public ReturningRunnable() {
        result = null;
    }

    public T getResult() {
        return result;
    }

    protected void setResult(T value) {
        result = value;
    }
}