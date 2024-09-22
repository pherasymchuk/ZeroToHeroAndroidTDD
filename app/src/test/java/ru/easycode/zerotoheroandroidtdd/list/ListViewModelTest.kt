package ru.easycode.zerotoheroandroidtdd.list

import org.junit.Before
import org.junit.Test
import ru.easycode.zerotoheroandroidtdd.core.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.create.CreateScreen
import ru.easycode.zerotoheroandroidtdd.main.FakeNavigation
import ru.easycode.zerotoheroandroidtdd.main.Navigation

class ListViewModelTest {

    private lateinit var viewModel: ListViewModel
    private lateinit var navigation: FakeNavigation
    private lateinit var liveDataWrapper: FakeListLiveDataWrapper

    @Before
    fun setup() {
        liveDataWrapper = FakeListLiveDataWrapper.Default()
        val mutableLiveDataWrapper: ListLiveDataWrapper.Mutable = liveDataWrapper

        navigation = FakeNavigation.Default()
        val navigationUpdate: Navigation.UpdateScreen = navigation

        viewModel = ListViewModel.Default(
            liveDataList = mutableLiveDataWrapper,
            screenNavigation = navigationUpdate
        )
    }

    @Test
    fun test_navigation() {
        viewModel.create()
        navigation.checkUpdateCalled(listOf(CreateScreen))
    }

    @Test
    fun test_save_and_restore() {
        liveDataWrapper.update(listOf("1", "2", "3"))
        val bundleWrapper = FakeBundleWrapper.Default()
        val save: BundleWrapper.Save = bundleWrapper
        val restore: BundleWrapper.Restore = bundleWrapper

        viewModel.saveState(bundleWrapper = save)

        setup()

        viewModel.restoreState(bundleWrapper = restore)
        liveDataWrapper.checkCalledList(listOf("1", "2", "3"))
    }
}

private interface FakeBundleWrapper : BundleWrapper.Mutable {

    class Default : FakeBundleWrapper {
        private val cached = ArrayList<CharSequence>()

        override fun save(list: ArrayList<CharSequence>) {
            cached.addAll(list)
        }

        override fun restore(): List<CharSequence> {
            return cached
        }
    }
}
