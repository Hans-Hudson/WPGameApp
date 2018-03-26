package app.com.hudson.wpgame.application.dagger

import android.content.Context
import app.com.hudson.wpgame.application.MyApplication
import app.com.hudson.wpgame.rest.RetrofitEndPoint
import app.com.hudson.wpgame.ui.gamedetail.GameDetailActivity
import app.com.hudson.wpgame.ui.gamedetail.GameDetailContract
import app.com.hudson.wpgame.ui.gamedetail.GameDetailPresenter
import app.com.hudson.wpgame.ui.gamelist.GameActivity
import app.com.hudson.wpgame.ui.gamelist.gamelist.*
import app.com.hudson.wpgame.ui.gamelist.gamelist.GameCache
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Hans on 23/03/2018.
 */
@Module(includes = [ActivitiesBuilder::class,AndroidInjectionModule::class,GameListModule::class,TwitchApiModule::class,GameDetailModule::class])
class CoreModule(private val app: MyApplication) {
    @Provides @Singleton
    fun provideContext(): Context = app

    @Provides @Singleton
    fun provideApplication(): MyApplication = app

    @Provides @Singleton
    fun provideMovieCache(): GameCache = GameCache()
}

@Module
class TwitchApiModule {
    @Provides
    @Singleton
    fun provideApi(): RetrofitEndPoint {
        return Retrofit.Builder()
                .client(
                        OkHttpClient.Builder()
                                .build()
                )
                .baseUrl("https://api.twitch.tv/kraken/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitEndPoint::class.java)
    }
}

@Module
abstract class ActivitiesBuilder {
    @ContributesAndroidInjector(modules = [GameListModule::class])
    abstract fun gameListFragment(): GameListFragment


    @ContributesAndroidInjector
    abstract fun gameActivity(): GameActivity

    @ContributesAndroidInjector(modules = [GameDetailModule::class])
    abstract fun gameDetailActivity(): GameDetailActivity

}

@Module
class GameListModule{

    @Provides
    fun provideView(view : GameListFragment) : GameListContract.View = view

    @Provides
    fun providePresenter(view: GameListContract.View, source: GameListContract.Repository, cacheGame : GameCache ): GameListContract.Presenter {
        return GameListPresenter(view, source, cacheGame)
    }

    @Provides
    fun provideRepository(api : RetrofitEndPoint, cacheGame : GameCache) : GameListContract.Repository{
        return GameListRepository(api, cacheGame)
    }

}

@Module
class GameDetailModule{

    @Provides
    fun provideView(view : GameDetailActivity) : GameDetailContract.View = view

    @Provides
    fun providePresenter(view: GameDetailContract.View): GameDetailContract.Presenter {
        return GameDetailPresenter(view)
    }
}