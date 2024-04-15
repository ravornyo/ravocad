package com.ravocad.diagram.dialogs;

import java.util.Arrays;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.SelectionDialog;

public class ToolInputDialog extends SelectionDialog {

	private IInputValidator validator;
	private String initialValue;
	private Text text;
	/**
	 * Error message label widget.
	 */
	private Text errorMessageText;

	public ToolInputDialog(Shell parentShell, String dialogMessage, String initialValue, IInputValidator validator) {
		super(parentShell);
		setShellStyle(SWT.ON_TOP | SWT.TOOL | SWT.NO_FOCUS);
		//setTitle(dialogTitle);
		if (initialValue == null) {
			this.initialValue = "";
		} else {
			this.initialValue = initialValue;
		}
		setMessage(dialogMessage);
		this.validator = validator;
		
		//setShellStyle(SWT.NO_TRIM | SWT.ON_TOP | SWT.APPLICATION_MODAL | SWT.MAX | SWT.RESIZE);
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		//super.createButtonsForButtonBar(parent);
		text.setFocus();
	}
	
	@Override
	protected Control createButtonBar(Composite parent) {
		return null;
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

		text = new Text(composite, getInputTextStyle());
		text.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_FILL));
		
		if (initialValue != null) {
			text.setText(initialValue);
			text.selectAll();
		}
		text.addSelectionListener(new SelectionAdapter() {
			public void widgetDefaultSelected(SelectionEvent e) {
				okPressed();
			}
		});
		text.addModifyListener(e -> validateInput());
		
		errorMessageText = new Text(composite, SWT.READ_ONLY | SWT.WRAP);
		errorMessageText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_FILL));
		errorMessageText.setBackground(errorMessageText.getDisplay()
				.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		errorMessageText.setForeground(errorMessageText.getDisplay().getSystemColor(SWT.COLOR_RED));
		//setErrorMessage(errorMessage);

		applyDialogFont(composite);
		return composite;
	}
	
	public void setErrorMessage(String errorMessage) {
		if (errorMessageText != null && !errorMessageText.isDisposed()) {
			errorMessageText.setText(errorMessage == null ? " \n " : errorMessage); //$NON-NLS-1$
			// Disable the error message text control if there is no error, or
			// no error text (empty or whitespace only).  Hide it also to avoid
			// color change.
			// See https://bugs.eclipse.org/bugs/show_bug.cgi?id=130281
			boolean hasError = errorMessage != null && (StringConverter.removeWhiteSpaces(errorMessage)).length() > 0;
			errorMessageText.setEnabled(hasError);
			errorMessageText.setVisible(hasError);
			errorMessageText.getParent().update();
			// Access the ok button by id, in case clients have overridden button creation.
			// See https://bugs.eclipse.org/bugs/show_bug.cgi?id=113643
			Control button = getButton(IDialogConstants.OK_ID);
			if (button != null) {
				button.setEnabled(errorMessage == null);
			}
		}
	}
	
	@Override
	protected void okPressed() {
		String value = text.getText();
		setResult(Arrays.asList(value));
		super.okPressed();
	}

	protected void validateInput() {
		String errorMessage = null;
		if (validator != null) {
			errorMessage = validator.isValid(text.getText());
		}
		setErrorMessage(errorMessage);
	}

	
	protected int getInputTextStyle() {
		return SWT.SINGLE | SWT.BORDER;
	}

}
