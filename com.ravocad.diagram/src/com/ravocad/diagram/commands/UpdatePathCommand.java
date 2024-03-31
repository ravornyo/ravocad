   package com.ravocad.diagram.commands;

import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.swt.graphics.PathData;

import com.ravocad.diagram.geometry.GeometryProvider;
import com.ravocad.diagram.i10n.Messages;
import com.ravocad.notation.NotationPackage;
import com.ravocad.notation.Path;
import com.ravocad.notation.View;

public class UpdatePathCommand extends org.eclipse.gef.commands.Command {

	private View view;
	private PathData pathDataCopy;

	public UpdatePathCommand(View view) {
        super(Messages.getString("UpdatePathCommand_Label"));
		Assert.isNotNull(view, "View adapter cannot be null");
		this.view = view;	
	}
	
	@Override
	public void execute() {
		primExecute();
	}
	
	@Override
	public void redo() {
		primExecute();
	}
	
	@Override
	public void undo() {
		view.eSet(NotationPackage.eINSTANCE.getPath_Data(), pathDataCopy);
	}
	
	private void primExecute() {
		PathData pathData = ((Path)view).getData();	
		
		PointList points = ((Path)view).getHandles().getCopy();
		pathDataCopy = new PathData();
		pathDataCopy.points = pathData.points.clone();
		pathDataCopy.types = pathData.types.clone();

		org.eclipse.swt.graphics.Path path = GeometryProvider.getInstance().createPath(view.getHint(), points);
		if(path != null) {
			pathData = path.getPathData();		
			view.eSet(NotationPackage.eINSTANCE.getPath_Data(), pathData);		
			path.dispose();
		}
	}
}
