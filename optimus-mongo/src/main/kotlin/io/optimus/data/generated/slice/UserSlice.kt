package io.optimus.data.generated.slice

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import io.optimus.data.generated.input.operation.UserInputs
import io.optimus.data.generated.model.UserModel
import io.optimus.data.mongo.OptimusMongoClient
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection

class UserSlice(private val collection: MongoCollection<UserModel>, private val client: MongoClient) {

    fun findUnique(block: UserInputs.FindUnique.() -> Unit): UserModel? {
        val filter = UserInputs.FindUnique(block).getFilter()
        return collection.findOne(filter)
    }

    fun findFirst(block: UserInputs.Find.() -> Unit): UserModel? {
        val filter = UserInputs.Find(block).getFilter()
        return collection.findOne(filter)
    }

    fun findMany(block: UserInputs.Find.() -> Unit): List<UserModel> {
        val filter = UserInputs.Find(block).getFilter()
        return if (filter != null) collection.find(filter).toList() else collection.find().toList()
    }

    fun createOne(block: UserInputs.Create.() -> Unit): UserModel {
        val data = UserInputs.Create(block).getInitializer()
        client.startSession().use { clientSession ->
            clientSession.startTransaction()
            collection.insertOne(data)
            clientSession.commitTransaction()
        }
        return data
    }

    fun createMany(vararg blocks: UserInputs.Create.() -> Unit): List<UserModel> {
        var users = emptyList<UserModel>()
        client.startSession().use { clientSession ->
            clientSession.startTransaction()
            users = blocks.map { UserInputs.Create(it).getInitializer() }
            collection.insertMany(users)
            clientSession.commitTransaction()
        }
        return users
    }

    fun updateOne(block: UserInputs.UpdateOne.() -> Unit): UserModel {
        val input = UserInputs.UpdateOne(block)
        val filter = input.getFilter()
        val update = input.getUpdate()

        var model: UserModel
        client.startSession().use { clientSession ->
            clientSession.startTransaction()
            collection.updateOne(filter, update)
            model = collection.findOne(filter) ?: throw Exception("User Not Found!")
            clientSession.commitTransaction()
        }
        return model
    }

    fun updateMany(block: UserInputs.UpdateMany.() -> Unit): List<UserModel> {
        val input = UserInputs.UpdateMany(block)
        val filter = input.getFilter()
        val update = input.getUpdate()

        var models: List<UserModel>
        client.startSession().use { clientSession ->
            clientSession.startTransaction()
            collection.updateMany(filter, update)
            models = collection.find(filter).toList()
            clientSession.commitTransaction()
        }
        return models
    }

    fun deleteOne(block: UserInputs.DeleteOne.() -> Unit): UserModel {
        lateinit var model: UserModel
        client.startSession().use { clientSession ->
            val filter = UserInputs.DeleteOne(block).getFilter()
            clientSession.startTransaction()
            model = collection.findOneAndDelete(filter) ?: throw Exception("User Not Found!")
            clientSession.commitTransaction()
        }
        return model
    }

    fun deleteMany(block: UserInputs.DeleteMany.() -> Unit): List<UserModel> {
        lateinit var models: List<UserModel>
        client.startSession().use { clientSession ->
            val filter = UserInputs.DeleteMany(block).getFilter()
            clientSession.startTransaction()
            models = collection.find(filter).toList()
            collection.deleteMany(filter)
            clientSession.commitTransaction()
        }
        return models
    }

}

val OptimusMongoClient.user
    get() = UserSlice(this.database.getCollection(), this.nativeClient)

val client = OptimusMongoClient()

val users = client.user.deleteOne {
    where {
        email = "hello"
    }
}