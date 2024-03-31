package com.ravocad.diagram.edit.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.PathData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.ravocad.diagram.edit.figures.PathShape;
import com.ravocad.diagram.edit.policies.PathComponentPolicy;
import com.ravocad.diagram.edit.policies.PathResizableEditPolicy;
import com.ravocad.notation.NotationPackage;
import com.ravocad.notation.Path;


public class PathEditPart extends ViewEditPart  {


	protected void createEditPolicies() {
		// allow removal of the associated model element
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new PathComponentPolicy());
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new PathResizableEditPolicy());
	}


	@Override
	public void performRequest(Request req) {
		if (req.getType() == RequestConstants.REQ_OPEN) {
			try {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				page.showView(IPageLayout.ID_PROP_SHEET);
			} catch (PartInitException e) {
			}
		}
	}
	
	@Override
	protected IFigure createFigure() {
		return new PathShape();
	}

	
	@Override
	public void notifyChanged(Notification notification) {
		Object feature = notification.getFeature();
		if (NotationPackage.eINSTANCE.getPath_Data().equals(feature)) {
			refreshBounds();
		} else if (NotationPackage.eINSTANCE.getPath_LineWidth().equals(feature)) {
			refreshLineWidth();			
		} else if (NotationPackage.eINSTANCE.getPath_LineColor().equals(feature)) {
			RGB rgb = (RGB) notification.getNewValue();
			if(rgb != null) {
				setForegroundColor(new Color(rgb));	
			}			
		} else if (NotationPackage.eINSTANCE.getPath_FillColor().equals(feature)) {
			RGB rgb = (RGB) notification.getNewValue();
			if(rgb != null) {
				setBackgroundColor(new Color(rgb));	
			}
		} else if (NotationPackage.eINSTANCE.getPath_Alpha().equals(feature)) {
			Integer alpha = (Integer) notification.getNewValue();
			if(alpha != null) {
				setTransparency(alpha);	
			}			
		} else {
			super.notifyChanged(notification);
		}
	}


	@Override
	protected void refreshVisuals() {
		refreshBounds();
		refreshLineWidth();
		refreshForegroundColor();
		refreshBackgroundColor();
		refreshTransparency();
	}

	protected void refreshBounds() {
		PathShape shape = (PathShape)getFigure();
		
		Path view = (Path)getView();
		PathData pathData = view.getData(); 
		org.eclipse.swt.graphics.Path path = new org.eclipse.swt.graphics.Path(PlatformUI.getWorkbench().getDisplay(), pathData);
		float[] arr = new float[4];
		path.getBounds(arr);
		path.dispose();
		
		shape.setPathData(pathData);
		shape.setHandlePoints(view.getHandles());
		
		int lineWidth = Math.max(1, view.getLineWidth());
		Rectangle bounds = new PrecisionRectangle(arr[0], arr[1], Math.ceil(arr[2]), Math.ceil(arr[3]));
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, shape, bounds.expand(lineWidth, lineWidth));
	}
	
	protected void refreshLineWidth() {
		Path view = (Path)getView();
		
		PathShape shape = (PathShape)getFigure();
		if (shape != null) {
			int lineWidth = Math.max(1, view.getLineWidth());
			shape.setLineWidth(lineWidth);
		}
	}
	
	protected void refreshForegroundColor() {
		Path view = (Path)getView();
		if(view.getLineColor() != null) {
			setForegroundColor(new Color(view.getLineColor()));	
		}	
	}
	
	protected void setForegroundColor(Color color) {
		PathShape shape = (PathShape)getFigure();
		if (shape != null) {
			shape.setForegroundColor(color);
		}
	}
	
	protected void refreshBackgroundColor() {
		Path view = (Path)getView();
		if(view.getFillColor() != null) {
			setBackgroundColor(new Color(view.getFillColor()));	
		}
	}
	
	protected void setBackgroundColor(Color color) {
		PathShape shape = (PathShape)getFigure();
		if (shape != null) {
			shape.setBackgroundColor(color);
		}
	}
	
	protected void refreshTransparency() {
		Path view = (Path)getView();
		setTransparency(view.getAlpha());
	}
	
	protected void setTransparency(Integer alpha) {
		PathShape shape = (PathShape)getFigure();
		if (shape != null) {
			shape.setAlpha(alpha);
		}
	}

}
