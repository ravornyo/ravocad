package com.ravocad.diagram.tools;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

import com.ravocad.diagram.constants.VisualConstants;
import com.ravocad.diagram.geometry.EllipseGeometryHelper;
import com.ravocad.diagram.geometry.IGeometryHelper;

public class EllipseTool extends SketchTool {
	
	public EllipseTool() {
		super(VisualConstants.ELLIPSE);
	}
	
	@Override
	protected IGeometryHelper createGeometry() {
		return new EllipseGeometryHelper();
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
