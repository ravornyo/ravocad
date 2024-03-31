package com.ravocad.diagram.edit.parts;

import org.eclipse.gef.EditPolicy;

import com.ravocad.diagram.edit.policies.EllipseResizableEditPolicy;

public class EllipseEditPart extends PathEditPart {
	
	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new EllipseResizableEditPolicy());
	}

}
