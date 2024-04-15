package com.ravocad.diagram.tools;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.swt.widgets.Display;

import com.ravocad.diagram.dialogs.ToolInputDialog;
import com.ravocad.diagram.i10n.Messages;
import com.ravocad.diagram.requests.OrientationRequest;
import com.ravocad.diagram.requests.ScaleRequest;

public class ScaleTool extends OrientationTool {

	@Override
	public OrientationRequest createToolRequest() {
		return new ScaleRequest();
	}

	
	@Override
	protected void updateTargetRequest() {
		super.updateTargetRequest();
		ScaleRequest request = (ScaleRequest)getTargetRequest();
		if (isInState(STATE_DRAG_IN_PROGRESS)) {
			Point origin = request.getOrigin();
			Point loc = getCurrentInput().isModKeyDown(MODIFIER_OTHO_SNAPPING)? 
					getOthoPoint(getLocation()): getLocation();
			
			double h1 = Math.sqrt((origin.x * origin.y + origin.y * origin.y));
			double h2 = Math.sqrt((loc.x * loc.y + loc.y * loc.y));
			
			double scale = h2/h1;

			request.setScale(scale == 0? 1.0: scale);
		} else {
			request.setScale(1.0);
		}
	}  

	@Override
	protected String getCommandName() {
		return "Scale";
	}
	
	private double getScale() {
		ScaleRequest request = (ScaleRequest)getTargetRequest();
		double scale = request.getScale();
		String initialValue = String.format("%.2f", scale);
		ToolInputDialog dialog = new ToolInputDialog(
				Display.getCurrent().getActiveShell(),
				Messages.getString("ScaleDialogMessage"),
				initialValue,
				new ScaleValidator()
		);
		if (dialog.open() == Dialog.CANCEL 
				|| dialog.getResult() == null
				|| dialog.getResult().length <= 0) {
			return scale;
		} else {
			return Double.parseDouble(dialog.getResult()[0].toString());
		}
	}
	
	protected void performOrientation(int button) {
		double scale = getScale();
		ScaleRequest request = (ScaleRequest)getTargetRequest();
		request.setScale(scale);

		setCurrentCommand(getCommand());
		
		super.performOrientation(button);
	}
	
	public static class ScaleValidator implements IInputValidator{
		ScaleValidator(){
		}
		@Override
		public String isValid(String newText) {
			try {
				double scale = Double.parseDouble(newText);
				return scale != 0? null : Messages.getString("ValidationInvalidScale");
			} catch (NumberFormatException e) {
				return Messages.getString("ValidationInvalidScale");
			}
		}
	}

}
