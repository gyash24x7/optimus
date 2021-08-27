package io.optimus.data.generated.input.user

import io.optimus.data.generated.table.Users
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.and
import java.util.UUID

class UserWhereUniqueInput(block: UserWhereUniqueInput.() -> Unit) {
	var id: EntityID<UUID>? = null
	var email: String? = null
	var rollNumber: String? = null

	init {
		this.apply(block)
	}

	fun getFilter() = Op.build {
		val opList = listOfNotNull(
			id?.let { Users.id eq it },
			email?.let { Users.email eq it },
			rollNumber?.let { Users.rollNumber eq it }
		)

		if (opList.isEmpty()) Op.TRUE else opList.reduce { acc, op -> acc and op }
	}
}