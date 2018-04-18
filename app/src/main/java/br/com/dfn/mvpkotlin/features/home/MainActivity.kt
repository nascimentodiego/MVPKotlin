package br.com.dfn.mvpkotlin.features.home

import android.arch.lifecycle.ViewModelProviders
import br.com.dfn.mvpkotlin.R
import br.com.dfn.mvpkotlin.features.base.BaseActivity
import br.com.dfn.mvpkotlin.features.base.BaseViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainPresenter, MainContract.MainView>(), MainContract.MainView {


    override fun onCreate() {

        val mViewModel = ViewModelProviders.of(this).get(BaseViewModel::class.java)

        if (mViewModel.mPresenter == null) {
            mPresenter = MainPresenter()
            mPresenter.attachLifecycle(lifecycle)
            mViewModel.mPresenter = mPresenter

        } else {
            mPresenter = mViewModel.mPresenter as MainPresenter
        }

    }

    override fun onResume() {
        super.onResume()
        mPresenter.doRequestFilms()
    }

    override fun getView(): MainContract.MainView {
        return this
    }

    override fun onRequestLayout(): Int {
        return R.layout.activity_main
    }

    override fun onLoadFilms(size: Int) {
        txt_size.text = "A Quantidade de filmes é $size"
    }


}
