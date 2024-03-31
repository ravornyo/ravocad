package com.ravocad.diagram.palette;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.jface.resource.ImageDescriptor;

import com.ravocad.diagram.requests.NotationCreationFactory;

public class PaletteToolEntry extends CombinedTemplateCreationEntry {

	public PaletteToolEntry(String label, String shortDesc, EClass template, Class<?> tool, 
			ImageDescriptor iconSmall, ImageDescriptor iconLarge) {
		super(label, shortDesc, template, new NotationCreationFactory(template), iconSmall, iconLarge);
		setToolClass(tool);
	}

}
