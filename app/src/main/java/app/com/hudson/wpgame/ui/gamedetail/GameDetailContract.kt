package app.com.hudson.wpgame.ui.gamedetail

/**
 * Created by SlowMotion on 26/03/2018.
 */
interface GameDetailContract {
    interface View{
        fun bindTxt()
        fun isNetworkAvailable() : Boolean
        fun getImageFromNetwork()
        fun getImageFromCache()
    }

    interface Presenter{
        fun start()
    }
}