package io.optimus.data.mongo

import org.litote.kmongo.*
import java.time.LocalDateTime
import kotlin.reflect.KProperty

interface BaseFilter<T> {
    var eq: T?
    var not: T?
    var isIn: List<T>?
    var isNotIn: List<T>?

    fun applyOn(property: KProperty<T?>) = and(
        listOfNotNull(
            eq?.let { property eq it },
            not?.let { property ne it },
            isIn?.let { property `in` it },
            isNotIn?.let { property nin it },
        )
    )
}

interface ComparableFilter<T : Comparable<T>> : BaseFilter<T> {
    var lt: T?
    var lte: T?
    var gt: T?
    var gte: T?

    override fun applyOn(property: KProperty<T?>) = and(
        listOfNotNull(
            lt?.let { property lt it },
            lte?.let { property lte it },
            gt?.let { property gt it },
            gte?.let { property gte it },
            super.applyOn(property)
        )
    )
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

data class IdFilter<T>(
    override var eq: Id<T>? = null,
    override var isIn: List<Id<T>>? = null,
    override var isNotIn: List<Id<T>>? = null,
    override var not: Id<T>? = null
) : BaseFilter<Id<T>>