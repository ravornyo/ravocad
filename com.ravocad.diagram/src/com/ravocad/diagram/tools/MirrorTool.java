package com.ravocad.diagram.tools;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;

import com.ravocad.diagram.dialogs.ToolConfirmDialog;
import com.ravocad.diagram.i10n.Messages;
import com.ravocad.diagram.requests.MirrorRequest;
import com.ravocad.diagram.requests.OrientationRequest;

public class MirrorTool extends OrientationTool {

	@Override
	public OrientationRequest createToolRequest() {
		return new MirrorRequest();
	}
	
	@Override
	protected void updateTargetRequest() {
		MirrorRequest request = (MirrorRequest)getTargetRequest();
		if (isInState(STATE_DRAG_IN_PROGRESS)) {
			Point p1 = request.getOrigin();
			Point p2 = getCurrentInput().isModKeyDown(MODIFIER_OTHO_SNAPPING)? 
					getOthoPoint(getLocation()): getLocation();
			
			double[] params = getLineEquation(p1, p2);
			request.setGradient(params[0]);
			request.setIntercept(params[1]);
		} 
	}
	
	protected void doFinish(int button) {
		boolean eraseSource = confirmEraseSource();
		MirrorRequest request = (MirrorRequest)getTargetRequest();
		request.setEraseSource(eraseSource);
		
		setCurrentCommand(getCommand());
		
		super.doFinish(button);
	}

	private boolean confirmEraseSource() {
		ToolConfirmDialog dialog = new ToolConfirmDialog(
				Display.getCurrent().getActiveShell(),
				Messages.getString("MirrorDialogMessage")
		);
		if (dialog.open() == Dialog.CANCEL) {
			return false;
		} else {
			return true;
		}
	}

	private double[] getLineEquation(Point p1, Point p2) {
		double x1 = (double)p1.x; 
		double y1 = (double)p1.y; 
		double x2 = (double)p2.x;
        double y2 = (double)p2.y;
        
        double gradient;
        double intercept;
          
        if(x2 == x1) {
        	gradient = Double.POSITIVE_INFINITY;
        	intercept = x1;
        } else {
        	gradient = (y2 - y1) / (x2 - x1);
        	intercept = ((x2 * y1) - (x1*y2))/(x2 - x1);
        }

        return new double[] { gradient, intercept };
    }

	@Override
	protected String getCommandName() {
		return "Mirror";
	}

}
