package com.ravocad.diagram.edit.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.ravocad.notation.Diagram;
import com.ravocad.notation.Path;

public class GraphicalEditPartFactory implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart child = null;

		if (model instanceof Diagram) {
			child = new DiagramEditPart();
		} else if (model instanceof Path) {
			child = new PathEditPart();
		}
		if(child != null) {
			child.setModel(model);
		}
		return child;
	}

}
