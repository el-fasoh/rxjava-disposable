package com.fasoh.rxjavadisposables.usecases

import com.fasoh.rxjavadisposables.LifecycleAwareTest
import com.fasoh.rxjavadisposables.TestResponse.parseJson
import com.fasoh.rxjavadisposables.models.Rate
import com.fasoh.rxjavadisposables.repositories.MainRepository
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Observable
import org.junit.Assert.assertEquals
import org.junit.Test

class MainUseCaseImplTest : LifecycleAwareTest() {

    private val listener: MainUseCaseListener = mock()
    private val mainRepository: MainRepository = mock()
    private val mainUseCase: MainUseCase by lazy {
        MainUseCaseImpl(mainRepository)
    }



    @Test
    fun executeSuccessfully() {
        whenever(mainRepository.fetchRates())
            .thenReturn(Observable.just(parseJson()))

        val captor = argumentCaptor<List<Rate>>()
        lifecycleOwner.onResume()
        mainUseCase.execute(autoDisposable, listener)

        verify(listener).onSuccess(captor.capture())
        assertEquals(3, captor.firstValue.size)
        assertEquals(1, autoDisposable.compositeDisposable?.size())

        lifecycleOwner.onPause()
        assertEquals(0, autoDisposable.compositeDisposable?.size())
    }

    @Test
    fun executeWithError() {
        whenever(mainRepository.fetchRates())
            .thenReturn(Observable.error(RuntimeException(ERROR)))

        val captor = argumentCaptor<RuntimeException>()
        lifecycleOwner.onResume()
        mainUseCase.execute(autoDisposable, listener)

        verify(listener).onError(captor.capture())
        assertEquals(ERROR, captor.firstValue.message)
        assertEquals(1, autoDisposable.compositeDisposable?.size())

        lifecycleOwner.onPause()
        assertEquals(0, autoDisposable.compositeDisposable?.size())
    }

    companion object {
        val ERROR = "Some error"
    }
}