/**
 */
package com.ravocad.notation.provider;


import com.ravocad.notation.Diagram;
import com.ravocad.notation.NotationPackage;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link com.ravocad.notation.Diagram} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DiagramItemProvider 
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DiagramItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addGridVisiblePropertyDescriptor(object);
			addGridColorPropertyDescriptor(object);
			addGridSpacingPropertyDescriptor(object);
			addGridUnitPropertyDescriptor(object);
			addSnapToGridPropertyDescriptor(object);
			addSnapToGeometryPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Grid Visible feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addGridVisiblePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Diagram_gridVisible_feature"),
				 getString("_UI_Diagram_gridVisible_description"),
				 NotationPackage.Literals.DIAGRAM__GRID_VISIBLE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 getString("_UI_GridPropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the Grid Color feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addGridColorPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Diagram_gridColor_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Diagram_gridColor_feature", "_UI_Diagram_type"),
				 NotationPackage.Literals.DIAGRAM__GRID_COLOR,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 getString("_UI_GridPropertyCategory"),
				 null,
				 URI.createURI("editor://com.ravocad.color/TOOL|MAX|RESIZE")));
	}

	/**
	 * This adds a property descriptor for the Grid Spacing feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addGridSpacingPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Diagram_gridSpacing_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Diagram_gridSpacing_feature", "_UI_Diagram_type"),
				 NotationPackage.Literals.DIAGRAM__GRID_SPACING,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 getString("_UI_GridPropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the Grid Unit feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addGridUnitPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Diagram_gridUnit_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Diagram_gridUnit_feature", "_UI_Diagram_type"),
				 NotationPackage.Literals.DIAGRAM__GRID_UNIT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 getString("_UI_GridPropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the Snap To Grid feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSnapToGridPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Diagram_snapToGrid_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Diagram_snapToGrid_feature", "_UI_Diagram_type"),
				 NotationPackage.Literals.DIAGRAM__SNAP_TO_GRID,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 getString("_UI_SnapHelperPropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the Snap To Geometry feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSnapToGeometryPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Diagram_snapToGeometry_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Diagram_snapToGeometry_feature", "_UI_Diagram_type"),
				 NotationPackage.Literals.DIAGRAM__SNAP_TO_GEOMETRY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 getString("_UI_SnapHelperPropertyCategory"),
				 null));
	}

	/**
	 * This returns Diagram.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Diagram"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		Diagram diagram = (Diagram)object;
		return getString("_UI_Diagram_type") + " " + diagram.isGridVisible();
	}


	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Diagram.class)) {
			case NotationPackage.DIAGRAM__GRID_VISIBLE:
			case NotationPackage.DIAGRAM__GRID_COLOR:
			case NotationPackage.DIAGRAM__GRID_SPACING:
			case NotationPackage.DIAGRAM__GRID_UNIT:
			case NotationPackage.DIAGRAM__SNAP_TO_GRID:
			case NotationPackage.DIAGRAM__SNAP_TO_GEOMETRY:
			case NotationPackage.DIAGRAM__ZOOM:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return NotationEditPlugin.INSTANCE;
	}

}
