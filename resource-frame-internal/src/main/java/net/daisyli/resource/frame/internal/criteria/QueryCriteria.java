package net.daisyli.resource.frame.internal.criteria;

import net.daisyli.resource.frame.internal.Element;
import net.daisyli.resource.frame.internal.SortOrder;


public interface QueryCriteria {

	public QueryCriteria equal(Element key, Object val);

	public QueryCriteria greater(Element key, Object val);

	public QueryCriteria greaterThanEqual(Element key, Object val);

	public QueryCriteria less(Element key, Object val);

	public QueryCriteria lessThanEquel(Element key, Object val);

	public QueryCriteria notEqual(Element key, Object val);

	public QueryCriteria in(Element key, Object... val);

	public QueryCriteria notIn(Element key, Object... val);

	public QueryCriteria exists(Element key);

	public QueryCriteria notExists(Element key);

	public QueryCriteria offset(Integer num);
	
	public QueryCriteria limit(Integer num);
	
	public QueryCriteria sortBy(Element key, SortOrder order);

}
