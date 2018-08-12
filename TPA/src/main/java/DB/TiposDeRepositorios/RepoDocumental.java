package DB.TiposDeRepositorios;

import org.bson.Document;

public interface RepoDocumental {

//    public MongoCollection<Document> getCollection();
//
//    public void setCollection(MongoCollection<Document> collection);

    public Object jsonToObjeto(Document json);

    public String getTabla();

    public Document crearDocument(Object object);
}
