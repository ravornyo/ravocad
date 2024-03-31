package com.ravocad.diagram.edit.policies;

import java.util.Iterator;

import org.eclipse.draw2d.XYLayout;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

import com.ravocad.diagram.commands.CloneViewCommand;
import com.ravocad.diagram.commands.CreateViewCommand;
import com.ravocad.notation.Diagram;
import com.ravocad.notation.View;

public class DiagramLayoutEditPolicy extends org.eclipse.gef.editpolicies.XYLayoutEditPolicy {

	public DiagramLayoutEditPolicy(XYLayout layout) {
		super();
		setXyLayout(layout);
	}

	/**
	 * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createChangeConstraintCommand(org.eclipse.gef.EditPart,
	 *      java.lang.Object)
	 */
	protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
		return null;
	}

	@Override
	protected EditPolicy createChildEditPolicy(EditPart child) {
		EditPolicy childEditPolicy = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
		return childEditPolicy != null? childEditPolicy : new ResizableEditPolicy();
	}

	@Override
	protected Command getCloneCommand(ChangeBoundsRequest request) {
		CloneViewCommand clone = new CloneViewCommand();

		clone.setParent((Diagram) getHost().getModel());

		Iterator<?> i = request.getEditParts().iterator();
		GraphicalEditPart currPart = null;

		while (i.hasNext()) {
			currPart = (GraphicalEditPart) i.next();
			clone.addView((View)currPart.getModel());
		}

		return clone;
	}

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		CreateViewCommand cmd = new CreateViewCommand();
		cmd.setParent((Diagram) getHost().getModel());
		View view = (View) request.getNewObject();
		cmd.setChild(view);

		return cmd;
	}

}
