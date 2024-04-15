package com.ravocad.diagram.editor;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.actions.ActionFactory;

import com.ravocad.diagram.constants.ActionConstants;

/**
 * Provides context menu actions for the DiagramEditor.
 */
public class DiagramContextMenuProvider extends ContextMenuProvider {

	/*
	 * The editor's action registry. 
	 */
	private ActionRegistry actionRegistry;
	
	public DiagramContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
		super(viewer);
		if (registry == null) {
			throw new IllegalArgumentException();
		}
		this.actionRegistry = registry;
	}

	/**
	 * Called when the context menu is about to show. Actions, 
	 * whose state is enabled, will appear in the context menu.
	 * @see org.eclipse.gef.ContextMenuProvider#buildContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	public void buildContextMenu(IMenuManager menu) {
		// Add standard action groups to the menu
		GEFActionConstants.addStandardActionGroups(menu);
		
		IAction action;
		
		// Add actions to the menu
		menu.appendToGroup(
				GEFActionConstants.GROUP_UNDO, // target group id
				getAction(ActionFactory.UNDO.getId())); // action to add
		menu.appendToGroup(
				GEFActionConstants.GROUP_UNDO, 
				getAction(ActionFactory.REDO.getId()));
		
		action = getAction(ActionFactory.COPY.getId());
		if (action.isEnabled()) {
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}		
		action = getAction(ActionFactory.PASTE.getId());
		if (action.isEnabled()) {
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}		
		action = getAction(ActionFactory.DELETE.getId());
		if (action.isEnabled()) {
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		}		
		action = getAction(ActionConstants.ROTATE);
		if (action.isEnabled()) {
			menu.appendToGroup(GEFActionConstants.GROUP_REST, action);
		}
		action = getAction(ActionConstants.MIRROR);
		if (action.isEnabled()) {
			menu.appendToGroup(GEFActionConstants.GROUP_REST, action);
		}
	}
	
	private IAction getAction(String actionId) {
		return actionRegistry.getAction(actionId);
	}

}
