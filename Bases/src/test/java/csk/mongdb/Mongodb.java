package csk.mongdb;


import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Mongodb {
    private static MongoClient mongoClient;
    private static MongoDatabase mongoDatabase;

    @BeforeClass
    public static void init() {
        mongoClient = new MongoClient("192.168.142.171", 27017);
        mongoDatabase = mongoClient.getDatabase("MyDatabase");
        System.out.println("Connect to database successfully");
    }

    @AfterClass
    public static void destroy() {
        mongoClient.close();
    }

    @Test
    public void createDbAndDocumentTest() throws IllegalAccessException, InvocationTargetException {
        MongoCollection<Document> collection = mongoDatabase.getCollection("MyDocument");
        System.out.println("select a collection of MyDocument");
        List<Object> documents = new ArrayList<>();
        Entity document = new Entity("小华2", 14, "No101");
        documents.add(document);
        Entity document2 = new Entity("小红3", 15, "No103");
        documents.add(document2);
        List<Document> docs = Conversion.toBsons(documents);
        collection.insertMany(docs);
        System.out.println("successfully insert a document to db");
    }

    /**
     * 备注：
     * $gt： >
     * $gte：>=
     * $eq:  =
     * $ne:  !=
     * $lt:  <
     * $lte: <=
     * $in:  in(后面的值为bson对象数组)
     * $nin: not in(后面的值为bson对象数组)
     */
    @Test
    public void getTest() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        MongoCollection<Document> collection = mongoDatabase.getCollection("MyDocument");
//        组合in和and select * from MyDocument where (age=12 or age=13) and (name='小华' or name ='小红')
        BasicDBObject query11 = new BasicDBObject();
        query11.put("age", 12);
        BasicDBObject query12 = new BasicDBObject();
        query12.put("age", 13);
        List<BasicDBObject> orQueryList1 = new ArrayList<>();
        orQueryList1.add(query11);
        orQueryList1.add(query12);
        BasicDBObject orQuery1 = new BasicDBObject("$or", orQueryList1);
        BasicDBObject query21 = new BasicDBObject();
        query21.put("name", "小华");
        BasicDBObject query22 = new BasicDBObject();
        query22.put("name", "小红");
        List<BasicDBObject> orQueryList2 = new ArrayList<>();
        orQueryList2.add(query21);
        orQueryList2.add(query22);
        BasicDBObject orQuery2 = new BasicDBObject("$or", orQueryList2);
        List<BasicDBObject> orQueryCombinationList = new ArrayList<>();
        orQueryCombinationList.add(orQuery1);
        orQueryCombinationList.add(orQuery2);
        BasicDBObject finalQuery = new BasicDBObject("$and", orQueryCombinationList);
        FindIterable<Document> documents = collection.find(finalQuery).sort(new BasicDBObject("age", -1)).skip(0).limit(10);
        MongoCursor<Document> iterator = documents.iterator();
        List<Entity> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Entity entity = Conversion.toBean(iterator.next(), Entity.class);
            list.add(entity);
            System.out.println(entity);
        }
        System.out.println(list);
    }
}
