package net.daisyli.resource.frame.test;

import net.daisyli.resource.frame.mongodb.MongodbResourceManager;
import net.daisyli.resource.frame.mongodb.datasource.MongodbDataSource;

public class TestMongodbResourceManager {

	public static void main(String args[]) {
		MongodbDataSource ds = new MongodbDataSource();
		ds.setIpAddress("127.0.0.1");
		ds.setPort(12338);
		ds.init();
		MongodbResourceManager rm = new MongodbResourceManager();
		rm.setDatasource(ds);
		rm.init();

		// RelationFollow rf = new RelationFollow();
		// rf.setSdid(12345L);
		// rf.setFsdid(123456L);
		// rf.setGroupId(new Long[] { 1L, 2L, 3L });
		// rf.setRelationId(123456789L);
		//		
		// rm.insert(rf);

		RelationFollow rf = new RelationFollow();
		rf.setSdid(12345L);
		rf.setFsdid(123456L);
		for (int i = 0; i < 100; i++) {
			rm.fetch(rf);
		}

	}
}
