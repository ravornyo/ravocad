package com.ravocad.diagram.commands;

import org.eclipse.core.runtime.Assert;

import com.ravocad.diagram.i10n.Messages;
import com.ravocad.notation.NotationPackage;
import com.ravocad.notation.Text;

public class SetTextCommand extends org.eclipse.gef.commands.Command {
	
	private Text text;
	private String value;
	private String oldValue;

	public SetTextCommand(Text text, String value) {
        super(Messages.getString("UpdateTextCommand_Label"));
		Assert.isNotNull(text, "View adapter cannot be null");
		this.text = text;	
		this.value = value;
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
		text.eSet(NotationPackage.eINSTANCE.getText_Content(), oldValue);
	}
	
	private void primExecute() {
		oldValue = text.getContent();
		text.eSet(NotationPackage.eINSTANCE.getText_Content(), value);
	}

}
