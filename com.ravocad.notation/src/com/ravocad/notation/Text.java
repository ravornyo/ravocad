/**
 */
package com.ravocad.notation;

import org.eclipse.draw2d.geometry.Point;

import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Text</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.ravocad.notation.Text#getContent <em>Content</em>}</li>
 *   <li>{@link com.ravocad.notation.Text#getLocation <em>Location</em>}</li>
 *   <li>{@link com.ravocad.notation.Text#getFont <em>Font</em>}</li>
 *   <li>{@link com.ravocad.notation.Text#getColor <em>Color</em>}</li>
 * </ul>
 *
 * @see com.ravocad.notation.NotationPackage#getText()
 * @model
 * @generated
 */
public interface Text extends View {
	/**
	 * Returns the value of the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Content</em>' attribute.
	 * @see #setContent(String)
	 * @see com.ravocad.notation.NotationPackage#getText_Content()
	 * @model
	 * @generated
	 */
	String getContent();

	/**
	 * Sets the value of the '{@link com.ravocad.notation.Text#getContent <em>Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Content</em>' attribute.
	 * @see #getContent()
	 * @generated
	 */
	void setContent(String value);

	/**
	 * Returns the value of the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Location</em>' attribute.
	 * @see #setLocation(Point)
	 * @see com.ravocad.notation.NotationPackage#getText_Location()
	 * @model dataType="com.ravocad.notation.Point"
	 * @generated
	 */
	Point getLocation();

	/**
	 * Sets the value of the '{@link com.ravocad.notation.Text#getLocation <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' attribute.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(Point value);

	/**
	 * Returns the value of the '<em><b>Font</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Font</em>' attribute.
	 * @see #setFont(FontData)
	 * @see com.ravocad.notation.NotationPackage#getText_Font()
	 * @model dataType="com.ravocad.notation.FontData"
	 * @generated
	 */
	FontData getFont();

	/**
	 * Sets the value of the '{@link com.ravocad.notation.Text#getFont <em>Font</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Font</em>' attribute.
	 * @see #getFont()
	 * @generated
	 */
	void setFont(FontData value);

	/**
	 * Returns the value of the '<em><b>Color</b></em>' attribute.
	 * The default value is <code>"0,0,0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Color</em>' attribute.
	 * @see #setColor(RGB)
	 * @see com.ravocad.notation.NotationPackage#getText_Color()
	 * @model default="0,0,0" dataType="com.ravocad.notation.RGB"
	 * @generated
	 */
	RGB getColor();

	/**
	 * Sets the value of the '{@link com.ravocad.notation.Text#getColor <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color</em>' attribute.
	 * @see #getColor()
	 * @generated
	 */
	void setColor(RGB value);

} // Text
