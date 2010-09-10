package net.daisyli.resource.frame.internal.utils;

public class Tuple<K, V> {
	private K key;
	private V value;

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public Tuple() {

	}

	public Tuple(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}

	@Override
	public String toString() {
		return "Tuple [key=" + key + ", value=" + value + "]";
	}

}
