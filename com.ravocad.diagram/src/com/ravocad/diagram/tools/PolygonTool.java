package com.ravocad.diagram.tools;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Display;

import com.ravocad.diagram.constants.VisualConstants;
import com.ravocad.diagram.dialogs.InputPopupDialog;
import com.ravocad.diagram.geometry.IGeometryHelper;
import com.ravocad.diagram.geometry.PolygonGeometryHelper;
import com.ravocad.diagram.i10n.Messages;

public class PolygonTool extends SketchTool {
	
	int sides = -1;

	public PolygonTool() {
		this(-1);
	}
	
	public PolygonTool(int sides) {
		super(VisualConstants.POLYGON);
		this.sides = sides;
	}

	@Override
	public void activate() {
		if(this.sides < 0) {
			this.sides = getNumSides();
		}
		super.activate();
	}
	
	private int getNumSides() {
		InputPopupDialog dialog = new InputPopupDialog(
				Display.getCurrent().getActiveShell(),
				Messages.getString("PolygonCreationDialogTitle"),
				Messages.getString("PolygonCreationDialogMessage"),
				Messages.getString("PolygonCreationDialogInitialValue"),
				new IntegerValidator(3)
		);
		if (dialog.open() == Dialog.CANCEL 
				|| dialog.getResult() == null
				|| dialog.getResult().length <= 0) {
			return 5;
		} else {
			return Integer.parseInt(dialog.getResult()[0].toString());
		}
	}
	
	protected PointList getHandles(PointList points) {
		return ((PolygonGeometryHelper)createGeometry()).getTemplatePoints(points);
	}
	
	@Override
	protected IGeometryHelper createGeometry() {
		return new PolygonGeometryHelper() {
			public PointList getTemplatePoints(PointList points) {
				if(points != null && points.size() > 1) {
					return calcPolygonPoints(points, sides);
				}
				return super.getTemplatePoints(points);
			}
			private PointList calcPolygonPoints(PointList points, int numSides) {
				PointList newPoints = new PointList();
				
				Point centerPoint = points.getPoint(0);
				Point targetPoint = points.getPoint(1);
				
				newPoints.addPoint(targetPoint);
				
				double radius = Math.abs(targetPoint.getDistance(centerPoint));		
				double startAngle = Math.atan2((targetPoint.y - centerPoint.y), (targetPoint.x - centerPoint.x));
				double increment = (2 * Math.PI)/ (double)numSides;
				
				double angle = startAngle;
				for(int i=0; i < numSides - 1; i++) {
					angle += increment; 
					
					double x = centerPoint.x + (Math.cos(angle) * radius); 
			        double y = centerPoint.y + (Math.sin(angle) * radius);
			        PrecisionPoint p = new PrecisionPoint(x, y);
			        newPoints.addPoint(p);
				}		
				return newPoints;	
			}
		};
	}
	
	@Override
	protected boolean isComplete() {
		return getWayPoints().size() >= 2;
	}
	
	protected Point getOthoPoint(Point location) {
		if(getWayPoints().size() > 0) {
			Point lastPoint = getWayPoints().getLastPoint();
			if(lastPoint == null) {
				lastPoint = getStartLocation();
			}
			Dimension delta = location.getDifference(lastPoint);
			int increment = Math.max(delta.width, delta.height);
			return new Point(lastPoint.x + increment, lastPoint.y + increment);
		}
		return location;		
	}

	public static class IntegerValidator implements IInputValidator{
		private int minValue;
		IntegerValidator(int minValue){
			this.minValue = minValue;
		}
		@Override
		public String isValid(String newText) {
			try {
				int intValue = Integer.parseInt(newText);
				return intValue > 2 ? null : 
					NLS.bind(Messages.getString("ValidationInvalidInteger"), new Object[] {minValue});
			} catch (NumberFormatException e) {
				return Messages.getString("ValidationInvalidInteger");
			}
		}
	}

}
