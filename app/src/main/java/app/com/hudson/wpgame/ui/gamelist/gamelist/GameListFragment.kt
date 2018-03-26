package app.com.hudson.wpgame.ui.gamelist.gamelist

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.com.hudson.wpgame.R
import app.com.hudson.wpgame.model.Game
import app.com.hudson.wpgame.ui.gamedetail.GameDetailActivity
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_game_list.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity
import javax.inject.Inject


class GameListFragment : Fragment(), GameListContract.View {

    @Inject
    lateinit var presenter: GameListContract.Presenter
    private lateinit var listAdapter: GameListAdapterGrid

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_game_list, container, false)

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun initGameList() {

        listAdapter = GameListAdapterGrid {
            presenter.openMovieDetail(it)
        }

        game_list_rv.run {
            layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL,false)
            setOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager as GridLayoutManager){
                override fun onLoadMore(page: Int, totalCaptureResult: Int, view: RecyclerView?) {
                    loadNextDataFromApi(page)
                }
            })

            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }

        swipe_container.setOnRefreshListener {
            presenter.refreshGames()
            swipe_container.isRefreshing = false
        }
    }

    override fun loadNextDataFromApi(offset: Int) {
        presenter.loadGames(offset * 20)
    }

    override fun showGames(games: List<Game>) {
        listAdapter.addAllGames(games)
    }

    override fun resetGameList() {
        listAdapter.resetGames()
    }

    override fun showOffLineMessage() {
        activity?.alert ("Sua conexão esta sem continue, verifique suas fichas. carregaremos o que possuir em seu checkpoint", "Game Over"){ positiveButton("Entendi"){null} }?.show()
    }

    override fun startDetailActivy(game: Game) {
        activity?.startActivity<GameDetailActivity>(GameDetailActivity.GAME_NAME to game.gameMeta.name,
                                                             GameDetailActivity.GAME_VIEWERS to game.viewers,
                                                             GameDetailActivity.GAME_CHANNELS to game.channels,
                                                             GameDetailActivity.GAME_PHOTOS to game.gameMeta.gamePhotos.small)
    }

    override fun isNetworkAvailable() : Boolean {
        return activity!!.isNetworkAvailable()
    }


    override fun getAdapteritemCount(): Int {
        return listAdapter.itemCount
    }
}

fun Activity.isNetworkAvailable() : Boolean{
    val cm = baseContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkInfo = cm.activeNetworkInfo

    if (networkInfo != null && networkInfo.isConnected){
        return true
    }


    return false
}