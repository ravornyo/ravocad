package com.ravocad.diagram.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Display;
import com.ravocad.notation.View;

public class CreationTool extends org.eclipse.gef.tools.CreationTool {
	
	/**
	 * the anti scroll flag
	 */
	protected boolean antiScroll = false;
	
	/** Constant used to specify no point has been defined for the shape creation
	 * request.  It will be up to the layout edit policy to lay the shape in 
	 * the correct position*/
	private static final Point UNDEFINED_POINT = new Point(-1, -1);

	/**
	 * Since both the view and semantic requests contain results we need to free
	 * them when the tool is deactivated
	 */
	public void deactivate() {
		super.deactivate();
		setTargetRequest(null);
	}

	protected void performCreation(int button) {	
		antiScroll = true;	
		super.performCreation(button);
		antiScroll = false;
	}

	
	/**
	 * Select the newly added shape view by default
	 * @param viewer
	 * @param objects
	 */
	protected void selectAddedObject(EditPartViewer viewer, Collection<?> objects) {
		final List<Object> editparts = new ArrayList<Object>();
		for (Iterator<?> i = objects.iterator(); i.hasNext();) {
			Object object = i.next();
			if (object instanceof IAdaptable) {
				Object editPart =
					viewer.getEditPartRegistry().get(((IAdaptable)object).getAdapter(View.class));
				if (editPart != null)
					editparts.add(editPart);
			}
		}

		if (!editparts.isEmpty()) {
			viewer.setSelection(new StructuredSelection(editparts));
		
			// automatically put the first shape into edit-mode
			Display.getCurrent().asyncExec(new Runnable() {
				public void run(){
					EditPart editPart = (EditPart) editparts.get(0);
					//
					// add active test since test scripts are failing on this
					// basically, the editpart has been deleted when this 
					// code is being executed. (see RATLC00527114)
					if ( editPart.isActive() ) {
						editPart.performRequest(new Request(RequestConstants.REQ_DIRECT_EDIT));
						revealEditPart((EditPart)editparts.get(0));
					}
				}
			});
		}
	}

	/**
	*  Handles double click to create the shape in defualt position
	*/
	protected boolean handleDoubleClick(int button) {
		createShapeAt(UNDEFINED_POINT);
		setState(STATE_TERMINAL);
		handleFinished();
		
		return true;
	}
	
	/**
	 * Create the shape corresponding to the current selected tool
	 * on the current diagram at the <code>point</code> specified
	 * @param point to create shape at
	 */
	protected void createShapeAt(Point point) {
		setTargetEditPart(getCurrentViewer().getRootEditPart().getContents());
		getCreateRequest().setLocation(point);
		setCurrentCommand(getCommand());
		performCreation(0);
	}

	/*  Overide to handle use case when the user has selected an tool
	 * and then click on the enter key which translated to SWT.Selection
	 * it will result in the new shape being created
	 * @see org.eclipse.gef.tools.AbstractTool#handleKeyUp(org.eclipse.swt.events.KeyEvent)
	 */
	protected boolean handleKeyUp(KeyEvent e) {
		if (e.keyCode==SWT.Selection){
			setEditDomain(getCurrentViewer().getEditDomain());
			createShapeAt(UNDEFINED_POINT);
			setState(STATE_TERMINAL);
			handleFinished();
			return true;
		}
		return false;
	}

	protected void setTargetEditPart(EditPart editpart) {
		// Set the target request to null if the target editpart has changed.
		// This needs to be done so that the context element (if applicable) in
		// the request can be reset.
		if (editpart != getTargetEditPart()) {
			setTargetRequest(null);
		}
		super.setTargetEditPart(editpart);
	}
	
	/**
	 * Overridden so that the current tool will remain active (locked) if the
	 * user is pressing the ctrl key on windows/linux or the cmd key on mac.
	 */
	protected void handleFinished() {
		if (!getCurrentInput().isModKeyDown(SWT.MOD1)) {
			super.handleFinished();
		} else {
			reactivate();
		}
	}
		
	@Override
	protected boolean handleMove() {
		if (!antiScroll) {
			return super.handleMove();
		}
		return false;
	}
	
	@Override
	protected void doAutoexpose() {
		if (!antiScroll)
			super.doAutoexpose();
		return;
	}
	
	@Override
	protected Command getCommand() {	
		if (!antiScroll) {
			return super.getCommand();
		}
		return null;
	}
	
	/**
	 * Reveals the newly created editpart
	 * @param editPart
	 */
	protected void revealEditPart(EditPart editPart){
		if ((editPart != null)&& (editPart.getViewer() != null)) {
			editPart.getViewer().reveal(editPart);
		}
	}

	/**
	 * Creates the request.
	 * 
	 * @see #createTargetRequest()
	 */
	public final Request createCreateRequest() {
		return createTargetRequest();
	}
	/**
	 * Selects the new shapes and puts them in direct edit mode if desired.
	 * 
	 * @param viewer
	 *            the editpart viewer
	 * @param objects
	 *            the collection of new shapes
	 * @see #selectAddedObject(EditPartViewer, Collection)
	 */
	public final void selectNewShapes(EditPartViewer viewer, Collection<?> objects) {
		selectAddedObject(viewer, objects);
	}

}
