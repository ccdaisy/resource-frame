package net.daisyli.resource.frame.internal;

import java.util.Map;

public interface EntityDetail {
	Class<?> getEntityClass();

	Map<String, String> getMapping();

	Map<String, FieldDetail> getReverseMapping();

	String getCollection();

	void init(Class<?> clazz);

}
