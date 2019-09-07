package com.fabione.vaadinBidingData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.annotation.WebServlet;

import com.fabione.clases.ObjectUtils;
import com.fabione.clases.People;
import com.fabione.interfaces.AddPerson;
import com.fabione.interfaces.DeletePerson;
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
import com.vaadin.ui.ComboBox;

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
		ObjectUtils buttonContainer = new ObjectUtils();
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
        ObjectUtils obj = new ObjectUtils();
        obj.setForm(form);
        obj.setBinder(binder);
        
        button.addClickListener(new Button.ClickListener() {
		/**
			 * 
			 */
			private static final long serialVersionUID = 5887537478463132204L;

		@Override
		public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
			
			if(obj.getBinder().isValid()) {
				//final VerticalLayout layout = 
				obj.setLayout(new VerticalLayout());
				Label labelInfo = new Label("Registro exitoso!!");
				Label info = new Label(obj.getBinder().getBean().getName()+" "+obj.getBinder().getBean().getAge()+" "+obj.getBinder().getBean().getNationality()+" "+obj.getBinder().getBean().getProfession());
				
				obj.getLayout().addComponent(labelInfo);
				obj.getLayout().addComponent(info);
				Button buttonBack = new Button("Go back to form..");
				Button buttonSeeAll = new Button("See all registrared..");
				Button buttonDeleteThis = new Button("Delete this register..");
				Button buttonModifyThis = new Button("Modify a user..");
				buttonContainer.setLayout(new VerticalLayout());
				buttonContainer.getLayout().addComponent(buttonBack);
				buttonContainer.getLayout().addComponent(buttonDeleteThis);
				buttonContainer.getLayout().addComponent(buttonSeeAll);
				obj.getLayout().addComponent(buttonBack);
				obj.getLayout().addComponent(buttonSeeAll);
				obj.getLayout().addComponent(buttonDeleteThis);
				obj.getLayout().addComponent(buttonModifyThis);
				
				setContent(obj.getLayout());
				addThis(obj,peopleAndarts);
				obj.setPeopleAndarts(peopleAndarts);
				
				buttonBack.addClickListener(new Button.ClickListener() {
					
					private static final long serialVersionUID = 3255432766847634889L;

					@Override
					public void buttonClick(ClickEvent event) {
						setContent(form);
						
						
					}
				});
				deleteThis(obj, buttonBack, buttonSeeAll, buttonDeleteThis);
				seeAll(obj, buttonSeeAll);
				
				modifyThis(buttonContainer, peopleAndarts, button, obj, buttonModifyThis);
			}
		}

		

		

		

		
	});
        
    }
	
	public void deleteThis(ObjectUtils obj) {
		People pd = obj.getPeopleAndarts().stream().filter(p -> obj.getBinder().getBean().getName().equalsIgnoreCase(p.getName())).findAny().orElse(null);
		DeletePerson dl = (l,p) -> l.remove(p);
		dl.deletePerson(obj.getPeopleAndarts(),pd);
	}
	
	public void addThis(ObjectUtils obj,ArrayList<People> peopleAndarts) {
		AddPerson add = (p,b) -> p.add(b);
		add.addPerson(peopleAndarts,new People(obj.getBinder().getBean()));
	}
	
	public void SetModify(ObjectUtils obj, ObjectUtils buttons,Button btn) {
		btn.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				deleteThis(obj);
				addThis(obj,obj.getPeopleAndarts());
				Label confirmation = new Label("Usuario modificado exitosamente!!!");
				Label message = new Label(obj.getBinder().getBean().getName()+" "+obj.getBinder().getBean().getAge()+" "+obj.getBinder().getBean().getNationality()+" "+obj.getBinder().getBean().getProfession());
				buttons.getLayout().addComponent(confirmation);
				buttons.getLayout().addComponent(message);
				setContent(buttons.getLayout());
			}
		});
	}
	
	public void modifySelection(ObjectUtils buttonContainer, Button button, ObjectUtils obj, Button buttonModifySelection) {
		buttonModifySelection.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(!obj.getComboBox().getValue().isEmpty()) {
					obj.getBinder().setBean(obj.getPeopleAndarts().stream().filter(p -> obj.getComboBox().getValue().equalsIgnoreCase(p.getName())).findAny().orElse(null));
					Button buttonSetNewData = new Button("Set new data");
					buttonContainer.getLayout().addComponent(buttonSetNewData);
					obj.getLayout().removeAllComponents();
					obj.getForm().removeComponent(button);
					obj.getLayout().addComponent(obj.getForm());
					obj.getLayout().addComponent(buttonSetNewData);
					setContent(obj.getLayout());
					
					SetModify(obj, buttonContainer, buttonSetNewData);
				}
				
				
				
			}

			
		});
	}
	
	public void modifyThis(ObjectUtils buttonContainer, ArrayList<People> peopleAndarts, Button button,
			ObjectUtils obj, Button buttonModifyThis) {
		buttonModifyThis.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				ComboBox<String> comboBox = new ComboBox<>("Personas");
				obj.setComboBox(comboBox);
				if(!peopleAndarts.isEmpty()) {
					LinkedList<String> listOfNames = new LinkedList<String>();
					Iterator<People> it2 = peopleAndarts.iterator();
					
					while(it2.hasNext()) {
						People perName = it2.next();
						listOfNames.add(perName.getName());
					}
					obj.getComboBox().setItems(listOfNames);
					Button buttonModifySelection = new Button("Modify selected");
					obj.getLayout().removeAllComponents();
					obj.getLayout().addComponent(obj.getComboBox());
					obj.getLayout().addComponent(buttonModifySelection);
					
					modifySelection(buttonContainer, button, obj,
							buttonModifySelection);
					 
				}
				
			}

			
		});
	}
	
	public void seeAll(ObjectUtils obj, Button buttonSeeAll) {
		buttonSeeAll.addClickListener(new Button.ClickListener() {
			
			private static final long serialVersionUID = 8462101421731560938L;

			@Override
			public void buttonClick(ClickEvent event) {
				Iterator<People> it1 = obj.getPeopleAndarts().iterator();//peopleAndarts.iterator();
				while(it1.hasNext()) {
					People persona = it1.next();
					//Label persons = new Label(persona.toString());
					obj.getLayout().addComponent(new Label(persona.toString()));
				}
				obj.getLayout().removeComponent(buttonSeeAll);
				setContent(obj.getLayout());
			}
		});
	}
	
	public void deleteThis(ObjectUtils obj, Button buttonBack, Button buttonSeeAll, Button buttonDeleteThis) {
		buttonDeleteThis.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				//deleteThis(obj);
				obj.getLayout().removeAllComponents();
				obj.getLayout().addComponent(buttonBack);
				obj.getLayout().addComponent(buttonSeeAll);
				
				Label labelDeleteSucces = new Label("Usuario eliminado exitosamente!!");
				obj.getLayout().addComponent(labelDeleteSucces);
				setContent(obj.getLayout());
				
			}

			
		});
	}
	
	

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
