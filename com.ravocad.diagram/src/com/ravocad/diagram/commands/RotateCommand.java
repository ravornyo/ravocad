package com.ravocad.diagram.commands;

import org.eclipse.gef.commands.Command;

import com.ravocad.diagram.i10n.Messages;
import com.ravocad.notation.View;

public class RotateCommand extends Command {

	View child = null;
	double angle;
	
	int direction;
	
	public RotateCommand(double angle){
		super(Messages.getString("RotateClockwiseCommand_LabelText"));
		this.angle = angle;
	}
	
	public void setChild(View child){
		this.child = child;
	}
	
	public void execute(){

	}
	
	public void undo(){

	}
	
	public void redo(){
		execute();
	}

}
