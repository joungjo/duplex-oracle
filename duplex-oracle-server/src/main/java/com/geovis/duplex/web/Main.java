package com.geovis.duplex.web;

import java.net.UnknownHostException;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws UnknownHostException {
		new FileSystemXmlApplicationContext("jetty.xml");
	}
}
