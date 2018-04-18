package br.com.dfn.mvpkotlin.features.base

import android.content.Context
import android.support.v4.app.Fragment

abstract class BaseFragment<P : BasePresenter<V>, V : BaseView> : Fragment(), BaseView {

    protected lateinit var mPresenter: P
    protected lateinit var mView: V
    protected var forceUpdate = true


    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    protected fun cancelForceUpdate() {
        forceUpdate = false
    }

    override fun onDestroy() {
        super.onDestroy()
        detachPresenter()
    }

    private fun inject() {
        onCreateView()
        setView(getFragmentView())
        attachPresenter()
    }

    private fun setView(view: V) {
        this.mView = view
    }

    abstract fun getFragmentView(): V

    abstract fun onCreateView()

    private fun attachPresenter() {
        mPresenter.attachView(mView)
    }

    private fun detachPresenter() {
        mPresenter.detachView()
    }

}