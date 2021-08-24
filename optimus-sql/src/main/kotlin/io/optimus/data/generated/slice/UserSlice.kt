package io.optimus.data.generated.slice

import io.optimus.data.generated.input.operation.UserInputs
import io.optimus.data.generated.model.UserModel
import io.optimus.data.sql.OptimusSqlClient
import org.jetbrains.exposed.sql.transactions.transaction

class UserSlice {

    fun findUnique(block: UserInputs.FindUnique.() -> Unit): UserModel? {
        val filter = UserInputs.FindUnique(block).getFilter()
        return UserModel.find(filter).firstOrNull()
    }

    fun findFirst(block: UserInputs.Find.() -> Unit): UserModel? {
        val filter = UserInputs.Find(block).getFilter()
        return UserModel.find(filter).firstOrNull()
    }

    fun findMany(block: UserInputs.Find.() -> Unit): List<UserModel> {
        val filter = UserInputs.Find(block).getFilter()
        return UserModel.find(filter).toList()
    }

    fun createOne(block: UserInputs.Create.() -> Unit): UserModel {
        return transaction {
            val initializer = UserInputs.Create(block).getInitializer()
            UserModel.new(initializer)
        }
    }

    fun createMany(vararg blocks: UserInputs.Create.() -> Unit): List<UserModel> {
        return transaction {
            blocks.map {
                val initializer = UserInputs.Create(it).getInitializer();
                UserModel.new(initializer)
            }
        }
    }

    fun updateOne(block: UserInputs.UpdateOne.() -> Unit): UserModel {
        val input = UserInputs.UpdateOne(block)
        val filter = input.getFilter()
        val update = input.getUpdate()
        return transaction {
            val model = UserModel.find(filter).firstOrNull() ?: throw Exception("User Not Found!")
            model.apply(update)
        }
    }

    fun updateMany(block: UserInputs.UpdateMany.() -> Unit): List<UserModel> {
        val input = UserInputs.UpdateMany(block)
        val filter = input.getFilter()
        val update = input.getUpdate()
        return transaction {
            val models = UserModel.find(filter)
            models.map { it.apply(update) }
        }
    }

    fun deleteOne(block: UserInputs.DeleteOne.() -> Unit): UserModel {
        return transaction {
            val filter = UserInputs.DeleteOne(block).getFilter()
            val model = UserModel.find(filter).firstOrNull() ?: throw Exception("User Not Found!")
            model.delete()
            model
        }
    }

    fun deleteMany(block: UserInputs.DeleteMany.() -> Unit): List<UserModel> {
        return transaction {
            val filter = UserInputs.DeleteMany(block).getFilter()
            val models = UserModel.find(filter)
            models.forEach { it.delete() }
            models.toList()
        }
    }
}

val OptimusSqlClient.user
    get() = UserSlice()

val client = OptimusSqlClient()

val users = client.user.updateMany {
    where {
        email {
            eq = ""
        }
    }

    data {
        rollNumber = ""
    }
}