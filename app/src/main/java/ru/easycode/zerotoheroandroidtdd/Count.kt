package ru.easycode.zerotoheroandroidtdd

interface Count {
    fun increment(number: String): String

    class Base(private val step: Int) : Count {

        init {
            if (step == 0) throw IllegalStateException("step should be more that 0")
            if (step < 0) throw IllegalStateException("step should be positive, but was $step")
        }

        override fun increment(number: String): String {
            if (number.toIntOrNull() == null) throw IllegalStateException("argument was not a number")
            return (number.toInt() + step).toString()
        }
    }
}
