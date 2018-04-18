package br.com.dfn.mvpkotlin.features.home

import br.com.dfn.mvpkotlin.features.base.BaseView

class MainContract : BaseView {
    interface MainView : BaseView {
        fun onLoadFilms(size: Int)
    }
}