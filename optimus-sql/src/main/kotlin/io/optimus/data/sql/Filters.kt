package io.optimus.data.sql

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.and
import java.time.LocalDateTime
import java.util.UUID

interface BaseFilter<T> {
	var eq: T?
	var not: T?
	var isIn: List<T>?
	var isNotIn: List<T>?

	fun applyOn(column: Column<T>) = Op.build {
		val opList = listOfNotNull(
			eq?.let { column eq it },
			not?.let { column neq it },
			isIn?.let { column inList it },
			isNotIn?.let { column notInList it },
		)

		if (opList.isEmpty()) Op.TRUE else opList.reduce { acc, op -> acc and op }
	}

	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("applyOnNullable")
	fun applyOn(column: Column<T?>) = Op.build {
		val opList = listOfNotNull(
			eq?.let { column eq it },
			not?.let { column neq it },
			isIn?.let { column inList it },
			isNotIn?.let { column notInList it },
		)

		if (opList.isEmpty()) Op.TRUE else opList.reduce { acc, op -> acc and op }
	}
}

interface ComparableFilter<T : Comparable<T>> : BaseFilter<T> {
	var lt: T?
	var lte: T?
	var gt: T?
	var gte: T?

	override fun applyOn(column: Column<T>) = Op.build {
		val opList = listOfNotNull(
			super.applyOn(column),
			lt?.let { column less it },
			lte?.let { column lessEq it },
			gt?.let { column greater it },
			gte?.let { column greaterEq it }
		)

		if (opList.isEmpty()) Op.TRUE else opList.reduce { acc, op -> acc and op }
	}

	@Suppress("INAPPLICABLE_JVM_NAME")
	@JvmName("applyOnNullable")
	override fun applyOn(column: Column<T?>) = Op.build {
		val opList = listOfNotNull(
			super.applyOn(column),
			lt?.let { column less it },
			lte?.let { column lessEq it },
			gt?.let { column greater it },
			gte?.let { column greaterEq it }
		)

		if (opList.isEmpty()) Op.TRUE else opList.reduce { acc, op -> acc and op }
	}
}

data class BoolFilter(
	override var eq: Boolean? = null,
	override var not: Boolean? = null,
	override var isIn: List<Boolean>? = null,
	override var isNotIn: List<Boolean>? = null
) : BaseFilter<Boolean>

data class DateTimeFilter(
	override var eq: LocalDateTime? = null,
	override var isIn: List<LocalDateTime>? = null,
	override var isNotIn: List<LocalDateTime>? = null,
	override var lt: LocalDateTime? = null,
	override var lte: LocalDateTime? = null,
	override var gt: LocalDateTime? = null,
	override var gte: LocalDateTime? = null,
	override var not: LocalDateTime? = null
) : ComparableFilter<LocalDateTime>

data class EnumFilter<T>(
	override var eq: T? = null,
	override var isIn: List<T>? = null,
	override var isNotIn: List<T>? = null,
	override var not: T? = null
) : BaseFilter<T>

data class IntFilter(
	override var eq: Int? = null,
	override var isIn: List<Int>? = null,
	override var isNotIn: List<Int>? = null,
	override var lt: Int? = null,
	override var lte: Int? = null,
	override var gt: Int? = null,
	override var gte: Int? = null,
	override var not: Int? = null
) : ComparableFilter<Int>

data class StringFilter(
	override var eq: String? = null,
	override var isIn: List<String>? = null,
	override var isNotIn: List<String>? = null,
	override var lt: String? = null,
	override var lte: String? = null,
	override var gt: String? = null,
	override var gte: String? = null,
	override var not: String? = null
) : ComparableFilter<String>

data class EntityIdFilter(
	override var eq: EntityID<UUID>? = null,
	override var isIn: List<EntityID<UUID>>? = null,
	override var isNotIn: List<EntityID<UUID>>? = null,
	override var lt: EntityID<UUID>? = null,
	override var lte: EntityID<UUID>? = null,
	override var gt: EntityID<UUID>? = null,
	override var gte: EntityID<UUID>? = null,
	override var not: EntityID<UUID>? = null
) : ComparableFilter<EntityID<UUID>>