package app.com.hudson.wpgame.ui.gamelist

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import app.com.hudson.wpgame.R
import app.com.hudson.wpgame.ui.gamelist.gamelist.GameListFragment
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class GameActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        AndroidInjection.inject(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.game_list_container, GameListFragment())
                    .commit()
        }

    }

    fun isNetworkAvailable()  {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager){
            val networkInfo = connectivityManager.activeNetworkInfo
            networkInfo.isConnected

            Toast.makeText(this, "" + networkInfo.isConnected, Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "deu ruim", Toast.LENGTH_SHORT).show()
        }


    }

    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector
}
