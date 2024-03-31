package com.ravocad.diagram.handles;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.SquareHandle;

import com.ravocad.diagram.edit.figures.PathShape;
import com.ravocad.diagram.locator.MovePointHandleLocator;
import com.ravocad.diagram.tools.MoveHandleTracker;

public class MovePointHandle extends SquareHandle implements PropertyChangeListener {
	
	private final int index;

	public MovePointHandle(GraphicalEditPart owner, MovePointHandleLocator loc, int index) {
		super(owner, loc, Cursors.CROSS);
		this.index = index;
	}
	
	@Override
	public void addNotify() {
		super.addNotify();
		
		PathShape shape = (PathShape)getOwnerFigure();
		shape.addPropertyChangeListener(PathShape.PROPERTY_POINTS, this);
	}
	
	@Override
	public void removeNotify() {
		PathShape shape = (PathShape)getOwnerFigure();
		shape.removePropertyChangeListener(PathShape.PROPERTY_POINTS, this);
		super.removeNotify();
	}

	@Override
	protected DragTracker createDragTracker() {
		return new MoveHandleTracker(getOwner(), index);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(PathShape.PROPERTY_POINTS)) {
			PointList points = (PointList)evt.getNewValue();

			Point point  = points.getPoint(index);
			((MovePointHandleLocator)getLocator()).setTargetLocation(point);
			revalidate();		
		}
	}

}
