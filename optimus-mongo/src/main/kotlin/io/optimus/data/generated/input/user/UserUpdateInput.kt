package io.optimus.data.generated.input.user

import io.optimus.data.generated.enum.Department
import io.optimus.data.generated.enum.UserRole
import io.optimus.data.generated.model.UserModel
import org.litote.kmongo.and
import org.litote.kmongo.setValue

class UserUpdateInput(block: UserUpdateInput.() -> Unit) {
	var name: String? = null
	var email: String? = null
	var rollNumber: String? = null
	var department: Department? = null
	var role: UserRole? = null
	var password: String? = null
	var salt: String? = null
	var mobile: String? = null
	var upi: String? = null
	var profilePic: String? = null
	var coverPic: String? = null
	var finManagerForDept: Department? = null
	var enabled: Boolean? = null
	var verified: Boolean? = null

	init {
		this.apply(block)
	}

	fun getUpdate() = and(
		listOf(
			setValue(UserModel::name, name),
			setValue(UserModel::email, email),
			setValue(UserModel::rollNumber, rollNumber),
			setValue(UserModel::department, department),
			setValue(UserModel::role, role),
			setValue(UserModel::password, password),
			setValue(UserModel::salt, salt),
			setValue(UserModel::profilePic, profilePic),
			setValue(UserModel::coverPic, coverPic),
			setValue(UserModel::mobile, mobile),
			setValue(UserModel::upi, upi),
			setValue(UserModel::finManagerForDept, finManagerForDept),
			setValue(UserModel::enabled, enabled),
			setValue(UserModel::verified, verified),
		)
	)

}