package com.hackathon2020.botlemeasure;

public class BedInfo {
    private int Bedno;
    private int Fluid;

    public BedInfo(int bedno, int fluid) {
        Bedno = bedno;
        Fluid = fluid;
    }

    public int getBedno() {
        return Bedno;
    }

    public void setBedno(int bedno) {
        Bedno = bedno;
    }

    public int getFluid() {
        return Fluid;
    }

    public void setFluid(int fluid) {
        Fluid = fluid;
    }
}
