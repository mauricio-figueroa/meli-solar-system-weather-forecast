package com.mauriciofigueroa.utils;

import java.awt.geom.Point2D;

public class GeometryUtils {

    public static boolean doubleEquals(double num1, double num2) {
        return Math.abs(num1 - num2) < 0.0001;
    }

    //Formula de Heron
    public static Double triangleArea(Point2D position1, Point2D position2, Point2D position3) {
        Double distancePoint1Point2 = position1.distance(position2);
        Double distancePoint2Point3 = position2.distance(position3);
        Double distancePoint3Point1 = position3.distance(position1);

        Double perimeter = distancePoint1Point2 + distancePoint2Point3 + distancePoint3Point1;
        Double semiPerimeter = perimeter / 2;

        Double area = Math.sqrt(semiPerimeter * (semiPerimeter - distancePoint1Point2) * (semiPerimeter - distancePoint2Point3) * (semiPerimeter - distancePoint3Point1));

        return area.isNaN() ? 0 : area;
    }

    public static Double trianglePerimeter(Point2D position1, Point2D position2, Point2D position3) {

        Double distancePoint1Point2 = position1.distance(position2);
        Double distancePoint2Point3 = position2.distance(position3);
        Double distancePoint3Point1 = position3.distance(position1);

        return distancePoint1Point2 + distancePoint2Point3 + distancePoint3Point1;
    }
}
