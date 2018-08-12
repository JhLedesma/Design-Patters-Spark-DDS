package DB;

import com.google.gson.GsonBuilder;
import com.mongodb.MongoClient;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public abstract class MongoDBManager {

    private MongoClient mongoClient = new MongoClient();

    private MongoDatabase cache = mongoClient.getDatabase("cache");

    public MongoCollection<Document> getCollectionMongo(String nombre){
        MongoCollection<Document> collection = cache.getCollection(nombre);
        return collection;
    }

    public Document ejecutarQueryMongo(String nombreColecction, Bson consulta){
        Document doc = getCollectionMongo(nombreColecction).find(consulta).first();
        String json = new GsonBuilder().create().toJson(doc);
        System.out.println("Se obtuvo : " + json);
        return doc;
    }

    public List<Document> ejecutarQueryMongoReturnList(String nombreColecction, Bson consulta){
        List<Document> lista = new ArrayList<Document>();
        MongoCursor<Document> cursor = getCollectionMongo(nombreColecction).find(consulta).iterator();
        usoCursor(lista, cursor);
        return lista;
    }

    public void addObjectMongo(String nombreColecction, Document doc){
        getCollectionMongo(nombreColecction).insertOne(doc);
        System.out.println("Se agrego : " + doc.toJson());
    }

    public void addListMongo(String nombreColecction, List<Document> docs){
        for (Document doc : docs){
            getCollectionMongo(nombreColecction).insertOne(doc);
            System.out.println("Se agrego : " + doc.toJson());
        }
    }

    public List<Document> getElementsColecction(String nombreColecction){
        List<Document> lista = new ArrayList<Document>();
        MongoCursor<Document> cursor = getCollectionMongo(nombreColecction).find().iterator();
        usoCursor(lista, cursor);
        return lista;
    }

    public void eliminarPorQuery(String nombreColecction, Bson consulta){
        DeleteResult deleteResult = getCollectionMongo(nombreColecction).deleteMany(consulta);
        System.out.println("Se elimino " + deleteResult.getDeletedCount() + " documento/s");
    }

    private void usoCursor(List<Document> lista, MongoCursor<Document> cursor) {
        try {
            while (cursor.hasNext()) {
                Document json = cursor.next();
                lista.add(json);
                System.out.println("Se obtuvo : " + json.toJson());
            }
        } finally {
            cursor.close();
        }
    }

}

