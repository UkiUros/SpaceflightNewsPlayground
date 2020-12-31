package rs.fourexample.spaceflightnews.db

import rs.fourexample.spaceflightnews.model.Article
import rs.fourexample.spaceflightnews.utils.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor() : EntityMapper<ArticleCacheEntity, Article> {
    override fun mapFromEntity(entity: ArticleCacheEntity): Article {
        return Article(
            id = entity.id,
            title = entity.title,
            articleUrl = entity.articleUrl,
            imageUrl = entity.imageUrl
        )
    }

    override fun mapToEntity(domainModel: Article): ArticleCacheEntity {
        return ArticleCacheEntity(
            id = domainModel.id,
            title = domainModel.title,
            articleUrl = domainModel.articleUrl,
            imageUrl = domainModel.imageUrl
        )
    }

    fun mapFromEntityList(entities: List<ArticleCacheEntity>): List<Article> {
        return entities.map { mapFromEntity(it) }
    }
}
