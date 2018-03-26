package app.com.hudson.wpgame.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Hans on 25/03/2018.
 */


data class Twitch (var _total : String, var top : List<Game>) {}
data class Game(@SerializedName("game")var gameMeta : GameMeta, var viewers : String, var channels : String) {}
data class GameMeta (var name : String, @SerializedName("box") var gamePhotos: GamePhotos, var image : Int) {}
data class GamePhotos (var small : String)