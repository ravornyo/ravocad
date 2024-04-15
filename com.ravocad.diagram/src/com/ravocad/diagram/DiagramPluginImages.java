package com.ravocad.diagram;

import org.eclipse.jface.resource.ImageDescriptor;

public class DiagramPluginImages {
	
	public static final ImageDescriptor DESC_ROTATE;
	public static final ImageDescriptor DESC_ROTATE_DIS;
	
	public static final ImageDescriptor DESC_MIRROR;
	public static final ImageDescriptor DESC_MIRROR_DIS;
	
	public static final ImageDescriptor DESC_SCALE;
	public static final ImageDescriptor DESC_SCALE_DIS;
	
	static {
		DESC_ROTATE = DiagramPlugin.getBundledImageDescriptor("icons/etool16/rotate.png"); 
		DESC_ROTATE_DIS = DiagramPlugin.getBundledImageDescriptor("icons/dtool16/rotate.png"); 
		
		DESC_MIRROR = DiagramPlugin.getBundledImageDescriptor("icons/etool16/mirror.png"); 
		DESC_MIRROR_DIS = DiagramPlugin.getBundledImageDescriptor("icons/dtool16/mirror.png"); 
		
		DESC_SCALE = DiagramPlugin.getBundledImageDescriptor("icons/etool16/scale.png"); 
		DESC_SCALE_DIS = DiagramPlugin.getBundledImageDescriptor("icons/dtool16/scale.png"); 
	}

}
