package io.optimus.data.generated.input.user

import io.optimus.data.generated.enum.Department
import io.optimus.data.generated.enum.UserRole
import io.optimus.data.generated.model.UserModel
import org.litote.kmongo.newId

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

    fun getInitializer(): UserModel = UserModel(
        name = this.name!!,
        email = this.email!!,
        rollNumber = this.rollNumber!!,
        department = this.department!!,
        role = this.role ?: UserRole.COORDINATOR,
        password = this.password!!,
        salt = this.salt!!,
        mobile = this.mobile!!,
        upi = this.upi,
        profilePic = this.profilePic,
        coverPic = this.coverPic,
        finManagerForDept = this.finManagerForDept,
        enabled = this.enabled ?: false,
        verified = this.verified ?: false,
        id = newId()
    )

}