package com.mycompany.app.util;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {
	private static final String CONNECTION_STRING = "mongodb://localhost:27017";
	private static final String DATABASE_NAME = "guitar_shop";

	private MongoDatabase database;

	public MongoDBConnection() {
		this(CONNECTION_STRING, DATABASE_NAME);
	}

	public MongoDBConnection(String connectionString, String databaseName) {
		CodecProvider pojoCodecProvider = PojoCodecProvider.builder().register("com.mycompany.app.models")
				.automatic(true).build();
		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(pojoCodecProvider));
		MongoClientSettings clientSettings = MongoClientSettings.builder()
				.applyConnectionString(new ConnectionString(connectionString))
				.build();

		database = MongoClients.create(clientSettings).getDatabase(databaseName).withCodecRegistry(codecRegistry);
	}

	public MongoDatabase getDatabase() {
		return database;
	}
}