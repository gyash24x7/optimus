package io.optimus.data.generated.model

import io.optimus.data.generated.enum.Department
import io.optimus.data.generated.enum.UserRole
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.litote.kmongo.Id

@Serializable
data class UserModel(
	@SerialName("_id") var id: Id<UserModel>,
	var name: String,
	var email: String,
	var rollNumber: String,
	var role: UserRole,
	var password: String,
	var salt: String,
	var department: Department,
	var profilePic: String?,
	var coverPic: String?,
	var mobile: String,
	var upi: String?,
	var finManagerForDept: Department?,
	var enabled: Boolean = false,
	var verified: Boolean = false
)