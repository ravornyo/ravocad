package com.ravocad.diagram.edit.policies;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Handle;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.PathData;
import org.eclipse.ui.PlatformUI;

import com.ravocad.diagram.commands.MoveHandleCommand;
import com.ravocad.diagram.commands.UpdatePathCommand;
import com.ravocad.diagram.constants.RequestConstants;
import com.ravocad.diagram.edit.figures.PathShape;
import com.ravocad.diagram.geometry.GeometryProvider;
import com.ravocad.diagram.handles.MovePointHandle;
import com.ravocad.diagram.handles.MoveShapeHandle;
import com.ravocad.diagram.i10n.Messages;
import com.ravocad.diagram.locator.MovePointHandleLocator;
import com.ravocad.diagram.requests.MoveHandleRequest;
import com.ravocad.notation.View;

public class PathResizableEditPolicy extends ResizableEditPolicy {
	
	@Override
	protected List<?> createSelectionHandles() {	
		List<Handle> list = new ArrayList<Handle>();
		
		createShapeHandle(list);
		
		PointList points = getPathShape().getHandlePoints();
		if(points != null) {
			createPointHandles(list, points);
		}
		return list;	
	}
	
	protected void createShapeHandle(List<Handle> handles) {
		MoveShapeHandle handle = new MoveShapeHandle((GraphicalEditPart) getHost());
		handles.add(handle);	
	}
	
	protected void createPointHandles(List<Handle> list, PointList points) {
		for(int i=0; i < points.size(); i++) {
			Point point = points.getPoint(i);
			createPointHandle(list, point, i);
		}
	}

	protected void createPointHandle(List<Handle> handles, Point point, int index) {
		MovePointHandle handle = new MovePointHandle(
				(GraphicalEditPart) getHost(), 
				new MovePointHandleLocator(getHostFigure(), point), 
				index);
		handles.add(handle);	
	}
	
	protected PathShape getPathShape() {
		return (PathShape)getHostFigure();
	}
	
	@Override
	public Command getCommand(Request request) {
		if (RequestConstants.REQ_MOVE_POINT.equals(request.getType())) {
			MoveHandleRequest moveRequest = (MoveHandleRequest) request;
			return getMoveHandleCommand(moveRequest);
		} 
		return super.getCommand(request);
	}
	
	protected Command getMoveHandleCommand(MoveHandleRequest request) {
        View view = (View)getHost().getModel();
        double scale = getScale();

        PrecisionPoint moveDelta = new PrecisionPoint(request.getMoveDelta());
        moveDelta.performScale(1.0/scale);
 
        request.setMoveDelta(moveDelta);

		CompoundCommand cc = new CompoundCommand(Messages.getString("ChangeBoundsCommand_Label"));	
		cc.add(new MoveHandleCommand(view, request.getMoveDelta(), request.getIndices()));	
		cc.add(new UpdatePathCommand(view));
		
		return cc;	
	}
	
	protected double getScale() {
		ZoomManager zoomManager = ((ScalableFreeformRootEditPart)getHost().getRoot()).getZoomManager();
		return zoomManager.getZoom();
	}
	
	@Override
	protected Command getMoveCommand(ChangeBoundsRequest request) {
		MoveHandleRequest moveRequest = changeBoundsToMoveHandleRequest(request);
		moveRequest.setMoveDelta(request.getMoveDelta());
		return getMoveHandleCommand(moveRequest);
	}
	
	private MoveHandleRequest changeBoundsToMoveHandleRequest(ChangeBoundsRequest request) {
		int size = getPathShape().getHandlePoints().size();
		int[] indices = new int[size];
		for(int i=0; i < size; i++) {
			indices[i] = i;
		}
		MoveHandleRequest moveRequest = new MoveHandleRequest(indices);
		moveRequest.setMoveDelta(request.getMoveDelta());
		return moveRequest;
	}
	
	@Override
	public void showSourceFeedback(Request request) {
		if (RequestConstants.REQ_MOVE_POINT.equals(request.getType())) {
			showMoveHandleFeedback((MoveHandleRequest) request);
		}
		super.showSourceFeedback(request);
	}
	
	@Override
	protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
		MoveHandleRequest moveRequest = changeBoundsToMoveHandleRequest(request);
		showMoveHandleFeedback(moveRequest);
	}
	
    protected void showMoveHandleFeedback(MoveHandleRequest request) {
    	HandleDragFeedback feedback = (HandleDragFeedback)getDragSourceFeedbackFigure();
        Path path = getFeedbackPath(request);
        if(path != null) {
			float[] arr = new float[4];
			path.getBounds(arr);
			
			feedback.setPathData(path.getPathData());
			path.dispose(); 
			feedback.setBounds(new PrecisionRectangle(arr[0], arr[1], Math.ceil(arr[2]), Math.ceil(arr[3])));
			feedback.validate();	
        }
    }
	
	protected Path getFeedbackPath(MoveHandleRequest request) {
        PointList feedbackPoints = getPathShape().getHandlePoints().getCopy();
        getHostFigure().translateToAbsolute(feedbackPoints);

        enforceMoveConstraint(request, feedbackPoints);
        
        getDragSourceFeedbackFigure().translateToRelative(feedbackPoints);
        
        View view = (View)getHost().getModel();
        Path path = GeometryProvider.getInstance().createPath(view.getHint(), feedbackPoints);
		return path;
	}
	
	protected void enforceMoveConstraint(MoveHandleRequest request, PointList points) {
		for(int index: request.getIndices()) {
			Point point = points.getPoint(index);
			point.performTranslate(request.getMoveDelta());
			points.setPoint(point, index);
        }
	}

	@Override
	public void eraseSourceFeedback(Request request) {
		if (RequestConstants.REQ_MOVE_POINT.equals(request.getType())) {
			eraseChangeBoundsFeedback(new ChangeBoundsRequest());
		}
		super.eraseSourceFeedback(request);
	}

	protected HandleDragFeedback createDragSourceFeedbackFigure() {
		HandleDragFeedback feedback = new HandleDragFeedback(getPathShape().getPathData());
		FigureUtilities.makeGhostShape(feedback);
		feedback.setLineStyle(Graphics.LINE_DOT);
		feedback.setBounds(getInitialFeedbackBounds());
		feedback.validate();
		addFeedback(feedback);
		return feedback;
	}

	public class HandleDragFeedback extends Shape {	
		private PathData pathData;
		public HandleDragFeedback(PathData pathData) {
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
			// TODO Auto-generated method stub			
		}
		public void setPathData(PathData pathData) {
			this.pathData = pathData;
		}
	}

}
