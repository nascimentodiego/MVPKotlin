package br.com.dfn.mvpkotlin.features.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log
import java.lang.ref.WeakReference

abstract class BasePresenter<V>: LifecycleObserver {
    private var view: WeakReference<V>? = null
    private var viewAttachedAtLeastOnce = false

    fun attachView(view: V) {
        this.view = WeakReference(view)
        this.viewAttachedAtLeastOnce = true
    }

    fun detachView() {
        if (this.view != null) {
            this.view!!.clear()
            this.view = null
        }
    }

    fun attachLifecycle(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }

    fun detachLifecycle(lifecycle: Lifecycle) {
        lifecycle.removeObserver(this)
    }

    fun getView(): V? {
        if (!viewAttachedAtLeastOnce) {
            throw IllegalStateException("No view has ever been attached to this presenter!")
        }

        return view!!.get()
    }


    @OnLifecycleEvent(value = Lifecycle.Event.ON_CREATE)
    protected fun onCreate() {
        Log.d("Presenter","onCreate")
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_DESTROY)
    protected fun onDestroy() {
        Log.d("Presenter","onDestroy")
    }
}