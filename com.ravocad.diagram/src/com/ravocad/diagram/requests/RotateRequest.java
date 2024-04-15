package com.ravocad.diagram.requests;

import com.ravocad.diagram.constants.RequestConstants;

public class RotateRequest extends OrientationRequest {

	private double angle;

	public RotateRequest() {
		this(0);
	}
	
	public RotateRequest(double angle) {
		super(RequestConstants.REQ_ROTATE);
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

}
