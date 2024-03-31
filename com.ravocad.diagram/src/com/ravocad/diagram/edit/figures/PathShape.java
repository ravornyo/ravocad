package com.ravocad.diagram.edit.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionDimension;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.PathData;
import org.eclipse.ui.PlatformUI;

public class PathShape extends Shape {
	
	public static final String PROPERTY_POINTS = "points"; 

	private PointList handlePoints;
	private PathData pathData;

	public PathShape(){
		super();
		setFill(false);
		setOutline(true);
	}
	
	@Override
	public Dimension getPreferredSize(int wHint, int hHint) {
		if(pathData != null) {
			float lineInset = Math.max(1.0f, getLineWidthFloat());
			
			Path path = new Path(PlatformUI.getWorkbench().getDisplay(), pathData);
			float[] arr = new float[4];
			path.getBounds(arr);
			path.dispose();
			Dimension size = new PrecisionDimension(arr[2], arr[3]);
			return size.expand(lineInset, lineInset);	
		} else {
			return super.getPreferredSize(wHint, hHint);
		}	
	}
	
	@Override
	public boolean isOpaque() {
		return false;
	}
	
	@Override
	protected void outlineShape(Graphics g) {
		if(pathData != null) {
			g.pushState();
			g.setAntialias(SWT.ON);
			
			Path path = new Path(PlatformUI.getWorkbench().getDisplay(), pathData);
			g.drawPath(path);
			path.dispose();
			
			g.popState();
		}
	}
	
	@Override
	protected void fillShape(Graphics graphics) {
		// TODO Auto-generated method stub	
	}
	
	public void setHandlePoints(PointList handlePoints) {
		firePropertyChange(PROPERTY_POINTS, null, handlePoints);
		this.handlePoints = handlePoints;
	}
	
	public PointList getHandlePoints() {
		return handlePoints;
	}
	
	public void setPathData(PathData pathData) {
		this.pathData = pathData;
	}
	
	public PathData getPathData() {
		return pathData;
	}

}
