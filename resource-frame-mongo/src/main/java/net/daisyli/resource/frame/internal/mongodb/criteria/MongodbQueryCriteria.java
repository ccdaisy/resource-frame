package net.daisyli.resource.frame.internal.mongodb.criteria;

import net.daisyli.resource.frame.internal.Element;
import net.daisyli.resource.frame.internal.SortOrder;
import net.daisyli.resource.frame.internal.criteria.QueryCriteria;
import net.daisyli.resource.frame.internal.utils.Tuple;

import org.bson.BasicBSONObject;

public class MongodbQueryCriteria implements QueryCriteria, MongoCriteria {

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	/**
	 * 
	 */
	private Integer offset;

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	private Integer limit;

	public Tuple<String, Object> getSortBy() {
		return sortBy;
	}

	public void setSortBy(Tuple<String, Object> sortBy) {
		this.sortBy = sortBy;
	}

	private Tuple<String, Object> sortBy;
	private BasicBSONObject query;

	@Override
	public BasicBSONObject getBSON() {
		return query;
	}

	public QueryCriteria equal(Element key, Object val) {
		this.getBSON().put(key.toString(), val);
		return this;
	}

	public QueryCriteria greater(Element key, Object val) {
		this.getBSON().put(key.toString(), new BasicBSONObject("$gt", val));
		return this;
	}

	public QueryCriteria greaterThanEqual(Element key, Object val) {
		this.getBSON().put(key.toString(), new BasicBSONObject("$gte", val));
		return this;
	}

	public QueryCriteria less(Element key, Object val) {
		this.getBSON().put(key.toString(), new BasicBSONObject("$lt", val));
		return this;
	}

	public QueryCriteria lessThanEquel(Element key, Object val) {
		this.getBSON().put(key.toString(), new BasicBSONObject("lte", val));
		return this;
	}

	public QueryCriteria notEqual(Element key, Object val) {
		this.getBSON().put(key.toString(), new BasicBSONObject("$ne", val));
		return this;
	}

	public QueryCriteria in(Element key, Object... val) {
		this.getBSON().put(key.toString(), new BasicBSONObject("$in", val));
		return this;
	}

	public QueryCriteria notIn(Element key, Object... val) {
		this.getBSON().put(key.toString(), new BasicBSONObject("$nin", val));
		return this;
	}

	public QueryCriteria exists(Element key) {
		this.getBSON()
				.put(key.toString(), new BasicBSONObject("$exists", true));
		return this;
	}

	public QueryCriteria notExists(Element key) {
		this.getBSON().put(key.toString(),
				new BasicBSONObject("$exists", false));
		return this;
	}

	@Override
	public QueryCriteria limit(Integer num) {
		this.setLimit(num);
		return this;
	}

	@Override
	public QueryCriteria offset(Integer num) {
		this.setOffset(num);
		return this;
	}

	@Override
	public QueryCriteria sortBy(Element key, SortOrder order) {
		if (this.getSortBy() == null) {
			this.setSortBy(new Tuple<String, Object>(key.toString(), order
					.getValue()));
		} else {
			this.getSortBy().setKey(key.toString());
			this.getSortBy().setValue(order.getValue());
		}
		return null;
	}

}
