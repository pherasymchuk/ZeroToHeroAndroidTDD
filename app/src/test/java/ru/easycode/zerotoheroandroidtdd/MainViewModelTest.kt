package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.zerotoheroandroidtdd.ui.main.MainViewModel
import ru.easycode.zerotoheroandroidtdd.wrappers.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.wrappers.ListLiveDataWrapper

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private lateinit var listLiveDataWrapper: FakeListLiveDataWrapper

    @Before
    fun init() {
        listLiveDataWrapper = FakeListLiveDataWrapper.Base()
        viewModel = MainViewModel.Default(listLiveDataWrapper = listLiveDataWrapper)
    }

    @Test
    fun test() {
        viewModel.add(text = "first")
        listLiveDataWrapper.checkListSame(listOf("first"))

        viewModel.add(text = "second")
        listLiveDataWrapper.checkListSame(listOf("first", "second"))

        val bundleWrapper: BundleWrapper.Mutable = FakeBundleWrapper()
        val bundleWrapperSave: BundleWrapper.Save = bundleWrapper
        val bundleWrapperRestore: BundleWrapper.Restore = bundleWrapper

        viewModel.saveState(bundle = bundleWrapperSave)

        init()

        viewModel.restoreState(bundle = bundleWrapperRestore)
        listLiveDataWrapper.checkListSame(listOf("first", "second"))
    }
}

private interface FakeListLiveDataWrapper : ListLiveDataWrapper.Mutable {

    fun checkListSame(expected: List<String>)

    class Base : FakeListLiveDataWrapper {

        private val list = ArrayList<String>()

        override fun checkListSame(expected: List<String>) {
            assertEquals(expected, list)
        }

        override fun liveData(): LiveData<ArrayList<String>> {
            throw IllegalStateException("not used here")
        }

        override fun add(new: String) {
            list.add(new)
        }

        override fun saveState(bundle: BundleWrapper.Save) {
            bundle.save(list)
        }

        override fun update(list: ArrayList<String>) {
            this.list.addAll(list)
        }
    }
}

class FakeBundleWrapper : BundleWrapper.Mutable {

    private val cache = ArrayList<String>()

    override fun save(list: ArrayList<String>) {
        cache.addAll(list)
    }

    override fun restore(): ArrayList<String> {
        return cache
    }
}
