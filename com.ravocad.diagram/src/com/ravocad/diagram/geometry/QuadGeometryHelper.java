package com.ravocad.diagram.geometry;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.swt.graphics.Path;
import org.eclipse.ui.PlatformUI;

public class QuadGeometryHelper implements IGeometryHelper {

	@Override
	public Path createPath(PointList points) {
		Path path = new Path(PlatformUI.getWorkbench().getDisplay());
		if(points != null && points.size() >= 3) {
			Point start = points.getFirstPoint();
			
			path.moveTo((float)start.x, (float)start.y);		
			for (int i = 1; i <= points.size() - 2; i = i + 2) {			
				Point cntrl = new Point(points.getPoint(i));
				Point end = new Point(points.getPoint(i + 1));	
				path.quadTo((float)cntrl.x, (float)cntrl.y, (float)end.x, (float)end.y);
			}	
			return path;
		} else {
			for(int i=0; i < points.size(); i++) {
				Point point = points.getPoint(i);
				if(i == 0) {
					path.moveTo((float)point.preciseX(), (float)point.preciseY());
				} else {
					path.lineTo((float)point.preciseX(), (float)point.preciseY());
				}
			}
		}
		return path;
	}

}
