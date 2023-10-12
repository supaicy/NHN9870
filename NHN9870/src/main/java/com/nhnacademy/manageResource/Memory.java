package com.nhnacademy.manageResource;

public class Memory {
    public long getTotal() {
        return Runtime.getRuntime().maxMemory();
    }

    public long getUsableMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    public long getUsingMemory() {
        return getTotal()-getUsableMemory();
    }
}
