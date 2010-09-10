package net.daisyli.resource.frame.internal.impl;

import java.util.HashMap;
import java.util.Map;

import net.daisyli.resource.frame.internal.EntityDetail;
import net.daisyli.resource.frame.internal.EntityDetailManager;

public class BasicEntityDetailManager implements EntityDetailManager {

	private Map<Class<?>, EntityDetail> detailMap = new HashMap<Class<?>, EntityDetail>();

	public EntityDetail getDetail(Class<?> clazz) {
		EntityDetail detail = detailMap.get(clazz);
		if (detail == null) {
			detail = new BasicEntityDetail();
			detail.init(clazz);
			synchronized (this) {
				detailMap.put(clazz, detail);
			}
		}
		return detail;
	}
}
