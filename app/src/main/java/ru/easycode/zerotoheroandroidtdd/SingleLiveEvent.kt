package ru.easycode.zerotoheroandroidtdd

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Please use this class for LiveDataWrapper
 */
class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val isPending = AtomicBoolean(false)

    /**
     * Observes the LiveData, only emitting the first value to the observer.
     *
     * This method is safe to call from any thread.
     *
     * @param owner The LifecycleOwner which controls the observer
     * @param observer The observer that will receive the emitted value
     */
    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner) { t ->
            if (isPending.compareAndSet(true, false))
                observer.onChanged(t)
        }
    }

    @MainThread
    override fun setValue(t: T?) {
        isPending.set(true)
        super.setValue(t)
    }
}
