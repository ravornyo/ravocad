package com.ravocad.diagram.commands;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.commands.Command;

import com.ravocad.diagram.i10n.Messages;
import com.ravocad.notation.Diagram;
import com.ravocad.notation.View;

public class CloneViewCommand extends Command {

	private List<View> views, newTopLevelViews;

	private Diagram parent;

	public CloneViewCommand() {
		super(Messages.getString("CloneCommand_Label"));
		views = new LinkedList<View>();
	}

	public void addView(View part) {
		views.add(part);
	}

	protected void clonePart(View oldView, Diagram newParent) {	
		View newPart = EcoreUtil.copy(oldView);

		newParent.getView().add(newPart);

		// keep track of the new parts so we can delete them in undo
		// keep track of the oldpart -> newpart map so that we can properly attach
		// all connections.
		if (newParent == parent) {
			newTopLevelViews.add(newPart);
		}
	}

	public void execute() {
		newTopLevelViews = new LinkedList<View>();

		Iterator<View> i = views.iterator();

		View part = null;
		while (i.hasNext()) {
			part = i.next();
			clonePart(part, parent);
		}

	}

	public void setParent(Diagram parent) {
		this.parent = parent;
	}

	public void redo() {
		for (Iterator<View> iter = newTopLevelViews.iterator(); iter.hasNext();)
			parent.getView().add(iter.next());
	}

	public void undo() {
		for (Iterator<View> iter = newTopLevelViews.iterator(); iter.hasNext();)
			parent.getView().remove(iter.next());
	}

}
