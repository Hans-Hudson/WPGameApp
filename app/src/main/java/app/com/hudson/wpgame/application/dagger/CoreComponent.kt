package app.com.hudson.wpgame.application.dagger

import app.com.hudson.wpgame.application.MyApplication
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Hans on 23/03/2018.
 */
@Singleton
@Component( modules = [(CoreModule::class)])
interface CoreComponent {
    infix fun inject(application: MyApplication)
}