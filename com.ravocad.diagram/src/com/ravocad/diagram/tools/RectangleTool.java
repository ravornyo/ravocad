package com.ravocad.diagram.tools;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;

import com.ravocad.diagram.constants.VisualConstants;
import com.ravocad.diagram.geometry.IGeometryHelper;
import com.ravocad.diagram.geometry.PolygonGeometryHelper;

public class RectangleTool extends SketchTool {

	public RectangleTool() {
		super(VisualConstants.RECTANGLE);
	}
	
	protected PointList getHandles(PointList points) {
		return ((PolygonGeometryHelper)createGeometry()).getTemplatePoints(points);
	}
	
	@Override
	protected IGeometryHelper createGeometry() {
		return new PolygonGeometryHelper() {
			public PointList getTemplatePoints(PointList points) {
				if(points != null && points.size() > 1) {
					return calcRectanglePoints(points);
				}
				return super.getTemplatePoints(points);
			}
			private PointList calcRectanglePoints(PointList points) {
				PointList newPoints = new PointList();
				
				Point p1 = points.getPoint(0);
				Point p3 = points.getPoint(1);
				Point p2 = new Point(p3.x, p1.y);
				Point p4 = new Point(p1.x, p3.y);
				
				newPoints.addPoint(p1);
				newPoints.addPoint(new PrecisionPoint((p1.x + p2.x)/2.0, p1.y));
				newPoints.addPoint(p2);
				newPoints.addPoint(new PrecisionPoint(p2.x, (p2.y + p3.y)/2.0));
				newPoints.addPoint(p3);
				newPoints.addPoint(new PrecisionPoint((p3.x + p4.x)/2.0, p3.y));
				newPoints.addPoint(p4);
				newPoints.addPoint(new PrecisionPoint(p1.x, (p1.y + p4.y)/2.0));
				
				return newPoints;
			}
		};
	}
	
	@Override
	protected boolean isComplete() {
		return getWayPoints().size() >= 2;
	}
	
	protected Point getOthoPoint(Point location) {
		if(getWayPoints().size() > 0) {
			Point lastPoint = getWayPoints().getLastPoint();
			if(lastPoint == null) {
				lastPoint = getStartLocation();
			}
			Dimension delta = location.getDifference(lastPoint);
			int increment = Math.max(delta.width, delta.height);
			return new Point(lastPoint.x + increment, lastPoint.y + increment);
		}
		return location;		
	}

}
