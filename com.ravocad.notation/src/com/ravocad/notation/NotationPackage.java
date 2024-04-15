/**
 */
package com.ravocad.notation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
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
	 * The feature id for the '<em><b>Grid Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__GRID_VISIBLE = 1;

	/**
	 * The feature id for the '<em><b>Grid Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__GRID_COLOR = 2;

	/**
	 * The feature id for the '<em><b>Grid Spacing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__GRID_SPACING = 3;

	/**
	 * The feature id for the '<em><b>Grid Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__GRID_UNIT = 4;

	/**
	 * The feature id for the '<em><b>Snap To Grid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__SNAP_TO_GRID = 5;

	/**
	 * The feature id for the '<em><b>Snap To Geometry</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__SNAP_TO_GEOMETRY = 6;

	/**
	 * The feature id for the '<em><b>Zoom</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__ZOOM = 7;

	/**
	 * The number of structural features of the '<em>Diagram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_FEATURE_COUNT = 8;

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
	 * The feature id for the '<em><b>Fill</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH__FILL = VIEW_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH__FILL_COLOR = VIEW_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Alpha</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH__ALPHA = VIEW_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Path</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_FEATURE_COUNT = VIEW_FEATURE_COUNT + 7;

	/**
	 * The number of operations of the '<em>Path</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_OPERATION_COUNT = VIEW_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.ravocad.notation.impl.TextImpl <em>Text</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ravocad.notation.impl.TextImpl
	 * @see com.ravocad.notation.impl.NotationPackageImpl#getText()
	 * @generated
	 */
	int TEXT = 3;

	/**
	 * The feature id for the '<em><b>Hint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__HINT = VIEW__HINT;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__CONTENT = VIEW_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__LOCATION = VIEW_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Font</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__FONT = VIEW_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__COLOR = VIEW_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Text</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FEATURE_COUNT = VIEW_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Text</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_OPERATION_COUNT = VIEW_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.ravocad.notation.GridUnit <em>Grid Unit</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ravocad.notation.GridUnit
	 * @see com.ravocad.notation.impl.NotationPackageImpl#getGridUnit()
	 * @generated
	 */
	int GRID_UNIT = 4;

	/**
	 * The meta object id for the '{@link com.ravocad.notation.LineType <em>Line Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.ravocad.notation.LineType
	 * @see com.ravocad.notation.impl.NotationPackageImpl#getLineType()
	 * @generated
	 */
	int LINE_TYPE = 5;

	/**
	 * The meta object id for the '<em>Path Data</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.swt.graphics.PathData
	 * @see com.ravocad.notation.impl.NotationPackageImpl#getPathData()
	 * @generated
	 */
	int PATH_DATA = 6;


	/**
	 * The meta object id for the '<em>Point List</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.draw2d.geometry.PointList
	 * @see com.ravocad.notation.impl.NotationPackageImpl#getPointList()
	 * @generated
	 */
	int POINT_LIST = 7;


	/**
	 * The meta object id for the '<em>RGB</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.swt.graphics.RGB
	 * @see com.ravocad.notation.impl.NotationPackageImpl#getRGB()
	 * @generated
	 */
	int RGB = 8;


	/**
	 * The meta object id for the '<em>Font Data</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.swt.graphics.FontData
	 * @see com.ravocad.notation.impl.NotationPackageImpl#getFontData()
	 * @generated
	 */
	int FONT_DATA = 9;

	/**
	 * The meta object id for the '<em>Point</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.draw2d.geometry.Point
	 * @see com.ravocad.notation.impl.NotationPackageImpl#getPoint()
	 * @generated
	 */
	int POINT = 10;


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
	 * Returns the meta object for the attribute '{@link com.ravocad.notation.Diagram#getGridColor <em>Grid Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Grid Color</em>'.
	 * @see com.ravocad.notation.Diagram#getGridColor()
	 * @see #getDiagram()
	 * @generated
	 */
	EAttribute getDiagram_GridColor();

	/**
	 * Returns the meta object for the attribute '{@link com.ravocad.notation.Diagram#getGridSpacing <em>Grid Spacing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Grid Spacing</em>'.
	 * @see com.ravocad.notation.Diagram#getGridSpacing()
	 * @see #getDiagram()
	 * @generated
	 */
	EAttribute getDiagram_GridSpacing();

	/**
	 * Returns the meta object for the attribute '{@link com.ravocad.notation.Diagram#getGridUnit <em>Grid Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Grid Unit</em>'.
	 * @see com.ravocad.notation.Diagram#getGridUnit()
	 * @see #getDiagram()
	 * @generated
	 */
	EAttribute getDiagram_GridUnit();

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
	 * Returns the meta object for class '{@link com.ravocad.notation.Text <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Text</em>'.
	 * @see com.ravocad.notation.Text
	 * @generated
	 */
	EClass getText();

	/**
	 * Returns the meta object for the attribute '{@link com.ravocad.notation.Text#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Content</em>'.
	 * @see com.ravocad.notation.Text#getContent()
	 * @see #getText()
	 * @generated
	 */
	EAttribute getText_Content();

	/**
	 * Returns the meta object for the attribute '{@link com.ravocad.notation.Text#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see com.ravocad.notation.Text#getLocation()
	 * @see #getText()
	 * @generated
	 */
	EAttribute getText_Location();

	/**
	 * Returns the meta object for the attribute '{@link com.ravocad.notation.Text#getFont <em>Font</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Font</em>'.
	 * @see com.ravocad.notation.Text#getFont()
	 * @see #getText()
	 * @generated
	 */
	EAttribute getText_Font();

	/**
	 * Returns the meta object for the attribute '{@link com.ravocad.notation.Text#getColor <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color</em>'.
	 * @see com.ravocad.notation.Text#getColor()
	 * @see #getText()
	 * @generated
	 */
	EAttribute getText_Color();

	/**
	 * Returns the meta object for enum '{@link com.ravocad.notation.GridUnit <em>Grid Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Grid Unit</em>'.
	 * @see com.ravocad.notation.GridUnit
	 * @generated
	 */
	EEnum getGridUnit();

	/**
	 * Returns the meta object for enum '{@link com.ravocad.notation.LineType <em>Line Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Line Type</em>'.
	 * @see com.ravocad.notation.LineType
	 * @generated
	 */
	EEnum getLineType();

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
	 * Returns the meta object for the attribute '{@link com.ravocad.notation.Path#isFill <em>Fill</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fill</em>'.
	 * @see com.ravocad.notation.Path#isFill()
	 * @see #getPath()
	 * @generated
	 */
	EAttribute getPath_Fill();

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
	 * Returns the meta object for data type '{@link org.eclipse.swt.graphics.FontData <em>Font Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Font Data</em>'.
	 * @see org.eclipse.swt.graphics.FontData
	 * @model instanceClass="org.eclipse.swt.graphics.FontData"
	 * @generated
	 */
	EDataType getFontData();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.draw2d.geometry.Point <em>Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Point</em>'.
	 * @see org.eclipse.draw2d.geometry.Point
	 * @model instanceClass="org.eclipse.draw2d.geometry.Point"
	 * @generated
	 */
	EDataType getPoint();

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
		 * The meta object literal for the '<em><b>Grid Visible</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIAGRAM__GRID_VISIBLE = eINSTANCE.getDiagram_GridVisible();

		/**
		 * The meta object literal for the '<em><b>Grid Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIAGRAM__GRID_COLOR = eINSTANCE.getDiagram_GridColor();

		/**
		 * The meta object literal for the '<em><b>Grid Spacing</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIAGRAM__GRID_SPACING = eINSTANCE.getDiagram_GridSpacing();

		/**
		 * The meta object literal for the '<em><b>Grid Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIAGRAM__GRID_UNIT = eINSTANCE.getDiagram_GridUnit();

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
		 * The meta object literal for the '{@link com.ravocad.notation.impl.TextImpl <em>Text</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ravocad.notation.impl.TextImpl
		 * @see com.ravocad.notation.impl.NotationPackageImpl#getText()
		 * @generated
		 */
		EClass TEXT = eINSTANCE.getText();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXT__CONTENT = eINSTANCE.getText_Content();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXT__LOCATION = eINSTANCE.getText_Location();

		/**
		 * The meta object literal for the '<em><b>Font</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXT__FONT = eINSTANCE.getText_Font();

		/**
		 * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXT__COLOR = eINSTANCE.getText_Color();

		/**
		 * The meta object literal for the '{@link com.ravocad.notation.GridUnit <em>Grid Unit</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ravocad.notation.GridUnit
		 * @see com.ravocad.notation.impl.NotationPackageImpl#getGridUnit()
		 * @generated
		 */
		EEnum GRID_UNIT = eINSTANCE.getGridUnit();

		/**
		 * The meta object literal for the '{@link com.ravocad.notation.LineType <em>Line Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.ravocad.notation.LineType
		 * @see com.ravocad.notation.impl.NotationPackageImpl#getLineType()
		 * @generated
		 */
		EEnum LINE_TYPE = eINSTANCE.getLineType();

		/**
		 * The meta object literal for the '<em><b>Line Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PATH__LINE_WIDTH = eINSTANCE.getPath_LineWidth();

		/**
		 * The meta object literal for the '<em><b>Fill</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PATH__FILL = eINSTANCE.getPath_Fill();

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

		/**
		 * The meta object literal for the '<em>Font Data</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.swt.graphics.FontData
		 * @see com.ravocad.notation.impl.NotationPackageImpl#getFontData()
		 * @generated
		 */
		EDataType FONT_DATA = eINSTANCE.getFontData();

		/**
		 * The meta object literal for the '<em>Point</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.draw2d.geometry.Point
		 * @see com.ravocad.notation.impl.NotationPackageImpl#getPoint()
		 * @generated
		 */
		EDataType POINT = eINSTANCE.getPoint();

	}

} //NotationPackage
