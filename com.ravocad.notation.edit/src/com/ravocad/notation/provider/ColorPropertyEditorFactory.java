 package com.ravocad.notation.provider;

import java.util.Map;
import java.util.TreeMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IPropertyEditorFactory;
import org.eclipse.emf.edit.ui.provider.EMFEditUIPropertyEditorFactory;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColorCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import com.ravocad.notation.NotationFactory;
import com.ravocad.notation.NotationPackage;

/**
 * An example for how to implement an {@link IPropertyEditorFactory} that
 * supports colors.
 */
public class ColorPropertyEditorFactory extends EMFEditUIPropertyEditorFactory {

	/**
	 * The URI for which this property editor factory is registered.
	 */
	public static final URI PROPERTY_EDITOR_FACTORY_URI = URI.createURI("editor://com.ravocad.color/");

	/**
	 * The singleton instance of this property editor factory.
	 */
	public static final ColorPropertyEditorFactory INSTANCE = new ColorPropertyEditorFactory();

	/**
	 * A sample of how to support SWST style parameters for a property editor
	 * factory.
	 */
	private static final Map<String, Integer> SHELL_STYLES;
	static {
		Map<String, Integer> shellStyles = new TreeMap<String, Integer>();
		shellStyles.put("RESIZE", SWT.RESIZE);
		shellStyles.put("TOOL", SWT.TOOL);
		shellStyles.put("SHEET", SWT.SHEET);
		shellStyles.put("MAX", SWT.MAX);
		SHELL_STYLES = shellStyles;
	}

	/**
	 * The RGB value for black.
	 */
	private static final RGB BLACK = new RGB(0, 0, 0);

	/**
	 * The RGB value for white.
	 */
	private static final RGB WHITE = new RGB(255, 255, 255);

	/**
	 * The RGB value for gray..
	 */
	private static final RGB GRAY = new RGB(128, 128, 128);

	/**
	 * A map of color images used by {@link #getImage(Object)}.
	 */
	//private static final Map<RGB, Image> IMAGES = new HashMap<RGB, Image>();
	private static final ImageRegistry registry = new ImageRegistry();
	
	/**
	 * Returns the color image for the given object.
	 * 
	 * @param object
	 *            the object.
	 * @return the color image for the given object.
	 * @see #LABEL_PROVIDER
	 */
	private static Image getImage(Object object) {
		RGB rgb = toRGB(object);
		if (rgb == null) {
			rgb = new RGB(0, 0, 0);
		}

		String key = rgb.toString();
		Image result = registry.get(key);
		if (result == null) {

			// Build the image data with a palette with three colors.
			// The first is the transparent pixel, and must be different from black and the
			// rgb color.
			RGB transparent = rgb.equals(WHITE) ? GRAY : WHITE;
			PaletteData palette = new PaletteData(transparent, rgb, BLACK);
			ImageData imageData = new ImageData(13, 13, 4, palette);
			imageData.transparentPixel = 0;

			// Create the image.
			Display display = Display.getCurrent();//PlatformUI.getWorkbench().getDisplay();
			result = new Image(display, imageData);

			// Create a GC to draw a rectangle on the image.
			GC gc = new GC(result);
			Rectangle rectangle = new Rectangle(2, 2, 9, 9);

			// Fill with the rgb color.
			Color color = new Color(display, rgb);
			gc.setBackground(color);
			gc.fillRectangle(rectangle);

			// Draw a black rectangle around it.
			gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
			gc.drawRectangle(rectangle);

			// Dipose the handles.
			color.dispose();
			gc.dispose();
			
			registry.put(key, result);
		}
		return result;
	}
	
	private static RGB toRGB(Object value) {
		if(value instanceof RGB) {
			return (RGB)value;
		}
		return (RGB)NotationFactory.eINSTANCE.createFromString(NotationPackage.eINSTANCE.getRGB(), (String)value);
	}

	/**
	 * The stateless singleton item label provider.
	 * 
	 * @see #createLabelProvider(Object, IItemPropertyDescriptor)
	 */
	private static final IItemLabelProvider LABEL_PROVIDER = new IItemLabelProvider() {
		@Override
		public String getText(Object object) {
			return object == null ? "" : object.toString();
		}
		@Override
		public Object getImage(Object object) {
			return ColorPropertyEditorFactory.getImage(object);
		}
	};

	/**
	 * Creates an instance.
	 */
	public ColorPropertyEditorFactory() {
		super(PROPERTY_EDITOR_FACTORY_URI);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * This is specialized to return a {@link ColorPropertyCellEditor}.
	 * </p>
	 */
	@Override
	public CellEditor createEditor(Object object, IItemPropertyDescriptor propertyDescriptor, Composite composite) {
		// Get the feature to check that it's a single-valued attribute.
		EStructuralFeature eStructuralFeature = getEStructuralFeature(object, propertyDescriptor);
		if (eStructuralFeature instanceof EAttribute && !eStructuralFeature.isMany()) {
			// Check that the instance type of the attribute's type is String.class.
			EAttribute eAttribute = (EAttribute) eStructuralFeature;
			EDataType eDataType = eAttribute.getEAttributeType();
			if (eDataType.getInstanceClass() == String.class || eDataType.getInstanceClass() == RGB.class) {
				// Get the actual URI associated with this property descriptor.
				URI propertyEditorURI = getPropertyEditorURI(object, propertyDescriptor);

				// Extract the style from the first segment, if present.
				int style = SWT.NONE;
				if (propertyEditorURI.segmentCount() > 0) {
					String styleSegment = propertyEditorURI.segment(0);
					style = getStyle(styleSegment, SHELL_STYLES);
				}

				// Create the cell editor, with the style specified in the URI.
				return new ColorCellEditor(composite, style);
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * This is specialized to return images based on the color specified by the
	 * value.
	 * </p>
	 */
	@Override
	public IItemLabelProvider createLabelProvider(Object object, IItemPropertyDescriptor propertyDescriptor) {
		return LABEL_PROVIDER;
	}

}
