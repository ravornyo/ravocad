package com.ravocad.diagram.geometry;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.swt.graphics.Path;
import org.eclipse.ui.PlatformUI;

public class ArcGeometryHelper implements IGeometryHelper {
	
	static final double TOL = 0.0000001;

	static int SEGMENTS = 98;

	@Override
	public Path createPath(PointList controlPoints) {
		PointList points = getTemplatePoints(controlPoints);		
		Path path = new Path(PlatformUI.getWorkbench().getDisplay());
		for(int i=0; i < points.size(); i++) {
			Point point = points.getPoint(i);
			if(i == 0) {
				path.moveTo((float)point.preciseX(), (float)point.preciseY());
			} else {
				path.lineTo((float)point.preciseX(), (float)point.preciseY());
			}
		}
		return path;
	}
	
	protected PointList getTemplatePoints(PointList controlPoints) {
		if(controlPoints != null && controlPoints.size() >= 3) {
			return calcArcPoints(controlPoints);
		}
		return controlPoints;
	}
	
	private PointList calcArcPoints(PointList points) {
		return calcArcPoints(points, circleFromPoints(points));
	}
	
	public PointList calcArcPoints(PointList points, Circle circle) {
		if(circle == null) {
			return points;
		}
		
		Point p1 = points.getPoint(0);
		Point p2 = points.getPoint(1);
		Point p3 = points.getPoint(2);
		if(p1.x > p3.x) {
			Point temp = p1.getCopy();
			p1 = p3.getCopy();
			p3 = temp;
		}
		
		PrecisionPointList newPoints = new PrecisionPointList();

		PrecisionPoint center = circle.getCenter();   
		double xc = center.preciseX();
		double yc = center.preciseY();
		double r = circle.getRadius();

		double twoPI = Math.PI * 2;
		double a1 = Math.atan2((yc - p1.y ), (p1.x - xc));
		if(a1 < 0) {
			a1 = twoPI + a1;
		}
 		double a2 = Math.atan2((yc - p2.y ), (p2.x - xc));
 		if(a2 < 0) {
			a2 = twoPI + a2;
		}
		double a3 = Math.atan2((yc - p3.y), (p3.x - xc));
		if(a3 < 0) {
			a3 = twoPI + a3;
		}
		double startAngle = Math.min(a1, a3);
		double midAngle = a2;
		double endAngle = Math.max(a1, a3);

		if(startAngle > endAngle) {
			double temp = startAngle;
			startAngle = endAngle;
			endAngle = temp;
		}
		double length = endAngle - startAngle;//(endAngle > startAngle) ? (endAngle - startAngle) : (startAngle - endAngle);
		boolean cw = anglesInClockwiseSequence(startAngle, midAngle, endAngle);	
		if(cw == false) {
			length = twoPI - length;
		}
		double increment = (length/SEGMENTS);
		increment = cw ? increment : -increment;	

		newPoints.addPoint((startAngle == a1)? p1: p3);
		double angle = startAngle;
		for(int i=0; i < SEGMENTS; i++) {
			angle += increment; 
			//angle = angle % (Math.PI * 2); 
			
			double x = xc + (Math.cos(angle) * r); 
	        double y = yc - (Math.sin(angle) * r);
	        PrecisionPoint p = new PrecisionPoint(x, y);
	        newPoints.addPoint(p); 	
		}
		newPoints.addPoint((endAngle == a3)? p3: p1);
		
		return newPoints;
	}
	
	private Circle circleFromPoints(PointList points) {
		if(points.size() < 3) {
			return null;
		}
		final Point p1 = points.getPoint(0);
		final Point p2 = points.getPoint(1);
		final Point p3 = points.getPoint(2);
		
		final double offset = Math.pow(p2.x, 2) + Math.pow(p2.y, 2);
		final double bc = (Math.pow(p1.x, 2) + Math.pow(p1.y, 2) - offset) / 2.0;
		final double cd = (offset - Math.pow(p3.x, 2) - Math.pow(p3.y, 2)) / 2.0;
		final double det = (p1.x - p2.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p2.y);

		if (Math.abs(det) < TOL) {
			return null; //throw new IllegalArgumentException("Yeah, lazy.");
		}

		final double idet = 1 / det;

		final double centerx = (bc * (p2.y - p3.y) - cd * (p1.y - p2.y)) * idet;
		final double centery = (cd * (p1.x - p2.x) - bc * (p2.x - p3.x)) * idet;
		final double radius = Math.sqrt(Math.pow(p2.x - centerx, 2) + Math.pow(p2.y - centery, 2));

		return new Circle(new PrecisionPoint(centerx, centery), radius);
	}
	
	private boolean anglesInClockwiseSequence(double x, double y, double z){
        return angularDiffSigned(x, y) + angularDiffSigned(y, z) < 2*Math.PI;
    }
	
	private double angularDiffSigned(double theta1, double theta2) {
        double dif = theta2 - theta1;
        while (dif >= 2 * Math.PI)
            dif -= 2 * Math.PI;
        while (dif <= 0)
            dif += 2 * Math.PI;
        return dif;
    }

}
