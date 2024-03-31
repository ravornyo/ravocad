package com.ravocad.diagram.tools;

import com.ravocad.diagram.constants.VisualConstants;
import com.ravocad.diagram.geometry.CubicGeometryHelper;
import com.ravocad.diagram.geometry.IGeometryHelper;

public class CubicTool extends PolylineTool {

	public CubicTool() {
		super(VisualConstants.CUBIC_CURVE);
	}
	
	@Override
	protected IGeometryHelper createGeometry() {
		return new CubicGeometryHelper();
	}
	
	@Override
	protected boolean isComplete() {
		return false;
	}

}
