package org.sky.webcrawler.util;

import com.mongodb.*;

import java.util.*;

public class MongoClient {
	
	int port = 27017;
	String DBName = "webData";
	String CollectionName = "stock";
	String hostAdd = "locahost";

	private Mongo mongoClient = null;
	DBCollection coll;
	DB db;


	public String getCollectionName() {
		return CollectionName;
	}

	public void setCollectionName(String collectionName) {
		CollectionName = collectionName;
	}

	public DBCollection getColl() {
		return coll;
	}

	public void setColl(DBCollection coll) {
		this.coll = coll;
	}

	public DB getDb() {
		return db;
	}

	public void setDb(DB db) {
		this.db = db;
	}

	public MongoClient(String CollectionName){
		this.CollectionName =CollectionName;
		mongoClient = new Mongo(new ServerAddress(hostAdd, port));
		db = mongoClient.getDB(DBName);
		coll = db.getCollection(CollectionName);
	}

	@SuppressWarnings("deprecation")
	public MongoClient(String hostAddr, int port, String DBName, String CollectionName){
		this.port = port;
		this.DBName = DBName;
		this.hostAdd = hostAddr;
		this.CollectionName =CollectionName;
		mongoClient = new Mongo(new ServerAddress(hostAddr, port));
		db = mongoClient.getDB(DBName);
		coll = db.getCollection(CollectionName);
	}

	public List<Integer> getIntList(BasicDBObject query, String colName){
		List<Integer> list = new ArrayList<Integer>();
		DBCursor cursor = coll.find(query);
		try {
		    while (cursor.hasNext()) {
				DBObject  obj = cursor.next();
				if(obj.containsField(colName)){
					int rate = (Integer) obj.get(colName) ;
					list.add(rate);
		    	}
		    }
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		 finally {
		    cursor.close();
		}
		return list;
	}

	public List<DBObject> getObjectList(BasicDBObject query){
		List<DBObject> list = new ArrayList<DBObject>();
		DBCursor cursor = coll.find(query);
		try {
		    while (cursor.hasNext()) {
				DBObject  obj = cursor.next();
				list.add(obj);
		    }
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		 finally {
		    cursor.close();
		}
		return list;
	}

	public DBObject getObject(BasicDBObject query){
		List<DBObject> list =  getObjectList(query);
		if(list.size() != 0){
			return list.get(0);
		}
		else{
			return null;
		}
	}

	public List<DBObject> getObjectList(BasicDBObject query, int offset, int limit){
		List<DBObject> list = new ArrayList<DBObject>();
		DBCursor cursor = coll.find(query);
		cursor.skip(offset);
		cursor.limit(limit);
		try {
		    while (cursor.hasNext()) {
				DBObject  obj = cursor.next();
				list.add(obj);
		    }
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		 finally {
		    cursor.close();
		}
		return list;
	}

	public Set<Object> getDataSet(BasicDBObject query, String colName) {
		Set<Object> set = new HashSet<Object>();
		DBCursor cursor = coll.find(query);
		try {
		    while (cursor.hasNext()) {
				DBObject  obj = cursor.next();
				if(obj.containsField(colName)){
					String datasource = (String) obj.get(colName) ;
					set.add(datasource);
		    	}
		    }
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		 finally {
		    cursor.close();
		}
		return set;
	}

	public void saveObject(){

	}

	public Set<String> getDataSet(BasicDBObject query, String colName, int offset, int limit) {
		Set<String> set = new HashSet<String>();
		DBCursor cursor = coll.find(query);
		cursor.skip(offset);
		cursor.limit(limit);
		try {
		    while (cursor.hasNext()) {
				DBObject  obj = cursor.next();
				if(obj.containsField(colName)){
					String datasource = (String) obj.get(colName) ;
					set.add(datasource);
		    	}
		    }
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		 finally {
		    cursor.close();
		}
		return set;
	}

	public static void main(String[] args){
		String city = "赣州";
		String Area = "businessArea";
//		Map<String,Object> statistics = new HashMap<String, Object>();
//		statistics.put(key, value);
		MongoClient mongo = new MongoClient("10.1.7.182", 27017, "CDSPDevDB", "dianPing");
		Set<Object> datasources = mongo.getDataSet(new BasicDBObject("city", city), Area);//domiancreateCreratir
		for(Object data: datasources){
			BasicDBObject query = new BasicDBObject(Area, data.toString());
			List<DBObject> list = mongo.getObjectList(query);
			Set<Object> mainCates = new HashSet<Object>();
//			mainCates.

			Map<String, Integer> result = new HashMap<String, Integer>();
			for(Object o : mainCates){
				result.put(o.toString(), 0);
			}

			for(DBObject obj : list){
				Integer count = result.get(obj.get("mainCate"));
				result.put(obj.get("mainCate").toString(), ++count);
			}

			System.out.println(data+":"+result);
		}
	}

	public List<String> getColList(BasicDBObject basicDBObject, String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
