package ru.easycode.zerotoheroandroidtdd

interface Count {
    fun initial(number: String): UiState
    fun increment(number: String): UiState
    fun decrement(number: String): UiState

    class Base(private val step: Int, private val max: Int, private val min: Int) : Count {

        init {
            if (step <= 0) {
                throw IllegalStateException("step should be positive, but was $step")
            }
            if (max <= 0) {
                throw IllegalStateException("max should be positive, but was -2")
            }
            if (max < step) {
                throw java.lang.IllegalStateException("max should be more than step")
            }
            if (max < min) {
                throw IllegalStateException("max should be more than min")
            }
        }

        override fun initial(number: String): UiState {
            val numberAsInt = number.toInt()
            return when {
                numberAsInt + step > max -> UiState.Max(number)
                numberAsInt - step < min -> UiState.Min(number)
                else -> UiState.Base(number)
            }
        }

        override fun increment(number: String): UiState {
            val numberAsInt = number.toInt()
            return if (numberAsInt + step * 2 <= max) {
                UiState.Base((numberAsInt + step).toString())
            } else {
                UiState.Max((numberAsInt + step).toString())
            }
        }

        override fun decrement(number: String): UiState {
            val numberAsInt = number.toInt()
            return if (numberAsInt - step * 2 < min) {
                UiState.Min((numberAsInt - step).toString())
            } else {
                UiState.Base((numberAsInt - step).toString())
            }
        }

    }
}
