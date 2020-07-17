package com.fasoh.rxjavadisposables.repositories

import com.fasoh.rxjavadisposables.FxApi
import com.fasoh.rxjavadisposables.RxImmediateSchedulerRule
import com.fasoh.rxjavadisposables.TestResponse.parseJson
import com.fasoh.rxjavadisposables.models.Rate
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.TestObserver
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeUnit

class MainRepositoryImplTest {
    @get:Rule
    public val schedulers = RxImmediateSchedulerRule()

    private val fxApi: FxApi = mock()
    private val mainRepository: MainRepository by lazy {
        MainRepositoryImpl(fxApi)
    }

    @Test
    fun fetchRates() {
        whenever(fxApi.getRates()).thenReturn(Single.just(parseJson()))
        val testObserver = TestObserver<List<Rate>>()
        val testScheduler = TestScheduler()
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }

        mainRepository.fetchRates(1L).subscribe(testObserver)
        testScheduler.advanceTimeTo(1, TimeUnit.SECONDS)

        testObserver.assertValueAt(0) {
            3 == it.size
        }
    }
}