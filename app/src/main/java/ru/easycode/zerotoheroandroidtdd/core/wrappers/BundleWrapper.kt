package ru.easycode.zerotoheroandroidtdd.wrappers

import android.os.Bundle

interface BundleWrapper {

    interface Save {
        fun save(list: ArrayList<String>)
    }

    interface Restore {
        fun restore(): ArrayList<String>
    }

    interface Mutable : BundleWrapper, Save, Restore

    class Default(private val bundle: Bundle) : Mutable {
        private val key: String = "State_Key"
        override fun save(list: ArrayList<String>) {
            bundle.putCharSequenceArrayList(key, ArrayList(list))
        }

        override fun restore(): ArrayList<String> {
            val strings: List<String> = (bundle.getCharSequenceArrayList(key)?.map { it.toString() }
                ?: throw IllegalArgumentException("No such key"))
            return ArrayList(strings)
        }

    }
}
