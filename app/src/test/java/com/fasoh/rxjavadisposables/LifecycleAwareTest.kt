package com.fasoh.rxjavadisposables

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before

open class LifecycleAwareTest {
    protected val autoDisposable = RxDisposer()
    protected val lifecycleOwner = TestLifecycleOwner()

    @Before
    open fun setUp() {
        autoDisposable.bind(lifecycleOwner.lifecycle)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }
}