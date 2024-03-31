package com.ravocad.diagram.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;

import com.ravocad.notation.View;

public class MoveRectangleHandleCommand extends MoveHandleCommand {

	public MoveRectangleHandleCommand(View view, Point moveDelta, int[] indices) {
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
