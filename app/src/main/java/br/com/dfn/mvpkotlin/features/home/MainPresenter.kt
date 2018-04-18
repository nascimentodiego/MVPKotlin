package br.com.dfn.mvpkotlin.features.home

import android.util.Log
import br.com.dfn.mvpkotlin.data.source.remote.StartWarsClient
import br.com.dfn.mvpkotlin.features.base.BasePresenter
import br.com.dfn.starwarskotlin.core.model.Film
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter : BasePresenter<MainContract.MainView>() {

    private var mListOfMovies: List<Film>? = null

    fun doRequestFilms() {

        var view = getView()

        if (mListOfMovies != null) {
            mListOfMovies?.forEach {
                Log.d("Main", "Film: " + it.title)
            }
            view?.onLoadFilms(mListOfMovies!!.size)
            return
        }

        var startApi = StartWarsClient
        startApi.getApi().getFilms()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { movies ->
                            movies.results.forEach {
                                Log.d("Main", "Film: " + it.title)
                            }
                            mListOfMovies = movies.results
                            view?.onLoadFilms(mListOfMovies!!.size)
                        },
                        { t: Throwable -> this.handleError(t) },
                        { Log.d("Main", "OnComplete") })

    }

    private fun handleError(t: Throwable) {
        Log.d("handleError", "Throwable: " + t.message)
    }

}