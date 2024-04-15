package com.ravocad.diagram.edit.parts;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.ravocad.diagram.edit.policies.ViewComponentPolicy;
import com.ravocad.notation.View;

public abstract class ViewEditPart extends AbstractGraphicalEditPart implements Adapter {

	private AccessibleEditPart acc;

	private Notifier target;
	
	/**
	 * Upon activation, attach to the model element as a property change listener.
	 */
	public void activate() {
		if (!isActive()) {
			hookIntoModel(getView());
			super.activate();
		}
	}
	
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ViewComponentPolicy());	
	}

	protected AccessibleEditPart createAccessible() {
		return new AccessibleGraphicalEditPart(){
			public void getName(AccessibleEvent e) {
				e.result = getView().toString();
			}
		};
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object getAdapter(Class key) {
		if (getView() instanceof IAdaptable)
			return ((IAdaptable)getView()).getAdapter(key);
		
		return super.getAdapter(key);
	}
	
	protected ResourceManager getResourceManager() {
        return JFaceResources.getResources();
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

	/**
	 * Makes the EditPart insensible to changes in the model by removing itself from
	 * the model's list of listeners.
	 */
	public void deactivate() {
		if (!isActive())
			return;
		unhookFromModel(getView());
		super.deactivate();
	}

	protected AccessibleEditPart getAccessibleEditPart() {
		if (acc == null)
			acc = createAccessible();
		return acc;
	}

	/**
	 * Returns the model associated with this as a LogicSubPart.
	 *
	 * @return The model of this as a LogicSubPart.
	 */
	protected View getView() {
		return (View) getModel();
	}

	private void hookIntoModel(EObject model) {
		if(model != null) {
			model.eAdapters().add(this);
		}
	}
	
	private void unhookFromModel(EObject model) {
		if(model != null) {
			model.eAdapters().remove(this);
		}
	}
	
	@Override
	public void notifyChanged(Notification notification) {

	}

	@Override
	public Notifier getTarget() {
		return target;
	}

	@Override
	public void setTarget(Notifier newTarget) {
		target = newTarget;
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return (getModel().getClass() == type);
	}
}
