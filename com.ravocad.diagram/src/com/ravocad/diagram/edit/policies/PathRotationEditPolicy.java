package com.ravocad.diagram.edit.policies;

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.PathData;
import org.eclipse.ui.PlatformUI;

import com.ravocad.diagram.commands.RotateCommand;
import com.ravocad.diagram.commands.UpdatePathCommand;
import com.ravocad.diagram.constants.RequestConstants;
import com.ravocad.diagram.edit.figures.PathShape;
import com.ravocad.diagram.geometry.GeometryProvider;
import com.ravocad.diagram.geometry.PrecisionPointList;
import com.ravocad.diagram.i10n.Messages;
import com.ravocad.diagram.requests.RotateRequest;
import com.ravocad.notation.View;

public class PathRotationEditPolicy extends GraphicalEditPolicy {

	private IFigure feedback;
	
	@Override
	public Command getCommand(Request request) {
		if (RequestConstants.REQ_ROTATE.equals(request.getType())) {
			return getRotationCommand((RotateRequest)request);
		} 
		return super.getCommand(request);
	}
	
	protected Command getRotationCommand(RotateRequest request) {
		View view = (View)getHost().getModel();
		CompoundCommand cc = new CompoundCommand(Messages.getString("RotationCommand_Label "));	
		cc.add(new RotateCommand(view, request.getOrigin(), request.getAngle()));	
		cc.add(new UpdatePathCommand(view));

		return cc;
	}
	
	@Override
	public void showTargetFeedback(Request request) {
		if (RequestConstants.REQ_ROTATE.equals(request.getType())) {
			TargetFeedback feedback = (TargetFeedback)getTargetFeedbackFigure();
			Path path = getFeedbackPath(request);
	        if(path != null) {
				float[] arr = new float[4];
				path.getBounds(arr);
				
				feedback.setPathData(path.getPathData());
				path.dispose(); 
				feedback.setBounds(new PrecisionRectangle(Math.floor(arr[0]), Math.floor(arr[1]), Math.ceil(arr[2]), Math.ceil(arr[3])).expand(2, 2));
				feedback.validate();	
	        }
		}
		super.showTargetFeedback(request);
	}
	
	@Override
	public void eraseTargetFeedback(Request request) {
		if (RequestConstants.REQ_ROTATE.equals(request.getType())) {
			if (feedback != null) {
				removeFeedback(feedback);
			}
			feedback = null;
		}
		super.eraseTargetFeedback(request);
	}

	protected IFigure getTargetFeedbackFigure() {
		if (feedback == null)
			feedback = createTargetFeedbackFigure();
		return feedback;
	}
	
	protected Path getFeedbackPath(Request request) {
		PathShape shape = getPathShape();
		PrecisionPointList feedbackPoints = new PrecisionPointList(shape.getHandlePoints());
        shape.translateToAbsolute(feedbackPoints);

        if (RequestConstants.REQ_ROTATE.equals(request.getType())) {
        	enforceConstraint((RotateRequest)request, feedbackPoints);
        }

        getTargetFeedbackFigure().translateToRelative(feedbackPoints);
        
        View view = (View)getHost().getModel();
        Path path = GeometryProvider.getInstance().createPath(view.getHint(), feedbackPoints);
		return path;
	}
	
	protected void enforceConstraint(RotateRequest request, PointList points) {
		Point rotationPoint = request.getOrigin();
		double angle = request.getAngle();
		
		for(int i=0; i < points.size(); i++) {
			Point vertex = points.getPoint(i);
			
			double translatedToOriginX = vertex.x - rotationPoint.x;
		    double translatedToOriginY = vertex.y - rotationPoint.y;

		    double rotatedX = translatedToOriginX * Math.cos(angle) - translatedToOriginY * Math.sin(angle);
		    double rotatedY = translatedToOriginX * Math.sin(angle) + translatedToOriginY * Math.cos(angle);

		    double reverseTranslatedX = rotatedX + rotationPoint.x;
		    double reverseTranslatedY = rotatedY + rotationPoint.y;
		    
			points.setPoint(new PrecisionPoint(reverseTranslatedX, reverseTranslatedY), i);
        }
	}

	protected PathShape getPathShape() {
		return (PathShape)getHostFigure();
	}

	@Override
	public boolean understandsRequest(Request request) {
		if (RequestConstants.REQ_ROTATE.equals(request.getType())) {
			return true;
		}
		return super.understandsRequest(request);
	}
	
	protected Rectangle getInitialFeedbackBounds() {
		if (((GraphicalEditPart) getHost()).getFigure() instanceof HandleBounds)
			return ((HandleBounds) ((GraphicalEditPart) getHost()).getFigure()).getHandleBounds();
		return ((GraphicalEditPart) getHost()).getFigure().getBounds();
	}
	
	protected TargetFeedback createTargetFeedbackFigure() {
		TargetFeedback feedback = new TargetFeedback(getPathShape().getPathData());
		FigureUtilities.makeGhostShape(feedback);
		feedback.setLineStyle(Graphics.LINE_DOT);
		feedback.setBounds(getInitialFeedbackBounds());
		feedback.validate();
		addFeedback(feedback);
		return feedback;
	}

	public class TargetFeedback extends Shape {	
		private PathData pathData;
		public TargetFeedback(PathData pathData) {
			setPathData(pathData);
			setFill(false);
			setOutline(true);
		}
		@Override
		protected void outlineShape(Graphics g) {
			if(pathData != null) {
				g.pushState();
				g.setAntialias(SWT.ON);
				g.setAdvanced(true);
				
				Path path = new Path(PlatformUI.getWorkbench().getDisplay(), pathData);
				g.drawPath(path);
				path.dispose();
				
				g.popState();
			}
		}
		@Override
		protected void fillShape(Graphics graphics) {		
		}
		public void setPathData(PathData pathData) {
			this.pathData = pathData;
		}
	}

}
