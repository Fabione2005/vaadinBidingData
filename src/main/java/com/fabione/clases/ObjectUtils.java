package com.fabione.clases;

import java.util.ArrayList;

import com.vaadin.data.Binder;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ObjectUtils {

	private ArrayList<People> peopleAndarts = null;
	private Binder<People> binder = null;
	private FormLayout form = null;
	
	private Label l1 = null;
	private Button button = null;
	private VerticalLayout layout = null;
	private ComboBox<String> comboBox = null;
	
	
	

	public ObjectUtils(ArrayList<People> peopleAndarts,Binder<People> binder,FormLayout form,Label l1,VerticalLayout layout){
		this.peopleAndarts = peopleAndarts;
		this.binder=binder;
		this.form=form;
		this.l1=l1;
		this.layout=layout;
	}
	
	public ObjectUtils(){
		
	}
	
	public ComboBox<String> getComboBox() {
		return comboBox;
	}

	public void setComboBox(ComboBox<String> comboBox) {
		this.comboBox = comboBox;
	}
	
	public ArrayList<People> getPeopleAndarts() {
		return peopleAndarts;
	}

	public void setPeopleAndarts(ArrayList<People> peopleAndarts) {
		this.peopleAndarts.addAll(peopleAndarts);
	}

	public void setLayout(VerticalLayout layout) {
		this.layout = layout;
	}

	public void setForm(FormLayout form) {
		this.form = form;
	}
	public Binder<People> getBinder() {
		return binder;
	}
	public void setBinder(Binder<People> binder) {
		this.binder = binder;
	}
	public Label getL1() {
		return l1;
	}
	public void setL1(Label l1) {
		this.l1 = l1;
	}
	public Button getButton() {
		return button;
	}
	public void setButton(Button button) {
		this.button = button;
	}
	public FormLayout getForm() {
		return form;
	}
	public VerticalLayout getLayout() {
		return layout;
	}
	
	
}
