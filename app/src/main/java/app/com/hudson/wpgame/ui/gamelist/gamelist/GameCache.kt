package app.com.hudson.wpgame.ui.gamelist.gamelist

import app.com.hudson.wpgame.model.Game

/**
 * Created by SlowMotion on 26/03/2018.
 */
class GameCache {
    val isEmpty: Boolean
        get() = games.isEmpty()

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