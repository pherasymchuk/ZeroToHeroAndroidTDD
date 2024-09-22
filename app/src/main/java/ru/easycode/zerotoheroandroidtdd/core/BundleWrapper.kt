package ru.easycode.zerotoheroandroidtdd.core

interface BundleWrapper {
    interface Save : BundleWrapper {
        fun save(list: ArrayList<CharSequence>)
    }

    interface Restore : BundleWrapper {
        fun restore(): List<CharSequence>
    }

    interface Mutable : Save, Restore
}
