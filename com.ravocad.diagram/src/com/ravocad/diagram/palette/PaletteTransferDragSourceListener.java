package com.ravocad.diagram.palette;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;

public class PaletteTransferDragSourceListener extends TemplateTransferDragSourceListener {

	/**
	 * Constructs a listener on the specified viewer.
	 * 
	 * @param viewer
	 */
	public PaletteTransferDragSourceListener(EditPartViewer viewer) {
		super(viewer);
	}

}
