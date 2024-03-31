package com.ravocad.diagram.locator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;

public class MovePointHandleLocator implements Locator {
	
	private IFigure reference;
	
	private Point targetLocation;

	public MovePointHandleLocator(IFigure reference, Point point) {
		setReferenceFigure(reference);
		setTargetLocation(point);
	}

	/**
	 * Returns the Figure this locator is relative to.
	 * 
	 * @return the reference figure
	 * @since 2.0
	 */
	public IFigure getReferenceFigure() {
		return reference;
	}

	/**
	 * Relocates the target using the relative offset locations.
	 * 
	 * @see org.eclipse.draw2d.Locator#relocate(org.eclipse.draw2d.IFigure)
	 */
	@Override
	public void relocate(IFigure target) {
		Dimension targetSize = target.getPreferredSize();
		
		getReferenceFigure().translateToAbsolute(targetLocation);
		target.translateToRelative(targetLocation);
		
		Rectangle targetBounds = new Rectangle(targetLocation, targetSize);
		targetBounds.x -= ((double)(targetSize.width) / 2.0);
		targetBounds.y -= ((double)(targetSize.height) / 2.0);
		target.setBounds(targetBounds);
	}

	public void setReferenceFigure(IFigure reference) {
		this.reference = reference;
	}

	protected Rectangle getReferenceBox() {
		IFigure f = getReferenceFigure();
		if (f instanceof HandleBounds) {
			return ((HandleBounds) f).getHandleBounds();
		}
		return getReferenceFigure().getBounds();
	}
	
	public void setTargetLocation(Point point) {
		this.targetLocation = point;
	}


}
