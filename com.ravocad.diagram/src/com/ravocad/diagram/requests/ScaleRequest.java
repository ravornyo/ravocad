package com.ravocad.diagram.requests;

import com.ravocad.diagram.constants.RequestConstants;

public class ScaleRequest extends OrientationRequest {

	private double scale;

	public ScaleRequest() {
		this(0);
	}
	
	public ScaleRequest(double angle) {
		super(RequestConstants.REQ_SCALE);
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

}
