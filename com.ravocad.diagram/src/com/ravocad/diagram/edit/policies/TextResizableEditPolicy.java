package com.ravocad.diagram.edit.policies;

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.swt.graphics.Color;

import com.ravocad.diagram.commands.SetLocationCommand;
import com.ravocad.notation.Text;

public class TextResizableEditPolicy extends NonResizableEditPolicy {

	@Override
	protected Command getMoveCommand(ChangeBoundsRequest request) {
		Text view = (Text)getHost().getModel();
		Point moveDelta = request.getMoveDelta();
		return new SetLocationCommand(view, moveDelta);
	}
	
	protected Label getHostLabel() {
		return (Label)getHostFigure();
	}

	@Override
	protected IFigure createDragSourceFeedbackFigure() {
		Text view = (Text)getHost().getModel();
		// Use a ghost rectangle for feedback
		Label r = new Label();
		r.setText(view.getContent());
		r.setFont(getHostLabel().getFont());
		r.setForegroundColor(FigureUtilities.lighter(getHostLabel().getForegroundColor()));
		r.setBorder(new LineBorder(new Color(null, 31, 31, 31), 1, Graphics.LINE_DOT));
		r.setBackgroundColor(new Color(null, 31, 31, 31));
		r.setBounds(getInitialFeedbackBounds().expand(2, 2));
		r.validate();
		addFeedback(r);
		return r;
	}
	
}
