package app.com.hudson.wpgame.ui.gamedetail

/**
 * Created by SlowMotion on 26/03/2018.
 */
class GameDetailPresenter(private val view : GameDetailContract.View) : GameDetailContract.Presenter{


    override fun start() {
        view.bindTxt()
        bindImageLogic()
    }

    fun bindImageLogic(){
        if (view.isNetworkAvailable()){
            view.getImageFromNetwork()
        }
    }
}