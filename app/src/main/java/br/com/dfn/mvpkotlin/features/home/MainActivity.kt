package br.com.dfn.mvpkotlin.features.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import br.com.dfn.mvpkotlin.R
import br.com.dfn.mvpkotlin.features.base.BaseActivity
import br.com.dfn.mvpkotlin.features.base.BaseViewModel
import br.com.dfn.mvpkotlin.features.home.adapters.VerticalRecyclerAdapter
import br.com.dfn.starwarskotlin.core.model.Film
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<MainPresenter, MainContract.MainView>(), MainContract.MainView {

    private val LAST_POSITION = "LAST_POSITION"
    private var lastFirstVisiblePosition: Int = 0

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

    override fun onLoadFilms(films: List<Film>) {
        recyclerView.setHasFixedSize(false)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        val adapterListOfRecipes = VerticalRecyclerAdapter(films)
        recyclerView.adapter = adapterListOfRecipes

        (recyclerView.layoutManager as LinearLayoutManager).scrollToPosition(lastFirstVisiblePosition)
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        lastFirstVisiblePosition = (recyclerView.layoutManager as? LinearLayoutManager)?.findFirstCompletelyVisibleItemPosition() ?: 0
        outState?.putInt(LAST_POSITION, lastFirstVisiblePosition)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        lastFirstVisiblePosition = savedInstanceState?.getInt(LAST_POSITION, 0) ?: 0
    }

}
