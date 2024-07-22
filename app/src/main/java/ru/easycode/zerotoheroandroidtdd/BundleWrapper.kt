package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle
import android.os.Parcelable

interface BundleWrapper : SaveParcelable,
    RestoreParcelable,
    SaveStringArrayList,
    RestoreStringArrayList {

    class Default(private val bundle: Bundle) : BundleWrapper {
        override fun <T : Parcelable> saveParcelable(key: String, data: T) {
            bundle.putParcelable(key, data)
        }

        override fun <T : Parcelable> restoreParcelable(key: String, clazz: Class<T>): T {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable(key, clazz)
            } else {
                @Suppress("DEPRECATION")
                bundle.getParcelable(key)
            } ?: throw IllegalArgumentException("Parcelable with key $key not found")
        }

        override fun saveStringArrayList(key: String, data: ArrayList<String>) {
            bundle.putStringArrayList(key, data)
        }

        override fun restoreStringArrayList(key: String): ArrayList<String> {
            return bundle.getStringArrayList(key)
                ?: throw IllegalArgumentException("StringArrayList with key $key not found")
        }
    }
}

interface SaveParcelable {
    fun <T : Parcelable> saveParcelable(key: String, data: T)
}

interface RestoreParcelable {
    fun <T : Parcelable> restoreParcelable(key: String, clazz: Class<T>): T
}

interface SaveStringArrayList {
    fun saveStringArrayList(key: String, data: ArrayList<String>)
}

interface RestoreStringArrayList {
    fun restoreStringArrayList(key: String): ArrayList<String>
}
