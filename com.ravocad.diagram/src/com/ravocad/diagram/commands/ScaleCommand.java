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

public class ScaleCommand extends Command {

	private PointList handleCopy;
	private View view ;
	private double scale;
	private Point center;

	public ScaleCommand(View view, Point center, double scale){
		super(Messages.getString("ScaleCommand_LabelText"));
		Assert.isNotNull(view, "view cannot be null");
		this.view = view;
		this.center = center;
		this.scale = scale;
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

		    double scaledX = scale * ( vertex.x - center.x) + center.x;
		    double scaledY = scale * ( vertex.y - center.y) + center.y;

			points.setPoint(new PrecisionPoint(scaledX, scaledY), i);
        }
	}

	@Override
	public void undo() {
		view.eSet(NotationPackage.eINSTANCE.getPath_Handles(), handleCopy);
	}

}
