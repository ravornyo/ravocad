package com.ravocad.diagram.palette;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;

import com.ravocad.diagram.DiagramPlugin;
import com.ravocad.diagram.i10n.Messages;
import com.ravocad.diagram.tools.ArcTool;
import com.ravocad.diagram.tools.CubicTool;
import com.ravocad.diagram.tools.EllipseTool;
import com.ravocad.diagram.tools.LineTool;
import com.ravocad.diagram.tools.PolygonTool;
import com.ravocad.diagram.tools.PolylineTool;
import com.ravocad.diagram.tools.QuadTool;
import com.ravocad.diagram.tools.RectangleTool;
import com.ravocad.diagram.tools.SmoothPolylineTool;
import com.ravocad.diagram.tools.TextTool;
import com.ravocad.notation.NotationPackage;

public class PaletteFactory {

	public static PaletteRoot createPalette() {
		PaletteRoot palette = new PaletteRoot();
		palette.add(addCommonTools(palette));
		palette.add(createDrawingTools());
		return palette;
	}
	
	private static PaletteGroup addCommonTools(final PaletteContainer parent) {
		final PaletteGroup commonTools = new PaletteGroup("Common Tools");
		final SelectionToolEntry selectionTool = new SelectionToolEntry();
		commonTools.add(selectionTool);
		if (parent instanceof PaletteRoot) {
			((PaletteRoot) parent).setDefaultEntry(selectionTool);
		}
		commonTools.add(new MarqueeToolEntry());
		
		PaletteToolEntry textTool = new PaletteToolEntry(
				Messages.getString("Palette_Text_Tool_Label"), 
				Messages.getString("Palette_Text_Tool_desc"), 
				NotationPackage.eINSTANCE.getText(),
				TextTool.class, 
				DiagramPlugin.getBundledImageDescriptor("icons/obj16/text.png"), 
				DiagramPlugin.getBundledImageDescriptor("icons/obj24/text@2x.png"));
		commonTools.add(textTool);
		
		return commonTools;
	}

	private static PaletteEntry createDrawingTools() {
		PaletteDrawer drawer = new PaletteDrawer(Messages.getString("Palette_Path_Drawer_Label"));
		
		EClass template = NotationPackage.eINSTANCE.getPath();
		PaletteToolEntry entry = new PaletteToolEntry(
				Messages.getString("Palette_Line_Tool_Label"), 
				Messages.getString("Palette_Line_Tool_desc"), 
				template,
				LineTool.class, 
				DiagramPlugin.getBundledImageDescriptor("icons/obj16/line.png"), 
				DiagramPlugin.getBundledImageDescriptor("icons/obj24/line@2x.png"));
		drawer.add(entry);

		entry = new PaletteToolEntry(
				Messages.getString("Palette_Polyline_Tool_Label"), 
				Messages.getString("Palette_Polyline_Tool_desc"), 
				template,
				PolylineTool.class, 
				DiagramPlugin.getBundledImageDescriptor("icons/obj16/polyline.png"), 
				DiagramPlugin.getBundledImageDescriptor("icons/obj24/polyline@2x.png"));
		drawer.add(entry);
		
		entry = new PaletteToolEntry(
				Messages.getString("Palette_Rectangle_Tool_Label"), 
				Messages.getString("Palette_Rectangle_Tool_desc"), 
				template,
				RectangleTool.class, 
				DiagramPlugin.getBundledImageDescriptor("icons/obj16/rectangle.png"), 
				DiagramPlugin.getBundledImageDescriptor("icons/obj24/rectangle@2x.png"));
		drawer.add(entry);
		
		entry = new PaletteToolEntry(
				Messages.getString("Palette_Polygon_Tool_Label"), 
				Messages.getString("Palette_Polygon_Tool_desc"), 
				template,
				PolygonTool.class, 
				DiagramPlugin.getBundledImageDescriptor("icons/obj16/polygon.png"), 
				DiagramPlugin.getBundledImageDescriptor("icons/obj24/polygon@2x.png"));
		drawer.add(entry);
		
		entry = new PaletteToolEntry(
				Messages.getString("Palette_Arc_Tool_Label"), 
				Messages.getString("Palette_Arc_Tool_desc"), 
				template,
				ArcTool.class, 
				DiagramPlugin.getBundledImageDescriptor("icons/obj16/arc.png"), 
				DiagramPlugin.getBundledImageDescriptor("icons/obj24/arc@2x.png"));
		drawer.add(entry);
		
		entry = new PaletteToolEntry(
				Messages.getString("Palette_Ellipse_Tool_Label"), 
				Messages.getString("Palette_Ellipse_Tool_desc"), 
				template,
				EllipseTool.class, 
				DiagramPlugin.getBundledImageDescriptor("icons/obj16/ellipse.png"), 
				DiagramPlugin.getBundledImageDescriptor("icons/obj24/ellipse@2x.png"));
		drawer.add(entry);
		
		entry = new PaletteToolEntry(
				Messages.getString("Palette_SmoothPolyline_Tool_Label"), 
				Messages.getString("Palette_SmoothPolyline_Tool_desc"), 
				template,
				SmoothPolylineTool.class, 
				DiagramPlugin.getBundledImageDescriptor("icons/obj16/smoothPolyline.png"), 
				DiagramPlugin.getBundledImageDescriptor("icons/obj24/smoothPolyline@2x.png"));
		drawer.add(entry);
		
		entry = new PaletteToolEntry(
				Messages.getString("Palette_QuadCurve_Tool_Label"), 
				Messages.getString("Palette_QuadCurve_Tool_desc"), 
				template,
				QuadTool.class,
				DiagramPlugin.getBundledImageDescriptor("icons/obj16/quad.png"), 
				DiagramPlugin.getBundledImageDescriptor("icons/obj24/quad@2x.png"));
		drawer.add(entry);
		
		entry = new PaletteToolEntry(
				Messages.getString("Palette_CubicCurve_Tool_Label"), 
				Messages.getString("Palette_CubicCurve_Tool_desc"), 
				template,
				CubicTool.class, 
				DiagramPlugin.getBundledImageDescriptor("icons/obj16/cubic.png"), 
				DiagramPlugin.getBundledImageDescriptor("icons/obj24/cubic@2x.png"));
		drawer.add(entry);
		
		return drawer;
	}

}
