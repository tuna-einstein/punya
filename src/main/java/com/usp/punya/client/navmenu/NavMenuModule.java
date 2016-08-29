package com.usp.punya.client.navmenu;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.usp.punya.client.navmenu.NavMenuActivityMapper.ActivityFactory;

public class NavMenuModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(MenuView.class).to(MenuViewGwtImpl.class);
		install(new GinFactoryModuleBuilder().build(ActivityFactory.class));
	}
}