package net.daisyli.resource.frame.mongodb.datasource;

import java.net.UnknownHostException;

import net.daisyli.resource.frame.internal.exception.ResourceFrameException;

import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;

public class MongodbDataSource {
	public Mongo m;
	
	public Mongo getM() {
		return m;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	private String ipAddress = "127.0.0.1";

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	private Integer port = 27107;

	public Integer getMaxConnection() {
		return maxConnection;
	}

	public void setMaxConnection(Integer maxConnection) {
		this.maxConnection = maxConnection;
	}

	private Integer maxConnection = 100;

	public Integer getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(Integer maxWait) {
		this.maxWait = maxWait;
	}

	private Integer maxWait = 1000;

	public Integer getConnTimeOut() {
		return connTimeOut;
	}

	public void setConnTimeOut(Integer connTimeOut) {
		this.connTimeOut = connTimeOut;
	}

	private Integer connTimeOut = 0;

	public Boolean getAutoConnRetry() {
		return autoConnRetry;
	}

	public void setAutoConnRetry(Boolean autoConnRetry) {
		this.autoConnRetry = autoConnRetry;
	}

	private Boolean autoConnRetry = false;

	public void init() {
		MongoOptions options = new MongoOptions();
		options.maxWaitTime = this.getMaxWait();
		options.connectionsPerHost = this.getMaxConnection();
		options.autoConnectRetry = this.getAutoConnRetry();
		options.connectTimeout = this.getConnTimeOut();
		ServerAddress address;
		try {
			address = new ServerAddress(this.getIpAddress(), this.getPort());
		} catch (UnknownHostException e) {
			// TODO should process here
			throw new ResourceFrameException(123, "host not found");
		}
		this.m = new Mongo(address, options);

	}
}
