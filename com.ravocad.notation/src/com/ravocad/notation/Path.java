/**
 */
package com.ravocad.notation;

import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.swt.graphics.PathData;
import org.eclipse.swt.graphics.RGB;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Path</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.ravocad.notation.Path#getData <em>Data</em>}</li>
 *   <li>{@link com.ravocad.notation.Path#getHandles <em>Handles</em>}</li>
 *   <li>{@link com.ravocad.notation.Path#getLineColor <em>Line Color</em>}</li>
 *   <li>{@link com.ravocad.notation.Path#getLineWidth <em>Line Width</em>}</li>
 *   <li>{@link com.ravocad.notation.Path#isFill <em>Fill</em>}</li>
 *   <li>{@link com.ravocad.notation.Path#getFillColor <em>Fill Color</em>}</li>
 *   <li>{@link com.ravocad.notation.Path#getAlpha <em>Alpha</em>}</li>
 * </ul>
 *
 * @see com.ravocad.notation.NotationPackage#getPath()
 * @model
 * @generated
 */
public interface Path extends View {
	/**
	 * Returns the value of the '<em><b>Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data</em>' attribute.
	 * @see #setData(PathData)
	 * @see com.ravocad.notation.NotationPackage#getPath_Data()
	 * @model dataType="com.ravocad.notation.PathData"
	 * @generated
	 */
	PathData getData();

	/**
	 * Sets the value of the '{@link com.ravocad.notation.Path#getData <em>Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data</em>' attribute.
	 * @see #getData()
	 * @generated
	 */
	void setData(PathData value);

	/**
	 * Returns the value of the '<em><b>Handles</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Handles</em>' attribute.
	 * @see #setHandles(PointList)
	 * @see com.ravocad.notation.NotationPackage#getPath_Handles()
	 * @model dataType="com.ravocad.notation.PointList"
	 * @generated
	 */
	PointList getHandles();

	/**
	 * Sets the value of the '{@link com.ravocad.notation.Path#getHandles <em>Handles</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Handles</em>' attribute.
	 * @see #getHandles()
	 * @generated
	 */
	void setHandles(PointList value);

	/**
	 * Returns the value of the '<em><b>Line Color</b></em>' attribute.
	 * The default value is <code>"0,0,0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Line Color</em>' attribute.
	 * @see #setLineColor(RGB)
	 * @see com.ravocad.notation.NotationPackage#getPath_LineColor()
	 * @model default="0,0,0" dataType="com.ravocad.notation.RGB"
	 * @generated
	 */
	RGB getLineColor();

	/**
	 * Sets the value of the '{@link com.ravocad.notation.Path#getLineColor <em>Line Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Line Color</em>' attribute.
	 * @see #getLineColor()
	 * @generated
	 */
	void setLineColor(RGB value);

	/**
	 * Returns the value of the '<em><b>Fill Color</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fill Color</em>' attribute.
	 * @see #setFillColor(RGB)
	 * @see com.ravocad.notation.NotationPackage#getPath_FillColor()
	 * @model default="" dataType="com.ravocad.notation.RGB"
	 * @generated
	 */
	RGB getFillColor();

	/**
	 * Sets the value of the '{@link com.ravocad.notation.Path#getFillColor <em>Fill Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fill Color</em>' attribute.
	 * @see #getFillColor()
	 * @generated
	 */
	void setFillColor(RGB value);

	/**
	 * Returns the value of the '<em><b>Alpha</b></em>' attribute.
	 * The default value is <code>"255"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Alpha</em>' attribute.
	 * @see #setAlpha(int)
	 * @see com.ravocad.notation.NotationPackage#getPath_Alpha()
	 * @model default="255"
	 * @generated
	 */
	int getAlpha();

	/**
	 * Sets the value of the '{@link com.ravocad.notation.Path#getAlpha <em>Alpha</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Alpha</em>' attribute.
	 * @see #getAlpha()
	 * @generated
	 */
	void setAlpha(int value);

	/**
	 * Returns the value of the '<em><b>Line Width</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Line Width</em>' attribute.
	 * @see #setLineWidth(int)
	 * @see com.ravocad.notation.NotationPackage#getPath_LineWidth()
	 * @model default="1"
	 * @generated
	 */
	int getLineWidth();

	/**
	 * Sets the value of the '{@link com.ravocad.notation.Path#getLineWidth <em>Line Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Line Width</em>' attribute.
	 * @see #getLineWidth()
	 * @generated
	 */
	void setLineWidth(int value);

	/**
	 * Returns the value of the '<em><b>Fill</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fill</em>' attribute.
	 * @see #setFill(boolean)
	 * @see com.ravocad.notation.NotationPackage#getPath_Fill()
	 * @model
	 * @generated
	 */
	boolean isFill();

	/**
	 * Sets the value of the '{@link com.ravocad.notation.Path#isFill <em>Fill</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fill</em>' attribute.
	 * @see #isFill()
	 * @generated
	 */
	void setFill(boolean value);

} // Path
