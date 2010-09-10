package net.daisyli.resource.frame.internal;

public enum SortOrder {
	DESC(-1), ASC(1);
	private final Integer value;

	private SortOrder(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

}
