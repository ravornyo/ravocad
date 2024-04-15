package com.ravocad.diagram.tools;

import org.eclipse.draw2d.Cursors;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.swt.widgets.Display;

import com.ravocad.diagram.constants.VisualConstants;
import com.ravocad.notation.Text;

public class TextTool extends CreationTool {
	
	private String hint;
	
	public TextTool() {
		this(VisualConstants.LABEL);
	}
	
	public TextTool(String hint) {
		super();
		setDefaultCursor(Cursors.CROSS);
		setDisabledCursor(Cursors.NO);
		setHint(hint);
	}
	
	@Override
	protected void performCreation(int button) {
		CreateRequest request = getCreateRequest();	
		Text notation = (Text)request.getNewObject();
		notation.setLocation(request.getLocation());
		notation.setHint(getHint());
		
		setCurrentCommand(getCommand());
		super.performCreation(button);
		
		performDirectEdit();
	}
	
	protected void performDirectEdit() {
		EditPartViewer viewer = getCurrentViewer();
	    final Object model = getCreateRequest().getNewObject();
	    if (model == null || viewer == null) {
	      return;
	    }
	     
	    final Object o = getCurrentViewer().getEditPartRegistry().get(model);
	    if(o instanceof EditPart) {
	      Display.getCurrent().asyncExec(new Runnable() {         
	        @Override 
	        public void run() {
	          EditPart part = (EditPart)o;
	          Request request = new DirectEditRequest();
	          part.performRequest(request);
	        }
	      });
	    }	
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

}
