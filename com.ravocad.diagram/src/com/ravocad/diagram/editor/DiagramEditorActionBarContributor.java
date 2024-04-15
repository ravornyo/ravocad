package com.ravocad.diagram.editor;

import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.gef.ui.actions.ZoomInRetargetAction;
import org.eclipse.gef.ui.actions.ZoomOutRetargetAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.RetargetAction;

import com.ravocad.diagram.actions.MirrorRetargetAction;
import com.ravocad.diagram.actions.RotateRetargetAction;
import com.ravocad.diagram.actions.ScaleRetargetAction;
import com.ravocad.diagram.constants.ActionConstants;
import com.ravocad.diagram.i10n.Messages;

public class DiagramEditorActionBarContributor extends ActionBarContributor {

	@Override
	protected void buildActions() {
		addRetargetAction(new UndoRetargetAction());
		addRetargetAction(new RedoRetargetAction());
		
		addRetargetAction(new RotateRetargetAction());
		addRetargetAction(new MirrorRetargetAction());		
		addRetargetAction(new ScaleRetargetAction());

		addRetargetAction(new ZoomInRetargetAction());
		addRetargetAction(new ZoomOutRetargetAction());

		addRetargetAction(new RetargetAction(GEFActionConstants.TOGGLE_RULER_VISIBILITY,
				Messages.getString("ToggleRulerVisibility_Label"), IAction.AS_CHECK_BOX));

		addRetargetAction(new RetargetAction(GEFActionConstants.TOGGLE_GRID_VISIBILITY, 
				Messages.getString("ToggleGrid_Label"), IAction.AS_CHECK_BOX));
		
		addRetargetAction(new RetargetAction(GEFActionConstants.TOGGLE_SNAP_TO_GEOMETRY,
				Messages.getString("ToggleSnapToGeometry_Label"), IAction.AS_CHECK_BOX));
	}

	@Override
	protected void declareGlobalActionKeys() {
		addGlobalActionKey(ActionFactory.PRINT.getId());
		addGlobalActionKey(ActionFactory.SELECT_ALL.getId());
		addGlobalActionKey(ActionFactory.PASTE.getId());
		addGlobalActionKey(ActionFactory.DELETE.getId());
	}

	@Override
	public void contributeToToolBar(IToolBarManager tbm) {
		tbm.add(getAction(ActionFactory.UNDO.getId()));
		tbm.add(getAction(ActionFactory.REDO.getId()));

		tbm.add(new Separator());
		
		tbm.add(getAction(ActionConstants.MIRROR));
		tbm.add(getAction(ActionConstants.ROTATE));
		tbm.add(getAction(ActionConstants.SCALE));
		tbm.add(new Separator());

		String[] zoomStrings = new String[] { 
				ZoomManager.FIT_ALL, 
				ZoomManager.FIT_HEIGHT, 
				ZoomManager.FIT_WIDTH };
		tbm.add(new ZoomComboContributionItem(getPage(), zoomStrings));
	}

	@Override
	public void contributeToMenu(IMenuManager menubar) {
		super.contributeToMenu(menubar);
		
		IMenuManager windowMenu = menubar.findMenuUsingPath(IWorkbenchActionConstants.M_WINDOW);
		
		IMenuManager editorMenu = windowMenu.findMenuUsingPath("org.eclipse.ui.editors");
		if(editorMenu == null) {
			editorMenu = new MenuManager(Messages.getString("EditorMenu_LabelText"));
			if(windowMenu.find("openNewWindow") != null) {
				windowMenu.insertAfter("openNewWindow", editorMenu);
			} else {
				windowMenu.add(editorMenu);
			}
		} else {
			editorMenu.remove("org.eclipse.ui.edit.text.zoomIn");
			editorMenu.remove("org.eclipse.ui.edit.text.zoomOut");
			editorMenu.add(new Separator());
		}
		
		editorMenu.add(getAction(GEFActionConstants.ZOOM_IN));
		editorMenu.add(getAction(GEFActionConstants.ZOOM_OUT));
		editorMenu.add(new Separator());
		editorMenu.add(getAction(GEFActionConstants.TOGGLE_RULER_VISIBILITY));
		editorMenu.add(getAction(GEFActionConstants.TOGGLE_GRID_VISIBILITY));
		editorMenu.add(getAction(GEFActionConstants.TOGGLE_SNAP_TO_GEOMETRY));
	}

} 
