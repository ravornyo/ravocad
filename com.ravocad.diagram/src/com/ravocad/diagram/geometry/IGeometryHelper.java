package com.ravocad.diagram.geometry;

import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.swt.graphics.Path;

public interface IGeometryHelper {
	Path createPath(PointList controlPoints);
}
