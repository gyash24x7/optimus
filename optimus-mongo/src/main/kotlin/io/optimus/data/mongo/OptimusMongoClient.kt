package io.optimus.data.mongo

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import io.optimus.data.DbClient
import org.litote.kmongo.KMongo

class OptimusMongoClient : DbClient {
    lateinit var database: MongoDatabase
    lateinit var nativeClient: MongoClient

    override fun connect() {
        nativeClient = KMongo.createClient()
        database = nativeClient.getDatabase("db-name")
    }
}