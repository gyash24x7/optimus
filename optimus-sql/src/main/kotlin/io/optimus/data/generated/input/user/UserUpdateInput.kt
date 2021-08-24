package io.optimus.data.generated.input.user

import io.optimus.data.generated.enum.Department
import io.optimus.data.generated.enum.UserRole
import io.optimus.data.generated.model.UserModel

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

    fun getUpdate(): UserModel.() -> Unit = {
        name = this@UserUpdateInput.name!!
        email = this@UserUpdateInput.email!!
        rollNumber = this@UserUpdateInput.rollNumber!!
        department = this@UserUpdateInput.department!!
        role = this@UserUpdateInput.role ?: UserRole.COORDINATOR
        rollNumber = this@UserUpdateInput.rollNumber!!
        password = this@UserUpdateInput.password!!
        salt = this@UserUpdateInput.salt!!
        mobile = this@UserUpdateInput.mobile!!
        upi = this@UserUpdateInput.upi
        profilePic = this@UserUpdateInput.profilePic
        coverPic = this@UserUpdateInput.coverPic
        finManagerForDept = this@UserUpdateInput.finManagerForDept
        enabled = this@UserUpdateInput.enabled ?: false
        verified = this@UserUpdateInput.verified ?: false
    }

}