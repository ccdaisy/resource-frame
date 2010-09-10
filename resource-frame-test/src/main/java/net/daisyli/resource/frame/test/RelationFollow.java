package net.daisyli.resource.frame.test;

import java.util.Arrays;

import net.daisyli.resource.frame.annotation.Ignore;
import net.daisyli.resource.frame.annotation.PropertyMapping;
import net.daisyli.resource.frame.annotation.Resource;

@Resource("RELATION_FOLLOW")
public class RelationFollow {
	@PropertyMapping("s")
	private Long sdid;
	@PropertyMapping("f")
	private Long fsdid;
	@PropertyMapping("gi")
	private Long[] groupId;
	@Ignore	private Long lastModifiedTime;

	private Long relationId;

	public Long getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Long lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public Long getRelationId() {
		return relationId;
	}

	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}

	public Long getSdid() {
		return sdid;
	}

	public void setSdid(Long sdid) {
		this.sdid = sdid;
	}

	public Long getFsdid() {
		return fsdid;
	}

	public void setFsdid(Long fsdid) {
		this.fsdid = fsdid;
	}

	public Long[] getGroupId() {
		return groupId;
	}

	public void setGroupId(Long[] groupId) {
		this.groupId = groupId;
	}

	@Override
	public String toString() {
		return "RelationFollow [fsdid=" + fsdid + ", groupId="
				+ Arrays.toString(groupId) + ", lastModifiedTime="
				+ lastModifiedTime + ", relationId=" + relationId + ", sdid="
				+ sdid + "]";
	}
	
}
