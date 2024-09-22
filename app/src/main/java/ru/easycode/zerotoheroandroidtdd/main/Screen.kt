package ru.easycode.zerotoheroandroidtdd.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface Screen {
    fun show(supportFragmentManager: FragmentManager, containerId: Int)

    abstract class Replace(private val fragmentClass: Class<out Fragment>) : Screen {
        override fun show(supportFragmentManager: FragmentManager, containerId: Int) {
            supportFragmentManager.beginTransaction()
                .replace(containerId, fragmentClass.getDeclaredConstructor().newInstance())
                .commit()
        }
    }

    abstract class Add(private val fragmentClass: Class<out Fragment>) : Screen {
        override fun show(supportFragmentManager: FragmentManager, containerId: Int) {
            supportFragmentManager.beginTransaction()
                .add(containerId, fragmentClass.getDeclaredConstructor().newInstance())
                .addToBackStack(null)
                .commit()
        }
    }

    object Pop : Screen {
        override fun show(supportFragmentManager: FragmentManager, containerId: Int) {
            supportFragmentManager.beginTransaction()
                .remove(supportFragmentManager.findFragmentById(containerId)!!)
                .commit()
        }
    }
}
