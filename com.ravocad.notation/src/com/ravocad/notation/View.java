/**
 */
package com.ravocad.notation;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>View</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.ravocad.notation.View#getHint <em>Hint</em>}</li>
 * </ul>
 *
 * @see com.ravocad.notation.NotationPackage#getView()
 * @model abstract="true"
 * @generated
 */
public interface View extends EObject {

	/**
	 * Returns the value of the '<em><b>Hint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hint</em>' attribute.
	 * @see #setHint(String)
	 * @see com.ravocad.notation.NotationPackage#getView_Hint()
	 * @model
	 * @generated
	 */
	String getHint();

	/**
	 * Sets the value of the '{@link com.ravocad.notation.View#getHint <em>Hint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hint</em>' attribute.
	 * @see #getHint()
	 * @generated
	 */
	void setHint(String value);
} // View
