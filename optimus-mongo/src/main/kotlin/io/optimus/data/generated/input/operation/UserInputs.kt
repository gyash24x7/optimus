package io.optimus.data.generated.input.operation

import io.optimus.data.generated.input.user.UserCreateInput
import io.optimus.data.generated.input.user.UserUpdateInput
import io.optimus.data.generated.input.user.UserWhereInput
import io.optimus.data.generated.input.user.UserWhereUniqueInput

object UserInputs {

    class FindUnique(block: FindUnique.() -> Unit) {
        private var where: UserWhereUniqueInput? = null

        init {
            this.apply(block)
        }

        fun where(block: UserWhereUniqueInput.() -> Unit) {
            where = UserWhereUniqueInput(block)
        }

        fun getFilter() = where?.getFilter()
    }

    class Find(block: Find.() -> Unit) {
        private var where: UserWhereInput? = null

        init {
            this.apply(block)
        }

        fun where(block: UserWhereInput.() -> Unit) {
            where = UserWhereInput(block)
        }

        fun getFilter() = where?.getFilter()
    }

    class Create(block: Create.() -> Unit) {
        private var data: UserCreateInput? = null

        init {
            this.apply(block)
        }

        fun data(block: UserCreateInput.() -> Unit) {
            data = UserCreateInput(block)
        }

        fun getInitializer() = data!!.getInitializer()
    }

    class UpdateMany(block: UpdateMany.() -> Unit) {
        private var where: UserWhereInput? = null
        private var data: UserUpdateInput? = null

        init {
            this.apply(block)
        }

        fun where(block: UserWhereInput.() -> Unit) {
            where = UserWhereInput(block)
        }

        fun getFilter() = where!!.getFilter()

        fun data(block: UserUpdateInput.() -> Unit) {
            data = UserUpdateInput(block)
        }

        fun getUpdate() = data!!.getUpdate()
    }

    class UpdateOne(block: UpdateOne.() -> Unit) {
        private var where: UserWhereUniqueInput? = null
        private var data: UserUpdateInput? = null

        init {
            this.apply(block)
        }

        fun where(block: UserWhereUniqueInput.() -> Unit) {
            where = UserWhereUniqueInput(block)
        }

        fun getFilter() = where!!.getFilter()

        fun data(block: UserUpdateInput.() -> Unit) {
            data = UserUpdateInput(block)
        }

        fun getUpdate() = data!!.getUpdate()
    }

    class DeleteMany(block: DeleteMany.() -> Unit) {
        private var where: UserWhereInput? = null

        init {
            this.apply(block)
        }

        fun where(block: UserWhereInput.() -> Unit) {
            where = UserWhereInput(block)
        }

        fun getFilter() = where!!.getFilter()
    }

    class DeleteOne(block: DeleteOne.() -> Unit) {
        private var where: UserWhereUniqueInput? = null

        init {
            this.apply(block)
        }

        fun where(block: UserWhereUniqueInput.() -> Unit) {
            where = UserWhereUniqueInput(block)
        }

        fun getFilter() = where!!.getFilter()
    }
}