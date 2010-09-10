package net.daisyli.resource.frame.internal.mongodb.criteria;

import org.bson.BasicBSONObject;

public class MongodbUpdateCriteria extends BasicBSONObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MongodbUpdateCriteria inc(String key, Integer val) {
		BasicBSONObject incArr = (BasicBSONObject) this.get("$inc");
		if (incArr != null) {
			incArr.put(key, val);
		} else {
			this.put("$inc", new BasicBSONObject(key, val));
		}
		return this;
	}

	public MongodbUpdateCriteria set(String key, Object val) {
		BasicBSONObject setArr = (BasicBSONObject) this.get("$set");
		if (setArr != null) {
			setArr.put(key, val);
		} else {
			this.put("$set", new BasicBSONObject(key, val));
		}
		return this;
	}

	public MongodbUpdateCriteria unset(String key) {
		BasicBSONObject unsetArr = (BasicBSONObject) this.get("$unset");
		if (unsetArr != null) {
			unsetArr.put(key, 1);
		} else {
			this.put("$unset", new BasicBSONObject(key, 1));
		}
		return this;
	}

	public MongodbUpdateCriteria push(String key, Object... val) {
		BasicBSONObject pushArr = (BasicBSONObject) this.get("$pushAll");
		if (pushArr != null) {
			pushArr.put(key, val);
		} else {
			this.put("$pushAll", new BasicBSONObject(key, val));
		}
		return this;
	}

	public MongodbUpdateCriteria pushSet(String key, Object... val) {
		BasicBSONObject pushArr = (BasicBSONObject) this.get("$addToSet");
		if (pushArr != null) {
			pushArr.put(key, new BasicBSONObject("$each", val));
		} else {
			this.put("$addToSet", new BasicBSONObject(key, new BasicBSONObject(
					"$each", val)));
		}
		return this;
	}

	public MongodbUpdateCriteria popFirst(String key) {
		this.pop(key, -1);
		return this;
	}

	public MongodbUpdateCriteria popLast(String key) {
		this.pop(key, 1);
		return this;
	}

	private MongodbUpdateCriteria pop(String key, Integer order) {
		BasicBSONObject popArr = (BasicBSONObject) this.get("$pop");
		if (popArr != null) {
			popArr.put(key, -1);
		} else {
			this.put("$pop", new BasicBSONObject(key, -1));
		}
		return this;
	}

	public MongodbUpdateCriteria pull(String key, Object... val) {
		BasicBSONObject pullArr = (BasicBSONObject) this.get("$pullAll");
		if (pullArr != null) {
			pullArr.put(key, val);
		} else {
			this.put("$pullAll", new BasicBSONObject(key, val));
		}
		return this;

	}

	public static void main(String[] args) {
		System.out.println(new MongodbUpdateCriteria().pushSet("groupId", 135).inc("groupCount", 1));
	}
}
