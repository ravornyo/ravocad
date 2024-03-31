package com.ravocad.diagram.tools;

import com.ravocad.diagram.constants.VisualConstants;
import com.ravocad.diagram.geometry.IGeometryHelper;
import com.ravocad.diagram.geometry.QuadGeometryHelper;

public class QuadTool extends PolylineTool {

	public QuadTool() {
		super(VisualConstants.QUAD_CURVE);
	}
	
	@Override
	protected IGeometryHelper createGeometry() {
		return new QuadGeometryHelper();
	}
	
	@Override
	protected boolean isComplete() {
		return false;
	}

}
