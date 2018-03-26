package app.com.hudson.wpgame.ui.gamelist.gamelist

import app.com.hudson.wpgame.model.Game
import app.com.hudson.wpgame.model.Twitch

/**
 * Created by Hans on 24/03/2018.
 */
interface GameListContract {
    interface View{
       fun initGameList()
        fun showGames(games : List<Game>)
        fun resetGameList()
        fun showOffLineMessage()
        fun startDetailActivy(game : Game)
        fun isNetworkAvailable() : Boolean
        fun loadNextDataFromApi(offset: Int)
        fun getAdapteritemCount(): Int
    }

    interface Presenter{
        fun start()
        fun loadGames(page : Int)
        fun openMovieDetail(it: Game)
        fun refreshGames()

    }

    interface Repository{
//        fun getMovies() : Twitch?
        fun getMovies(page: Int ,onSuccess: (MutableList<Game>) -> Unit, onError: (Throwable?) -> Unit)
    }
}