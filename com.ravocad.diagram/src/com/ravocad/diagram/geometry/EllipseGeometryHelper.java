package com.ravocad.diagram.geometry;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Path;
import org.eclipse.ui.PlatformUI;

public class EllipseGeometryHelper implements IGeometryHelper {

	@Override
	public Path createPath(PointList controlPoints) {
		Rectangle bounds = getBounds(controlPoints);
		float width = (float)bounds.width;
		float height = (float)bounds.height;
		float x = bounds.x;
		float y = bounds.y;
		
		Path path = new Path(PlatformUI.getWorkbench().getDisplay());
		path.addArc(x, y, width, height, 0, 360);
		return path;
	}

	protected Rectangle getBounds(PointList controlPoints) {
		if(controlPoints != null && controlPoints.size() > 1) {
			if(controlPoints.size() > 2) {
				return controlPoints.getBounds();
			} else {
				return getTemplatePoints(controlPoints).getBounds();
			}
		}
		return controlPoints.getBounds();
	}
	
	public PointList getTemplatePoints(PointList points) {
		Point center = points.getPoint(0);
		Point target = points.getPoint(1);
		
		PointList newPoints = new PointList();

		Dimension diff = target.getDifference(center);

		Point p1 = new Point(center.x, center.y - diff.height);
		Point p2 = new Point(center.x + diff.width, center.y);
		Point p3 = new Point(center.x, center.y + diff.height);
		Point p4 = new Point(center.x - diff.width, center.y);
		
		newPoints.addPoint(center);
		newPoints.addPoint(p1);
		newPoints.addPoint(p2);
		newPoints.addPoint(p3);
		newPoints.addPoint(p4);
		
		return newPoints;
	}

}
