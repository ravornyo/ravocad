/**
 */
package com.ravocad.notation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Grid Unit</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.ravocad.notation.NotationPackage#getGridUnit()
 * @model
 * @generated
 */
public enum GridUnit implements Enumerator {
	/**
	 * The '<em><b>UNIT INCHES</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNIT_INCHES_VALUE
	 * @generated
	 * @ordered
	 */
	UNIT_INCHES(0, "UNIT_INCHES", "UNIT_INCHES"),

	/**
	 * The '<em><b>UNIT CENTIMETERS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNIT_CENTIMETERS_VALUE
	 * @generated
	 * @ordered
	 */
	UNIT_CENTIMETERS(1, "UNIT_CENTIMETERS", "UNIT_CENTIMETERS"),

	/**
	 * The '<em><b>UNIT PIXELS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNIT_PIXELS_VALUE
	 * @generated
	 * @ordered
	 */
	UNIT_PIXELS(2, "UNIT_PIXELS", "UNIT_PIXELS");

	/**
	 * The '<em><b>UNIT INCHES</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNIT_INCHES
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int UNIT_INCHES_VALUE = 0;

	/**
	 * The '<em><b>UNIT CENTIMETERS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNIT_CENTIMETERS
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int UNIT_CENTIMETERS_VALUE = 1;

	/**
	 * The '<em><b>UNIT PIXELS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNIT_PIXELS
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int UNIT_PIXELS_VALUE = 2;

	/**
	 * An array of all the '<em><b>Grid Unit</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final GridUnit[] VALUES_ARRAY =
		new GridUnit[] {
			UNIT_INCHES,
			UNIT_CENTIMETERS,
			UNIT_PIXELS,
		};

	/**
	 * A public read-only list of all the '<em><b>Grid Unit</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<GridUnit> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Grid Unit</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static GridUnit get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			GridUnit result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Grid Unit</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static GridUnit getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			GridUnit result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Grid Unit</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static GridUnit get(int value) {
		switch (value) {
			case UNIT_INCHES_VALUE: return UNIT_INCHES;
			case UNIT_CENTIMETERS_VALUE: return UNIT_CENTIMETERS;
			case UNIT_PIXELS_VALUE: return UNIT_PIXELS;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private GridUnit(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //GridUnit
