package com.ravocad.diagram.edit.policies;

import org.eclipse.draw2d.Label;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;

import com.ravocad.diagram.commands.SetTextCommand;
import com.ravocad.notation.Text;

public class TextDirectEditPolicy extends DirectEditPolicy {

	@Override
	protected Command getDirectEditCommand(DirectEditRequest request) {
		Text view = (Text)getHost().getModel();
		String value = (String) request.getCellEditor().getValue();
		SetTextCommand command = new SetTextCommand(view, value);
	    return command;
	}

	@Override
	protected void showCurrentEditValue(DirectEditRequest request) {
		String value = (String)request.getCellEditor().getValue();
	    ((Label)getHostFigure()).setText(value);
	}

}
