package com.nhnacademy.manageResource;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

public class Cpu {

    public static void main(String[] args) {
        Cpu a = new Cpu();
        System.out.println(a.getCpu());
    }

    public double getCpu() {
        OperatingSystemMXBean fuck = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        return fuck.getSystemCpuLoad() * 100;
    }
}
