package com.ravocad.diagram.commands;

import org.eclipse.gef.commands.Command;

import com.ravocad.diagram.i10n.Messages;
import com.ravocad.notation.Diagram;
import com.ravocad.notation.View;

public class DeleteViewCommand extends Command {

	private View child;
	private Diagram parent;
	private int index = -1;

	public DeleteViewCommand() {
		super(Messages.getString("DeleteViewCommand_Label"));
	}

	public void execute() {
		primExecute();
	}

	protected void primExecute() {
		index = parent.getView().indexOf(child);
		parent.getView().remove(child);
	}

	public void redo() {
		primExecute();
	}

	public void setChild(View c) {
		child = c;
	}

	public void setParent(Diagram p) {
		parent = p;
	}

	public void undo() {
		parent.getView().add(index, child);
	}

}
