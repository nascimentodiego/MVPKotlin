package br.com.dfn.mvpkotlin.features.home

import br.com.dfn.mvpkotlin.features.base.BaseView
import br.com.dfn.starwarskotlin.core.model.Film

class MainContract : BaseView {
    interface MainView : BaseView {
        fun onLoadFilms(films: List<Film>)
    }
}