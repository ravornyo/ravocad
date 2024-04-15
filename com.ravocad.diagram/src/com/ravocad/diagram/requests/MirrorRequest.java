package com.ravocad.diagram.requests;

import com.ravocad.diagram.constants.RequestConstants;

public class MirrorRequest extends OrientationRequest {

	private double gradient;
	private double intercept;
	private boolean eraseSource;
	
	public MirrorRequest() {
		super(RequestConstants.REQ_MIRROR);
	}

	public double getGradient() {
		return gradient;
	}

	public void setGradient(double gradient) {
		this.gradient = gradient;
	}

	public double getIntercept() {
		return intercept;
	}

	public void setIntercept(double intercept) {
		this.intercept = intercept;
	}

	public boolean isEraseSource() {
		return eraseSource;
	}

	public void setEraseSource(boolean eraseSource) {
		this.eraseSource = eraseSource;
	}

}
