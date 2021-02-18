package rs.fourexample.spaceflightnews.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rs.fourexample.spaceflightnews.db.ArticleDao
import rs.fourexample.spaceflightnews.db.CacheMapper
import rs.fourexample.spaceflightnews.networking.ArticleService
import rs.fourexample.spaceflightnews.networking.NetworkMapper
import rs.fourexample.spaceflightnews.repository.ArticleRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideArticleRepository(
        articleDao: ArticleDao,
        service: ArticleService,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): ArticleRepository {
        return ArticleRepository(articleDao, service, cacheMapper, networkMapper)
    }

}