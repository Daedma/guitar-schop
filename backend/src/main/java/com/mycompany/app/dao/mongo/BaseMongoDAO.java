package com.mycompany.app.dao.mongo;

import com.mycompany.app.dao.BaseDAO;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

public abstract class BaseMongoDAO<T> implements BaseDAO<T> {
	protected final MongoCollection<T> collection;

	public BaseMongoDAO(MongoDatabase database, String collectionName, Class<T> entityClass) {
		this.collection = database.getCollection(collectionName, entityClass);
	}

	@Override
	public String save(T entity) {
		InsertOneResult result = collection.insertOne(entity);
		return result.getInsertedId().asObjectId().getValue().toHexString();
	}

	@Override
	public T findById(String id) {
		return collection.find(Filters.eq("_id", new ObjectId(id))).first();
	}

	@Override
	public Iterable<T> findAll() {
		return collection.find();
	}

	@Override
	public void deleteById(String id) {
		collection.deleteOne(Filters.eq("_id", new ObjectId(id)));
	}

	@Override
	public void update(T entity) {
		Bson filter = Filters.eq("_id", getId(entity));
		Bson updateOperation = createUpdateOperation(entity);

		UpdateResult result = collection.updateOne(filter, updateOperation);
		if (result.getMatchedCount() == 0) {
			throw new RuntimeException("Document not found with id: " + getId(entity));
		}
	}

	protected abstract ObjectId getId(T entity);

	protected abstract Bson createUpdateOperation(T entity);
}
