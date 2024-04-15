/**
 */
package com.ravocad.notation;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.RGB;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Diagram</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.ravocad.notation.Diagram#getView <em>View</em>}</li>
 *   <li>{@link com.ravocad.notation.Diagram#isGridVisible <em>Grid Visible</em>}</li>
 *   <li>{@link com.ravocad.notation.Diagram#getGridColor <em>Grid Color</em>}</li>
 *   <li>{@link com.ravocad.notation.Diagram#getGridSpacing <em>Grid Spacing</em>}</li>
 *   <li>{@link com.ravocad.notation.Diagram#getGridUnit <em>Grid Unit</em>}</li>
 *   <li>{@link com.ravocad.notation.Diagram#isSnapToGrid <em>Snap To Grid</em>}</li>
 *   <li>{@link com.ravocad.notation.Diagram#isSnapToGeometry <em>Snap To Geometry</em>}</li>
 *   <li>{@link com.ravocad.notation.Diagram#getZoom <em>Zoom</em>}</li>
 * </ul>
 *
 * @see com.ravocad.notation.NotationPackage#getDiagram()
 * @model
 * @generated
 */
public interface Diagram extends EObject {
	/**
	 * Returns the value of the '<em><b>Zoom</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Zoom</em>' attribute.
	 * @see #setZoom(double)
	 * @see com.ravocad.notation.NotationPackage#getDiagram_Zoom()
	 * @model default="1.0"
	 * @generated
	 */
	double getZoom();

	/**
	 * Sets the value of the '{@link com.ravocad.notation.Diagram#getZoom <em>Zoom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Zoom</em>' attribute.
	 * @see #getZoom()
	 * @generated
	 */
	void setZoom(double value);

	/**
	 * Returns the value of the '<em><b>View</b></em>' containment reference list.
	 * The list contents are of type {@link com.ravocad.notation.View}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>View</em>' containment reference list.
	 * @see com.ravocad.notation.NotationPackage#getDiagram_View()
	 * @model containment="true"
	 * @generated
	 */
	EList<View> getView();

	/**
	 * Returns the value of the '<em><b>Grid Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grid Visible</em>' attribute.
	 * @see #setGridVisible(boolean)
	 * @see com.ravocad.notation.NotationPackage#getDiagram_GridVisible()
	 * @model
	 * @generated
	 */
	boolean isGridVisible();

	/**
	 * Sets the value of the '{@link com.ravocad.notation.Diagram#isGridVisible <em>Grid Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grid Visible</em>' attribute.
	 * @see #isGridVisible()
	 * @generated
	 */
	void setGridVisible(boolean value);

	/**
	 * Returns the value of the '<em><b>Grid Color</b></em>' attribute.
	 * The default value is <code>"211,211,211"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grid Color</em>' attribute.
	 * @see #setGridColor(RGB)
	 * @see com.ravocad.notation.NotationPackage#getDiagram_GridColor()
	 * @model default="211,211,211" dataType="com.ravocad.notation.RGB"
	 * @generated
	 */
	RGB getGridColor();

	/**
	 * Sets the value of the '{@link com.ravocad.notation.Diagram#getGridColor <em>Grid Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grid Color</em>' attribute.
	 * @see #getGridColor()
	 * @generated
	 */
	void setGridColor(RGB value);

	/**
	 * Returns the value of the '<em><b>Grid Spacing</b></em>' attribute.
	 * The default value is <code>"10"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grid Spacing</em>' attribute.
	 * @see #setGridSpacing(double)
	 * @see com.ravocad.notation.NotationPackage#getDiagram_GridSpacing()
	 * @model default="10"
	 * @generated
	 */
	double getGridSpacing();

	/**
	 * Sets the value of the '{@link com.ravocad.notation.Diagram#getGridSpacing <em>Grid Spacing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grid Spacing</em>' attribute.
	 * @see #getGridSpacing()
	 * @generated
	 */
	void setGridSpacing(double value);

	/**
	 * Returns the value of the '<em><b>Grid Unit</b></em>' attribute.
	 * The default value is <code>"UNIT_PIXELS"</code>.
	 * The literals are from the enumeration {@link com.ravocad.notation.GridUnit}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grid Unit</em>' attribute.
	 * @see com.ravocad.notation.GridUnit
	 * @see #setGridUnit(GridUnit)
	 * @see com.ravocad.notation.NotationPackage#getDiagram_GridUnit()
	 * @model default="UNIT_PIXELS"
	 * @generated
	 */
	GridUnit getGridUnit();

	/**
	 * Sets the value of the '{@link com.ravocad.notation.Diagram#getGridUnit <em>Grid Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grid Unit</em>' attribute.
	 * @see com.ravocad.notation.GridUnit
	 * @see #getGridUnit()
	 * @generated
	 */
	void setGridUnit(GridUnit value);

	/**
	 * Returns the value of the '<em><b>Snap To Grid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Snap To Grid</em>' attribute.
	 * @see #setSnapToGrid(boolean)
	 * @see com.ravocad.notation.NotationPackage#getDiagram_SnapToGrid()
	 * @model
	 * @generated
	 */
	boolean isSnapToGrid();

	/**
	 * Sets the value of the '{@link com.ravocad.notation.Diagram#isSnapToGrid <em>Snap To Grid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Snap To Grid</em>' attribute.
	 * @see #isSnapToGrid()
	 * @generated
	 */
	void setSnapToGrid(boolean value);

	/**
	 * Returns the value of the '<em><b>Snap To Geometry</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Snap To Geometry</em>' attribute.
	 * @see #setSnapToGeometry(boolean)
	 * @see com.ravocad.notation.NotationPackage#getDiagram_SnapToGeometry()
	 * @model
	 * @generated
	 */
	boolean isSnapToGeometry();

	/**
	 * Sets the value of the '{@link com.ravocad.notation.Diagram#isSnapToGeometry <em>Snap To Geometry</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Snap To Geometry</em>' attribute.
	 * @see #isSnapToGeometry()
	 * @generated
	 */
	void setSnapToGeometry(boolean value);

} // Diagram
