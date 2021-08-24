package io.optimus.data.generated.input.user

import io.optimus.data.generated.enum.Department
import io.optimus.data.generated.enum.UserRole
import io.optimus.data.generated.table.Users
import io.optimus.data.sql.BoolFilter
import io.optimus.data.sql.EntityIdFilter
import io.optimus.data.sql.EnumFilter
import io.optimus.data.sql.StringFilter
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.or

class UserWhereInput(block: UserWhereInput.() -> Unit) {
    private var id: EntityIdFilter? = null
    private var name: StringFilter? = null
    private var email: StringFilter? = null
    private var rollNumber: StringFilter? = null
    private var department: EnumFilter<Department>? = null
    private var role: EnumFilter<UserRole>? = null
    private var password: StringFilter? = null
    private var salt: StringFilter? = null
    private var profilePic: StringFilter? = null
    private var coverPic: StringFilter? = null
    private var mobile: StringFilter? = null
    private var upi: StringFilter? = null
    private var finManagerForDept: EnumFilter<Department>? = null
    private var enabled: BoolFilter? = null
    private var verified: BoolFilter? = null
    private var _and: UserWhereInput? = null
    private var _or: UserWhereInput? = null

    init {
        this.apply(block)
    }

    fun id(block: EntityIdFilter.() -> Unit) {
        id = EntityIdFilter().apply(block)
    }

    fun name(block: StringFilter.() -> Unit) {
        name = StringFilter().apply(block)
    }

    fun email(block: StringFilter.() -> Unit) {
        email = StringFilter().apply(block)
    }

    fun rollNumber(block: StringFilter.() -> Unit) {
        rollNumber = StringFilter().apply(block)
    }

    fun department(block: EnumFilter<Department>.() -> Unit) {
        department = EnumFilter<Department>().apply(block)
    }

    fun role(block: EnumFilter<UserRole>.() -> Unit) {
        role = EnumFilter<UserRole>().apply(block)
    }

    fun password(block: StringFilter.() -> Unit) {
        password = StringFilter().apply(block)
    }

    fun salt(block: StringFilter.() -> Unit) {
        email = StringFilter().apply(block)
    }

    fun profilePic(block: StringFilter.() -> Unit) {
        profilePic = StringFilter().apply(block)
    }

    fun coverPic(block: StringFilter.() -> Unit) {
        coverPic = StringFilter().apply(block)
    }

    fun upi(block: StringFilter.() -> Unit) {
        upi = StringFilter().apply(block)
    }

    fun mobile(block: StringFilter.() -> Unit) {
        mobile = StringFilter().apply(block)
    }

    fun finManagerForDept(block: EnumFilter<Department>.() -> Unit) {
        finManagerForDept = EnumFilter<Department>().apply(block)
    }

    fun enabled(block: BoolFilter.() -> Unit) {
        enabled = BoolFilter().apply(block)
    }

    fun verified(block: BoolFilter.() -> Unit) {
        verified = BoolFilter().apply(block)
    }

    fun and(block: UserWhereInput.() -> Unit) {
        _and = UserWhereInput(block)
    }

    fun or(block: UserWhereInput.() -> Unit) {
        _or = UserWhereInput(block)
    }

    fun getFilter(): Op<Boolean> = Op.build {
        val opList = listOfNotNull(
            id?.applyOn(Users.id),
            name?.applyOn(Users.name),
            email?.applyOn(Users.email),
            rollNumber?.applyOn(Users.rollNumber),
            department?.applyOn(Users.department),
            role?.applyOn(Users.role),
            password?.applyOn(Users.password),
            salt?.applyOn(Users.salt),
            profilePic?.applyOn(Users.profilePic),
            coverPic?.applyOn(Users.coverPic),
            mobile?.applyOn(Users.mobile),
            upi?.applyOn(Users.upi),
            finManagerForDept?.applyOn(Users.finManagerForDept),
            enabled?.applyOn(Users.enabled),
            verified?.applyOn(Users.verified)
        )

        var finalOp = if (opList.isEmpty()) Op.TRUE else opList.reduce { acc, op -> acc and op }

        _and?.let { finalOp = finalOp and it.getFilter() }
        _or?.let { finalOp = finalOp or it.getFilter() }

        finalOp
    }
}