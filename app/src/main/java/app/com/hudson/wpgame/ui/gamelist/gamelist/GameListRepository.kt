package app.com.hudson.wpgame.ui.gamelist.gamelist

import app.com.hudson.wpgame.model.Game
import app.com.hudson.wpgame.model.Twitch
import app.com.hudson.wpgame.rest.RetrofitEndPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Hans on 24/03/2018.
 */
class GameListRepository(private val api: RetrofitEndPoint
                         ,private val gameCache: GameCache
) : GameListContract.Repository {


    override fun getMovies(page: Int, onSuccess: (MutableList<Game>) -> Unit, onError: (Throwable?) -> Unit) {

        api.getGames(20, page).enqueue(object : Callback<Twitch> {
            override fun onFailure(call: Call<Twitch>?, t: Throwable?) {
                onError.invoke(t)
            }

            override fun onResponse(call: Call<Twitch>?, response: Response<Twitch>?) {
                var twitch: Twitch
                if (response?.body() != null) {
                    if (response.isSuccessful) {
                        response?.body()?.let {

                            if (page != 0) {
                                gameCache.store(it.top.toMutableList())
                            } else {
                                gameCache.restartList(it.top.toMutableList())
                            }
                            onSuccess.invoke(it.top.toMutableList())
                        }
                    }
                }
            }
        })
    }
}