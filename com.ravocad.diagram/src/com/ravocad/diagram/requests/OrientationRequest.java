package com.ravocad.diagram.requests;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.requests.GroupRequest;

public class OrientationRequest extends GroupRequest {
	
	private Point origin;

	public OrientationRequest(Object type) {
		super(type);
	}

	public Point getOrigin() {
		return origin;
	}

	public void setOrigin(Point origin) {
		this.origin = origin;
	}

}
