package rs.fourexample.spaceflightnews.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ArticleCacheEntity::class], version = 1)
abstract class ArticleDatabase: RoomDatabase(){

    abstract fun articleDao(): ArticleDao

    companion object {
        val DATABASE_NAME = "articles_db"
    }
}