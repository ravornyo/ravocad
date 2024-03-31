package com.ravocad.diagram.tools;

public class LineTool extends PolylineTool {

	@Override
	protected boolean isComplete() {
		return getWayPoints().size() >= 2;
	}
	
}
