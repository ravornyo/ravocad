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

import com.ravocad.diagram.commands.MoveRectangleHandleCommand;
import com.ravocad.diagram.commands.UpdatePathCommand;
import com.ravocad.diagram.handles.MovePointHandle;
import com.ravocad.diagram.i10n.Messages;
import com.ravocad.diagram.locator.MovePointHandleLocator;
import com.ravocad.diagram.requests.MoveHandleRequest;
import com.ravocad.notation.View;

public class RectangleResizableEditPolicy extends PathResizableEditPolicy {

	@Override
	protected void createPointHandles(List<Handle> list, PointList points) {
		for(int i=0; i < points.size(); i++) {
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
		cc.add(new MoveRectangleHandleCommand(view, request.getMoveDelta(), request.getIndices()));	
		cc.add(new UpdatePathCommand(view));
		
		return cc;	
	}
	
	@Override
	protected void createPointHandle(List<Handle> handles, Point point, int index) {
		MovePointHandle handle = new MovePointHandle(
				(GraphicalEditPart) getHost(), 
				new MovePointHandleLocator(getHostFigure(), point), 
				index);
		if(index == 0) {
			handle.setCursor(Cursors.SIZENW);
		} else if(index == 1) {
			handle.setCursor(Cursors.SIZEN);
		} else if(index == 2) {
			handle.setCursor(Cursors.SIZENE);
		} else if(index == 3) {
			handle.setCursor(Cursors.SIZEE);
		} else if(index == 4) {
			handle.setCursor(Cursors.SIZESE);
		} else if(index == 5) {
			handle.setCursor(Cursors.SIZES);
		} else if(index == 6) {
			handle.setCursor(Cursors.SIZESW);
		} else if(index == 7) {
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
		if(index == 0) {
			Point p0 = points.getPoint(0).getTranslated(moveDelta.x, moveDelta.y);
			Point p1 = points.getPoint(1).getTranslated(0, moveDelta.y);
			Point p2 = points.getPoint(2).getTranslated(0, moveDelta.y);
			Point p3 = points.getPoint(6).getTranslated(moveDelta.x, 0);
			Point p4 = points.getPoint(7).getTranslated(moveDelta.x, 0);
			points.setPoint(p0, 0);
			points.setPoint(p1, 1);
			points.setPoint(p2, 2);
			points.setPoint(p3, 6);
			points.setPoint(p4, 7);
	
		} else if(index == 1) {
			Point p0 = points.getPoint(0).getTranslated(0, moveDelta.y);
			Point p1 = points.getPoint(1).getTranslated(0, moveDelta.y);
			Point p2 = points.getPoint(2).getTranslated(0, moveDelta.y);
			points.setPoint(p0, 0);
			points.setPoint(p1, 1);
			points.setPoint(p2, 2);
	
		} else if(index == 2) {
			Point p0 = points.getPoint(2).getTranslated(moveDelta.x, moveDelta.y);
			Point p1 = points.getPoint(0).getTranslated(0, moveDelta.y);
			Point p2 = points.getPoint(1).getTranslated(0, moveDelta.y);
			Point p3 = points.getPoint(3).getTranslated(moveDelta.x, 0);
			Point p4 = points.getPoint(4).getTranslated(moveDelta.x, 0);
			points.setPoint(p0, 2);
			points.setPoint(p1, 0);
			points.setPoint(p2, 1);
			points.setPoint(p3, 3);
			points.setPoint(p4, 4);
	
		} else if(index == 3) {
			Point p0 = points.getPoint(2).getTranslated(moveDelta.x, 0);
			Point p1 = points.getPoint(3).getTranslated(moveDelta.x, 0);
			Point p2 = points.getPoint(4).getTranslated(moveDelta.x, 0);
			points.setPoint(p0, 2);
			points.setPoint(p1, 3);
			points.setPoint(p2, 4);
			
		} else if(index == 4) {
			Point p0 = points.getPoint(4).getTranslated(moveDelta.x, moveDelta.y);
			Point p1 = points.getPoint(6).getTranslated(0, moveDelta.y);
			Point p2 = points.getPoint(5).getTranslated(0, moveDelta.y);
			Point p3 = points.getPoint(2).getTranslated(moveDelta.x, 0);
			Point p4 = points.getPoint(3).getTranslated(moveDelta.x, 0);
			points.setPoint(p0, 4);
			points.setPoint(p1, 6);
			points.setPoint(p2, 5);
			points.setPoint(p3, 2);
			points.setPoint(p4, 3);
	
		} else if(index == 5) {
			Point p0 = points.getPoint(4).getTranslated(0, moveDelta.y);
			Point p1 = points.getPoint(5).getTranslated(0, moveDelta.y);
			Point p2 = points.getPoint(6).getTranslated(0, moveDelta.y);
			points.setPoint(p0, 4);
			points.setPoint(p1, 5);
			points.setPoint(p2, 6);
			
		} else if(index == 6) {
			Point p0 = points.getPoint(6).getTranslated(moveDelta.x, moveDelta.y);
			Point p1 = points.getPoint(5).getTranslated(0, moveDelta.y);
			Point p2 = points.getPoint(4).getTranslated(0, moveDelta.y);
			Point p3 = points.getPoint(7).getTranslated(moveDelta.x, 0);
			Point p4 = points.getPoint(0).getTranslated(moveDelta.x, 0);
			points.setPoint(p0, 6);
			points.setPoint(p1, 5);
			points.setPoint(p2, 4);
			points.setPoint(p3, 7);
			points.setPoint(p4, 0);
	
		} else if(index == 7) {
			Point p0 = points.getPoint(6).getTranslated(moveDelta.x, 0);
			Point p1 = points.getPoint(7).getTranslated(moveDelta.x, 0);
			Point p2 = points.getPoint(0).getTranslated(moveDelta.x, 0);
			points.setPoint(p0, 6);
			points.setPoint(p1, 7);
			points.setPoint(p2, 0);
		} else {
			Point p0 = points.getPoint(index).getTranslated(moveDelta.x, moveDelta.y);
			points.setPoint(p0, index);
		}

	}
	
}
