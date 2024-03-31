package com.ravocad.diagram.geometry;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.swt.graphics.Path;
import org.eclipse.ui.PlatformUI;

public class SplineGeometryHelper implements IGeometryHelper {

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
	
	private PointList getTemplatePoints(PointList points) {
		if(points != null && points.size() >= 3) {
			return calcSplinePoints(points);
		}
		return points;
	}
	
	private PointList calcSplinePoints(PointList points) {
		if(points.size() < 3) {
			return points;
		}
		return SplineUtilities.calcSmoothPolyline(points, 30, SEGMENTS);
	}

}
