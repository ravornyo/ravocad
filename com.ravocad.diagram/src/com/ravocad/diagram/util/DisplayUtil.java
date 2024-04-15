package com.ravocad.diagram.util;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

public class DisplayUtil {
	/**
	 * Returns a non-null instance of Display object. Tries to find the Display
	 * object for the current thread first and if it fails tries to get:
	 * <li> Workbench display if the workbench running
	 * <li> Default display object
	 * 
	 * @return non-null Display object
	 * @since 1.2
	 */
	public static Display getDisplay() {
		Display display = Display.getCurrent();
		if (display == null && PlatformUI.isWorkbenchRunning()) {
			display = PlatformUI.getWorkbench().getDisplay();
		}
		return display != null ? display : Display.getDefault();
	}
	
}
