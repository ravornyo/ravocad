package com.ravocad.diagram.palette;

import org.eclipse.gef.ui.palette.DefaultPaletteViewerPreferences;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.jface.preference.IPreferenceStore;

import com.ravocad.diagram.DiagramPlugin;

public class PalettePreferences extends DefaultPaletteViewerPreferences implements FlyoutPreferences {

	protected static final String PALETTE_DOCK_LOCATION = "DiagramEditorPaletteFactory.location"; //$NON-NLS-1$
	protected static final String PALETTE_WIDTH = "DiagramEditorPaletteFactory.PaletteSize"; //$NON-NLS-1$
	protected static final String PALETTE_STATE = "DiagramEditorPaletteFactory.PaletteState"; //$NON-NLS-1$
	protected static final int DEFAULT_PALETTE_SIZE = 250;
	
	public PalettePreferences() {
		this(DiagramPlugin.getInstance().getPreferenceStore());
	}
	
	public PalettePreferences(final IPreferenceStore store) {
		super(store);
		store.setDefault(PALETTE_WIDTH, DEFAULT_PALETTE_SIZE);
		store.setDefault(PREFERENCE_LAYOUT, LAYOUT_COLUMNS);
	}
	
	@Override
	public int getDockLocation() {
		return getPreferenceStore().getInt(PALETTE_DOCK_LOCATION);
	}
	
	@Override
	public int getPaletteState() {
		return getPreferenceStore().getInt(PALETTE_STATE);
	}
	
	@Override
	public int getPaletteWidth() {
		return getPreferenceStore().getInt(PALETTE_WIDTH);
	}
	
	@Override
	public void setDockLocation(int location) {
		getPreferenceStore().setValue(PALETTE_DOCK_LOCATION, location);				
	}
	
	@Override
	public void setPaletteState(int state) {
		getPreferenceStore().setValue(PALETTE_STATE, state);
	}
	
	@Override
	public void setPaletteWidth(int width) {
		getPreferenceStore().setValue(PALETTE_WIDTH, width);				
	}

}
