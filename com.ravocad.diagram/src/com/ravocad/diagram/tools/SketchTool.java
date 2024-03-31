 package com.ravocad.diagram.tools;

import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Path;

import com.ravocad.diagram.geometry.IGeometryHelper;

public abstract class SketchTool extends CreationTool {

	private static final int FLAG_TARGET_FEEDBACK = AbstractTool.MAX_FLAG << 2;
	
	/**
	 * Key modifier for ignoring snap while dragging. It's CTRL on Mac, and ALT on
	 * all other platforms.
	 */
	static final int MODIFIER_OTHO_SNAPPING;
	static final int MODIFIER_NO_SNAPPING;

	static {
		if (Platform.OS_MACOSX.equals(Platform.getOS())) {
			MODIFIER_NO_SNAPPING = SWT.CTRL;
			MODIFIER_OTHO_SNAPPING = SWT.SHIFT;
		} else {
			MODIFIER_NO_SNAPPING = SWT.ALT;
			MODIFIER_OTHO_SNAPPING = SWT.SHIFT;
		}
	}
	
	private IFigure targetFeedback;
	
	private PointList waypoints;
	private Point startPoint;
	
	private SnapToHelper helper;
	
	private boolean orthoMode = false;
	private String hint;

	public SketchTool(String hint) {
		super();

		setHint(hint);
		setDefaultCursor(Cursors.CROSS);
		setDisabledCursor(Cursors.NO);
	}
	
	@Override
	public void activate() {
		super.activate();
		waypoints = new PointList();
	}
	
	@Override
	public void deactivate() {
		super.deactivate();
		waypoints = null;
		helper = null;
	}

	@Override
	protected void setStartLocation(Point p) {
		if(startPoint == null) {
			startPoint = p;
		}
	}
	
	@Override
	protected Point getStartLocation() {
		return startPoint;
	}
	
	protected PointList getWayPoints() {
		return waypoints;
	}
	
	@Override
	protected Request createTargetRequest() {
		CreateRequest createRequest = (CreateRequest)super.createTargetRequest();
		createRequest.setSnapToEnabled(!getCurrentInput().isModKeyDown(MODIFIER_NO_SNAPPING));
		return createRequest;
	}

	@Override
	protected void updateTargetRequest() {
		CreateRequest createRequest = getCreateRequest();
		if (isInState(STATE_DRAG_IN_PROGRESS)) {
			createRequest.setSize(null);
			createRequest.setLocation(getStartLocation());
			createRequest.getExtendedData().clear();
		} else {
			createRequest.setSize(null);
			createRequest.setLocation(getLocation());
		}
		createRequest.setSnapToEnabled(!getCurrentInput().isModKeyDown(MODIFIER_NO_SNAPPING));
	}
	
	@Override
	protected void showTargetFeedback() {
		if (isInState(STATE_DRAG_IN_PROGRESS)) {
			IFigure targetFeedback = getTargetFeedback();
			if(targetFeedback instanceof SketchFeedback) {
				SketchFeedback sketchFeedback = (SketchFeedback)targetFeedback;
				PointList feedbackPoints = waypoints.getCopy();
				
				PrecisionPoint loc = new PrecisionPoint(getLocation());
				if(getCurrentInput().isModKeyDown(MODIFIER_OTHO_SNAPPING) || orthoMode) {
					feedbackPoints.addPoint(getOthoPoint(loc));
				} else {
					feedbackPoints.addPoint(getSnapPoint(loc));
				}

				sketchFeedback.translateToRelative(feedbackPoints);				
				sketchFeedback.setPoints(feedbackPoints);
	
				Rectangle bounds = sketchFeedback.calculateBounds();
				sketchFeedback.setBounds(bounds);
				sketchFeedback.validate();
			}
		}
		setFlag(FLAG_TARGET_FEEDBACK, true);
	}
	
	private IFigure getTargetFeedback() {
		if(targetFeedback == null) {
			targetFeedback = createTargetFeedback();
			addFeedback(targetFeedback);
		}
		return targetFeedback;
	}
	
	@Override
	protected void eraseTargetFeedback() {
		if (!isShowingTargetFeedback())
			return;
		setFlag(FLAG_TARGET_FEEDBACK, false);
		if (targetFeedback != null) {
			removeFeedback(targetFeedback);
			targetFeedback = null;
		}
	}

	protected abstract IGeometryHelper createGeometry();

	protected IFigure createTargetFeedback() {
		return new SketchFeedback(createGeometry());
	}

	@Override
	protected boolean handleButtonDown(int button) {
		if (button != 1) {
			if(waypoints.size() > 0) {
				doFinish(button);
			} else {
				setState(STATE_INVALID);
				handleInvalidInput();
			}
			return true;
		}

		if (stateTransition(STATE_INITIAL, STATE_DRAG)) {			
			lockTargetEditPart(getTargetEditPart());
			if (getTargetEditPart() != null) {
				helper = getTargetEditPart().getAdapter(SnapToHelper.class);
				waypoints.addPoint(getSnapPoint(getLocation()));
			}
		} else if (getState() > 0) {
			Point loc = getLocation();
			if(getCurrentInput().isModKeyDown(MODIFIER_OTHO_SNAPPING) || orthoMode) {
				waypoints.addPoint(getOthoPoint(loc));
			} else {
				waypoints.addPoint(getSnapPoint(loc));
			}
		}
		return true;
	}
	
	@Override
	protected boolean handleButtonUp(int button) {
		if (isInState(STATE_DRAG_IN_PROGRESS) && isComplete()) {
			doFinish(button);
		} else if(isInState(STATE_DRAG_IN_PROGRESS)) {
			setState(STATE_DRAG);
		}
		return true;
	}

	protected abstract boolean isComplete();
	
	private void doFinish(int button) {
		setState(STATE_TERMINAL);
		
		unlockTargetEditPart();
		performCreation(button);
		eraseTargetFeedback();
		
		handleFinished();
	}

	@Override
	protected boolean handleDoubleClick(int button) {
		if(isInState(STATE_DRAG)) {
			doFinish(button);
			return true;
		}
		return super.handleDoubleClick(button);
	}

	@Override
	protected boolean handleKeyDown(KeyEvent e) {
		if (e.character == SWT.ESC) {
			if(waypoints.size() > 1) {
				doFinish(1);
			} else {
				getDomain().loadDefaultTool();
			}	
			return true;
		}
		return false;
	}

	protected boolean handleKeyUp(KeyEvent e) {
		if (e.keyCode == SWT.Selection) {
			setUnloadWhenFinished(true);
			doFinish(1);
			return true;
		}
		return false;
	}
	
	@Override
	protected boolean handleDragStarted() {
		setDefaultCursor(Cursors.CROSS);
		return super.handleDragStarted();
	}
	
	@Override
	protected boolean handleMove() {
		if(isInState(STATE_DRAG)) {
			if (movedPastThreshold()) {
				handleDragStarted();			
			}
		} 
		return super.handleMove();
	}
	
	protected boolean handleViewerExited() {
		return handleFocusLost();
	}
	
	@Override
	protected void performCreation(int button) {
		CreateRequest createRequest = getCreateRequest();	
		if(targetFeedback != null && targetFeedback instanceof SketchFeedback) {
			SketchFeedback sketchFeedback = (SketchFeedback)targetFeedback;

			PointList feedbackPoints = getWayPoints().getCopy();
			((GraphicalEditPart)getTargetEditPart()).getFigure().translateToRelative(feedbackPoints);	
			sketchFeedback.setPoints(feedbackPoints);	
			com.ravocad.notation.Path notation = (com.ravocad.notation.Path)createRequest.getNewObject();
			notation.setHint(getHint());
			
			Path path = sketchFeedback.getPath();
			if(path != null) {
				notation.setData(path.getPathData());
				path.dispose();
			}
			notation.setHandles(getHandles(sketchFeedback.getPoints()));
		} 
		setCurrentCommand(getCommand());
		super.performCreation(button);
	}
	
	protected PointList getHandles(PointList points) {
		return points;
	}
	
	protected Point getOthoPoint(Point location) {
		if(getWayPoints().size() > 0) {
			Point lastPoint = getWayPoints().getLastPoint();
			if(lastPoint == null) {
				lastPoint = startPoint;
			}
			Dimension delta = location.getDifference(lastPoint);
			if(Math.abs(delta.width) > Math.abs(delta.height)) {
				return new Point(location.x, lastPoint.y);
			} else if(Math.abs(delta.height) > Math.abs(delta.width)) {
				return new Point(lastPoint.x, location.y);
			}
		}
		return location;
	}
	
	protected Point getSnapPoint(Point point) {
		CreateRequest request = getCreateRequest();
		if(helper != null && request.isSnapToEnabled()) {
			PrecisionPoint loc = new PrecisionPoint(point);
			PrecisionPoint snappedLoc = loc.getPreciseCopy();
			helper.snapPoint(getCreateRequest(), PositionConstants.NSEW, loc, snappedLoc);

			return new PrecisionPoint(snappedLoc.preciseX(), snappedLoc.preciseY());
		}
		return point;
	}

	public void setOrthoMode(boolean orthoMode) {
		this.orthoMode = orthoMode;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public static class SketchFeedback extends Shape {		
		private PointList points;
		private IGeometryHelper geometry;
		
		public SketchFeedback(IGeometryHelper geometry) {
			this.geometry = geometry;
		}
		@Override
		protected void outlineShape(Graphics g) {
			g.pushState();
			g.setForegroundColor(ColorConstants.black);
			g.setLineStyle(Graphics.LINE_SOLID);
			
			Path path = getPath();
			g.drawPath(path);
			path.dispose();
			
			g.popState();
		}
		@Override
		protected void fillShape(Graphics graphics) {
			// Do nothing
		}		
		protected Rectangle calculateBounds() {
			Path path = getPath();
			float[] arr = new float[4];
			path.getBounds(arr);
			path.dispose();

			int lineWidth = Math.max(1, getLineWidth());
			Rectangle bounds = new PrecisionRectangle(arr[0], arr[1], Math.ceil(arr[2]), Math.ceil(arr[3]));
			return bounds.expand(lineWidth, lineWidth);
		}
		@Override
		public Dimension getPreferredSize(int wHint, int hHint) {
			return points.getBounds().getSize();
		}
		public PointList getPoints() {
			return points;
		}
		public void setPoints(PointList points) {
			this.points = points;
		}
		public Path getPath() {
			return geometry.createPath(points);
		}
	}

}
