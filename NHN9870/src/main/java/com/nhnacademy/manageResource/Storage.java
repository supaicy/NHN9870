package com.nhnacademy.manageResource;

import java.io.File;

public class Storage {
    File file = new File(".");

    public long getTotalSpace() {
        return file.getTotalSpace();
    }

    public long getCurrentUsing() {
        return file.getTotalSpace()- file.getUsableSpace();
    }
}
