package io.optimus.data.generated.input.user

import io.optimus.data.generated.enum.Department
import io.optimus.data.generated.enum.UserRole
import io.optimus.data.generated.model.UserModel

class UserCreateInput(block: UserCreateInput.() -> Unit) {
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

	fun getInitializer(): UserModel.() -> Unit = {
		name = this@UserCreateInput.name!!
		email = this@UserCreateInput.email!!
		rollNumber = this@UserCreateInput.rollNumber!!
		department = this@UserCreateInput.department!!
		role = this@UserCreateInput.role ?: UserRole.COORDINATOR
		rollNumber = this@UserCreateInput.rollNumber!!
		password = this@UserCreateInput.password!!
		salt = this@UserCreateInput.salt!!
		mobile = this@UserCreateInput.mobile!!
		upi = this@UserCreateInput.upi
		profilePic = this@UserCreateInput.profilePic
		coverPic = this@UserCreateInput.coverPic
		finManagerForDept = this@UserCreateInput.finManagerForDept
		enabled = this@UserCreateInput.enabled ?: false
		verified = this@UserCreateInput.verified ?: false
	}

}