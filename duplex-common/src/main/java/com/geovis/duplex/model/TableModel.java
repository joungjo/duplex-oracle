package com.geovis.duplex.model;

import java.util.List;
import java.util.Map;

public class TableModel {
	private String remoteTable;
	private String localSchema;
	private String localTable;
	private SchemaModel schema;
	private List<String> fields;
	private Map<String, Column> columns;
	private String url;
	private List<FilterModel> filterModels;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRemoteTable() {
		return remoteTable;
	}
	public void setRemoteTable(String remoteTable) {
		this.remoteTable = remoteTable;
	}
	public String getLocalTable() {
		return localTable;
	}
	public void setLocalTable(String localTable) {
		this.localTable = localTable;
	}
	public SchemaModel getSchema() {
		return schema;
	}
	public void setSchema(SchemaModel schema) {
		this.schema = schema;
	}
	public List<String> getFields() {
		return fields;
	}
	public void setFields(List<String> fields) {
		this.fields = fields;
	}
	public Map<String, Column> getColumns() {
		return columns;
	}
	public void setColumns(Map<String, Column> columns) {
		this.columns = columns;
	}
	public String getLocalSchema() {
		return localSchema;
	}
	public void setLocalSchema(String localSchema) {
		this.localSchema = localSchema;
	}
	public List<FilterModel> getFilterModels() {
		return filterModels;
	}
	public void setFilterModels(List<FilterModel> filterModels) {
		this.filterModels = filterModels;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		sb.append("{remoteTable:").append(remoteTable)
		.append(",schema:").append(schema)
		.append(",localTable:").append(localTable)
		.append(",localSchema:").append(localSchema)
		.append(",fieldPars:").append(fields).append(columns);
		return sb.toString();
	}
}
