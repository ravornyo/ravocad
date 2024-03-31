package com.ravocad.diagram.tools;

import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.gef.tools.SimpleDragTracker;
import org.eclipse.gef.tools.ToolUtilities;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;

import com.ravocad.diagram.constants.RequestConstants;
import com.ravocad.diagram.edit.figures.PathShape;
import com.ravocad.diagram.requests.MoveHandleRequest;

public class MoveHandleTracker extends SimpleDragTracker {

	private static final int FLAG_SOURCE_FEEDBACK = AbstractTool.MAX_FLAG << 1;

	static final int MODIFIER_NO_SNAPPING;

	static {
		if (Platform.OS_MACOSX.equals(Platform.getOS())) {
			MODIFIER_NO_SNAPPING = SWT.CTRL;
		} else {
			MODIFIER_NO_SNAPPING = SWT.ALT;
		}
	}
	
	private int index;
	private GraphicalEditPart owner;
	private SnapToHelper snapToHelper;
	private PrecisionPoint sourcePoint;

	public MoveHandleTracker(GraphicalEditPart owner, int index) {
		this.owner = owner;
		this.index = index;
		setDisabledCursor(SharedCursors.NO);
	}
	
	@Override
	public void activate() {
		super.activate();
		if (owner != null) {
			if (getTargetEditPart() != null) {
				snapToHelper = getTargetEditPart().getAdapter(SnapToHelper.class);
			}
			IFigure figure = owner.getFigure();
			if (figure instanceof PathShape) {
				PointList pts = ((PathShape) figure).getHandlePoints();
				sourcePoint = new PrecisionPoint(pts.getPoint(index));
				figure.translateToAbsolute(sourcePoint);
			} 
		}
	}
	
	@Override
	public void deactivate() {
		sourcePoint = null;
		snapToHelper = null;
		eraseSourceFeedback();
		getCurrentViewer().setFocus(null);
		super.deactivate();
	}
	
	protected GraphicalEditPart getTargetEditPart() {
		if (owner != null) {
			return (GraphicalEditPart) owner.getParent();
		}
		return null;
	}

	@Override
	public void commitDrag() {
		eraseSourceFeedback();
		super.commitDrag();
	}
	
	@Override
	protected Cursor calculateCursor() {
		if (isInState(STATE_INITIAL | STATE_DRAG | STATE_ACCESSIBLE_DRAG))
			return getDefaultCursor();
		return super.calculateCursor();
	}

	@Override
	protected List<?> createOperationSet() {
		List<?> list = super.createOperationSet();
		ToolUtilities.filterEditPartsUnderstanding(list, getSourceRequest());
		return list;
	}

	@Override
	protected Request createSourceRequest() {
		MoveHandleRequest request = new MoveHandleRequest(new int[] {index});
		request.setSourceEditPart(getSourceEditPart());
		return request;
	}

	@Override
	protected Command getCommand() {
		if (getSourceEditPart() == null) {
			return null;
		}
		return getSourceEditPart().getCommand(getSourceRequest());
	}

	@Override
	protected String getCommandName() {
		return RequestConstants.REQ_MOVE_POINT;
	}

	@Override
	protected Cursor getDefaultCursor() {
		return SharedCursors.getDirectionalCursor(0, getSourceEditPart().getFigure().isMirrored());
	}

	@Override
	protected String getDebugName() {
		return "Move Point Handle Tracker";
	}

	protected GraphicalEditPart getSourceEditPart() {
		return owner;
	}

	protected int getIndex() {
		return index;
	}

	@Override
	protected boolean handleButtonUp(int button) {
		if (stateTransition(STATE_DRAG_IN_PROGRESS, STATE_TERMINAL)) {
			eraseSourceFeedback();
			performDrag();
		}
		return true;
	}

	@Override
	protected boolean handleDragInProgress() {
		if (isInState(STATE_DRAG_IN_PROGRESS | STATE_ACCESSIBLE_DRAG_IN_PROGRESS)) {
			updateSourceRequest();
			showSourceFeedback();
			setCurrentCommand(getCommand());
		}
		return true;
	}

	@Override
	protected void showSourceFeedback() {
		setFlag(FLAG_SOURCE_FEEDBACK, true);
		if (getSourceEditPart() != null) {
			getSourceEditPart().showSourceFeedback(getSourceRequest());
		}
	}
	
	@Override
	protected void eraseSourceFeedback() {
		if (!getFlag(FLAG_SOURCE_FEEDBACK)) {
			return;
		}
		if (getSourceEditPart() != null) {
			getSourceEditPart().eraseSourceFeedback(getSourceRequest());
		}
		setFlag(FLAG_SOURCE_FEEDBACK, false);
	}

	@Override
	protected void updateSourceRequest() {
		MoveHandleRequest request = (MoveHandleRequest)getSourceRequest();

		Dimension moveDelta = getDragMoveDelta();
		
		//request.setSnapToEnabled(!getCurrentInput().isModKeyDown(MODIFIER_NO_SNAPPING));
		request.getExtendedData().clear();
		request.setIndices(new int[] {getIndex()});
		request.getExtendedData().clear();
		request.setMoveDelta(moveDelta);
		
		if (request.isSnapToEnabled() && snapToHelper != null) {
			PrecisionPoint point = sourcePoint.getPreciseCopy();
			point.translate(moveDelta);
			PrecisionPoint result = new PrecisionPoint();

			snapToHelper.snapPoint(request, PositionConstants.NSEW, point, result);
			
			PrecisionPoint preciseMove = new PrecisionPoint(result.preciseX() + moveDelta.width,
					result.preciseY() + moveDelta.height);
			request.setMoveDelta(preciseMove);
		}
	}

}
