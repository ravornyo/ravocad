package com.ravocad.diagram;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class DiagramPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String ID = "com.ravocad.diagram";

	// The shared instance
	private static DiagramPlugin instance;
	
	/**
	 * The constructor
	 */
	public DiagramPlugin() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		instance = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		instance = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static DiagramPlugin getInstance() {
		return instance;
	}
	
	public static ImageDescriptor getBundledImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin(ID, path);
	}

}
