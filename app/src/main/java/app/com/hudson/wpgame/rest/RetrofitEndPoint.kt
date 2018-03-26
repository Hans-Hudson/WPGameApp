package app.com.hudson.wpgame.rest

import app.com.hudson.wpgame.model.Twitch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created by Hans on 23/03/2018.
 */
interface RetrofitEndPoint {

    @Headers("Client-ID:nhz5dhstugedvkxqfisrq2ffs66281", "Accept: application/vnd.twitchtv.v5+json")
    @GET("games/top?limit=100")
    fun getGames(@Query("limit")limit: Int,
                 @Query("offset")offset: Int): Call<Twitch>

}