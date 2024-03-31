package com.ravocad.diagram.tools;

import com.ravocad.diagram.constants.VisualConstants;
import com.ravocad.diagram.geometry.IGeometryHelper;
import com.ravocad.diagram.geometry.PolylineGeometryHelper;

public class PolylineTool extends SketchTool {

	public PolylineTool() {
		super(VisualConstants.POLYLINE);
	}
	
	public PolylineTool(String hint) {
		super(hint);
	}
	
	@Override
	protected IGeometryHelper createGeometry() {
		return new PolylineGeometryHelper();
	}

	@Override
	protected boolean isComplete() {
		return false;
	}
	
}
