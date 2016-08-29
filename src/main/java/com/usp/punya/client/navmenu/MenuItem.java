package com.usp.punya.client.navmenu;

import java.io.Serializable;

public class MenuItem implements Serializable {

	private static final long serialVersionUID = 1;
	private String name;

	public MenuItem() {
	}

	public MenuItem(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}