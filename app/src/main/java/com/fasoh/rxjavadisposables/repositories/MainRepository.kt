package com.fasoh.rxjavadisposables.repositories

import com.fasoh.rxjavadisposables.FxApi
import com.fasoh.rxjavadisposables.models.Rate
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject


interface MainRepository {
    fun fetchRates(interval: Long? = 10): Observable<List<Rate>>
}

class MainRepositoryImpl @Inject constructor(
    private val fxApi: FxApi
) : MainRepository {
    override fun fetchRates(interval: Long?): Observable<List<Rate>> {
        return Observable.interval(interval!!, TimeUnit.SECONDS)
            .startWithItem(0)
            .flatMapSingle {
                fxApi.getRates()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}