package com.fabione.interfaces;

import com.vaadin.ui.Button;

public interface HandlerMethods {

	
	default void clickEvent(Button btn) 
    { 
        System.out.println("Default TestInterface2"); 
    }
	
	
	
}
