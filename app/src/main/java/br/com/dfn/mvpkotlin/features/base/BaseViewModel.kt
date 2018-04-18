package br.com.dfn.mvpkotlin.features.base

import android.arch.lifecycle.ViewModel
import br.com.dfn.mvpkotlin.features.base.BasePresenter

open class BaseViewModel : ViewModel() {

    var mPresenter: BasePresenter<*>? = null

    override fun onCleared() {
        super.onCleared()
    }

}