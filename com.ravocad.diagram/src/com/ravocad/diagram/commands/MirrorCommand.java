package com.ravocad.diagram.commands;

import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gef.commands.Command;

import com.ravocad.diagram.i10n.Messages;
import com.ravocad.notation.NotationPackage;
import com.ravocad.notation.Path;
import com.ravocad.notation.View;

public class MirrorCommand extends Command {

	private PointList handleCopy;
	private View view ;
	private double m;
	private double c;

	public MirrorCommand(View view, double m, double c){
		super(Messages.getString("RotateClockwiseCommand_LabelText"));
		Assert.isNotNull(view, "view cannot be null");

		this.view = view;
		this.m = m;
		this.c = c;
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

		enforceConstraint(points);
		view.eSet(NotationPackage.eINSTANCE.getPath_Handles(), points);
	}
	
	protected void enforceConstraint(PointList points) {
		for(int i=0; i < points.size(); i++) {
			Point point = points.getPoint(i);
			
			double x = (double)point.x;
			double y = (double)point.y;
			double reflectedX = x;
			double reflectedY = y;
			if(m == Double.POSITIVE_INFINITY) {
				double d = c - x;
				reflectedX = 2*d + x;
		        reflectedY = y;
			} else {
				double d = (x + (y - c)*m)/(1 + (m*m));
		        
		        reflectedX = 2*d - x;
		        reflectedY = 2*d*m - y + 2*c;
			}
		    
			points.setPoint(new PrecisionPoint(reflectedX, reflectedY), i);
        }
	}

	@Override
	public void undo() {
		view.eSet(NotationPackage.eINSTANCE.getPath_Handles(), handleCopy);
	}

}
