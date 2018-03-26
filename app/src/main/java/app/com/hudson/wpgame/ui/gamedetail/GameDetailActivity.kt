package app.com.hudson.wpgame.ui.gamedetail

import android.app.Activity
import android.os.Bundle
import app.com.hudson.wpgame.R
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_game_detail.*
import javax.inject.Inject

class GameDetailActivity : Activity(), GameDetailContract.View {

    companion object {
        val GAME_NAME = "GameDetailActivity:gamename"
        val GAME_VIEWERS = "GameDetailActivity:gameviewers"
        val GAME_CHANNELS = "GameDetailActivity:gamechannels"
        val GAME_PHOTOS = "GameDetailActivity:gamephotos"
    }

    @Inject
    lateinit var presenter: GameDetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_detail)
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun bindTxt() {
        tv_game_detail_name.text = intent.getStringExtra(GAME_NAME)
        tv_game_detail_channel.text = intent.getStringExtra(GAME_CHANNELS) + " canais"
        tv_game_detail_viewers.text = intent.getStringExtra(GAME_VIEWERS) + " espectadores"
    }

    override fun getImageFromNetwork() {
        val gamePhoto = intent.getStringExtra(GAME_PHOTOS)
//        Picasso.with(this).load(gamePhoto).into(img_game_detail)
    }

    override fun getImageFromCache() {
        val gamePhoto = intent.getStringExtra(GAME_PHOTOS)
//        Picasso.with(this).load(gamePhoto).networkPolicy(NetworkPolicy.OFFLINE).into(img_game_detail)
    }

    override fun isNetworkAvailable(): Boolean {
        return this.isNetworkAvailable()
    }
}
