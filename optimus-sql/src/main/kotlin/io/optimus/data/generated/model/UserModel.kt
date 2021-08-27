package io.optimus.data.generated.model

import io.optimus.data.generated.table.Users
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

class UserModel(id: EntityID<UUID>) : UUIDEntity(id) {
	companion object : UUIDEntityClass<UserModel>(Users)

	var name by Users.name
	var email by Users.email
	var rollNumber by Users.rollNumber
	var role by Users.role
	var password by Users.password
	var salt by Users.salt
	var department by Users.department
	var profilePic by Users.profilePic
	var coverPic by Users.coverPic
	var mobile by Users.mobile
	var upi by Users.upi
	var finManagerForDept by Users.finManagerForDept
	var enabled by Users.enabled
	var verified by Users.verified
}