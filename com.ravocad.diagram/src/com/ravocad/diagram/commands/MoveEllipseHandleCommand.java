package com.ravocad.diagram.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;

import com.ravocad.notation.View;

public class MoveEllipseHandleCommand extends MoveHandleCommand {

	public MoveEllipseHandleCommand(View view, Point moveDelta, int[] indices) {
		super(view, moveDelta, indices);
	}
	
	protected void enforceMoveConstraint(PointList points) {
		int[] indices = getIndices();
		if(indices.length > 1) {
			super.enforceMoveConstraint(points);
			return;
		}
		int index = indices[0];
		Point moveDelta = getMoveDelta();
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
