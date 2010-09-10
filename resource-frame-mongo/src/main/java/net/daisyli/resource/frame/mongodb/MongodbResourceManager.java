package net.daisyli.resource.frame.mongodb;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.bson.BSONObject;

import net.daisyli.resource.frame.internal.Element;
import net.daisyli.resource.frame.internal.EntityDetail;
import net.daisyli.resource.frame.internal.EntityDetailManager;
import net.daisyli.resource.frame.internal.FieldDetail;
import net.daisyli.resource.frame.internal.ResourceManager;
import net.daisyli.resource.frame.internal.Response;
import net.daisyli.resource.frame.internal.Result;
import net.daisyli.resource.frame.internal.SortOrder;
import net.daisyli.resource.frame.internal.UpdateWrapper;
import net.daisyli.resource.frame.internal.criteria.QueryCriteria;
import net.daisyli.resource.frame.internal.exception.ResourceFrameException;
import net.daisyli.resource.frame.internal.impl.BasicEntityDetailManager;
import net.daisyli.resource.frame.internal.mongodb.criteria.MongoCriteria;
import net.daisyli.resource.frame.internal.utils.PropertyInvoker;
import net.daisyli.resource.frame.mongodb.datasource.MongodbDataSource;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class MongodbResourceManager implements ResourceManager {

	Logger logger = Logger.getLogger(this.getClass());

	public MongodbDataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(MongodbDataSource datasource) {
		this.datasource = datasource;
	}

	private MongodbDataSource datasource;

	public EntityDetailManager getEntityMgr() {
		return entityMgr;
	}

	public void setEntityMgr(EntityDetailManager entityMgr) {
		this.entityMgr = entityMgr;
	}

	private EntityDetailManager entityMgr;

	@Override
	public String getNamespace() {
		return ns;
	}

	@Override
	public void setNamespace(String ns) {
		this.ns = ns;

	}

	private String ns = "test";

	private DB db;

	@Override
	public Boolean doMapping() {
		return true;
	}

	@Override
	public void fetch(Object o) {
		EntityDetail objectDetail = entityMgr.getDetail(o.getClass());
		BasicDBObject dbObject = new BasicDBObject();
		for (Entry<String, String> entry : objectDetail.getMapping().entrySet()) {
			Object val;
			val = PropertyInvoker.getter(o, entry.getKey());
			if (val != null) {
				dbObject.append(entry.getValue(), val);
			}
		}
		DBObject retObject = this.db
				.getCollection(objectDetail.getCollection()).findOne(dbObject);
		this.fillObjectFromDBObject(o, retObject, objectDetail);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response query(Object o) {

		EntityDetail objectDetail = entityMgr.getDetail(o.getClass());
		BasicDBObject dbObject = new BasicDBObject();
		fillDBObjectFromObject(dbObject, o, objectDetail);
		List retList = new ArrayList();
		DBCursor cursor = this.db.getCollection(objectDetail.getCollection())
				.find(dbObject);
		this.fillListWithCursor(retList, cursor, objectDetail);
		Response response = new MongodbResponse(retList);
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response query(Object o, Integer offset, Integer limit) {

		EntityDetail objectDetail = entityMgr.getDetail(o.getClass());
		BasicDBObject dbObject = new BasicDBObject();
		fillDBObjectFromObject(dbObject, o, objectDetail);
		List retList = new ArrayList();
		DBCursor cursor = this.db.getCollection(objectDetail.getCollection())
				.find(dbObject).skip(offset).limit(limit);
		this.fillListWithCursor(retList, cursor, objectDetail);
		Response response = new MongodbResponse(retList);
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response query(Object o, Element orderElement, SortOrder order) {
		EntityDetail objectDetail = entityMgr.getDetail(o.getClass());
		BasicDBObject dbObject = new BasicDBObject();
		fillDBObjectFromObject(dbObject, o, objectDetail);
		List retList = new ArrayList();
		DBCursor cursor = this.db.getCollection(objectDetail.getCollection())
				.find(dbObject).sort(
						new BasicDBObject(orderElement.toString(), order
								.getValue()));
		this.fillListWithCursor(retList, cursor, objectDetail);
		Response response = new MongodbResponse(retList);
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response query(Object o, Integer offset, Integer limit,
			Element orderElement, SortOrder order) {
		EntityDetail objectDetail = entityMgr.getDetail(o.getClass());
		BasicDBObject dbObject = new BasicDBObject();
		fillDBObjectFromObject(dbObject, o, objectDetail);
		List retList = new ArrayList();
		DBCursor cursor = this.db.getCollection(objectDetail.getCollection())
				.find(dbObject).skip(offset).limit(limit).sort(
						new BasicDBObject(orderElement.toString(), order
								.getValue()));
		this.fillListWithCursor(retList, cursor, objectDetail);
		Response response = new MongodbResponse(retList);
		return response;
	}

	@Override
	public Response query(QueryCriteria o, String resourceName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result insert(Object o) {
		EntityDetail objectDetail = entityMgr.getDetail(o.getClass());
		BasicDBObject dbObject = new BasicDBObject();
		for (Entry<String, String> entry : objectDetail.getMapping().entrySet()) {
			Object val;
			val = PropertyInvoker.getter(o, entry.getKey());
			if (val != null) {
				dbObject.append(entry.getValue(), val);
			}
		}
		this.db.getCollection(objectDetail.getCollection()).save(dbObject);
		CommandResult result = this.db.getLastError();
		Result re = new MongodbResult(result);
		return re;
	}

	@Override
	public Result update(Object before, UpdateWrapper after) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init() {
		this.db = datasource.getM().getDB(this.getNamespace());
		entityMgr = new BasicEntityDetailManager();
	}

	private Object newInstance(Class<?> clazz) {
		Object o;
		try {
			o = clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			throw new ResourceFrameException(11, "can't find such class");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new ResourceFrameException(11, "access the class");
		}
		return o;
	}

	@SuppressWarnings("unchecked")
	private void fillListWithCursor(List retList, DBCursor cursor,
			EntityDetail objectDetail) {
		while (cursor.hasNext()) {
			DBObject retObject = cursor.next();
			Object retElement = this.newInstance(objectDetail.getEntityClass());
			fillObjectFromDBObject(retElement, retObject, objectDetail);
			retList.add(retElement);
		}

	}

	private DBObject fillDBObjectFromObject(BasicDBObject dbObject, Object o,
			EntityDetail objectDetail) {
		for (Entry<String, String> entry : objectDetail.getMapping().entrySet()) {
			Object val;
			val = PropertyInvoker.getter(o, entry.getKey());
			if (val != null) {
				dbObject.append(entry.getValue(), val);
			}
		}
		return dbObject;

	}

	private DBObject fillDBObjectFromCriteria(DBObject dbObject,
			BSONObject bsonObject) {
		
		for(String key : bsonObject.keySet()){
		}

		return null;

	}

	private Object fillObjectFromDBObject(Object o, DBObject retObject,
			EntityDetail objectDetail) {

		logger.debug("start to fill object" + System.currentTimeMillis());
		for (Entry<String, FieldDetail> entry : objectDetail
				.getReverseMapping().entrySet()) {
			if (entry.getValue().getFieldClass().isArray()) {
				BasicDBList retList = (BasicDBList) retObject.get(entry
						.getKey());
				Object[] retArray = retList.toArray();
				Object finalArray = Array.newInstance(entry.getValue()
						.getFieldClass().getComponentType(), retArray.length);
				System.arraycopy(retArray, 0, finalArray, 0, retArray.length);
				PropertyInvoker.setter(o, entry.getValue().getFieldName(),
						finalArray, entry.getValue().getFieldClass());
			} else {
				PropertyInvoker.setter(o, entry.getValue().getFieldName(),
						retObject.get(entry.getKey()), entry.getValue()
								.getFieldClass());
			}

		}
		logger.debug("finish filling object" + System.currentTimeMillis());
		return null;
	}
}
