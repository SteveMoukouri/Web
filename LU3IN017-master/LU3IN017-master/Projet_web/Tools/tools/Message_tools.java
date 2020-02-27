package tools;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Message_tools {
	public static MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
	public static MongoClient mongoClient = new MongoClient(connectionString);
	public static MongoDatabase database = mongoClient.getDatabase("DB_eddy");
	public static MongoCollection<Document> col = database.getCollection("test");
	
	public static void m() {
		//MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
		//MongoClient mongoClient = new MongoClient(connectionString);
		//MongoDatabase database = mongoClient.getDatabase("DB_eddy");
		//MongoCollection<Document> col = database.getCollection("test");
		Document doc = new Document();
		doc.append("id", 1223);
		col.insertOne(doc);
		//MongoCurser<Document> c = message.find(doc);
		
	}
	public void addMessage(int id,String name,String text) throws Exception {
		Document query = new Document();
		query.append("author_id",id);
		query.append("author_name",name);
		query.append("date",new java.util.Date());
		query.append("text",text);
		col.insertOne(query);
		
	}
	
	public void getListMessage(int id) {
		Document query = new Document();
		query.append("id", id);
		MongoCursor<Document> cursor = col.find(query).iterator();
		while(cursor.hasNext()) {
		    System.out.println(cursor.next());
		}
		
	}
	public void deleteMessage(int id,String name, java.util.GregorianCalendar c , String text) {
		BasicDBObject andQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("id", id));
		obj.add(new BasicDBObject("name", name));
		obj.add(new BasicDBObject("date", c));
		obj.add(new BasicDBObject("text", text));
		andQuery.put("$and", obj);

		System.out.println(andQuery.toString());

		MongoCursor<Document> cursor = col.find(andQuery).iterator();
		while (cursor.hasNext()) {
			col.deleteOne(cursor.next());
		}
	}
	
}
