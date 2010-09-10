package net.daisyli.resource.frame.internal;

public interface Result {
	public Boolean getSuccess();
	public Integer getAffectedNum();
	public String getErrMsg();
}
