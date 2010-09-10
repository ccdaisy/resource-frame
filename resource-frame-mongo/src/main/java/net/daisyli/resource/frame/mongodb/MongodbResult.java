package net.daisyli.resource.frame.mongodb;

import com.mongodb.CommandResult;

import net.daisyli.resource.frame.internal.Result;
import net.daisyli.resource.frame.internal.exception.ResourceFrameException;

class MongodbResult implements Result{
	
	
	public MongodbResult(CommandResult cmdResult) {
		super();
		this.cmdResult = cmdResult;
	}

	CommandResult cmdResult;

	public CommandResult getCmdResult() {
		return cmdResult;
	}

	public void setCmdResult(CommandResult cmdResult) {
		this.cmdResult = cmdResult;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Integer getAffectedNum() {
		throw new ResourceFrameException(123, "not supported");
	}

	@Override
	public Boolean getSuccess() {
		return cmdResult.ok();
	}

	@Override
	public String getErrMsg() {
		return cmdResult.getErrorMessage();
	}

}
