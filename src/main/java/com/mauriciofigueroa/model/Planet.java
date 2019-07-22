package com.mauriciofigueroa.model;

import lombok.Data;

import java.awt.geom.Point2D;

import static java.lang.Math.cos;
import static java.lang.Math.toRadians;

@Data
public class Planet {

    private int angularVelocity;
    private boolean clockWise;
    private int sunDistance;

    public Planet(int angularVelocity, boolean clockWise, int sunDistance) {
        this.angularVelocity = angularVelocity;
        this.clockWise = clockWise;
        this.sunDistance = sunDistance;
    }


    public Point2D getCurrentPosition(int day) {

        int currentGrade = clockWise ? day * angularVelocity : day * -angularVelocity;

        double x = sunDistance * cos(toRadians(currentGrade - 90));
        double y = sunDistance * Math.sin(toRadians(currentGrade + 90));

        return new Point2D.Double(x, y);
    }

}
