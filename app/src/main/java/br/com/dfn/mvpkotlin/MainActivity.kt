package br.com.dfn.mvpkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import br.com.dfn.mvpkotlin.data.source.remote.StartWarsClient
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var startApi = StartWarsClient

        startApi.getApi().getFilms().subscribeOn(Schedulers.io()).subscribe(
                { movies ->
                    movies.results.forEach {
                        Log.d("Main", "Film: " + it.title)
                    }
                },
                { t: Throwable -> this.handleError(t) },
                { Log.d("Main", "OnComplete") })
    }

    private fun handleError(t: Throwable) {
        Log.d("handleError", "Throwable: " + t.message)
    }
}
