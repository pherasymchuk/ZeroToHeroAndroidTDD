package ru.easycode.zerotoheroandroidtdd.utils

interface Log {
    fun log(message: String)

    class Debug(
        private val tag: String = "DebugLogs",
    ) : Log {
        override fun log(message: String) {
            android.util.Log.d(tag, message)
        }
    }
}
