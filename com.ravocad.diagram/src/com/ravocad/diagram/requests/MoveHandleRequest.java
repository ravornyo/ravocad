package com.ravocad.diagram.requests;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;

import com.ravocad.diagram.constants.RequestConstants;

public class MoveHandleRequest extends Request implements RequestConstants {
	
	private Point moveDelta = new Point();
	private GraphicalEditPart sourceEditPart;
	private int[] indices;
	private boolean snapToEnabled;
	
	public MoveHandleRequest(int ...indices) {
		setIndices(indices);
		setType(REQ_MOVE_POINT);
	}
	
	public Point getMoveDelta() {
		return moveDelta;
	}
	
	public void setMoveDelta(Point moveDelta) {
		this.moveDelta = moveDelta;
	}
	
	public void setMoveDelta(Dimension moveDelta) {
		this.moveDelta = new Point(moveDelta.width, moveDelta.height);
	}
	
	public int[] getIndices() {
		return indices;
	}
	
	public void setIndices(int ...indices) {
		this.indices = indices;
	}

	public void setSnapToEnabled(boolean snapToEnabled) {
		this.snapToEnabled = snapToEnabled;
	}
	
	public boolean isSnapToEnabled() {
		return this.snapToEnabled;
	}

	public GraphicalEditPart getSourceEditPart() {
		return sourceEditPart;
	}

	public void setSourceEditPart(GraphicalEditPart sourceEditPart) {
		this.sourceEditPart = sourceEditPart;
	}
	
	public Point getTransformedPoint(Point point) {
		return point.getCopy().translate(moveDelta);
	}

}
