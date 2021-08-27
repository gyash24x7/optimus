package io.optimus.data.generated.table

import io.optimus.data.generated.enum.Department
import io.optimus.data.generated.enum.UserRole
import org.jetbrains.exposed.dao.id.UUIDTable

object Users : UUIDTable("users") {
	var name = varchar("name", 128)
	var email = varchar("email", 256).uniqueIndex()
	var rollNumber = varchar("roll_number", 8).uniqueIndex()
	var role = enumerationByName("role", 16, UserRole::class)
	var password = varchar("password", 1024)
	var salt = varchar("salt", 256).uniqueIndex()
	var department = enumerationByName("department", 64, Department::class)
	var profilePic = varchar("profile_pic", 512).nullable()
	var coverPic = varchar("cover_pic", 512).nullable()
	var mobile = varchar("mobile", 16)
	var upi = varchar("upi", 64).nullable()
	var finManagerForDept = enumerationByName("fin_manager_for_department", 64, Department::class).nullable()
	var enabled = bool("enabled").default(false)
	var verified = bool("verified").default(false)
}