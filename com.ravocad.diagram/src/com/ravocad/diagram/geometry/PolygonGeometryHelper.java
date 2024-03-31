package com.ravocad.diagram.geometry;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.swt.graphics.Path;
import org.eclipse.ui.PlatformUI;

public class PolygonGeometryHelper implements IGeometryHelper {

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
		path.close();
		return path;
	}
	
	public PointList getTemplatePoints(PointList points) {
		return points;
	}

}
