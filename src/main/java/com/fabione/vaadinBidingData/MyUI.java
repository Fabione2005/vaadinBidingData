package com.fabione.vaadinBidingData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.annotation.WebServlet;

import com.fabione.clases.People;
import com.fabione.interfaces.AddPerson;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8185915822701740181L;

	@Override
    protected void init(VaadinRequest vaadinRequest) {
		ArrayList<People> peopleAndarts = new ArrayList<People>();
        People p1 = new People();
        Binder<People> binder = new Binder<People>();
        final FormLayout form = new FormLayout();
        Label l1 = new Label("Please fill Below Form");
        Label labelName = new Label("Name--");
        TextField name = new TextField();
        binder.bind(name,People::getName,People::setName);
        Label labelAge = new Label("Age---");
        TextField age = new TextField();
        //binder.bind(age.getValue()),People::getAge,People::setAge);
        binder.forField(age).withNullRepresentation("").withConverter(new StringToIntegerConverter ( Integer.valueOf ( 0 ), "integers only" )).withValidator(ageUser -> ageUser > 0, "edad ingresada incorrecta").bind(People::getAge,People::setAge);
        Label labelProfession = new Label("Profession---");
        TextField profession = new TextField();
        binder.bind(profession,People::getProfession,People::setProfession);
        Label labelNationality = new Label("Nationality---");
        TextField nationality = new TextField();
        binder.bind(nationality,People::getNationality,People::setNationality);
        Button button = new Button("Process..");
        form.addComponents(l1,labelName,name,labelAge,age,labelProfession,profession,labelNationality,nationality,button);
        binder.setBean(p1);
        setContent(form);
        
        button.addClickListener(new Button.ClickListener() {
		/**
			 * 
			 */
			private static final long serialVersionUID = 5887537478463132204L;

		@Override
		public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
			
			if(binder.isValid()) {
				final VerticalLayout layout = new VerticalLayout();
				Label labelInfo = new Label("Registro exitoso!!");
				Label info = new Label(binder.getBean().getName()+" "+binder.getBean().getAge()+" "+binder.getBean().getNationality()+" "+binder.getBean().getProfession());
				layout.addComponent(labelInfo);
				layout.addComponent(info);
				Button buttonBack = new Button("Go back to form..");
				Button buttonSeeAll = new Button("See all registrared..");
				layout.addComponent(buttonBack);
				layout.addComponent(buttonSeeAll);
				setContent(layout);
				AddPerson add = (p,b) -> p.add(b);
				add.addPerson(peopleAndarts,new People(binder.getBean()));
				peopleAndarts.stream().forEach(System.out::print);
				buttonBack.addClickListener(new Button.ClickListener() {
					
					private static final long serialVersionUID = 3255432766847634889L;

					@Override
					public void buttonClick(ClickEvent event) {
						setContent(form);
						
						
					}
				});
				buttonSeeAll.addClickListener(new Button.ClickListener() {
					
					private static final long serialVersionUID = 8462101421731560938L;

					@Override
					public void buttonClick(ClickEvent event) {
						Iterator<People> it1 = peopleAndarts.iterator();
						while(it1.hasNext()) {
							People persona = it1.next();
							Label persons = new Label(persona.toString());
							layout.addComponent(persons);
						}
						layout.removeComponent(buttonSeeAll);
						setContent(layout);
					}
				});
			}
		}
	});
        
    }
	
	

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
