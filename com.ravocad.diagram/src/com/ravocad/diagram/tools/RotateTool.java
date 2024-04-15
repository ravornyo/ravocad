package com.ravocad.diagram.tools;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.swt.widgets.Display;

import com.ravocad.diagram.dialogs.ToolInputDialog;
import com.ravocad.diagram.i10n.Messages;
import com.ravocad.diagram.requests.OrientationRequest;
import com.ravocad.diagram.requests.RotateRequest;

public class RotateTool extends OrientationTool {
	
	@Override
	public OrientationRequest createToolRequest() {
		return new RotateRequest();
	}
	
	@Override
	protected void updateTargetRequest() {
		super.updateTargetRequest();
		RotateRequest request = (RotateRequest)getTargetRequest();
		if (isInState(STATE_DRAG_IN_PROGRESS)) {
			Point origin = request.getOrigin();
			Point loc = getCurrentInput().isModKeyDown(MODIFIER_OTHO_SNAPPING)? 
					getOthoPoint(getLocation()): getLocation();
			
			double angle = Math.atan2((loc.y - origin.y ), (loc.x - origin.x));
			request.setAngle(angle);
			request.setOrigin(origin);
		} else {
			request.setAngle(0.0);
		}
	}

	@Override
	protected String getCommandName() {
		return "Rotate";
	}
	
	@Override
	protected IFigure createTargetFeedback() {
		return new RotationToolFeedback();
	}
	
	private double getAngle() {
		RotateRequest request = (RotateRequest)getTargetRequest();
		double angle = Math.toDegrees(request.getAngle());
		String initialValue = String.format("%.2f", angle);
		ToolInputDialog dialog = new ToolInputDialog(
				Display.getCurrent().getActiveShell(),
				Messages.getString("RotationDialogMessage"),
				initialValue,
				new AngleValidator()
		);
		if (dialog.open() == Dialog.CANCEL 
				|| dialog.getResult() == null
				|| dialog.getResult().length <= 0) {
			return angle;
		} else {
			return Double.parseDouble(dialog.getResult()[0].toString());
		}
	}
	
	protected void showToolFeedback() {
		super.showToolFeedback();
		if (isInState(STATE_DRAG_IN_PROGRESS)) {
			RotationToolFeedback targetFeedback = (RotationToolFeedback)getTargetToolFeedback();
			RotateRequest request = (RotateRequest)getTargetRequest();
			targetFeedback.setAngle(Math.toDegrees(request.getAngle()));		
		}
	}
	
	protected void doFinish(int button) {
		double angle = getAngle();
		RotateRequest request = (RotateRequest)getTargetRequest();
		request.setAngle(Math.toRadians(angle));
		
		setCurrentCommand(getCommand());
		
		super.doFinish(button);
	}
	
	public static class RotationToolFeedback extends ToolFeedback {		
		private double angle;
		@Override
		protected void outlineShape(Graphics g) {
			g.pushState();
			g.setForegroundColor(ColorConstants.black);
			g.setLineStyle(Graphics.LINE_DOT);
			
			PointList pts = getPoints();
			if(pts.size() > 1) {
				double angle = getAngle();
				String text = String.format("%.2f°", angle);
				Dimension textDimen = FigureUtilities.getTextExtents(text, g.getFont());
				
				Point p1 = pts.getFirstPoint();
				Point p3 = pts.getLastPoint();
				double d = p1.getDistance(p3);
				Point p2 = new PrecisionPoint(p1.x + d, p1.y);
				
				double arcRadius = Math.max(textDimen.width + (10), d * 0.5);
				Rectangle aBounds = new PrecisionRectangle(p1.x - arcRadius, p1.y - arcRadius, (2*arcRadius), (2*arcRadius));
				
				g.drawArc(aBounds, 0, -(int)angle);
				g.drawLine(p1, p3);
				g.drawLine(p1, p2);
				
				Point t = new Point(5, 5);
				if(angle < 0) {
					t = new Point(5, -textDimen.height - 5);
				}
				g.drawText(text, p1.getTranslated(t));
			}		
			g.popState();
		}
		@Override
		protected Rectangle calculateBounds() {
			PointList pts = new PointList(getPoints().toIntArray());
			if(pts.size() > 1) {
				Point p1 = pts.getFirstPoint();
				Point p3 = pts.getLastPoint();
				double d = p1.getDistance(p3);
				Point p2 = new PrecisionPoint(p1.x + d, p1.y);
				pts.addPoint(p2);
				
				double arcRadius = Math.min(50, d/2.0);
				Rectangle aBounds = new PrecisionRectangle(p1.x - arcRadius, p1.y - arcRadius, (2*arcRadius), (2*arcRadius));
				
				return pts.getBounds().union(aBounds);
			}
			return pts.getBounds();
		}
		public double getAngle() {
			return angle;
		}
		public void setAngle(double angle) {
			this.angle = angle;
		}
	}
	
	public static class AngleValidator implements IInputValidator{
		AngleValidator(){
		}
		@Override
		public String isValid(String newText) {
			try {
				Double.parseDouble(newText);
				return null;
			} catch (NumberFormatException e) {
				return Messages.getString("ValidationInvalidAngle");
			}
		}
	}

}
