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

public class RotateCommand extends Command {

	private PointList handleCopy;
	private View view ;
	private Point origin;
	private double angle;

	public RotateCommand(View view, Point origin, double angle){
		super(Messages.getString("RotateCommand_LabelText"));
		Assert.isNotNull(view, "view cannot be null");
		Assert.isNotNull(origin, "Center of rotation cannot be null");
		this.view = view;
		this.origin = origin;
		this.angle = angle;
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
			Point vertex = points.getPoint(i);
			
			double translatedToOriginX = vertex.x - origin.x;
		    double translatedToOriginY = vertex.y - origin.y;

		    double rotatedX = translatedToOriginX * Math.cos(angle) - translatedToOriginY * Math.sin(angle);
		    double rotatedY = translatedToOriginX * Math.sin(angle) + translatedToOriginY * Math.cos(angle);

		    double reverseTranslatedX = rotatedX + origin.x;
		    double reverseTranslatedY = rotatedY + origin.y;
		    
			points.setPoint(new PrecisionPoint(reverseTranslatedX, reverseTranslatedY), i);
        }
	}

	@Override
	public void undo() {
		view.eSet(NotationPackage.eINSTANCE.getPath_Handles(), handleCopy);
	}

}
