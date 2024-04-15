package com.ravocad.diagram.edit.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import com.ravocad.diagram.constants.RequestConstants;
import com.ravocad.diagram.edit.policies.TextDirectEditPolicy;
import com.ravocad.diagram.edit.policies.TextResizableEditPolicy;
import com.ravocad.diagram.locator.LabelCellEditorLocator;
import com.ravocad.diagram.tools.TextDirectEditManager;
import com.ravocad.notation.NotationPackage;
import com.ravocad.notation.Text;

public class LabelEditPart extends ViewEditPart {

	@Override
	protected IFigure createFigure() {
		return new Label();
	}

	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new TextResizableEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new TextDirectEditPolicy());
	}
	
	@Override
	public void performRequest(Request request) {
		if (request.getType() == RequestConstants.REQ_DIRECT_EDIT) {
			performDirectEditing();
		}
	}
	
	private void performDirectEditing() {
	    Label label = (Label)getFigure();
	    TextDirectEditManager manager = new TextDirectEditManager(this, 
	    		TextCellEditor.class, new LabelCellEditorLocator(label), label);
	    manager.show();
	  } 
	
	@Override
	public void notifyChanged(Notification notification) {
		Object feature = notification.getFeature();
		if (NotationPackage.eINSTANCE.getText_Location().equals(feature)) {
			refreshBounds();						
		} else if (NotationPackage.eINSTANCE.getText_Content().equals(feature)) {
			String content = (String) notification.getNewValue();
			if(content != null) {
				setLabel(content);
				refreshBounds();
			}
		} else if (NotationPackage.eINSTANCE.getText_Font().equals(feature)) {
			FontData fontData = (FontData) notification.getNewValue();
			if(fontData != null) {
				setFont(new Font(Display.getCurrent(), fontData));	
				refreshBounds();
			}
		} else if (NotationPackage.eINSTANCE.getText_Color().equals(feature)) {
			RGB rgb = (RGB) notification.getNewValue();
			if(rgb != null) {
				setFontColor(new Color(rgb));	
			}			
		} else {
			super.notifyChanged(notification);
		}
	}
	
	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();
		refreshLabel();
		refreshFont();
		refreshFontColor();
		refreshBounds();
	}

	protected void refreshBounds() {
		Text view = (Text)getView();
		Point loc = view.getLocation();
		
		Label label = (Label)getFigure();
		Dimension size = label.getPreferredSize();
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, label, new Rectangle(loc, size));		
	}

	protected void refreshLabel() {
		Text view = (Text)getView();
		String content = view.getContent();	
		setLabel(content);
	}
	
	protected void setLabel(String content) {
		Label label = (Label)getFigure();
		if (label != null) {
			label.setText(content);
		}
	}

	protected void refreshFont() {
		Text view = (Text)getView();
		FontData fontData = view.getFont();	
		if(fontData != null) {
			Font font = getResourceManager().create(FontDescriptor.createFrom(fontData));
			setFont(font);
		}
	}
	
	protected void setFont(Font font) {
		Label label = (Label)getFigure();
		if (label != null && font != null) {
			label.setFont(font);
		}
	}

	protected void refreshFontColor() {
		Text view = (Text)getView();
		if(view.getColor() != null) {
			setFontColor(new Color(view.getColor()));	
		}	
	}
	
	protected void setFontColor(Color color) {
		Label label = (Label)getFigure();
		if (label != null) {
			label.setForegroundColor(color);
		}
	}

}
