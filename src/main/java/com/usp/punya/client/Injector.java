package com.usp.punya.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.usp.punya.client.PunyaPlaceHistoryMapper.PunyaPlaceTokenizers;

@GinModules(InjectorModule.class)
public interface Injector extends Ginjector, PunyaPlaceTokenizers {
    public static final Injector INSTANCE = GWT.create(Injector.class);
 
    public EventBus getEventBus();
    
    public void injectEntryPoint(Main entryPoint);
    
   // public BasicAuthHeaderDispatcherFilterFactory getAuthFactory();
}

//public interface Injector {
//	
//}