package br.com.dfn.mvpkotlin.features.base

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import br.com.dfn.mvpkotlin.R

abstract class BaseActivity<P : BasePresenter<V>, V : BaseView> : AppCompatActivity(), BaseView, LifecycleOwner {

    lateinit var mPresenter: P
    lateinit var mView: V
    lateinit var mToolbar: Toolbar

    private val lifecycleRegistry = LifecycleRegistry(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(onRequestLayout())
        setupToolbar()
        onCreate()
        setView(getView())
        attachPresenter(mView)
    }

    override fun onDestroy() {
        super.onDestroy()
        detachPresenter()
    }

    private fun setupToolbar() {
        if (findViewById<View>(R.id.default_toolbar) != null) {
            mToolbar = findViewById(R.id.default_toolbar)
            setSupportActionBar(mToolbar)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setView(view: V) {
        this.mView = view
    }

    abstract fun onRequestLayout(): Int

    abstract fun onCreate()

    abstract fun getView(): V

    fun setTitle(title: String) {
        if (mToolbar != null) {
            (mToolbar.findViewById<View>(R.id.toolbar_title) as TextView).text = title
        }
    }

    private fun attachPresenter(view: V) {
        mPresenter.attachView(view)
    }

    private fun detachPresenter() {
        mPresenter.detachView()
    }

    override fun getLifecycle(): LifecycleRegistry {
        return lifecycleRegistry
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}