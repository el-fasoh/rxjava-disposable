package com.fasoh.rxjavadisposables.usecases

import com.fasoh.rxjavadisposables.RxDisposer
import com.fasoh.rxjavadisposables.addToDisposer
import com.fasoh.rxjavadisposables.models.Rate
import com.fasoh.rxjavadisposables.repositories.MainRepository

interface MainUseCase {
    fun execute(rxDisposer: RxDisposer, listener: MainUseCaseListener)
}

class MainUseCaseImpl(
    private val mainRepository: MainRepository
) : MainUseCase {
    override fun execute(rxDisposer: RxDisposer, listener: MainUseCaseListener) {
        mainRepository.fetchRates()
            .subscribe({
                listener.onSuccess(it)
            }, {
                listener.onError(it)
            }).addToDisposer(rxDisposer)
    }

}

interface MainUseCaseListener {
    fun onSuccess(rates: List<Rate>)
    fun onError(error: Throwable)
}