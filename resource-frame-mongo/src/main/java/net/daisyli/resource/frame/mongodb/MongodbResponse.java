package net.daisyli.resource.frame.mongodb;

import java.util.List;

import net.daisyli.resource.frame.internal.Response;

class MongodbResponse implements Response {

	@SuppressWarnings("unchecked")
	List records;

	@SuppressWarnings("unchecked")
	@Override
	public List getRecords() {
		return records;
	}

	@SuppressWarnings("unchecked")
	public void setRecords(List records) {
		this.records = records;
	}

	@SuppressWarnings("unchecked") MongodbResponse(List records) {
		super();
		this.records = records;
	}

}
