package com.geovis.duplex.activemq.test;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TestBean implements Serializable {
	private String name;

	public TestBean() {
	}
	
	public TestBean(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
