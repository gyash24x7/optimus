package io.optimus.data.generated.input.user

import io.optimus.data.generated.model.UserModel
import org.litote.kmongo.Id
import org.litote.kmongo.and
import org.litote.kmongo.eq

class UserWhereUniqueInput(block: UserWhereUniqueInput.() -> Unit) {
    var id: Id<UserModel>? = null
    var email: String? = null
    var rollNumber: String? = null

    init {
        this.apply(block)
    }

    fun getFilter() = and(
        listOfNotNull(
            id?.let { UserModel::id eq it },
            email?.let { UserModel::email eq it },
            rollNumber?.let { UserModel::rollNumber eq it }
        )
    )
}