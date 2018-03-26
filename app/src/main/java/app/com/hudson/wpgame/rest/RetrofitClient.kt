package app.com.hudson.wpgame.rest

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Hans on 23/03/2018.
 */
class RetrofitClient {
    private val gson: Gson = GsonBuilder().create()

    private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.twitch.tv/kraken/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()


    fun getClient() : Retrofit{
        return retrofit
    }
}