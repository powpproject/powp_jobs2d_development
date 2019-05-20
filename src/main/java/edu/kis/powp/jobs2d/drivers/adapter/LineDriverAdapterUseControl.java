package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.powp.jobs2d.Job2dDriver;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class LineDriverAdapterUseControl implements Job2dDriver {

    private Job2dDriver job2dDriver;

    private double distance;
    private int prevX, prevY;

    public LineDriverAdapterUseControl(Job2dDriver job2dDriver) {
        this.job2dDriver = job2dDriver;
        distance = 0;
        prevX = 0;
        prevY = 0;
    }

    private void calculateDistance(int nextX, int nextY){

        double currentDistance = sqrt((pow(nextX - this.prevX,2) + pow(nextY - this.prevY,2)));
        this.distance += currentDistance;
    }

    @Override
    public void setPosition(int x, int y) {
        job2dDriver.setPosition(x,y);
    }

    @Override
    public void operateTo(int x, int y) {
        calculateDistance(x,y);
        System.out.println(distance);
        job2dDriver.operateTo(x,y);
    }
}
