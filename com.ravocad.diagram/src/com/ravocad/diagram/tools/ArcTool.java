package com.ravocad.diagram.tools;

import com.ravocad.diagram.constants.VisualConstants;
import com.ravocad.diagram.geometry.ArcGeometryHelper;
import com.ravocad.diagram.geometry.IGeometryHelper;

public class ArcTool extends PolylineTool {

	public ArcTool() {
		super(VisualConstants.ARC);
	}
	
	@Override
	protected IGeometryHelper createGeometry() {
		return new ArcGeometryHelper();
	}
	
	@Override
	protected boolean isComplete() {
		return getWayPoints().size() >= 3;
	}

}
