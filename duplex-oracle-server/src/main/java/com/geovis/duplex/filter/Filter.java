package com.geovis.duplex.filter;

import java.io.Serializable;

import com.geovis.duplex.activemq.push.MessagePush;

public interface Filter extends MessagePush {
	
	public void pass(Serializable object);

}
