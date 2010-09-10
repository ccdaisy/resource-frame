package net.daisyli.resource.frame.internal;

import net.daisyli.resource.frame.internal.criteria.QueryCriteria;

/**
 * @author lichengcheng
 * @since 2010-09-07
 * @version 1.0.0
 */
public interface ResourceManager {

	/**
	 * @param the
	 *            namespace of a resource set
	 */
	public void setNamespace(String ns);

	/**
	 * @return the namespace of a resource set
	 */
	public String getNamespace();

	public Response query(QueryCriteria criteria, String resourceName);

	public Response query(Object o);

	public Response query(Object o, Integer offset, Integer limit);

	public Response query(Object o, Element orderElement, SortOrder order);

	public Response query(Object o, Integer offset, Integer limit,
			Element orderElement, SortOrder order);

	public void fetch(Object o);

	/**
	 * @param object
	 *            to save in resource
	 * @return result of the operation
	 */
	public Result insert(Object o);

	/**
	 * @param before
	 *            object which include key in resource
	 * @param after
	 *            the values to set\push
	 * @return result of the opertation
	 */
	public Result update(Object before, UpdateWrapper after);

	/**
	 * Check whether this resource manager do property mapping
	 * 
	 * @return whether this Resource do mapping
	 */
	public Boolean doMapping();

	/**
	 * do some pre-processing work
	 */
	public void init();
}
