package com.ravocad.diagram.edit.parts;

import org.eclipse.gef.EditPolicy;

import com.ravocad.diagram.edit.policies.RectangleResizableEditPolicy;

public class RectangleEditPart extends PathEditPart {
	
	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new RectangleResizableEditPolicy());
	}

}
