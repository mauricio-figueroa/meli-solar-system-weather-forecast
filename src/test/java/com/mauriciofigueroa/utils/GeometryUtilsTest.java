package com.mauriciofigueroa.utils;


import org.junit.Assert;
import org.junit.Test;

import java.awt.geom.Point2D;

public class GeometryUtilsTest {


    @Test
    public void doubleEquals(){
        Assert.assertTrue(GeometryUtils.doubleEquals(0,0));
        Assert.assertTrue(GeometryUtils.doubleEquals(0,0.0));
        Assert.assertTrue(GeometryUtils.doubleEquals(0.00,0.0));
        Assert.assertTrue(GeometryUtils.doubleEquals(1,1.0));
        Assert.assertTrue(GeometryUtils.doubleEquals(1.0,1));
        Assert.assertTrue(GeometryUtils.doubleEquals(1.0000005,1));
    }

    @Test
    public void trianglePerimeter(){
        double trianglePerimeter = GeometryUtils.trianglePerimeter(new Point2D.Double(0, 0), new Point2D.Double(2, 0), new Point2D.Double(2, 2));
        Assert.assertTrue(GeometryUtils.doubleEquals(6.8284,trianglePerimeter));
    }


    @Test
    public void trianglePerimeter2(){
        double trianglePerimeter = GeometryUtils.trianglePerimeter(new Point2D.Double(0, 0), new Point2D.Double(2, 0), new Point2D.Double(4, 0));
        Assert.assertTrue(GeometryUtils.doubleEquals(6.8284,trianglePerimeter));
    }

    @Test
    public void triangleArea(){
        Double area = GeometryUtils.triangleArea(new Point2D.Double(0, 0), new Point2D.Double(2, 0), new Point2D.Double(2, 2));
        Assert.assertTrue(GeometryUtils.doubleEquals(2,area));
    }

}