package com.geovis.duplex.filter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;

import com.geovis.duplex.activemq.push.MessagePush;
import com.geovis.duplex.model.Carrier;
import com.geovis.duplex.model.FilterModel;

public class CarrierFilter implements Filter {
	private MessagePush push;
	private FilterModel filterModel;
	
	@Override
	public void pass(Serializable object) {
		if (filterModel == null) return;
		if(!(object instanceof Carrier)) return;
		Carrier carrier = (Carrier)	object;
		String method = filterModel.getMethod();
		if ("include".equalsIgnoreCase(method)) {
			include(carrier);
		} else if ("exclude".equals(method)) {
			exclude(carrier);
		}
	}
	
	private void include(Carrier carrier) {
		if (doInclude(carrier)) push.push(carrier);
	}
	
	private boolean doInclude(Carrier carrier){
		return true;
	}
	
	private void exclude(Carrier carrier){
		if (doExclude(carrier)) push.push(carrier);
	}

	private boolean doExclude(Carrier carrier){
		if (filterModel.getTableName().equals(carrier.getTablename())) {
			HashMap<String,StringBuffer> body = carrier.getBody();
			if (body == null) return true;
			for(String key : body.keySet()){
				if (filterModel.getColumnName().equalsIgnoreCase(key)&&
						filterModel.getValue().equals(body.get(key).toString())
						) return false;
			}
		}
		Map<String, Carrier> dependencies = carrier.getDependencies();
		if (dependencies == null) return true;
		for (Carrier carrier2 : dependencies.values()) {
			if(!doExclude(carrier2)) return false;
		}
		return true;
	}
	
	@Override
	public void push(Serializable object) {
		pass(object);
	}

	@Override
	public void close() {
		push.close();
	}

	@Override
	public void setTopic(String clientid, String topicName) {
		push.setTopic(clientid, topicName);
	}

	@Override
	public void commit() throws JMSException {
		push.commit();
	}

	@Override
	public void rollback() throws JMSException {
		push.rollback();
	}

	@Override
	public Message createMessage() throws JMSException {
		return push.createMessage();
	}

	public MessagePush getPush() {
		return push;
	}

	public void setPush(MessagePush push) {
		this.push = push;
	}

	public FilterModel getFilterModel() {
		return filterModel;
	}

	public void setFilterModel(FilterModel filterModel) {
		this.filterModel = filterModel;
	}

}
