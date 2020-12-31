package rs.fourexample.spaceflightnews.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rs.fourexample.spaceflightnews.networking.ArticleService
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideGson() : Gson {
        return GsonBuilder()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(gson: Gson) : Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://www.spaceflightnewsapi.net/api/v2/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideArticleService(retrofitBuilder: Retrofit.Builder): ArticleService {
        return retrofitBuilder
            .build()
            .create(ArticleService::class.java)
    }
}
