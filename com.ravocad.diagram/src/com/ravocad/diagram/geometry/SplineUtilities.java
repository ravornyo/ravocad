package com.ravocad.diagram.geometry;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Ray;
import org.eclipse.draw2d.geometry.Transform;

@SuppressWarnings("deprecation")
public class SplineUtilities {
	
	final static int MIN_LINE_LENGTH = 5;
	final static int MAX_BEZIERLINES = 32;
	
	/**
	 * Method calcSmoothPolyline.
	 * Calculate the smooth polyline approximation of this polyline based on 
	 *      a smooth factor.
	 * 
	 * @param points the <code>PointList</code> that is used to calculate the
	 * smooth point list from.  
	 * @param nSmoothFactor the <code>int</code> smooth factor to smooth the line with 
	 *          0 - None, 15 - some, 30 - lots
	 * @param nBezierSteps the <code>int</code> number of line steps used to approximate the smooth curve
	 * @return PolylinePointList List of PolylinePoint representing the smooth 
	 *      polyline.
	 */
	static public final PointList calcSmoothPolyline(
		PointList points,
		int nSmoothFactor,
		int nBezierSteps) {
		PointList theBezierPoints =
			calcBezier(
				points,
				nSmoothFactor,
				0,
				points.size() - 1);
		return calcApproxPolylineFromBezier(
			theBezierPoints,
			nBezierSteps);
	}
	
	/**
	 * Utility function that takes a set of bezier points and calculates a polyline 
	 * approximation.
	 * 
	 * @param points the <code>PointList</code> to calculate the bezier from.
	 * @param nBezierSteps the <code>int</code> number of line steps that will be used
	 *      to approximate each bezier curve
	 * @return PolylinePointList List of PolylinePoint representing the smooth 
	 *      polyline.
	 */
	static private PointList calcApproxPolylineFromBezier(
		final PointList points,
		int nBezierSteps) {
		PrecisionPointList thePolyPoints =
			new PrecisionPointList(points.size() * nBezierSteps + 2);

		Point ptCtl1, ptCtl2, ptCtl3, ptCtl4;
		boolean bStart = true;

		if (points.size() < 4)
			return thePolyPoints;

		ptCtl1 = new PrecisionPoint();
		for (int i = 0; i < points.size() - 3; i = i + 3) {
			if (bStart) {
				ptCtl1 = new PrecisionPoint(points.getPoint(i));
				bStart = false;
			} else {
				thePolyPoints.removePoint(thePolyPoints.size() - 1);
			}

			ptCtl2 = new PrecisionPoint(points.getPoint(i + 1));
			ptCtl3 = new PrecisionPoint(points.getPoint(i + 2));
			ptCtl4 = new PrecisionPoint(points.getPoint(i + 3));

			if (!BezierToLines(thePolyPoints,
				ptCtl1,
				ptCtl2,
				ptCtl3,
				ptCtl4,
				nBezierSteps))
				return null;

			ptCtl1 = new PrecisionPoint(ptCtl4);
		}

		// now construct the PolyLine based on the points
		thePolyPoints.setPoint(points.getPoint(0), 0);
		thePolyPoints.setPoint(
			points.getPoint(points.size() - 1),
			thePolyPoints.size() - 1);

		return thePolyPoints;
	}
	
	/**
	 * Calculate the actual bezier points of this polyline based on a smooth factor.
	 * 
	 * @param points PointList to calculate the bezier approximation from
	 * @param nSmoothFactor the <code>int</code> smooth factor to smooth the line with 
	 *          0 - None, 15 - some, 30 - lots
	 * @param nStartIndex int Index to start the calculation at
	 * @param nEndIndex int Index to end the calculation at
	 * @return PolylinePointList List of PolylinePoint representing the bezier.
	 */
	static private PointList calcBezier(
		final PointList points,
		int nSmoothFactor,
		int nStartIndex,
		int nEndIndex) {
		Point ptPrevControl = null;
		PrecisionPointList theBezierPoints = new PrecisionPointList(points.size() * 2);
		// parse through the line segments and create control points based on a smoothing factor.
		List<LineSeg> theSegments = getLineSegments(points);

		for (int i = 0; i < theSegments.size(); i++) {
			LineSeg pLineSeg = (LineSeg) theSegments.get(i);

			double dLineLength = pLineSeg.length();
			double dControlLength = dLineLength * nSmoothFactor / 100;

			boolean bAddToBezier = false;
			if (i >= nStartIndex && i <= nEndIndex)
				bAddToBezier = true;
			else if (i > nEndIndex)
				return theBezierPoints;

			if (dLineLength > MIN_LINE_LENGTH) {
				Point ptStartControl = new PrecisionPoint();
				Point ptTerminusControl = new PrecisionPoint();
				Point ptStart = new PrecisionPoint(pLineSeg.getOrigin());
				Point ptTerminus = new PrecisionPoint(pLineSeg.getTerminus());

				if (theBezierPoints.size() == 0) {
					if (bAddToBezier)
						theBezierPoints.addPoint(ptStart);
				}

				if (ptPrevControl != null) {
					LineSeg prevControlSeg =
						new LineSeg(ptPrevControl, ptStart);
					ptStartControl = new PrecisionPoint();
					prevControlSeg.pointOn(
						prevControlSeg.length() + dControlLength,
						LineSeg.KeyPoint.ORIGIN,
						ptStartControl);
				} else {
					ptStartControl = new PrecisionPoint();
					pLineSeg.pointOn(
						dControlLength,
						LineSeg.KeyPoint.ORIGIN,
						ptStartControl);
				}
				if (bAddToBezier)
					theBezierPoints.addPoint(ptStartControl);

				// Calculate the next terminating control point
				LineSeg pNextSeg = null;
				if (i + 1 < theSegments.size()) {
					pNextSeg = (LineSeg) theSegments.get(i + 1);
					while (pNextSeg != null
						&& pNextSeg.length() < MIN_LINE_LENGTH) {
						i++;
						if (i + 1 < theSegments.size())
							pNextSeg = (LineSeg) theSegments.get(i + 1);
						else
							pNextSeg = null;
					}
				}

				if (pNextSeg != null) {
					// compute two vectors and calculate the angle between them.
					Ray ptVector1 =
						new Ray(pLineSeg.getOrigin(), pLineSeg.getTerminus());
					Ray ptVector2 =
						new Ray(pNextSeg.getOrigin(), pNextSeg.getTerminus());
					double dNewAngle = 0.0;

					LineSeg.TrigValues val = pLineSeg.getTrigValues(ptVector2);

					dNewAngle = Math.atan2(-val.sinTheta, -val.cosTheta);

					if (dNewAngle > 0)
						dNewAngle = (Math.PI - dNewAngle) / -2;
					else
						dNewAngle = (-Math.PI - dNewAngle) / -2;

					Transform trans = new Transform();
					trans.setRotation(dNewAngle);
					Point ptVector1Prime =
						trans.getTransformed(
							new PrecisionPoint(ptVector1.x, ptVector1.y));

					LineSeg nextControlSeg =
						new LineSeg(
							new PrecisionPoint(0, 0),
							new PrecisionPoint(ptVector1Prime.x, ptVector1Prime.y));
					Point ptProjection = new PrecisionPoint();
					nextControlSeg.pointOn(
						dControlLength,
						LineSeg.KeyPoint.ORIGIN,
						ptProjection);

					ptTerminusControl =
						new PrecisionPoint(
							pLineSeg.getTerminus().x - ptProjection.x,
							pLineSeg.getTerminus().y - ptProjection.y);
				} else {
					pLineSeg.pointOn(
						dLineLength - dControlLength,
						LineSeg.KeyPoint.ORIGIN,
						ptTerminusControl);
				}

				ptPrevControl = new PrecisionPoint(ptTerminusControl);
				if (bAddToBezier) {
					theBezierPoints.addPoint(ptTerminusControl);
					theBezierPoints.addPoint(ptTerminus);
				}
			}
		}

		return theBezierPoints;
	}
	
	public static List<LineSeg> getLineSegments(PointList points) {
		if (points.size() <= 1)
			return new ArrayList<LineSeg>(0);

		ArrayList<LineSeg> lines = new ArrayList<LineSeg>(points.size() - 1);

		for (int i = 0; i < points.size() - 1; i++) {
			lines.add(new LineSeg(points.getPoint(i), points.getPoint(i + 1)));
		}

		return lines;
	}
	
	/**
	 * Method BezierToLines.
	 * Utility function that takes a set of bezier points and calculates a polyline 
	 * approximation.
	 * 
	 * @param thePolyPoints
	 * @param ptCtl1
	 * @param ptCtl2
	 * @param ptCtl3
	 * @param ptCtl4
	 * @param nSteps
	 * @return boolean
	 */
	private static boolean BezierToLines(
		PointList thePolyPoints,
		Point ptCtl1,
		Point ptCtl2,
		Point ptCtl3,
		Point ptCtl4,
		int nSteps) {
		boolean bRC = true;

		int nTotalPoints;
		/* total number of coordinate pairs in working arrays */
		int nLinePoints;
		/* total number of bezier endpoints in working arrays */
		double[] lpWorkX; /* pointer to X coordinate working array */
		double[] lpWorkY; /* pointer to Y coordinate working array */
		int i, j;

		nSteps = Math.min(MAX_BEZIERLINES, nSteps);

		/* get pointers to working arrays in workspace */
		lpWorkX = new double[3 * nSteps + 2];
		lpWorkY = new double[3 * nSteps + 2];

		/* enter original bezier X coordinates into X working array */
		lpWorkX[0] = ptCtl1.x;
		lpWorkX[1] = ptCtl2.x;
		lpWorkX[2] = ptCtl3.x;
		lpWorkX[3] = ptCtl4.x;

		/* enter original bezier Y coordinates into Y working array */
		lpWorkY[0] = ptCtl1.y;
		lpWorkY[1] = ptCtl2.y;
		lpWorkY[2] = ptCtl3.y;
		lpWorkY[3] = ptCtl4.y;

		/* initially 2 bezier endpoints and 4 coordinate pairs total */
		nLinePoints = 2;
		nTotalPoints = 4;

		/* generate enough bezier endpoints to satisfy requested number of steps */

		while (nLinePoints <= nSteps) {
			/* spread coordinate pairs in working array */

			for (i = nTotalPoints - 1; i > 0; i--) {
				lpWorkX[2 * i] = lpWorkX[i];
				lpWorkY[2 * i] = lpWorkY[i];
			}

			nTotalPoints = (2 * nTotalPoints) - 1;

			/* average consecutive coordinates and put average between coordinates */

			for (i = nTotalPoints - 2; i > 0; i -= 2) {
				lpWorkX[i] = (lpWorkX[i - 1] + lpWorkX[i + 1]) / 2;
				lpWorkY[i] = (lpWorkY[i - 1] + lpWorkY[i + 1]) / 2;
			}

			/* now average averages and store over control points */
			/* but do not overwrite bezier endpoints */

			for (i = nTotalPoints - 3; i > 0; i -= 2) {
				/* only control points */

				if ((i % 6) != 0) {
					lpWorkX[i] = (lpWorkX[i - 1] + lpWorkX[i + 1]) / 2;
					lpWorkY[i] = (lpWorkY[i - 1] + lpWorkY[i + 1]) / 2;
				}
			}

			/* lastly overwrite midpoint of control points with */
			/* average of previous calculation, yeilds midpoint on bezier */

			for (i = nTotalPoints - 4; i > 0; i -= 6) {
				lpWorkX[i] = (lpWorkX[i - 1] + lpWorkX[i + 1]) / 2;
				lpWorkY[i] = (lpWorkY[i - 1] + lpWorkY[i + 1]) / 2;
			}

			nLinePoints = (nTotalPoints / 3) + 1;
		}

		/* have enough bezier endpoints, move bezier endpoints to */
		/* polyline CGIPOINTs array */

		for (i = 0; i < nSteps; i++) {
			j = (3 * i);
			// consider converting to a float & running through the point convertor if needed */
			
			PrecisionPoint ptTemp = new PrecisionPoint(lpWorkX[j], lpWorkY[j]);

			thePolyPoints.addPoint(ptTemp);
		}

		return (bRC);
	}
}
