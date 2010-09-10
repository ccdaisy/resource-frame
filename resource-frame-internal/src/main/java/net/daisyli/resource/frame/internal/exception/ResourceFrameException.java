package net.daisyli.resource.frame.internal.exception;

public class ResourceFrameException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer errcode = 0;
	private String description = "";
	
	

	public ResourceFrameException(Integer errcode, String description) {
		super();
		this.errcode = errcode;
		this.description = description;
	}

	@Override
	public String toString() {
		return this.getClass().getCanonicalName() + "   errcode: " + errcode
				+ ", description: " + description;
	}

}
