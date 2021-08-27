package io.optimus.data.mongo

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo

class OptimusMongoClient {
	lateinit var database: MongoDatabase
	lateinit var nativeClient: MongoClient

	fun connect() {
		nativeClient = KMongo.createClient()
		database = nativeClient.getDatabase("db-name")
	}
}