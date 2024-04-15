package com.ravocad.diagram.edit.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Animation;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.CompoundSnapToHelper;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToGuides;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editparts.GridLayer;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;
import org.eclipse.gef.editpolicies.SnapFeedbackPolicy;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.gef.tools.DeselectAllTracker;
import org.eclipse.gef.tools.MarqueeDragTracker;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.graphics.Color;

import com.ravocad.diagram.edit.policies.DiagramLayoutEditPolicy;
import com.ravocad.diagram.i10n.Messages;
import com.ravocad.diagram.util.DisplayUtil;
import com.ravocad.notation.Diagram;
import com.ravocad.notation.GridUnit;
import com.ravocad.notation.NotationPackage;
import com.ravocad.notation.View;

/**
 * Holds all other GraphicalElementParts under this. It is activated by
 * It is sort of a blank board where all other EditParts get added.
 */
public class DiagramEditPart extends AbstractGraphicalEditPart implements Adapter, LayerConstants {

	private AccessibleEditPart acc;
	
	private Notifier target;
	
	/**
	 * Upon activation, attach to the model element as a property change listener.
	 */
	public void activate() {
		if (!isActive()) {
			hookIntoModel(getDiagram());
			super.activate();
		}
	}
	
	protected AccessibleEditPart createAccessible() {
		return new AccessibleGraphicalEditPart() {
			public void getName(AccessibleEvent e) {
				e.result = Messages.getString("Diagram_LabelText");
			}
		};
	}
	
	/**
	 * Installs EditPolicies specific to this.
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new DiagramLayoutEditPolicy((XYLayout)getContentPane().getLayoutManager()));
		installEditPolicy("Snap Feedback", new SnapFeedbackPolicy());
	}

	/**
	 * Returns a Figure to represent this.
	 *
	 * @return Figure.
	 */
	protected IFigure createFigure() {
		Figure f = new FreeformLayer();
		f.setLayoutManager(new FreeformLayout());
		f.setBorder(new MarginBorder(5));
		return f;
	}

	/**
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object getAdapter(Class adapter) {
		if (adapter == SnapToHelper.class) {
			List<SnapToHelper> snapStrategies = new ArrayList<SnapToHelper>();
			Boolean val = (Boolean) getViewer().getProperty(RulerProvider.PROPERTY_RULER_VISIBILITY);
			if (val != null && val.booleanValue())
				snapStrategies.add(new SnapToGuides(this));
			val = (Boolean) getViewer().getProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED);
			if (val != null && val.booleanValue())
				snapStrategies.add(new SnapToGeometry(this));
			val = (Boolean) getViewer().getProperty(SnapToGrid.PROPERTY_GRID_ENABLED);
			if (val != null && val.booleanValue())
				snapStrategies.add(new SnapToGrid(this));

			if (snapStrategies.size() == 0)
				return null;
			if (snapStrategies.size() == 1)
				return snapStrategies.get(0);

			SnapToHelper ss[] = new SnapToHelper[snapStrategies.size()];
			for (int i = 0; i < snapStrategies.size(); i++)
				ss[i] = snapStrategies.get(i);
			return new CompoundSnapToHelper(ss);
		}
		return super.getAdapter(adapter);
	}

	public DragTracker getDragTracker(Request req) {
		if (req instanceof SelectionRequest && ((SelectionRequest) req).getLastButtonPressed() == 3)
			return new DeselectAllTracker(this);
		return new MarqueeDragTracker();
	}
	
	public void deactivate() {
		if (!isActive())
			return;
		unhookFromModel(getDiagram());
		super.deactivate();
	}

	protected AccessibleEditPart getAccessibleEditPart() {
		if (acc == null)
			acc = createAccessible();
		return acc;
	}

	
	/**
	 * Returns the children of this through the model.
	 *
	 * @return Children of this as a List.
	 */
	protected List<View> getModelChildren() {
		return getDiagram().getView();
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
		Object feature = notification.getFeature();
		if (NotationPackage.eINSTANCE.getDiagram_View().equals(feature)) {	
			refreshChildren();
		} else if (NotationPackage.eINSTANCE.getDiagram_GridVisible().equals(feature)) {
			refreshGridVisibility();			
		} else if (NotationPackage.eINSTANCE.getDiagram_GridColor().equals(feature)) {
			refreshGridColor();			
		} else if (NotationPackage.eINSTANCE.getDiagram_GridSpacing().equals(feature)) {
			refreshGridSpacing();			
		} else if (NotationPackage.eINSTANCE.getDiagram_GridUnit().equals(feature)) {
			refreshGridUnit();			
		} else if (NotationPackage.eINSTANCE.getDiagram_SnapToGrid().equals(feature)) {
			refreshGridSnap();			
		} else if (NotationPackage.eINSTANCE.getDiagram_SnapToGeometry().equals(feature)) {
			refreshGeometrySnap();			
		}
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
	
	protected void refreshVisuals() {
		refreshGridColor();
		refreshGridSpacing();
		
		Animation.markBegin();
	}
	
	protected Diagram getDiagram() {
		return (Diagram) getModel();
	}
	
	protected void refreshGridVisibility() {
		getViewer().setProperty(SnapToGrid.PROPERTY_GRID_VISIBLE, getDiagram().isGridVisible());	
	}
	
	protected void refreshGridColor() {
		if(getDiagram().getGridColor() != null) {
			GridLayer gridLayer = (GridLayer) getLayer(LayerConstants.GRID_LAYER);
			gridLayer.setForegroundColor(new Color(getDiagram().getGridColor()));
		}
	}
	
	protected void refreshGridSpacing() {
		double gridSpacing = getDiagram().getGridSpacing();
		int gridUnits = getDiagram().getGridUnit().getValue();
		// Get the Displays DPIs
		double dotsPerInch = DisplayUtil.getDisplay().getDPI().x;
		int spacingInPixels = 0;

		// Evaluate the Grid Spacing based on the ruler units
		switch(gridUnits) {
			case GridUnit.UNIT_INCHES_VALUE:
				spacingInPixels = (int)Math.round(dotsPerInch * gridSpacing);
				break;
			case GridUnit.UNIT_CENTIMETERS_VALUE:
				spacingInPixels = (int)Math.round( dotsPerInch * gridSpacing / 2.54 );
				break;
			default:
				spacingInPixels = (int)gridSpacing;
		}
		getViewer().setProperty(SnapToGrid.PROPERTY_GRID_SPACING, new Dimension(spacingInPixels, spacingInPixels));
	}

	protected void refreshGridUnit() {
		refreshGridSpacing();
	}

	protected void refreshGridSnap() {
		getViewer().setProperty(SnapToGrid.PROPERTY_GRID_ENABLED, getDiagram().isSnapToGrid());
	}

	protected void refreshGeometrySnap() {
		getViewer().setProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED, getDiagram().isSnapToGrid());		
	}

}
