package com.ravocad.diagram.edit.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.ravocad.diagram.constants.VisualConstants;
import com.ravocad.notation.Diagram;
import com.ravocad.notation.Path;

public class GraphicalEditPartFactory implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart child = null;

		if (model instanceof Diagram) {
			child = new DiagramEditPart();
		} else if (model instanceof Path) {
			Path path = (Path)model;
			if(path.getHint().equalsIgnoreCase(VisualConstants.ELLIPSE)) {
				child = new EllipseEditPart();
			} else if(path.getHint().equalsIgnoreCase(VisualConstants.RECTANGLE)) {
				child = new RectangleEditPart();
			} else {
				child = new PathEditPart();
			}
		}
		if(child != null) {
			child.setModel(model);
		}
		return child;
	}

}
