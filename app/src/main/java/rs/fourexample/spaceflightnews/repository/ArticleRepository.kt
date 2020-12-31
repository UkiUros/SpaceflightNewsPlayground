package rs.fourexample.spaceflightnews.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import rs.fourexample.spaceflightnews.db.ArticleDao
import rs.fourexample.spaceflightnews.db.CacheMapper
import rs.fourexample.spaceflightnews.model.Article
import rs.fourexample.spaceflightnews.networking.ArticleService
import rs.fourexample.spaceflightnews.networking.NetworkMapper
import rs.fourexample.spaceflightnews.utils.DataState
import java.lang.Exception


class ArticleRepository constructor(
    private val articleDao: ArticleDao,
    private val articleService: ArticleService,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
){

    suspend fun getArticles(): Flow<DataState<List<Article>>> = flow {
        emit(DataState.Loading)

        try {
            val networkArticles = articleService.getArticles()
            val articles = networkMapper.mapFromEntityList(networkArticles)

            articles.forEach {
                articleDao.insert(cacheMapper.mapToEntity(it))
            }

            val cachedArticles = articleDao.getArticles()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedArticles)))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }

    }

}
