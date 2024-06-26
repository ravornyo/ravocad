/**
 */
package com.ravocad.notation.impl;

import com.ravocad.notation.Diagram;
import com.ravocad.notation.GridUnit;
import com.ravocad.notation.NotationFactory;
import com.ravocad.notation.NotationPackage;
import com.ravocad.notation.View;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.swt.graphics.RGB;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Diagram</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.ravocad.notation.impl.DiagramImpl#getView <em>View</em>}</li>
 *   <li>{@link com.ravocad.notation.impl.DiagramImpl#isGridVisible <em>Grid Visible</em>}</li>
 *   <li>{@link com.ravocad.notation.impl.DiagramImpl#getGridColor <em>Grid Color</em>}</li>
 *   <li>{@link com.ravocad.notation.impl.DiagramImpl#getGridSpacing <em>Grid Spacing</em>}</li>
 *   <li>{@link com.ravocad.notation.impl.DiagramImpl#getGridUnit <em>Grid Unit</em>}</li>
 *   <li>{@link com.ravocad.notation.impl.DiagramImpl#isSnapToGrid <em>Snap To Grid</em>}</li>
 *   <li>{@link com.ravocad.notation.impl.DiagramImpl#isSnapToGeometry <em>Snap To Geometry</em>}</li>
 *   <li>{@link com.ravocad.notation.impl.DiagramImpl#getZoom <em>Zoom</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DiagramImpl extends MinimalEObjectImpl.Container implements Diagram {
	/**
	 * The cached value of the '{@link #getView() <em>View</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getView()
	 * @generated
	 * @ordered
	 */
	protected EList<View> view;

	/**
	 * The default value of the '{@link #isGridVisible() <em>Grid Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isGridVisible()
	 * @generated
	 * @ordered
	 */
	protected static final boolean GRID_VISIBLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isGridVisible() <em>Grid Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isGridVisible()
	 * @generated
	 * @ordered
	 */
	protected boolean gridVisible = GRID_VISIBLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getGridColor() <em>Grid Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGridColor()
	 * @generated
	 * @ordered
	 */
	protected static final RGB GRID_COLOR_EDEFAULT = (RGB)NotationFactory.eINSTANCE.createFromString(NotationPackage.eINSTANCE.getRGB(), "211,211,211");

	/**
	 * The cached value of the '{@link #getGridColor() <em>Grid Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGridColor()
	 * @generated
	 * @ordered
	 */
	protected RGB gridColor = GRID_COLOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getGridSpacing() <em>Grid Spacing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGridSpacing()
	 * @generated
	 * @ordered
	 */
	protected static final double GRID_SPACING_EDEFAULT = 10.0;

	/**
	 * The cached value of the '{@link #getGridSpacing() <em>Grid Spacing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGridSpacing()
	 * @generated
	 * @ordered
	 */
	protected double gridSpacing = GRID_SPACING_EDEFAULT;

	/**
	 * The default value of the '{@link #getGridUnit() <em>Grid Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGridUnit()
	 * @generated
	 * @ordered
	 */
	protected static final GridUnit GRID_UNIT_EDEFAULT = GridUnit.UNIT_PIXELS;

	/**
	 * The cached value of the '{@link #getGridUnit() <em>Grid Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGridUnit()
	 * @generated
	 * @ordered
	 */
	protected GridUnit gridUnit = GRID_UNIT_EDEFAULT;

	/**
	 * The default value of the '{@link #isSnapToGrid() <em>Snap To Grid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSnapToGrid()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SNAP_TO_GRID_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSnapToGrid() <em>Snap To Grid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSnapToGrid()
	 * @generated
	 * @ordered
	 */
	protected boolean snapToGrid = SNAP_TO_GRID_EDEFAULT;

	/**
	 * The default value of the '{@link #isSnapToGeometry() <em>Snap To Geometry</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSnapToGeometry()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SNAP_TO_GEOMETRY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSnapToGeometry() <em>Snap To Geometry</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSnapToGeometry()
	 * @generated
	 * @ordered
	 */
	protected boolean snapToGeometry = SNAP_TO_GEOMETRY_EDEFAULT;

	/**
	 * The default value of the '{@link #getZoom() <em>Zoom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getZoom()
	 * @generated
	 * @ordered
	 */
	protected static final double ZOOM_EDEFAULT = 1.0;

	/**
	 * The cached value of the '{@link #getZoom() <em>Zoom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getZoom()
	 * @generated
	 * @ordered
	 */
	protected double zoom = ZOOM_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DiagramImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NotationPackage.Literals.DIAGRAM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getZoom() {
		return zoom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setZoom(double newZoom) {
		double oldZoom = zoom;
		zoom = newZoom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NotationPackage.DIAGRAM__ZOOM, oldZoom, zoom));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<View> getView() {
		if (view == null) {
			view = new EObjectContainmentEList<View>(View.class, this, NotationPackage.DIAGRAM__VIEW);
		}
		return view;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isGridVisible() {
		return gridVisible;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGridVisible(boolean newGridVisible) {
		boolean oldGridVisible = gridVisible;
		gridVisible = newGridVisible;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NotationPackage.DIAGRAM__GRID_VISIBLE, oldGridVisible, gridVisible));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RGB getGridColor() {
		return gridColor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGridColor(RGB newGridColor) {
		RGB oldGridColor = gridColor;
		gridColor = newGridColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NotationPackage.DIAGRAM__GRID_COLOR, oldGridColor, gridColor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getGridSpacing() {
		return gridSpacing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGridSpacing(double newGridSpacing) {
		double oldGridSpacing = gridSpacing;
		gridSpacing = newGridSpacing;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NotationPackage.DIAGRAM__GRID_SPACING, oldGridSpacing, gridSpacing));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GridUnit getGridUnit() {
		return gridUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGridUnit(GridUnit newGridUnit) {
		GridUnit oldGridUnit = gridUnit;
		gridUnit = newGridUnit == null ? GRID_UNIT_EDEFAULT : newGridUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NotationPackage.DIAGRAM__GRID_UNIT, oldGridUnit, gridUnit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSnapToGrid() {
		return snapToGrid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSnapToGrid(boolean newSnapToGrid) {
		boolean oldSnapToGrid = snapToGrid;
		snapToGrid = newSnapToGrid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NotationPackage.DIAGRAM__SNAP_TO_GRID, oldSnapToGrid, snapToGrid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSnapToGeometry() {
		return snapToGeometry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSnapToGeometry(boolean newSnapToGeometry) {
		boolean oldSnapToGeometry = snapToGeometry;
		snapToGeometry = newSnapToGeometry;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NotationPackage.DIAGRAM__SNAP_TO_GEOMETRY, oldSnapToGeometry, snapToGeometry));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case NotationPackage.DIAGRAM__VIEW:
				return ((InternalEList<?>)getView()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NotationPackage.DIAGRAM__VIEW:
				return getView();
			case NotationPackage.DIAGRAM__GRID_VISIBLE:
				return isGridVisible();
			case NotationPackage.DIAGRAM__GRID_COLOR:
				return getGridColor();
			case NotationPackage.DIAGRAM__GRID_SPACING:
				return getGridSpacing();
			case NotationPackage.DIAGRAM__GRID_UNIT:
				return getGridUnit();
			case NotationPackage.DIAGRAM__SNAP_TO_GRID:
				return isSnapToGrid();
			case NotationPackage.DIAGRAM__SNAP_TO_GEOMETRY:
				return isSnapToGeometry();
			case NotationPackage.DIAGRAM__ZOOM:
				return getZoom();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case NotationPackage.DIAGRAM__VIEW:
				getView().clear();
				getView().addAll((Collection<? extends View>)newValue);
				return;
			case NotationPackage.DIAGRAM__GRID_VISIBLE:
				setGridVisible((Boolean)newValue);
				return;
			case NotationPackage.DIAGRAM__GRID_COLOR:
				setGridColor((RGB)newValue);
				return;
			case NotationPackage.DIAGRAM__GRID_SPACING:
				setGridSpacing((Double)newValue);
				return;
			case NotationPackage.DIAGRAM__GRID_UNIT:
				setGridUnit((GridUnit)newValue);
				return;
			case NotationPackage.DIAGRAM__SNAP_TO_GRID:
				setSnapToGrid((Boolean)newValue);
				return;
			case NotationPackage.DIAGRAM__SNAP_TO_GEOMETRY:
				setSnapToGeometry((Boolean)newValue);
				return;
			case NotationPackage.DIAGRAM__ZOOM:
				setZoom((Double)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case NotationPackage.DIAGRAM__VIEW:
				getView().clear();
				return;
			case NotationPackage.DIAGRAM__GRID_VISIBLE:
				setGridVisible(GRID_VISIBLE_EDEFAULT);
				return;
			case NotationPackage.DIAGRAM__GRID_COLOR:
				setGridColor(GRID_COLOR_EDEFAULT);
				return;
			case NotationPackage.DIAGRAM__GRID_SPACING:
				setGridSpacing(GRID_SPACING_EDEFAULT);
				return;
			case NotationPackage.DIAGRAM__GRID_UNIT:
				setGridUnit(GRID_UNIT_EDEFAULT);
				return;
			case NotationPackage.DIAGRAM__SNAP_TO_GRID:
				setSnapToGrid(SNAP_TO_GRID_EDEFAULT);
				return;
			case NotationPackage.DIAGRAM__SNAP_TO_GEOMETRY:
				setSnapToGeometry(SNAP_TO_GEOMETRY_EDEFAULT);
				return;
			case NotationPackage.DIAGRAM__ZOOM:
				setZoom(ZOOM_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case NotationPackage.DIAGRAM__VIEW:
				return view != null && !view.isEmpty();
			case NotationPackage.DIAGRAM__GRID_VISIBLE:
				return gridVisible != GRID_VISIBLE_EDEFAULT;
			case NotationPackage.DIAGRAM__GRID_COLOR:
				return GRID_COLOR_EDEFAULT == null ? gridColor != null : !GRID_COLOR_EDEFAULT.equals(gridColor);
			case NotationPackage.DIAGRAM__GRID_SPACING:
				return gridSpacing != GRID_SPACING_EDEFAULT;
			case NotationPackage.DIAGRAM__GRID_UNIT:
				return gridUnit != GRID_UNIT_EDEFAULT;
			case NotationPackage.DIAGRAM__SNAP_TO_GRID:
				return snapToGrid != SNAP_TO_GRID_EDEFAULT;
			case NotationPackage.DIAGRAM__SNAP_TO_GEOMETRY:
				return snapToGeometry != SNAP_TO_GEOMETRY_EDEFAULT;
			case NotationPackage.DIAGRAM__ZOOM:
				return zoom != ZOOM_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (gridVisible: ");
		result.append(gridVisible);
		result.append(", gridColor: ");
		result.append(gridColor);
		result.append(", gridSpacing: ");
		result.append(gridSpacing);
		result.append(", gridUnit: ");
		result.append(gridUnit);
		result.append(", snapToGrid: ");
		result.append(snapToGrid);
		result.append(", snapToGeometry: ");
		result.append(snapToGeometry);
		result.append(", zoom: ");
		result.append(zoom);
		result.append(')');
		return result.toString();
	}

} //DiagramImpl
