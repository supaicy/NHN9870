//package com.nhnacademy.manageResource;
//
//import java.io.File;
//
//public class Storage {
//    public long getDiskSpace() {
//        File root = null;
//        try {
//            root = new File("/");
//            String[] list = new String[2];
//            list[0] = getStorage(root.getTotalSpace());
//            return list;
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    public String getStorage(long size) {
//        return String.valueOf((int)(size/(1024*1024)));
//    }
//}
