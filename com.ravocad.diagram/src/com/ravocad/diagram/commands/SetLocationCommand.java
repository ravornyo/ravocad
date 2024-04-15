package com.ravocad.diagram.commands;

import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.geometry.Point;

import com.ravocad.diagram.i10n.Messages;
import com.ravocad.notation.NotationPackage;
import com.ravocad.notation.Text;

public class SetLocationCommand extends org.eclipse.gef.commands.Command {
	
	private Text text;
	private Point moveDelta;
	private Point oldLocation;

	public SetLocationCommand(Text text, Point moveDelta) {
        super(Messages.getString("UpdateTextCommand_Label"));
		Assert.isNotNull(text, "View adapter cannot be null");
		Assert.isNotNull(moveDelta, "Move delta cannot be null");
		this.text = text;	
		this.moveDelta = moveDelta;
	}
	
	@Override
	public void execute() {
		primExecute();
	}
	
	@Override
	public void redo() {
		primExecute();
	}
	
	@Override
	public void undo() {
		text.eSet(NotationPackage.eINSTANCE.getText_Location(), oldLocation);
	}
	
	private void primExecute() {
		oldLocation = text.getLocation();
		Point newLocation = oldLocation.getCopy().getTranslated(moveDelta);
		text.eSet(NotationPackage.eINSTANCE.getText_Location(), newLocation);
	}

}
