package app.com.hudson.wpgame.features.gamelist

import app.com.hudson.wpgame.features.Game

/**
 * Created by SlowMotion on 26/03/2018.
 */
class GameCache {
    var games: MutableList<Game> = mutableListOf()

    fun store(newGames: List<Game>): List<Game> =
            games.apply {
                addAll(newGames)
            }

    fun restartList(newGames: List<Game>): List<Game> =
            games.apply {
                clear()
                addAll(newGames)
            }
}