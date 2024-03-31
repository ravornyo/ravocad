package com.ravocad.diagram.requests;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.requests.CreationFactory;

import com.ravocad.notation.NotationFactory;

public class NotationCreationFactory implements CreationFactory {

	private EClass clazz;

	public NotationCreationFactory(EClass aClass) {
		clazz = aClass;
	}
	
	@Override
	public Object getNewObject() {
		try {
			return NotationFactory.eINSTANCE.create(clazz);
		} catch (Exception exc) {
			return null;
		}
	}

	@Override
	public Object getObjectType() {
		return clazz;
	}

}
