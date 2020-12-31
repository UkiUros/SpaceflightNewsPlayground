package rs.fourexample.spaceflightnews.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import rs.fourexample.spaceflightnews.db.ArticleDao
import rs.fourexample.spaceflightnews.db.ArticleDatabase
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideArticleDb(@ApplicationContext context: Context): ArticleDatabase {
        return Room
            .databaseBuilder(
                context,
                ArticleDatabase::class.java,
                ArticleDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideArticleDAO(database: ArticleDatabase): ArticleDao {
        return database.articleDao()
    }

}