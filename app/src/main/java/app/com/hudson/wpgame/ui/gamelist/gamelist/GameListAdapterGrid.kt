package app.com.hudson.wpgame.ui.gamelist.gamelist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.com.hudson.wpgame.R
import app.com.hudson.wpgame.model.Game
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.game_list_item.view.*

/**
 * Created by Hans on 25/03/2018.
 */
class GameListAdapterGrid(private val itemClick: (Game) -> Unit) : RecyclerView.Adapter<GameListAdapterGrid.ViewHolder>() {

    var gameList = mutableListOf<Game>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
                layoutInflater.inflate(
                        R.layout.game_list_item,
                        parent,
                        false
                ), itemClick
        )
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindGame(gameList[position])
    }

    class ViewHolder(view: View, val itemClick: (Game) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bindGame(game: Game) {
            with(itemView) {
                name_game.text = game.gameMeta.name
//                Picasso.with(context).load(game.gameMeta.gamePhotos.small)
//                        .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
//                        .error(R.drawable.ic_broken_img)
//                                      .into(img_game)
                setOnClickListener { itemClick(game) }
            }
        }
    }

    fun addAllGames(games: List<Game>) {
        this.gameList.addAll(games)
        notifyDataSetChanged()
    }

    fun resetGames(){
        this.gameList.clear()
        notifyDataSetChanged()
    }

    fun datasetChanged(){
        notifyDataSetChanged()
    }

}