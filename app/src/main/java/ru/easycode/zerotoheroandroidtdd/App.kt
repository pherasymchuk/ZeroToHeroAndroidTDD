package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.easycode.zerotoheroandroidtdd.data.Repository
import ru.easycode.zerotoheroandroidtdd.network.SimpleService
import ru.easycode.zerotoheroandroidtdd.wrappers.LiveDataWrapper

class MainApplication : Application(), ProvideMainViewModel {
    private lateinit var mainViewModel: MainViewModel

    override fun provide(): MainViewModel {
        return mainViewModel
    }

    override fun onCreate() {
        super.onCreate()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: SimpleService = retrofit.create(SimpleService::class.java)

        mainViewModel = MainViewModel(
            LiveDataWrapper.Base(),
            Repository.Base(service, URL)
        )
    }

    companion object {
        private const val URL =
            "https://raw.githubusercontent.com/JohnnySC/ZeroToHeroAndroidTDD/task/018-clouddatasource/app/sampleresponse.json"
    }
}

interface ProvideMainViewModel {
    fun provide(): MainViewModel
}
