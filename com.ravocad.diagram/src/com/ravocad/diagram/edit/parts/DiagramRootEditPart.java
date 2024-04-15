package com.ravocad.diagram.edit.parts;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IScalablePane;
import org.eclipse.draw2d.ScalableFreeformLayeredPane;
import org.eclipse.draw2d.ScaledGraphics;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.swt.graphics.LineAttributes;

public class DiagramRootEditPart extends ScalableFreeformRootEditPart {
	
	protected ScalableFreeformLayeredPane createScaledLayers() {
		ScalableFreeformLayeredPane layers = createScalableFreeformLayeredPane();
		layers.add(createGridLayer(), GRID_LAYER);
		layers.add(getPrintableLayers(), PRINTABLE_LAYERS);
		layers.add(new FeedbackLayer(), SCALED_FEEDBACK_LAYER);
		return layers;
	}
	
	protected ScalableFreeformLayeredPane createScalableFreeformLayeredPane() {
		return new DiagramScalableFreeformLayeredPane();
	}
	
	static protected class DiagramScalableFreeformLayeredPane extends ScalableFreeformLayeredPane {
		@Override
		protected void paintClientArea(final Graphics graphics) {
			if (getChildren().isEmpty()) {
				return;
			}
			if (getScale() == 1.0) {
				super.paintClientArea(graphics);
			} else {
				Graphics graphicsToUse = prepareScaledGraphics(graphics, this);
				paintChildren(graphicsToUse);
				cleanupScaledGraphics(graphics, graphicsToUse);
			}
		}
		protected Graphics prepareScaledGraphics(final Graphics graphics, IScalablePane figurePane) {
			Graphics graphicsToUse = (figurePane.useScaledGraphics())? new DiagramScaledGraphics(graphics) : graphics;
			if (!figurePane.optimizeClip()) {
				graphicsToUse.clipRect(figurePane.getBounds().getShrinked(figurePane.getInsets()));
			}
			graphicsToUse.scale(figurePane.getScale());
			graphicsToUse.pushState();
			return graphicsToUse;
		}
		protected void cleanupScaledGraphics(final Graphics graphics, final Graphics graphicsUsed) {
			graphicsUsed.popState();
			if (graphicsUsed != graphics) {
				graphicsUsed.dispose();
			}
			graphics.restoreState();
		}
	}
	
	static class DiagramScaledGraphics extends ScaledGraphics {
		private final Graphics graphics;
		public DiagramScaledGraphics(Graphics g) {
			super(g);
			this.graphics = g;
		}
		@Override
		public void setLineAttributes(LineAttributes attributes) {
			getGraphics().setLineWidthFloat(attributes.width);
		}
		public Graphics getGraphics() {
			return graphics;
		}		
	}
	
	class FeedbackLayer extends FreeformLayer {
		FeedbackLayer() {
			setEnabled(false);
		}
	}

}
