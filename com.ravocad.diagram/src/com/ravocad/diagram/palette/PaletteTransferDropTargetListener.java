package com.ravocad.diagram.palette;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.requests.CreationFactory;

import com.ravocad.diagram.requests.NotationCreationFactory;

public class PaletteTransferDropTargetListener extends org.eclipse.gef.dnd.TemplateTransferDropTargetListener {

	/**
	 * Constructs a listener on the specified viewer.
	 * 
	 * @param viewer the EditPartViewer
	 */
	public PaletteTransferDropTargetListener(EditPartViewer viewer) {
		super(viewer);
	}

	protected CreationFactory getFactory(Object template) {
		if (template instanceof EClass) {
			return new NotationCreationFactory((EClass) template);
		} 
		return super.getFactory(template);
	}
}