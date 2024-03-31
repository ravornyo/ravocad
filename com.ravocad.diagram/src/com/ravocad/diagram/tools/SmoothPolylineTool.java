package com.ravocad.diagram.tools;

import com.ravocad.diagram.constants.VisualConstants;
import com.ravocad.diagram.geometry.IGeometryHelper;
import com.ravocad.diagram.geometry.SplineGeometryHelper;

public class SmoothPolylineTool extends PolylineTool {
	
	public SmoothPolylineTool() {
		super(VisualConstants.SMOOTH_POLYLINE);
	}
	
	@Override
	protected IGeometryHelper createGeometry() {
		return new SplineGeometryHelper();
	}
}
