package io.optimus.data.generated.input.user

import io.optimus.data.generated.enum.Department
import io.optimus.data.generated.enum.UserRole
import io.optimus.data.generated.model.UserModel
import io.optimus.data.mongo.BoolFilter
import io.optimus.data.mongo.EnumFilter
import io.optimus.data.mongo.IdFilter
import io.optimus.data.mongo.StringFilter
import org.bson.conversions.Bson
import org.litote.kmongo.and
import org.litote.kmongo.or

class UserWhereInput(block: UserWhereInput.() -> Unit) {
	private var id: IdFilter<UserModel>? = null
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

	fun id(block: IdFilter<UserModel>.() -> Unit) {
		id = IdFilter<UserModel>().apply(block)
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

	fun getFilter(): Bson {
		var finalBson = and(
			listOfNotNull(
				id?.applyOn(UserModel::id),
				name?.applyOn(UserModel::name),
				email?.applyOn(UserModel::email),
				rollNumber?.applyOn(UserModel::rollNumber),
				department?.applyOn(UserModel::department),
				role?.applyOn(UserModel::role),
				password?.applyOn(UserModel::password),
				salt?.applyOn(UserModel::salt),
				profilePic?.applyOn(UserModel::profilePic),
				coverPic?.applyOn(UserModel::coverPic),
				mobile?.applyOn(UserModel::mobile),
				upi?.applyOn(UserModel::upi),
				finManagerForDept?.applyOn(UserModel::finManagerForDept),
				enabled?.applyOn(UserModel::enabled),
				verified?.applyOn(UserModel::verified)
			)
		)

		_and?.let { finalBson = and(finalBson, it.getFilter()) }
		_or?.let { finalBson = or(finalBson, it.getFilter()) }

		return finalBson
	}
}