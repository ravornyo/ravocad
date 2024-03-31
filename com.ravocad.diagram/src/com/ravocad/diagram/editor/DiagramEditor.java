package com.ravocad.diagram.editor;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor.PropertyValueWrapper;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.CopyTemplateAction;
import org.eclipse.gef.ui.actions.DirectEditAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.PasteTemplateAction;
import org.eclipse.gef.ui.actions.PrintAction;
import org.eclipse.gef.ui.actions.ToggleGridAction;
import org.eclipse.gef.ui.actions.ToggleSnapToGeometryAction;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.gef.ui.properties.UndoablePropertySheetEntry;
import org.eclipse.gef.ui.properties.UndoablePropertySheetPage;
import org.eclipse.gef.ui.rulers.RulerComposite;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertySheetPage;

import com.ravocad.diagram.DiagramDebugOptions;
import com.ravocad.diagram.DiagramPlugin;
import com.ravocad.diagram.Trace;
import com.ravocad.diagram.edit.parts.GraphicalEditPartFactory;
import com.ravocad.diagram.i10n.Messages;
import com.ravocad.diagram.palette.PaletteCustomizer;
import com.ravocad.diagram.palette.PaletteFactory;
import com.ravocad.diagram.palette.PalettePreferences;
import com.ravocad.diagram.palette.PaletteTransferDragSourceListener;
import com.ravocad.diagram.palette.PaletteTransferDropTargetListener;
import com.ravocad.notation.Diagram;
import com.ravocad.notation.NotationFactory;
import com.ravocad.notation.provider.NotationItemProviderAdapterFactory;


public class DiagramEditor extends GraphicalEditorWithFlyoutPalette {
	
	public static final String ID = "com.ravocad.diagram.editor.DiagramEditorID";

	private KeyHandler sharedKeyHandler;
	private PaletteRoot paletteRoot;
	private PropertySheetPage propertyPage;

	private final Resource resource = new XMLResourceImpl();
	private Diagram diagram;
	private ResourceTracker resourceListener = new ResourceTracker();
	
	private RulerComposite rulerComposite;

	private boolean editorSaving = false;

	public DiagramEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}
	
	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);
	}

	protected void closeEditor(boolean save) {
		getSite().getPage().closeEditor(DiagramEditor.this, save);
	}

	public void commandStackChanged(EventObject event) {
		firePropertyChange(IEditorPart.PROP_DIRTY);
		super.commandStackChanged(event);
	}

	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		ScrollingGraphicalViewer viewer = (ScrollingGraphicalViewer) getGraphicalViewer();

		ScalableFreeformRootEditPart root = new ScalableFreeformRootEditPart();
		
		viewer.setRootEditPart(root);
		viewer.setEditPartFactory(new GraphicalEditPartFactory());
		viewer.setProperty(SnapToGrid.PROPERTY_GRID_SPACING, new Dimension(10, 10));
		
		ContextMenuProvider provider = new DiagramContextMenuProvider(viewer, getActionRegistry());
		viewer.setContextMenu(provider);
		getSite().registerContextMenu(provider, viewer);

		ZoomManager zoomManager = root.getZoomManager();
		getActionRegistry().registerAction(new ZoomInAction(zoomManager));
		getActionRegistry().registerAction(new ZoomOutAction(zoomManager));
		
		List<String> zoomContributions = new ArrayList<String>(3);
		zoomContributions.add(ZoomManager.FIT_ALL);
		zoomContributions.add(ZoomManager.FIT_WIDTH);
		zoomContributions.add(ZoomManager.FIT_HEIGHT);
		zoomManager.setZoomLevelContributions(zoomContributions);

		viewer.setKeyHandler(new GraphicalViewerKeyHandler(viewer).setParent(getCommonKeyHandler()));

		loadProperties();

		// Actions
		IAction snapAction = new ToggleSnapToGeometryAction(getGraphicalViewer());
		getActionRegistry().registerAction(snapAction);

		IAction showGrid = new ToggleGridAction(getGraphicalViewer());
		getActionRegistry().registerAction(showGrid);

		Listener listener = new Listener() {
			public void handleEvent(Event event) {
				handleActivationChanged(event);
			}
		};
		getGraphicalControl().addListener(SWT.Activate, listener);
		getGraphicalControl().addListener(SWT.Deactivate, listener);
	}

	protected void writeToOutputStream(OutputStream os) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(os);
		out.writeObject(getDiagram());
		out.close();
	}

	protected CustomPalettePage createPalettePage() {
		return new CustomPalettePage(getPaletteViewerProvider()) {
			public void init(IPageSite pageSite) {
				super.init(pageSite);
				IAction copy = getActionRegistry().getAction(ActionFactory.COPY.getId());
				pageSite.getActionBars().setGlobalActionHandler(ActionFactory.COPY.getId(), copy);
			}
		};
	}

	protected PaletteViewerProvider createPaletteViewerProvider() {
		return new PaletteViewerProvider(getEditDomain()) {
			protected void configurePaletteViewer(PaletteViewer viewer) {
				super.configurePaletteViewer(viewer);
				viewer.setCustomizer(new PaletteCustomizer());
				viewer.addDragSourceListener(new PaletteTransferDragSourceListener(viewer));
			}
		};
	}
	
	@Override
	public void dispose() {
		getSite().getWorkbenchWindow().getPartService().removePartListener(partListener);
		partListener = null;
		((IFileEditorInput) getEditorInput()).getFile().getWorkspace().removeResourceChangeListener(resourceListener);
		super.dispose();
	}

	@Override
	public void doSave(final IProgressMonitor progressMonitor) {
		editorSaving = true;
		SafeRunner.run(new SafeRunnable() {
			public void run() throws Exception {
				saveProperties();
				resource.save(getSaveOptions());
				getCommandStack().markSaveLocation();
			}
		});
		editorSaving = false;
	}

	@Override
	public void doSaveAs() {
		performSaveAs();
	}

	/**
	 * Performs a save as and reports the result state back to the
	 * given progress monitor. This default implementation does nothing.
	 * Subclasses may reimplement.
	 *
	 * @param progressMonitor the progress monitor for communicating result state or <code>null</code>
	 */
	protected boolean performSaveAs() {
		SaveAsDialog dialog = new SaveAsDialog(getSite().getWorkbenchWindow().getShell());
		dialog.setOriginalFile(((IFileEditorInput) getEditorInput()).getFile());
		dialog.open();
		IPath path = dialog.getResult();

		if (path == null) {
			return false;
		}
		
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		final IFile file = workspace.getRoot().getFile(path);

		if (!file.exists()) {
			WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
				public void execute(final IProgressMonitor monitor) {
					saveProperties();
					try {
						saveProperties();
						resource.save(getSaveOptions());
						getCommandStack().markSaveLocation();
					} catch (Exception e) {
						Trace.catching(
								DiagramPlugin.getInstance(),
								DiagramDebugOptions.EXCEPTIONS_CATCHING,
								getClass(), e.getMessage(), e);
					}
				}
			};
			try {
				new ProgressMonitorDialog(getSite().getWorkbenchWindow().getShell()).run(false, true, op);
			} catch (Exception e) {
				Trace.catching(
						DiagramPlugin.getInstance(),
						DiagramDebugOptions.EXCEPTIONS_CATCHING,
						getClass(), e.getMessage(), e);
			}
		}

		try {
			superSetInput(new FileEditorInput(file));
			getCommandStack().markSaveLocation();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;		
	}
	
	private Map<?, ?> getSaveOptions() {
		HashMap<String, Object> saveOptions = new HashMap<String, Object>();
		saveOptions.put(XMLResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$
		saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
		return saveOptions;
	}
	
	@Override
	protected void setInput(IEditorInput input) {
		superSetInput(input);

		if(input instanceof IFileEditorInput) {
			IFileEditorInput fileInput = (IFileEditorInput) input;
			IFile file = fileInput.getFile();
			URI uri = URI.createURI(file.getFullPath().toString());
			resource.setURI(uri);
			try {
				resource.load(Collections.EMPTY_MAP);
				Diagram diagram = (Diagram) resource.getContents().get(0);
				setDiagram(diagram);
			} catch(IOException e) {
				Trace.catching(
						DiagramPlugin.getInstance(),
						DiagramDebugOptions.EXCEPTIONS_CATCHING,
						getClass(),
						e.getMessage(),
						e);
				
				Diagram diagram = NotationFactory.eINSTANCE.createDiagram();
				resource.getContents().add(diagram);
				setDiagram(diagram);
			}
		}
		if (!editorSaving) {
			if (getGraphicalViewer() != null) {
				getGraphicalViewer().setContents(getDiagram());
				loadProperties();
			}
		}
	}

	protected void superSetInput(IEditorInput input) {
		// The workspace never changes for an editor. So, removing and re-adding the
		// resourceListener is not necessary. But it is being done here for the sake
		// of proper implementation. Plus, the resourceListener needs to be added
		// to the workspace the first time around.
		if (getEditorInput() != null) {
			IFile file = ((IFileEditorInput) getEditorInput()).getFile();
			file.getWorkspace().removeResourceChangeListener(resourceListener);
		}

		super.setInput(input);

		if (getEditorInput() != null) {
			IFile file = ((IFileEditorInput) getEditorInput()).getFile();
			file.getWorkspace().addResourceChangeListener(resourceListener);
			setPartName(file.getName());
		}
	}

	protected void setSite(IWorkbenchPartSite site) {
		super.setSite(site);
		getSite().getWorkbenchWindow().getPartService().addPartListener(partListener);
	}
	
	public void setDiagram(Diagram diagram) {
		this.diagram = diagram;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object getAdapter(Class type) {
		if(type.equals(IPropertySheetPage.class)) {
			if(propertyPage == null) {
                propertyPage = (UndoablePropertySheetPage)super.getAdapter(type);
                // A new PropertySourceProvider was implemented to fetch the model
                // from the edit part when required. The property source is provided
                // by the generated EMF classes and wrapped by the AdapterFactoryContentProvider
                // to yield standard eclipse interfaces.
                IPropertySourceProvider sourceProvider = new IPropertySourceProvider() {
                    IPropertySourceProvider modelPropertySourceProvider = 
                    		new AdapterFactoryContentProvider(new NotationItemProviderAdapterFactory());
 
                    @Override
                    public IPropertySource getPropertySource(Object object) {
                        IPropertySource source = null;
                        if(object instanceof EditPart) {
                            source = modelPropertySourceProvider.getPropertySource(((EditPart) object).getModel());
                        } else {
                            source = modelPropertySourceProvider.getPropertySource(object);
                        }
                        if(source != null) {
                            return new UnwrappingPropertySource(source);
                        } else {
                            return null;
                        }
                    }
                };
                UndoablePropertySheetEntry root = new UndoablePropertySheetEntry(getCommandStack());
                root.setPropertySourceProvider(sourceProvider);
                propertyPage.setRootEntry(root);
            }
            return propertyPage;
		}
        if (type == ZoomManager.class) {
			return getGraphicalViewer().getProperty(ZoomManager.class.toString());
		}
		return super.getAdapter(type);
	}

	@Override
	protected Control getGraphicalControl() {
		return rulerComposite;
	}

	/**
	 * Returns the KeyHandler with common bindings for both the Outline and
	 * Graphical Views. For example, delete is a common action.
	 */
	protected KeyHandler getCommonKeyHandler() {
		if (sharedKeyHandler == null) {
			sharedKeyHandler = new KeyHandler();
			sharedKeyHandler.put(KeyStroke.getPressed(SWT.F2, 0), getActionRegistry().getAction(GEFActionConstants.DIRECT_EDIT));
			sharedKeyHandler.put(KeyStroke.getPressed(SWT.CONTROL, 0), getActionRegistry().getAction(GEFActionConstants.DIRECT_EDIT));
		}
		return sharedKeyHandler;
	}

	protected Diagram getDiagram() {
		return diagram;
	}
	
	@Override
	protected PalettePreferences getPalettePreferences() {
		return new PalettePreferences();
	}

	@Override
	protected PaletteRoot getPaletteRoot() {
		if (paletteRoot == null) {
			paletteRoot = PaletteFactory.createPalette();
		}
		return paletteRoot;
	}

	protected void handleActivationChanged(Event event) {
		IAction copy = null;
		if (event.type == SWT.Deactivate)
			copy = getActionRegistry().getAction(ActionFactory.COPY.getId());
		if (getEditorSite().getActionBars().getGlobalActionHandler(ActionFactory.COPY.getId()) != copy) {
			//getEditorSite().getActionBars().setGlobalActionHandler(ActionFactory.COPY.getId(), copy);
			getEditorSite().getActionBars().updateActionBars();
		}
	}

	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		getGraphicalViewer().setContents(getDiagram());

		getGraphicalViewer().addDropTargetListener(
				(TransferDropTargetListener) new PaletteTransferDropTargetListener(getGraphicalViewer()));
	}

	@SuppressWarnings("unchecked")
	protected void createActions() {
		super.createActions();

		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		
		ActionRegistry registry = getActionRegistry();
		IAction action;

		action = new CopyTemplateAction(this);
		action.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
        action.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY_DISABLED));
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new PasteTemplateAction(this);
		action.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
        action.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE_DISABLED));
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		
		action = new PrintAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		
		action = new DirectEditAction((IWorkbenchPart) this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
	}

	@Override
	protected void createGraphicalViewer(Composite parent) {
		rulerComposite = new RulerComposite(parent, SWT.NONE);
		super.createGraphicalViewer(rulerComposite);
		rulerComposite.setGraphicalViewer((ScrollingGraphicalViewer) getGraphicalViewer());
	}

	protected void loadProperties() {
		getGraphicalViewer().setProperty(RulerProvider.PROPERTY_RULER_VISIBILITY, getDiagram().isRulerVisible());
		getGraphicalViewer().setProperty(SnapToGrid.PROPERTY_GRID_VISIBLE, getDiagram().isGridVisible());
		getGraphicalViewer().setProperty(SnapToGrid.PROPERTY_GRID_ENABLED, getDiagram().isSnapToGrid());
		getGraphicalViewer().setProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED, getDiagram().isSnapToGrid());

		// Zoom
		ZoomManager manager = (ZoomManager) getGraphicalViewer().getProperty(ZoomManager.class.toString());
		if (manager != null) {
			manager.setZoom(getDiagram().getZoom());
		}
		// Scroll-wheel Zoom
		getGraphicalViewer().setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.MOD1), MouseWheelZoomHandler.SINGLETON);

	}

	protected void saveProperties() {
		getDiagram().setRulerVisible(
				((Boolean) getGraphicalViewer().getProperty(RulerProvider.PROPERTY_RULER_VISIBILITY)).booleanValue());
		getDiagram().setGridVisible(
				((Boolean) getGraphicalViewer().getProperty(SnapToGrid.PROPERTY_GRID_VISIBLE)).booleanValue());
		getDiagram().setSnapToGrid(
				((Boolean) getGraphicalViewer().getProperty(SnapToGrid.PROPERTY_GRID_ENABLED)).booleanValue());
		getDiagram().setSnapToGeometry(
				((Boolean) getGraphicalViewer().getProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED)).booleanValue());
		ZoomManager manager = (ZoomManager) getGraphicalViewer().getProperty(ZoomManager.class.toString());
		if (manager != null) {
			getDiagram().setZoom(manager.getZoom());
		}
	}

	// This class listens to changes to the file system in the workspace, and
	// makes changes accordingly.
	// 1) An open, saved file gets deleted -> close the editor
	// 2) An open file gets renamed or moved -> change the editor's input
	// accordingly
	class ResourceTracker implements IResourceChangeListener, IResourceDeltaVisitor {
		public void resourceChanged(IResourceChangeEvent event) {
			IResourceDelta delta = event.getDelta();
			try {
				if (delta != null)
					delta.accept(this);
			} catch (CoreException exception) {
				exception.printStackTrace();
			}
		}
		public boolean visit(IResourceDelta delta) {
			if (delta == null || !delta.getResource().equals(((IFileEditorInput) getEditorInput()).getFile())) {
				return true;
			}
			if (delta.getKind() == IResourceDelta.REMOVED) {
				Display display = getSite().getShell().getDisplay();
				if ((IResourceDelta.MOVED_TO & delta.getFlags()) == 0) { // if the file was deleted
					// NOTE: The case where an open, unsaved file is deleted is being handled by the
					// PartListener added to the Workbench in the initialize() method.
					display.asyncExec(new Runnable() {
						public void run() {
							if (!isDirty()) {
								closeEditor(false);
							}
						}
					});
				} else { // else if it was moved or renamed
					final IFile newFile = ResourcesPlugin.getWorkspace().getRoot().getFile(delta.getMovedToPath());
					display.asyncExec(new Runnable() {
						public void run() {
							superSetInput(new FileEditorInput(newFile));
						}
					});
				}
			} else if (delta.getKind() == IResourceDelta.CHANGED) {
				if (!editorSaving) {
					// the file was overwritten somehow (could have been replaced by another
					// version in the respository)
					final IFile newFile = ResourcesPlugin.getWorkspace().getRoot().getFile(delta.getFullPath());
					Display display = getSite().getShell().getDisplay();
					display.asyncExec(new Runnable() {
						public void run() {
							setInput(new FileEditorInput(newFile));
							getCommandStack().flush();
						}
					});
				}
			}
			return false;
		}
	}

	private IPartListener partListener = new IPartListener() {
		// If an open, unsaved file was deleted, query the user to either do a "Save As"
		// or close the editor.
		public void partActivated(IWorkbenchPart part) {
			if (part != DiagramEditor.this)
				return;
			if (((IFileEditorInput) getEditorInput()).getFile().exists() == false) {
				Shell shell = getSite().getShell();
				String title = Messages.getString("DiagramEditor_File_Deleted_Title_UI");
				String message = Messages.getString("DiagramEditor_File_Deleted_Without_Save_Info");
				String[] buttons = { 
						Messages.getString("DiagramEditor_Save_Button_UI"),
						Messages.getString("DiagramEditor_Close_Button_UI") };
				MessageDialog dialog = new MessageDialog(shell, title, null, message, 
						MessageDialog.QUESTION, buttons, 0);
				if (dialog.open() == 0) {
					if (!performSaveAs())
						partActivated(part);
				} else {
					closeEditor(false);
				}
			}
		}
		public void partBroughtToTop(IWorkbenchPart part) {
		}
		public void partClosed(IWorkbenchPart part) {
		}
		public void partDeactivated(IWorkbenchPart part) {
		}
		public void partOpened(IWorkbenchPart part) {
		}
	};
	
	public class UnwrappingPropertySource implements IPropertySource {
        private IPropertySource source;
        public UnwrappingPropertySource(final IPropertySource source) {
            this.source = source;
        }
        @Override
        public Object getEditableValue() {
            Object value = source.getEditableValue();
            if(value instanceof PropertyValueWrapper) {
                PropertyValueWrapper wrapper = (PropertyValueWrapper) value;
                return wrapper.getEditableValue(null);
            } else {
                return source.getEditableValue();
            }
        }
        @Override
        public IPropertyDescriptor[] getPropertyDescriptors() {
            return source.getPropertyDescriptors();
        }
        @Override
        public Object getPropertyValue(Object id) {
            Object value = source.getPropertyValue(id);
            if(value instanceof PropertyValueWrapper) {
                PropertyValueWrapper wrapper = (PropertyValueWrapper) value;
                return wrapper.getEditableValue(null);
            } else {
                return source.getPropertyValue(id);
            }
        }
        @Override
        public boolean isPropertySet(Object id) {
            return source.isPropertySet(id);
        }
        @Override
        public void resetPropertyValue(Object id) {
            source.resetPropertyValue(id);
        }
        @Override
        public void setPropertyValue(Object id, Object value) {
            source.setPropertyValue(id, value);
        }
    }	
	
}
