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
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FontDialog;

/**
 * An example for how to implement an {@link IPropertyEditorFactory} that
 * supports colors.
 */
public class FontPropertyEditorFactory extends EMFEditUIPropertyEditorFactory {

	/**
	 * The URI for which this property editor factory is registered.
	 */
	public static final URI PROPERTY_EDITOR_FACTORY_URI = URI.createURI("editor://com.ravocad.font/");

	/**
	 * The singleton instance of this property editor factory.
	 */
	public static final FontPropertyEditorFactory INSTANCE = new FontPropertyEditorFactory();

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
	 * Creates an instance.
	 */
	public FontPropertyEditorFactory() {
		super(PROPERTY_EDITOR_FACTORY_URI);
	}
	
	/**
	 * The stateless singleton item label provider.
	 * 
	 * @see #createLabelProvider(Object, IItemPropertyDescriptor)
	 */
	private static final IItemLabelProvider LABEL_PROVIDER = new IItemLabelProvider() {
		@Override
		public String getText(Object object) {
			if(object != null) {
				FontData data = (FontData)object;
				return data.getName() + " | " + data.getHeight();
			} else {
				return "";
			}
		}
		@Override
		public Object getImage(Object object) {
			return null;
		}
	};
	
	@Override
	public IItemLabelProvider createLabelProvider(Object object, IItemPropertyDescriptor propertyDescriptor) {
		return LABEL_PROVIDER;
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
			if (eDataType.getInstanceClass() == FontData.class) {
				// Get the actual URI associated with this property descriptor.
				URI propertyEditorURI = getPropertyEditorURI(object, propertyDescriptor);

				// Extract the style from the first segment, if present.
				int style = SWT.NONE;
				if (propertyEditorURI.segmentCount() > 0) {
					String styleSegment = propertyEditorURI.segment(0);
					style = getStyle(styleSegment, SHELL_STYLES);
				}

				// Create the cell editor, with the style specified in the URI.
				return new FontCellEditor(composite, style);
			}
		}
		return null;
	}
	
	class FontCellEditor extends DialogCellEditor {
		
		public FontCellEditor(Composite parent, int style) {
			super(parent, style);
			doSetValue(new FontData());
		}

		@Override
		protected Object openDialogBox(Control cellEditorWindow) {
			FontDialog dialog = new FontDialog(cellEditorWindow.getShell());
			Object value = getValue();
			if (value != null) {
				dialog.setFontList(new FontData[] { (FontData)value });
			}
			value = dialog.open();
			return dialog.getFontList()[0];
		}
		
		@Override
		protected void updateContents(Object value) {
			if(value != null) {
				FontData data = (FontData)value;
				super.updateContents(data.getName() + " | " + data.getHeight());
			} else {
				super.updateContents("");
			}
		}
		
	}

}
