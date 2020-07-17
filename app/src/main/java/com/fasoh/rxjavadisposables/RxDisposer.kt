package com.fasoh.rxjavadisposables

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import java.lang.NullPointerException


class RxDisposer : LifecycleObserver {

   var compositeDisposable: CompositeDisposable? = null
    fun bind(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
        compositeDisposable = CompositeDisposable()
    }

    fun add(disposable: Disposable) {
        compositeDisposable?.let {
            it.add(it)
        }?:run {
            throw NullPointerException("No lifecycle bound")
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        compositeDisposable?.dispose()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        compositeDisposable?.dispose()
    }
}

fun Disposable.addToDisposer(rxDisposer: RxDisposer) {
    rxDisposer.add(this)
}