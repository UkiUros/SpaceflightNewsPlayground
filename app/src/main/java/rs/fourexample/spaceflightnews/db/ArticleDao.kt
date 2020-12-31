package rs.fourexample.spaceflightnews.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(articleCacheEntity: ArticleCacheEntity): Long

    @Query("SELECT * FROM articles")
    suspend fun getArticles(): List<ArticleCacheEntity>

}