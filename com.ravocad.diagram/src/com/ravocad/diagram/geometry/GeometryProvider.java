package com.ravocad.diagram.geometry;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.swt.graphics.Path;

import com.ravocad.diagram.constants.VisualConstants;

public class GeometryProvider {
	
	private final static GeometryProvider instance = new GeometryProvider();
	
	private Map<String, Class<?>> semanticToGeometryMap = new HashMap<String, Class<?>>();
	{
		semanticToGeometryMap.put(VisualConstants.ARC, ArcGeometryHelper.class);
		semanticToGeometryMap.put(VisualConstants.ELLIPSE, EllipseGeometryHelper  .class);
		semanticToGeometryMap.put(VisualConstants.LINE, PolylineGeometryHelper.class);
		semanticToGeometryMap.put(VisualConstants.POLYLINE, PolylineGeometryHelper.class);	
		semanticToGeometryMap.put(VisualConstants.POLYGON, PolygonGeometryHelper.class);
		semanticToGeometryMap.put(VisualConstants.RECTANGLE, PolygonGeometryHelper.class);
		semanticToGeometryMap.put(VisualConstants.SMOOTH_POLYLINE, SplineGeometryHelper.class);
		semanticToGeometryMap.put(VisualConstants.QUAD_CURVE, QuadGeometryHelper.class);
		semanticToGeometryMap.put(VisualConstants.CUBIC_CURVE, CubicGeometryHelper.class);
	}
	
    public static GeometryProvider getInstance() {
    	return instance;
    }

	public Path createPath(String semanticHint, PointList points) {
		Class<?> geometryClass = getGeometryClass(semanticHint);
		IGeometryHelper geometry = createNewGeometry(geometryClass);
		return geometry.createPath(points);
	}

	protected Class<?> getGeometryClass(String semanticHint) {
		Class<?> clazz = semanticToGeometryMap.get(semanticHint);
		return clazz;
	}
	
	private IGeometryHelper createNewGeometry(Class<?> geometryClass) {
		try {
			Constructor<?> constructor = getCreationConstructor(geometryClass);
			Assert.isNotNull(constructor);

			return (constructor == null)? null : (IGeometryHelper) constructor.newInstance();
		} catch (Throwable e) {
			return null;
		}
	}

	private Constructor<?> getCreationConstructor(Class<?> geometryClass) {
		Assert.isNotNull(geometryClass);
		if (geometryClass != null) {
			Constructor<?>[] consts = geometryClass.getConstructors();
			if (consts.length != 0)
				return consts[0];
		}
		return null;
	}

}
