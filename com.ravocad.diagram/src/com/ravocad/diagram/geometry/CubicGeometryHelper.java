package com.ravocad.diagram.geometry;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.swt.graphics.Path;
import org.eclipse.ui.PlatformUI;

public class CubicGeometryHelper implements IGeometryHelper {

	@Override
	public Path createPath(PointList points) {
		Path path = new Path(PlatformUI.getWorkbench().getDisplay());
		if(points != null && points.size() >= 4) {
			Point start = points.getFirstPoint();
			
			path.moveTo((float)start.x, (float)start.y);		
			for (int i = 1; i <= points.size() - 3; i = i + 3) {			
				Point cntrl1 = new Point(points.getPoint(i));
				Point cntrl2 = new Point(points.getPoint(i + 1));
				Point end = new Point(points.getPoint(i + 2));	
				path.cubicTo((float)cntrl1.x, (float)cntrl1.y, (float)cntrl2.x, 
						(float)cntrl2.y, (float)end.x, (float)end.y);
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
