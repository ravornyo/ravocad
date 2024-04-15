package com.ravocad.diagram.dialogs;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.SelectionDialog;

import com.ravocad.diagram.i10n.Messages;

public class ToolConfirmDialog extends SelectionDialog {

	public ToolConfirmDialog(Shell parentShell, String dialogMessage) {
		super(parentShell);
		setShellStyle(SWT.ON_TOP | SWT.TOOL | SWT.NO_FOCUS);
		setMessage(dialogMessage);
	}
	
	@Override
	protected void configureShell(Shell shell) {
	  super.configureShell(shell);
	  shell.addListener(SWT.Deactivate, event -> cancelPressed());
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		// create composite
		Composite composite = (Composite) super.createDialogArea(parent);

		createMessageArea(composite);
		applyDialogFont(composite);
		return composite;
	}
	
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, Messages.getString("DialogYesButton"), true);
		createButton(parent, IDialogConstants.CANCEL_ID, Messages.getString("DialogNoButton"), false);
	}
	
	@Override
	public boolean isHelpAvailable() {
		return false;
	}
	
}
