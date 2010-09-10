package net.daisyli.resource.frame.internal.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import net.daisyli.resource.frame.annotation.Resource;
import net.daisyli.resource.frame.annotation.Ignore;
import net.daisyli.resource.frame.annotation.PropertyMapping;
import net.daisyli.resource.frame.internal.EntityDetail;
import net.daisyli.resource.frame.internal.FieldDetail;
import net.daisyli.resource.frame.internal.exception.ResourceFrameException;

public class BasicEntityDetail implements EntityDetail {
	public static Logger logger = Logger.getLogger(BasicEntityDetail.class);
	private Class<?> entityClass;
	private String collection;
	private String[] ignored;
	private Map<String, String> mapping;
	private Map<String, FieldDetail> mappingReverse;

	class BasicFieldDetail implements FieldDetail{
		private String fieldName;
		private Class<?> fieldClass;
		
		
		public BasicFieldDetail(String fieldName, Class<?> fieldClass) {
			super();
			this.fieldName = fieldName;
			this.fieldClass = fieldClass;
		}
		@Override
		public Class<?> getFieldClass() {
			return this.fieldClass;
		}
		@Override
		public String getFieldName() {
			return this.fieldName;
		}
		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}
		public void setFieldClass(Class<?> fieldClass) {
			this.fieldClass = fieldClass;
		}
		
		
	}

	public String getCollection() {
		return collection;
	}

	public String[] getIgnored() {
		return ignored;
	}

	public void init(Class<?> clazz) {
		logger.debug("Now start init" + System.currentTimeMillis());
		this.entityClass = clazz;
		mapping = new HashMap<String, String>();
		mappingReverse = new HashMap<String, FieldDetail>();
		Resource coll = clazz.getAnnotation(Resource.class);
		this.collection = coll.value();
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			PropertyMapping mapping = fields[i]
					.getAnnotation(PropertyMapping.class);
			Ignore ignore = fields[i].getAnnotation(Ignore.class);
			if (ignore == null) {
				if (mapping == null) {
					handlePutMapping(fields[i].getName(), fields[i].getName(),fields[i].getType());
				} else {
					handlePutMapping(fields[i].getName(), mapping.value(),fields[i].getType());
				}
			}
		}

		logger.debug("Now end init" + System.currentTimeMillis());
	}

	private void handlePutMapping(String key1, String key2,Class<?> clazz) {
		this.mapping.put(key1, key2);
		if (mappingReverse.containsKey(key2))
			throw new ResourceFrameException(01, "DOUBLE");
		this.mappingReverse.put(key2, new BasicFieldDetail(key1, clazz));
	}

	@Override
	public Map<String, String> getMapping() {
		return this.mapping;
	}

	@Override
	public Map<String, FieldDetail> getReverseMapping() {
		return this.mappingReverse;
	}

	@Override
	public Class<?> getEntityClass() {
		return this.entityClass;
	}


}
