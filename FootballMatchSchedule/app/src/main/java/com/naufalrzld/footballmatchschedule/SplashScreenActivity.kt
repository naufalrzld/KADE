package com.naufalrzld.footballmatchschedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import org.jetbrains.anko.intentFor


class SplashScreenActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 3000

    private lateinit var presenter: SplashScreenPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            presenter = SplashScreenPresenter(this)
            presenter.getLeagues()

            startActivity(intentFor<MainActivity>())
            finish()
        }, SPLASH_TIME_OUT)
    }
}
