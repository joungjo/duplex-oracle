package com.geovis.duplex.model;

public class FilterModel {
	private String schema;
	private String database;
	private String tableName;
	private String columnName;
	private String method;
	private String value;
	private String type;
	
	public FilterModel(String tableName, String columnName, String method, String value) {
		super();
		this.tableName = tableName;
		this.columnName = columnName;
		this.method = method;
		this.value = value;
	}
	public FilterModel(String columnName, String method, String value) {
		super();
		this.columnName = columnName;
		this.method = method;
		this.value = value;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
}
