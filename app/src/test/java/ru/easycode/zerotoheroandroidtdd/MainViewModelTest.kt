package ru.easycode.zerotoheroandroidtdd

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import ru.easycode.zerotoheroandroidtdd.data.LoadResult
import ru.easycode.zerotoheroandroidtdd.data.UiState
import ru.easycode.zerotoheroandroidtdd.fakes.FakeBundleWrapper
import ru.easycode.zerotoheroandroidtdd.fakes.FakeLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.fakes.FakeRepository
import ru.easycode.zerotoheroandroidtdd.network.SimpleResponse
import ru.easycode.zerotoheroandroidtdd.wrappers.BundleWrapper

/**
 * Please also check out the ui test
 * @see ru.easycode.zerotoheroandroidtdd.Task019Test
 *
 * And other unit tests
 */
class MainViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        initialize()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private lateinit var repository: FakeRepository
    private lateinit var liveDataWrapper: FakeLiveDataWrapper
    private lateinit var viewModel: MainViewModel

    fun initialize() {
        repository = FakeRepository.Base()
        liveDataWrapper = FakeLiveDataWrapper.Base()
        viewModel = MainViewModel(
            liveDataWrapper = liveDataWrapper,
            repository = repository
        )
    }

    @Test
    fun testViewModelStateRestoration() {
        val expectedText = "testingText"
        repository.expectResult(LoadResult.Success(SimpleResponse(text = expectedText)))

        viewModel.load()

        liveDataWrapper.checkUpdateCalls(
            listOf(
                UiState.ShowProgress,
                UiState.ShowData(text = expectedText)
            )
        )
        repository.checkLoadCalledTimes(1)

        val bundleWrapper: BundleWrapper.Mutable = FakeBundleWrapper.Base()
        val bundleWrapperSave: BundleWrapper.SaveState = bundleWrapper
        val bundleWrapperRestore: BundleWrapper.RestoreState = bundleWrapper

        viewModel.saveState(bundleWrapper = bundleWrapperSave)

        initialize()

        viewModel.restoreState(bundleWrapper = bundleWrapperRestore)
        liveDataWrapper.checkUpdateCalls(listOf(UiState.ShowData(text = expectedText)))
        repository.checkLoadCalledTimes(0)
    }
}

data class Nma(private val one: String) {

}
