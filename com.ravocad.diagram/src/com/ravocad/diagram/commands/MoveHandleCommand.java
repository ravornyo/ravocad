   package com.ravocad.diagram.commands;

import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;

import com.ravocad.diagram.i10n.Messages;
import com.ravocad.notation.NotationPackage;
import com.ravocad.notation.Path;
import com.ravocad.notation.View;

public class MoveHandleCommand extends org.eclipse.gef.commands.Command {

	private PointList handleCopy;
	private View view ;
	private Point moveDelta;
	private int[] indices;

	public MoveHandleCommand(View view, Point moveDelta, int[] indices) {
        super(Messages.getString("MoveHandleCommand_Label"));
		Assert.isNotNull(view, "view cannot be null");
		Assert.isNotNull(moveDelta, "point cannot be null");
		this.view = view;			
		this.moveDelta = moveDelta;	
		this.indices = indices;
	}
	
	@Override
	public boolean canExecute() {
		return view != null && ((Path)view).getHandles() != null;
	}
	
	@Override
	public void execute() {
		primExecute();
	}
	
	@Override
	public void redo() {
		primExecute();
	}
	
	protected void primExecute() {
		PointList points = ((Path)view).getHandles().getCopy();
		handleCopy = points.getCopy();
		/*	
		for(int index: indices) {
			Point point = points.getPoint(index).getCopy();
			point.translate(moveDelta);	
			points.setPoint(point, index);		
		}
		*/
		enforceMoveConstraint(points);
		view.eSet(NotationPackage.eINSTANCE.getPath_Handles(), points);
	}
	
	protected void enforceMoveConstraint(PointList points) {
		for(int index: indices) {
			Point point = points.getPoint(index).getCopy();
			point.translate(moveDelta);	
			points.setPoint(point, index);		
		}
	}

	@Override
	public void undo() {
		view.eSet(NotationPackage.eINSTANCE.getPath_Handles(), handleCopy);
	}
	
	protected int[] getIndices() {
		return indices;
	}
	
	protected Point getMoveDelta() {
		return moveDelta;
	}
}
