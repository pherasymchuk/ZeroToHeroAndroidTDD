package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.easycode.zerotoheroandroidtdd.data.Repository
import ru.easycode.zerotoheroandroidtdd.network.SimpleService
import ru.easycode.zerotoheroandroidtdd.wrappers.LiveDataWrapper
import java.util.concurrent.TimeUnit

class MainApplication : Application(), ProvideMainViewModel {
    private lateinit var mainViewModel: MainViewModel

    override fun provide(): MainViewModel {
        return mainViewModel
    }

    override fun onCreate() {
        super.onCreate()

        val okHttpClient = OkHttpClient.Builder()
            .callTimeout(5, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()


        val service: SimpleService = retrofit.create(SimpleService::class.java)

        mainViewModel = MainViewModel(
            LiveDataWrapper.Default(),
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
