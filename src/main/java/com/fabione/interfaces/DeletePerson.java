package com.fabione.interfaces;

import java.util.LinkedList;

import com.fabione.clases.People;
import com.vaadin.data.Binder;

public interface DeletePerson {

	public void deletePerson(LinkedList<People> list,Binder<People> binder);
	
}
