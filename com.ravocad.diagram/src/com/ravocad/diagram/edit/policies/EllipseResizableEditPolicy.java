package com.ravocad.diagram.edit.policies;

import java.util.List;

import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Handle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.tools.DragEditPartsTracker;

import com.ravocad.diagram.commands.MoveEllipseHandleCommand;
import com.ravocad.diagram.commands.UpdatePathCommand;
import com.ravocad.diagram.handles.MovePointHandle;
import com.ravocad.diagram.i10n.Messages;
import com.ravocad.diagram.locator.MovePointHandleLocator;
import com.ravocad.diagram.requests.MoveHandleRequest;
import com.ravocad.notation.View;

public class EllipseResizableEditPolicy extends PathResizableEditPolicy {

	@Override
	protected void createPointHandles(List<Handle> list, PointList points) {
		createCenterHandle(list, points.getFirstPoint());
		for(int i=1; i < points.size(); i++) {
			Point point = points.getPoint(i);
			createPointHandle(list, point, i);
		}
	}
	
	protected void createCenterHandle(List<Handle> handles, Point point) {
		MovePointHandle handle = new MovePointHandle(
				(GraphicalEditPart) getHost(), 
				new MovePointHandleLocator(getHostFigure(), point), 0) {
			@Override
			protected DragTracker createDragTracker() {
				DragEditPartsTracker tracker = new DragEditPartsTracker(getOwner());
				tracker.setDefaultCursor(getCursor());
				return tracker;
			}
		};
		handle.setCursor(Cursors.SIZEALL);
		handles.add(handle);	
	}
	
	protected Command getMoveHandleCommand(MoveHandleRequest request) {
        View view = (View)getHost().getModel();
        double scale = getScale();

        PrecisionPoint moveDelta = new PrecisionPoint(request.getMoveDelta());
        moveDelta.performScale(1.0/scale);
 
        request.setMoveDelta(moveDelta);

		CompoundCommand cc = new CompoundCommand(Messages.getString("ChangeBoundsCommand_Label"));	
		cc.add(new MoveEllipseHandleCommand(view, request.getMoveDelta(), request.getIndices()));	
		cc.add(new UpdatePathCommand(view));
		
		return cc;	
	}
	
	@Override
	protected void createPointHandle(List<Handle> handles, Point point, int index) {
		MovePointHandle handle = new MovePointHandle(
				(GraphicalEditPart) getHost(), 
				new MovePointHandleLocator(getHostFigure(), point), 
				index);
		if(index == 1) {
			handle.setCursor(Cursors.SIZEN);
		} else if(index == 2) {
			handle.setCursor(Cursors.SIZEE);
		} else if(index == 3) {
			handle.setCursor(Cursors.SIZES);
		} else if(index == 4) {
			handle.setCursor(Cursors.SIZEW);
		}
		handles.add(handle);	
	}
	
	protected void enforceMoveConstraint(MoveHandleRequest request, PointList points) {
		int[] indices = request.getIndices();
		if(indices.length > 1) {
			super.enforceMoveConstraint(request, points);
			return;
		}
		int index = indices[0];
		Point moveDelta = request.getMoveDelta();
		if(index == 1) {
			Point f1 = points.getPoint(1);
			f1.performTranslate(0, moveDelta.y);
			points.setPoint(f1, 1);
			
			Point f3 = points.getPoint(3);
			f3.performTranslate(0, -moveDelta.y);
			points.setPoint(f3, 3);
		} else if(index == 2) {
			Point f2 = points.getPoint(2);
			f2.performTranslate(moveDelta.x, 0);
			points.setPoint(f2, 2);
			
			Point f4 = points.getPoint(4);
			f4.performTranslate(-moveDelta.x, 0);
			points.setPoint(f4, 4);
		} else if(index == 3) {
			Point f3 = points.getPoint(3);
			f3.performTranslate(0, moveDelta.y);
			points.setPoint(f3, 3);
			
			Point f1 = points.getPoint(1);
			f1.performTranslate(0, -moveDelta.y);
			points.setPoint(f1, 1);
		} else if(index == 4) {
			Point f4 = points.getPoint(4);
			f4.performTranslate(moveDelta.x, 0);
			points.setPoint(f4, 4);
			
			Point f2 = points.getPoint(2);
			f2.performTranslate(-moveDelta.x, 0);
			points.setPoint(f2, 2);			
		}
	}
	
}
