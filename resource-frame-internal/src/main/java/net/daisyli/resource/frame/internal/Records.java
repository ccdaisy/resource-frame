package net.daisyli.resource.frame.internal;

public interface Records {
	public Object getProperty(String property);
	public Object toEntity(Class<?> clazz);
}
