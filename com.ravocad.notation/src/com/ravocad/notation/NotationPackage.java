/**
 */
package com.ravocad.notation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.ravocad.notation.NotationFactory
 * @model kind="package"
 * @generated
 */
public interface NotationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "notation";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.ravocad.com/runtime/notation";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "notation";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	NotationPackage eINSTANCE = com.ravocad.notation.impl.NotationPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.ravocad.notation.impl.DiagramImpl <em>Diagram</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ravocad.notation.impl.DiagramImpl
	 * @see com.ravocad.notation.impl.NotationPackageImpl#getDiagram()
	 * @generated
	 */
	int DIAGRAM = 0;

	/**
	 * The feature id for the '<em><b>View</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__VIEW = 0;

	/**
	 * The feature id for the '<em><b>Ruler Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__RULER_VISIBLE = 1;

	/**
	 * The feature id for the '<em><b>Grid Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__GRID_VISIBLE = 2;

	/**
	 * The feature id for the '<em><b>Snap To Grid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__SNAP_TO_GRID = 3;

	/**
	 * The feature id for the '<em><b>Snap To Geometry</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__SNAP_TO_GEOMETRY = 4;

	/**
	 * The feature id for the '<em><b>Zoom</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__ZOOM = 5;

	/**
	 * The number of structural features of the '<em>Diagram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Diagram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.ravocad.notation.impl.ViewImpl <em>View</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ravocad.notation.impl.ViewImpl
	 * @see com.ravocad.notation.impl.NotationPackageImpl#getView()
	 * @generated
	 */
	int VIEW = 1;

	/**
	 * The feature id for the '<em><b>Hint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW__HINT = 0;

	/**
	 * The number of structural features of the '<em>View</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>View</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.ravocad.notation.impl.PathImpl <em>Path</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ravocad.notation.impl.PathImpl
	 * @see com.ravocad.notation.impl.NotationPackageImpl#getPath()
	 * @generated
	 */
	int PATH = 2;

	/**
	 * The feature id for the '<em><b>Hint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH__HINT = VIEW__HINT;

	/**
	 * The feature id for the '<em><b>Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH__DATA = VIEW_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Handles</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH__HANDLES = VIEW_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Line Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH__LINE_COLOR = VIEW_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Line Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH__LINE_WIDTH = VIEW_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH__FILL_COLOR = VIEW_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Alpha</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH__ALPHA = VIEW_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Path</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_FEATURE_COUNT = VIEW_FEATURE_COUNT + 6;

	/**
	 * The number of operations of the '<em>Path</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_OPERATION_COUNT = VIEW_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '<em>Path Data</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.swt.graphics.PathData
	 * @see com.ravocad.notation.impl.NotationPackageImpl#getPathData()
	 * @generated
	 */
	int PATH_DATA = 3;


	/**
	 * The meta object id for the '<em>Point List</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.draw2d.geometry.PointList
	 * @see com.ravocad.notation.impl.NotationPackageImpl#getPointList()
	 * @generated
	 */
	int POINT_LIST = 4;


	/**
	 * The meta object id for the '<em>RGB</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.swt.graphics.RGB
	 * @see com.ravocad.notation.impl.NotationPackageImpl#getRGB()
	 * @generated
	 */
	int RGB = 5;


	/**
	 * Returns the meta object for class '{@link com.ravocad.notation.Diagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Diagram</em>'.
	 * @see com.ravocad.notation.Diagram
	 * @generated
	 */
	EClass getDiagram();

	/**
	 * Returns the meta object for the attribute '{@link com.ravocad.notation.Diagram#getZoom <em>Zoom</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Zoom</em>'.
	 * @see com.ravocad.notation.Diagram#getZoom()
	 * @see #getDiagram()
	 * @generated
	 */
	EAttribute getDiagram_Zoom();

	/**
	 * Returns the meta object for the containment reference list '{@link com.ravocad.notation.Diagram#getView <em>View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>View</em>'.
	 * @see com.ravocad.notation.Diagram#getView()
	 * @see #getDiagram()
	 * @generated
	 */
	EReference getDiagram_View();

	/**
	 * Returns the meta object for the attribute '{@link com.ravocad.notation.Diagram#isRulerVisible <em>Ruler Visible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ruler Visible</em>'.
	 * @see com.ravocad.notation.Diagram#isRulerVisible()
	 * @see #getDiagram()
	 * @generated
	 */
	EAttribute getDiagram_RulerVisible();

	/**
	 * Returns the meta object for the attribute '{@link com.ravocad.notation.Diagram#isGridVisible <em>Grid Visible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Grid Visible</em>'.
	 * @see com.ravocad.notation.Diagram#isGridVisible()
	 * @see #getDiagram()
	 * @generated
	 */
	EAttribute getDiagram_GridVisible();

	/**
	 * Returns the meta object for the attribute '{@link com.ravocad.notation.Diagram#isSnapToGrid <em>Snap To Grid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Snap To Grid</em>'.
	 * @see com.ravocad.notation.Diagram#isSnapToGrid()
	 * @see #getDiagram()
	 * @generated
	 */
	EAttribute getDiagram_SnapToGrid();

	/**
	 * Returns the meta object for the attribute '{@link com.ravocad.notation.Diagram#isSnapToGeometry <em>Snap To Geometry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Snap To Geometry</em>'.
	 * @see com.ravocad.notation.Diagram#isSnapToGeometry()
	 * @see #getDiagram()
	 * @generated
	 */
	EAttribute getDiagram_SnapToGeometry();

	/**
	 * Returns the meta object for class '{@link com.ravocad.notation.View <em>View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>View</em>'.
	 * @see com.ravocad.notation.View
	 * @generated
	 */
	EClass getView();

	/**
	 * Returns the meta object for the attribute '{@link com.ravocad.notation.View#getHint <em>Hint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hint</em>'.
	 * @see com.ravocad.notation.View#getHint()
	 * @see #getView()
	 * @generated
	 */
	EAttribute getView_Hint();

	/**
	 * Returns the meta object for class '{@link com.ravocad.notation.Path <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Path</em>'.
	 * @see com.ravocad.notation.Path
	 * @generated
	 */
	EClass getPath();

	/**
	 * Returns the meta object for the attribute '{@link com.ravocad.notation.Path#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data</em>'.
	 * @see com.ravocad.notation.Path#getData()
	 * @see #getPath()
	 * @generated
	 */
	EAttribute getPath_Data();

	/**
	 * Returns the meta object for the attribute '{@link com.ravocad.notation.Path#getHandles <em>Handles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Handles</em>'.
	 * @see com.ravocad.notation.Path#getHandles()
	 * @see #getPath()
	 * @generated
	 */
	EAttribute getPath_Handles();

	/**
	 * Returns the meta object for the attribute '{@link com.ravocad.notation.Path#getLineColor <em>Line Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line Color</em>'.
	 * @see com.ravocad.notation.Path#getLineColor()
	 * @see #getPath()
	 * @generated
	 */
	EAttribute getPath_LineColor();

	/**
	 * Returns the meta object for the attribute '{@link com.ravocad.notation.Path#getFillColor <em>Fill Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fill Color</em>'.
	 * @see com.ravocad.notation.Path#getFillColor()
	 * @see #getPath()
	 * @generated
	 */
	EAttribute getPath_FillColor();

	/**
	 * Returns the meta object for the attribute '{@link com.ravocad.notation.Path#getAlpha <em>Alpha</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Alpha</em>'.
	 * @see com.ravocad.notation.Path#getAlpha()
	 * @see #getPath()
	 * @generated
	 */
	EAttribute getPath_Alpha();

	/**
	 * Returns the meta object for the attribute '{@link com.ravocad.notation.Path#getLineWidth <em>Line Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line Width</em>'.
	 * @see com.ravocad.notation.Path#getLineWidth()
	 * @see #getPath()
	 * @generated
	 */
	EAttribute getPath_LineWidth();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.swt.graphics.PathData <em>Path Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Path Data</em>'.
	 * @see org.eclipse.swt.graphics.PathData
	 * @model instanceClass="org.eclipse.swt.graphics.PathData"
	 * @generated
	 */
	EDataType getPathData();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.draw2d.geometry.PointList <em>Point List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Point List</em>'.
	 * @see org.eclipse.draw2d.geometry.PointList
	 * @model instanceClass="org.eclipse.draw2d.geometry.PointList"
	 * @generated
	 */
	EDataType getPointList();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.swt.graphics.RGB <em>RGB</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>RGB</em>'.
	 * @see org.eclipse.swt.graphics.RGB
	 * @model instanceClass="org.eclipse.swt.graphics.RGB"
	 * @generated
	 */
	EDataType getRGB();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	NotationFactory getNotationFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.ravocad.notation.impl.DiagramImpl <em>Diagram</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ravocad.notation.impl.DiagramImpl
		 * @see com.ravocad.notation.impl.NotationPackageImpl#getDiagram()
		 * @generated
		 */
		EClass DIAGRAM = eINSTANCE.getDiagram();

		/**
		 * The meta object literal for the '<em><b>Zoom</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIAGRAM__ZOOM = eINSTANCE.getDiagram_Zoom();

		/**
		 * The meta object literal for the '<em><b>View</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIAGRAM__VIEW = eINSTANCE.getDiagram_View();

		/**
		 * The meta object literal for the '<em><b>Ruler Visible</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIAGRAM__RULER_VISIBLE = eINSTANCE.getDiagram_RulerVisible();

		/**
		 * The meta object literal for the '<em><b>Grid Visible</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIAGRAM__GRID_VISIBLE = eINSTANCE.getDiagram_GridVisible();

		/**
		 * The meta object literal for the '<em><b>Snap To Grid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIAGRAM__SNAP_TO_GRID = eINSTANCE.getDiagram_SnapToGrid();

		/**
		 * The meta object literal for the '<em><b>Snap To Geometry</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIAGRAM__SNAP_TO_GEOMETRY = eINSTANCE.getDiagram_SnapToGeometry();

		/**
		 * The meta object literal for the '{@link com.ravocad.notation.impl.ViewImpl <em>View</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ravocad.notation.impl.ViewImpl
		 * @see com.ravocad.notation.impl.NotationPackageImpl#getView()
		 * @generated
		 */
		EClass VIEW = eINSTANCE.getView();

		/**
		 * The meta object literal for the '<em><b>Hint</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VIEW__HINT = eINSTANCE.getView_Hint();

		/**
		 * The meta object literal for the '{@link com.ravocad.notation.impl.PathImpl <em>Path</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ravocad.notation.impl.PathImpl
		 * @see com.ravocad.notation.impl.NotationPackageImpl#getPath()
		 * @generated
		 */
		EClass PATH = eINSTANCE.getPath();

		/**
		 * The meta object literal for the '<em><b>Data</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PATH__DATA = eINSTANCE.getPath_Data();

		/**
		 * The meta object literal for the '<em><b>Handles</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PATH__HANDLES = eINSTANCE.getPath_Handles();

		/**
		 * The meta object literal for the '<em><b>Line Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PATH__LINE_COLOR = eINSTANCE.getPath_LineColor();

		/**
		 * The meta object literal for the '<em><b>Fill Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PATH__FILL_COLOR = eINSTANCE.getPath_FillColor();

		/**
		 * The meta object literal for the '<em><b>Alpha</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PATH__ALPHA = eINSTANCE.getPath_Alpha();

		/**
		 * The meta object literal for the '<em><b>Line Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PATH__LINE_WIDTH = eINSTANCE.getPath_LineWidth();

		/**
		 * The meta object literal for the '<em>Path Data</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.swt.graphics.PathData
		 * @see com.ravocad.notation.impl.NotationPackageImpl#getPathData()
		 * @generated
		 */
		EDataType PATH_DATA = eINSTANCE.getPathData();

		/**
		 * The meta object literal for the '<em>Point List</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.draw2d.geometry.PointList
		 * @see com.ravocad.notation.impl.NotationPackageImpl#getPointList()
		 * @generated
		 */
		EDataType POINT_LIST = eINSTANCE.getPointList();

		/**
		 * The meta object literal for the '<em>RGB</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.swt.graphics.RGB
		 * @see com.ravocad.notation.impl.NotationPackageImpl#getRGB()
		 * @generated
		 */
		EDataType RGB = eINSTANCE.getRGB();

	}

} //NotationPackage
