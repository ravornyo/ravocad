package com.ravocad.diagram.commands;

import com.ravocad.diagram.i10n.Messages;
import com.ravocad.notation.Diagram;
import com.ravocad.notation.View;

public class CreateViewCommand extends org.eclipse.gef.commands.Command {

	private View child;
	private Diagram parent;

	public CreateViewCommand() {
		super(Messages.getString("CreateViewCommand_Label"));
	}

	@Override
	public boolean canExecute() {
		return child != null && parent != null;
	}

	@Override
	public void execute() {
		redo();
	}

	@Override
	public void redo() {
		parent.getView().add(child);
	}

	public void setChild(View subpart) {
		child = subpart;
	}

	public void setParent(Diagram newParent) {
		parent = newParent;
	}

	@Override
	public void undo() {
		parent.getView().remove(child);
	}

}
