package app.com.hudson.wpgame.features

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Hans on 26/03/2018.
 */
fun Activity.isNetworkAvailableExtension() : Boolean {
    val cm = baseContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = cm.activeNetworkInfo
    if (networkInfo != null && networkInfo.isConnected) {
        return true
    }
    return false
}