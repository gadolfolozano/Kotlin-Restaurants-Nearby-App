package pe.com.gadolfolozano.kotlinzomatoapi.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pe.com.gadolfolozano.kotlinzomatoapi.data.DataManager
import pe.com.gadolfolozano.kotlinzomatoapi.data.DataManagerImplements
import pe.com.gadolfolozano.kotlinzomatoapi.data.remote.ApiHelper
import pe.com.gadolfolozano.kotlinzomatoapi.data.remote.ApiHelperImplements
import pe.com.gadolfolozano.kotlinzomatoapi.data.remote.util.ApiInterface
import pe.com.gadolfolozano.kotlinzomatoapi.data.remote.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideApiHelper(apiHelperImplements: ApiHelperImplements): ApiHelper {
        return apiHelperImplements
    }

    @Provides
    @Singleton
    internal fun provideDataManager(dataManagerImplements: DataManagerImplements): DataManager {
        return dataManagerImplements
    }

    @Provides
    @Singleton
    internal fun provideApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("user-key", Constants.API_KEY)
                    .build()
                chain.proceed(request)
            }
            .connectTimeout(40, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .cache(null).build()


        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(client)
            .build()
    }

}