package app.com.hudson.wpgame.ui.gamelist.gamelist

import android.util.Log
import app.com.hudson.wpgame.model.Game

/**
 * Created by Hans on 24/03/2018.
 */
class GameListPresenter(private val view: GameListContract.View,
                        private val repository: GameListContract.Repository,
                        private val gameCache: GameCache)
    : GameListContract.Presenter {



    override fun start() {
        view.initGameList()
        view.loadNextDataFromApi(0)
        view.showGames(listOf())
    }

    override fun loadGames(page: Int) {
        if (view.isNetworkAvailable()){
            if (page < 100) {
                repository.getMovies(page,
                        { games -> view.showGames(games) },
                        { exception -> Log.e("Erro", exception?.message) }
                )
            }
        }
        else{

            view.showOffLineMessage()

            if (gameCache.games.isEmpty()){
                view.showGames(listOf())
            }else{
                if (view.getAdapteritemCount() == 0){
                    view.showGames(gameCache.games)
                }
            }
        }

    }

    override fun refreshGames() {
        view.resetGameList()
        loadGames(0)
    }

    override fun openMovieDetail(it: Game) {
        view.startDetailActivy(it)
    }
}