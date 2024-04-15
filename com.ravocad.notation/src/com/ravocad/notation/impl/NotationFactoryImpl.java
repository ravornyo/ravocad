/**
 */
package com.ravocad.notation.impl;

import com.ravocad.notation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.PathData;
import org.eclipse.swt.graphics.RGB;

import com.ravocad.notation.Diagram;
import com.ravocad.notation.NotationFactory;
import com.ravocad.notation.NotationPackage;
import com.ravocad.notation.Path;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class NotationFactoryImpl extends EFactoryImpl implements NotationFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static NotationFactory init() {
		try {
			NotationFactory theNotationFactory = (NotationFactory)EPackage.Registry.INSTANCE.getEFactory(NotationPackage.eNS_URI);
			if (theNotationFactory != null) {
				return theNotationFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new NotationFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotationFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case NotationPackage.DIAGRAM: return createDiagram();
			case NotationPackage.PATH: return createPath();
			case NotationPackage.TEXT: return createText();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case NotationPackage.GRID_UNIT:
				return createGridUnitFromString(eDataType, initialValue);
			case NotationPackage.LINE_TYPE:
				return createLineTypeFromString(eDataType, initialValue);
			case NotationPackage.PATH_DATA:
				return createPathDataFromString(eDataType, initialValue);
			case NotationPackage.POINT_LIST:
				return createPointListFromString(eDataType, initialValue);
			case NotationPackage.RGB:
				return createRGBFromString(eDataType, initialValue);
			case NotationPackage.FONT_DATA:
				return createFontDataFromString(eDataType, initialValue);
			case NotationPackage.POINT:
				return createPointFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case NotationPackage.GRID_UNIT:
				return convertGridUnitToString(eDataType, instanceValue);
			case NotationPackage.LINE_TYPE:
				return convertLineTypeToString(eDataType, instanceValue);
			case NotationPackage.PATH_DATA:
				return convertPathDataToString(eDataType, instanceValue);
			case NotationPackage.POINT_LIST:
				return convertPointListToString(eDataType, instanceValue);
			case NotationPackage.RGB:
				return convertRGBToString(eDataType, instanceValue);
			case NotationPackage.FONT_DATA:
				return convertFontDataToString(eDataType, instanceValue);
			case NotationPackage.POINT:
				return convertPointToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Diagram createDiagram() {
		DiagramImpl diagram = new DiagramImpl();
		return diagram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Path createPath() {
		PathImpl path = new PathImpl();
		return path;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Text createText() {
		TextImpl text = new TextImpl();
		return text;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GridUnit createGridUnitFromString(EDataType eDataType, String initialValue) {
		GridUnit result = GridUnit.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertGridUnitToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LineType createLineTypeFromString(EDataType eDataType, String initialValue) {
		LineType result = LineType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLineTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public PathData createPathDataFromString(EDataType eDataType, String initialValue) {
		StringTokenizer st = new StringTokenizer(initialValue, " ");
		
		List<Float> points = new ArrayList<Float>();
		List<Byte> types = new ArrayList<Byte>();
		
		while (st.hasMoreTokens()) {
			String token = st.nextToken().trim();
			float[] floatArray;
			switch (token) {
				case "M":
					types.add((byte)SWT.PATH_MOVE_TO);
					token = st.nextToken().trim();
					floatArray = floatingPointFromString(token);
					for(float f: floatArray) {
						points.add(f);
					}
					break;
				case "L":
					types.add((byte)SWT.PATH_LINE_TO);
					token = st.nextToken().trim();
					floatArray = floatingPointFromString(token);
					for(float f: floatArray) {
						points.add(f);
					}
					break;
				case "C":
					types.add((byte)SWT.PATH_CUBIC_TO);
					//Point #1
					token = st.nextToken().trim();
					floatArray = floatingPointFromString(token);
					for(float f: floatArray) {
						points.add(f);
					}
					//Point #2
					token = st.nextToken().trim();
					floatArray = floatingPointFromString(token);
					for(float f: floatArray) {
						points.add(f);
					}
					//Point #3
					token = st.nextToken().trim();
					floatArray = floatingPointFromString(token);
					for(float f: floatArray) {
						points.add(f);
					}
					break;
				case "Q":
					types.add((byte)SWT.PATH_QUAD_TO);
					//Point #1
					token = st.nextToken().trim();
					floatArray = floatingPointFromString(token);
					for(float f: floatArray) {
						points.add(f);
					}
					//Point #2
					token = st.nextToken().trim();
					floatArray = floatingPointFromString(token);
					for(float f: floatArray) {
						points.add(f);
					}
					break;
				case "Z":
					types.add((byte)SWT.PATH_CLOSE);
					break;
				default:
					break;
			}
		}
		PathData data = new PathData();
		data.points = new float[points.size()];
		data.types = new byte[types.size()];
		for (int i = 0; i < points.size(); i++) {
			data.points[i] = points.get(i);
		}
		for (int i = 0; i < types.size(); i++) {
			data.types[i] = types.get(i);
		}
		return data;
	}
	
	private float[] floatingPointFromString(String initialValue) {
		StringTokenizer st = new StringTokenizer(initialValue, ",");
		float[] array = new float[st.countTokens()];
		int index = 0;
		while (st.hasMoreTokens()) {
			array[index] = Float.parseFloat(st.nextToken().trim());
			index += 1;
		}
		return array;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String convertPathDataToString(EDataType eDataType, Object instanceValue) {
		PathData pathData = (PathData)instanceValue;
		float[] points = pathData.points;
		byte[] types = pathData.types;
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0, j = 0; i < types.length; i++) {
			if (sb.length() != 0) {
				sb.append(" ");
			}
			switch (types[i]) {
				case SWT.PATH_MOVE_TO:
					sb.append(String.format("M %f,%f", points[j++], points[j++]));
					break;
				case SWT.PATH_LINE_TO:
					sb.append(String.format("L %f,%f", points[j++], points[j++]));
					break;
				case SWT.PATH_CUBIC_TO:
					sb.append(String.format("C %f,%f %f,%f %f,%f", points[j++], points[j++], points[j++], points[j++], points[j++], points[j++]));
					break;
				case SWT.PATH_QUAD_TO:
					sb.append(String.format("Q %f,%f %f,%f", points[j++], points[j++], points[j++], points[j++]));
					break;
				case SWT.PATH_CLOSE:
					sb.append("Z");
					break;
				default:
					break;
			}
		}
		return sb.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public PointList createPointListFromString(EDataType eDataType, String initialValue) {
		StringTokenizer st = new StringTokenizer(initialValue, " ");
		int[] array = new int[st.countTokens()];
		int index = 0;
		while (st.hasMoreTokens()) {
			array[index] = Integer.parseInt(st.nextToken().trim());
			index += 1;
		}
		return new PointList(array);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String convertPointListToString(EDataType eDataType, Object instanceValue) {
		StringBuffer sb = new StringBuffer();
		PointList points = (PointList)instanceValue;
		int[] array = points.toIntArray();
		for (int element: array) {
			if (sb.length() != 0) {
				sb.append(" ");
			}
			sb.append(element);
		}
		return sb.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public RGB createRGBFromString(EDataType eDataType, String initialValue) {
		StringTokenizer st = new StringTokenizer(initialValue, ",");
		int[] array = new int[st.countTokens()];
		int index = 0;
		while (st.hasMoreTokens()) {
			array[index] = Integer.parseInt(st.nextToken().trim());
			index += 1;
		}
		if(array.length == 3) {
			return new RGB(array[0], array[1], array[2]);
		} else {
			return new RGB(0, 0, 0);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String convertRGBToString(EDataType eDataType, Object instanceValue) {
		RGB rgb = (RGB)instanceValue;
		if(rgb != null) {
			return rgb.red + "," + rgb.green + "," + rgb.blue;
		} else {
			return "0,0,0";
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public FontData createFontDataFromString(EDataType eDataType, String initialValue) {
		StringTokenizer st = new StringTokenizer(initialValue, "|");
		int index = 0;
		FontData data = new FontData();
		while (st.hasMoreTokens()) {
			if(index == 0) {
				data.setName(st.nextToken().trim());
			} else if(index == 1) {
				data.setHeight(Integer.parseInt(st.nextToken().trim()));
			} else if(index == 2) {
				data.setStyle(Integer.parseInt(st.nextToken().trim()));
			}
			index += 1;
		}
		return data;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String convertFontDataToString(EDataType eDataType, Object instanceValue) {
		FontData data = (FontData)instanceValue;
		if(data != null) {
			return data.getName() + "|" + data.getHeight() + "|" + data.getStyle();
		} else {
			return "Menlo|14|0";
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Point createPointFromString(EDataType eDataType, String initialValue) {
		StringTokenizer st = new StringTokenizer(initialValue, " ");
		int[] array = new int[st.countTokens()];
		int index = 0;
		while (st.hasMoreTokens()) {
			array[index] = Integer.parseInt(st.nextToken().trim());
			index += 1;
		}
		return new Point(array[0], array[1]);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String convertPointToString(EDataType eDataType, Object instanceValue) {
		Point point = (Point)instanceValue;
		return point.x + " " + point.y;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotationPackage getNotationPackage() {
		return (NotationPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static NotationPackage getPackage() {
		return NotationPackage.eINSTANCE;
	}

} //NotationFactoryImpl
