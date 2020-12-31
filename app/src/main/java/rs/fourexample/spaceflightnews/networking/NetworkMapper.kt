package rs.fourexample.spaceflightnews.networking

import rs.fourexample.spaceflightnews.model.Article
import rs.fourexample.spaceflightnews.utils.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : EntityMapper<ArticleNetworkEntity, Article> {
    override fun mapFromEntity(entity: ArticleNetworkEntity): Article {
        return Article(
            id = entity.id,
            title = entity.title,
            articleUrl = entity.url,
            imageUrl = entity.imageUrl
        )
    }

    override fun mapToEntity(domainModel: Article): ArticleNetworkEntity {
        return ArticleNetworkEntity(
            id = domainModel.id,
            title = domainModel.title,
            url = domainModel.articleUrl,
            imageUrl = domainModel.imageUrl
        )
    }

    fun mapFromEntityList(entities: List<ArticleNetworkEntity>): List<Article> {
        return entities.map { mapFromEntity(it) }
    }

}
