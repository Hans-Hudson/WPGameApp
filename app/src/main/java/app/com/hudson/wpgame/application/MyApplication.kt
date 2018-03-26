package app.com.hudson.wpgame.application

import android.app.Activity
import android.app.Application
import android.support.v7.app.AppCompatDelegate
import app.com.hudson.wpgame.application.dagger.CoreComponent
import app.com.hudson.wpgame.application.dagger.CoreModule
import app.com.hudson.wpgame.application.dagger.DaggerCoreComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Hans on 23/03/2018.
 */
class MyApplication : Application(), HasActivityInjector {
    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    companion object {
        private lateinit var INSTANCE: MyApplication
        fun get(): MyApplication = INSTANCE
    }

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    lateinit var coreComponent: CoreComponent


    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        coreComponent = DaggerCoreComponent.builder().coreModule(CoreModule(this)).build()
        coreComponent inject this
    }
}