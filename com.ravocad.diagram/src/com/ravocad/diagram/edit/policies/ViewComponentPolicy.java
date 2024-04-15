package com.ravocad.diagram.edit.policies;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.GroupRequest;

import com.ravocad.diagram.commands.DeleteViewCommand;
import com.ravocad.diagram.commands.RotateCommand;
import com.ravocad.diagram.constants.RequestConstants;
import com.ravocad.diagram.requests.RotateRequest;
import com.ravocad.notation.Diagram;
import com.ravocad.notation.View;

public class PathComponentPolicy extends org.eclipse.gef.editpolicies.ComponentEditPolicy {

	@Override
	protected Command createDeleteCommand(GroupRequest request) {
		Object parent = getHost().getParent().getModel();
		DeleteViewCommand deleteCmd = new DeleteViewCommand();
		deleteCmd.setParent((Diagram) parent);
		deleteCmd.setChild((View) getHost().getModel());
		return deleteCmd;
	}
	
	@Override
	public Command getCommand(Request request) {
		if (RequestConstants.REQ_ROTATE.equals(request.getType())) {
			RotateRequest rotateRequest = (RotateRequest)request;
			RotateCommand command = new RotateCommand(rotateRequest.getAngle());
			command.setChild((View)getHost().getModel());
			return command;
		} else if (REQ_CREATE.equals(request.getType())) {
			return getHost().getParent().getCommand(request);
		} 
		return super.getCommand(request);
	}
	
	@Override
	public boolean understandsRequest(Request request) {
		if (RequestConstants.REQ_ROTATE.equals(request.getType())) {
			return true;
		}
		return super.understandsRequest(request);
	}

}
