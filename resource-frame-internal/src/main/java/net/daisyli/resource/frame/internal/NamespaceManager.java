package net.daisyli.resource.frame.internal;

public interface NamespaceManager {
	public ResourceManager getResourceManager(String namespace);
	public ResourceManager putResourceManager(String namespace, ResourceManager rm);
	
}
