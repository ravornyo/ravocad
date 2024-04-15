package com.ravocad.diagram.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.tools.TargetingTool;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.console.IOConsoleInputStream;
import org.eclipse.ui.console.IOConsoleOutputStream;

import com.ravocad.diagram.console.ConsoleFactory;
import com.ravocad.diagram.requests.OrientationRequest;

public abstract class OrientationTool extends TargetingTool {

	static final int MODIFIER_OTHO_SNAPPING;
	static {
		if (Platform.OS_MACOSX.equals(Platform.getOS())) {
			MODIFIER_OTHO_SNAPPING = SWT.SHIFT;
		} else {
			MODIFIER_OTHO_SNAPPING = SWT.SHIFT;
		}
	}
	private IFigure toolFeedback;
	private List<?> selectedEditParts;

	public OrientationTool() {
		super();
		setDefaultCursor(Cursors.CROSS);
		setDisabledCursor(Cursors.NO);
	}
	
	@Override
	public void activate() {
		super.activate();
		if(selectedEditParts == null) {
			EditPartViewer viewer = getCurrentViewer();
			setSelectedEditParts(viewer.getSelectedEditParts());
		}
	}

	@Override
	protected Request createTargetRequest() {
		return createToolRequest();
	}
	
	@Override
	protected void updateTargetRequest() {
		OrientationRequest request = (OrientationRequest)getTargetRequest();
		request.setOrigin(getStartLocation());	
	}
	
	public abstract OrientationRequest createToolRequest();

	protected void showToolFeedback() {
		if (isInState(STATE_DRAG_IN_PROGRESS)) {
			IFigure targetFeedback = getTargetToolFeedback();
			if(targetFeedback instanceof ToolFeedback) {
				OrientationRequest request = (OrientationRequest)getTargetRequest();
				ToolFeedback toolFeedback = (ToolFeedback)targetFeedback;
				PointList feedbackPoints = new PointList();
				feedbackPoints.addPoint(request.getOrigin().getCopy());
				
				PrecisionPoint loc = new PrecisionPoint(getLocation());
				if(getCurrentInput().isModKeyDown(MODIFIER_OTHO_SNAPPING)) {
					feedbackPoints.addPoint(getOthoPoint(loc));
				} else {
					feedbackPoints.addPoint(loc);
				}

				toolFeedback.translateToRelative(feedbackPoints);				
				toolFeedback.setPoints(feedbackPoints);
	
				Rectangle bounds = toolFeedback.calculateBounds();
				toolFeedback.setBounds(bounds);
				toolFeedback.validate();
			}
		}
	}
	
	@Override
	protected void showTargetFeedback() {
		if (isInState(STATE_DRAG_IN_PROGRESS)) {
			for (Iterator<?> itr = selectedEditParts.iterator(); itr.hasNext();) {
				EditPart editPart = (EditPart) itr.next();
				editPart.showTargetFeedback(getTargetRequest());
			}
		}
	}
	
	protected void eraseTargetFeedback() {
		for (Iterator<?> itr = selectedEditParts.iterator(); itr.hasNext();) {
			EditPart editPart = (EditPart) itr.next();
			editPart.eraseTargetFeedback(getTargetRequest());
		}
	}
	
	protected IFigure getTargetToolFeedback() {
		if(toolFeedback == null) {
			toolFeedback = createTargetFeedback();
			addFeedback(toolFeedback);
		}
		return toolFeedback;
	}
	
	protected IFigure createTargetFeedback() {
		return new ToolFeedback();
	}

	@Override
	protected boolean handleButtonDown(int button) {
		if (button != 1) {
			setState(STATE_INVALID);
			handleInvalidInput();
			return true;
		}

		if (stateTransition(STATE_INITIAL, STATE_DRAG)) {			
			OrientationRequest request = (OrientationRequest)getTargetRequest();
			request.setOrigin(getLocation());
		} 
		return true;
	}
	
	@Override
	protected boolean handleButtonUp(int button) {
		if (isInState(STATE_DRAG_IN_PROGRESS)) {
			doFinish(button);
		} 
		return true;
	}
	
	@Override
	protected boolean handleMove() {
		if(isInState(STATE_DRAG)) {
			if (movedPastThreshold()) {
				handleDragStarted();			
			}
			return true;
		} 
		updateTargetRequest();
		//updateTargetUnderMouse();
		setCurrentCommand(getCommand());
		showToolFeedback();
		showTargetFeedback();
		return true;
	}
	
	@Override
	protected boolean handleKeyDown(KeyEvent e) {
		if (e.character == SWT.ESC) {
			setState(STATE_TERMINAL);			
			unlockTargetEditPart();
			eraseToolFeedback();
			eraseTargetFeedback();
			handleFinished();	
			return true;
		}
		return false;
	}
	
	@Override
	protected Command getCommand() {
		if (selectedEditParts == null || selectedEditParts.size() == 0) {
			return null;
		}
		CompoundCommand cc = new CompoundCommand();
		for (Iterator<?> itr = selectedEditParts.iterator(); itr.hasNext();) {
			EditPart editPart = (EditPart) itr.next();
			Command cmd = editPart.getCommand(getTargetRequest());
			if(cmd != null) {
				cc.add(cmd);
			}
		}
		return cc;
	}
	
	@Override
	protected boolean handleDragStarted() {
		setDefaultCursor(Cursors.CROSS);
		return stateTransition(STATE_DRAG, STATE_DRAG_IN_PROGRESS);
	}
	
	protected void doFinish(int button) {
		setState(STATE_TERMINAL);
		
		unlockTargetEditPart();
		performOrientation(button);
		eraseToolFeedback();
		eraseTargetFeedback();
		
		handleFinished();
	}
	
	protected void performOrientation(int button) {
		//EditPartViewer viewer = getCurrentViewer();
		executeCurrentCommand();
		//selectAddedObject(viewer);
	}

	protected void eraseToolFeedback() {
		if (toolFeedback != null) {
			removeFeedback(toolFeedback);
			toolFeedback = null;
		}
	}
	
	protected Point getOthoPoint(Point location) {
		OrientationRequest request = (OrientationRequest)getTargetRequest();
		Point origin = request.getOrigin().getCopy();
		if(origin != null) {
			Dimension delta = location.getDifference(origin);
			if(Math.abs(delta.width) > Math.abs(delta.height)) {
				return new Point(location.x, origin.y);
			} else if(Math.abs(delta.height) > Math.abs(delta.width)) {
				return new Point(origin.x, location.y);
			}
		}
		return location;
	}
	
	public List<?> getSelectedEditParts() {
		return selectedEditParts;
	}

	public void setSelectedEditParts(List<?> selectedEditParts) {
		this.selectedEditParts = selectedEditParts;
	}
	
	protected void writeToConsole(String msg) {
		IOConsoleOutputStream printer = ConsoleFactory.getConsole().newOutputStream();
		try {
			printer.write(msg + "\n");
		} catch (IOException e) {
		}
	}
	
	protected String readFromConsole() {
		IOConsoleInputStream is = ConsoleFactory.getConsole().getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		try {
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected String getConsoleInput(String message) {
		final AtomicReference<String> consoleInput = new AtomicReference<String>();
		
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				writeToConsole(message);
				String input = readFromConsole();
				consoleInput.set(input);
			}
		};
		new Thread(runnable).start();
		
		Shell loopShell = Display.getCurrent().getActiveShell();
		Display display = loopShell.getDisplay();
		while (loopShell != null && !loopShell.isDisposed() && consoleInput.get() == null) {
			try {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
				
			} catch (Throwable e) {}
		}
		if (!display.isDisposed()) display.update();
		
		return consoleInput.get();
	}

	public static class ToolFeedback extends Shape {		
		private PointList points;

		@Override
		protected void outlineShape(Graphics g) {
			g.pushState();
			g.setForegroundColor(ColorConstants.black);
			g.setLineStyle(Graphics.LINE_DOT);
			g.drawPolyline(getPoints());
			g.popState();
		}
		@Override
		protected void fillShape(Graphics graphics) {
			// Do nothing
		}		
		protected Rectangle calculateBounds() {
			return getPoints().getBounds();
		}
		@Override
		public Dimension getPreferredSize(int wHint, int hHint) {
			return calculateBounds().getSize();
		}
		public PointList getPoints() {
			return points;
		}
		public void setPoints(PointList points) {
			this.points = points;
		}
	}

}
