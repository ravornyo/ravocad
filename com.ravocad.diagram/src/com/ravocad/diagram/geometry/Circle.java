package com.ravocad.diagram.geometry;

import org.eclipse.draw2d.geometry.PrecisionPoint;

public class Circle {
    private final PrecisionPoint center;
    private final double radius;
    
    public Circle(PrecisionPoint center, double radius){
      this.center = center; 
      this.radius = radius;
    }
	public PrecisionPoint getCenter() {
		return center;
	}
	public double getRadius() {
		return radius;
	}
  }