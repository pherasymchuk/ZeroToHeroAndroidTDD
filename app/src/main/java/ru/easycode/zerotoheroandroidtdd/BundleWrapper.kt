package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle

interface BundleWrapper {

    interface Restore {
        fun restore(): List<CharSequence>
    }

    interface Save {
        fun save(list: ArrayList<CharSequence>)
    }

    interface Mutable : Save, Restore

    class Default(private val bundle: Bundle) : Mutable {
        private val key = "BundleWrapperState"
        override fun save(list: ArrayList<CharSequence>) {
            bundle.putCharSequenceArrayList(key, list)
        }

        override fun restore(): List<CharSequence> {
            return bundle.getCharSequenceArrayList(key) ?: ArrayList()
        }

    }
}
