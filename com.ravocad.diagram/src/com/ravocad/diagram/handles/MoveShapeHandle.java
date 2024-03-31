package com.ravocad.diagram.handles;

import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.AbstractHandle;
import org.eclipse.gef.handles.MoveHandleLocator;
import org.eclipse.gef.tools.DragEditPartsTracker;

public class MoveShapeHandle extends AbstractHandle {

	public MoveShapeHandle(GraphicalEditPart owner) {
		this(owner, new MoveHandleLocator(owner.getFigure()));
	}

	public MoveShapeHandle(GraphicalEditPart owner, Locator loc) {
		super(owner, loc);
		initialize();
	}  

	@Override
	public boolean containsPoint(int x, int y) {
		Point point = new Point(x, y);
		getOwnerFigure().translateToRelative(point);	
		return getOwnerFigure().containsPoint(point);
	}

	@Override
	protected DragTracker createDragTracker() {
		DragEditPartsTracker tracker = new DragEditPartsTracker(getOwner());
		tracker.setDefaultCursor(getCursor());
		return tracker;
	}
	
	@Override
	public void paint(Graphics g) {
		//super.paintFigure(g);
	}
	
	/**
	 * Initializes the handle. Sets the {@link DragTracker} and DragCursor.
	 */
	protected void initialize() {
		setOpaque(false);
		setBorder(null);
		setCursor(Cursors.SIZEALL);
	}

}
