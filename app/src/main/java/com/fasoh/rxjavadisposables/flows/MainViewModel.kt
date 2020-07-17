package com.fasoh.rxjavadisposables.flows

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fasoh.rxjavadisposables.RxDisposer
import com.fasoh.rxjavadisposables.models.Rate
import com.fasoh.rxjavadisposables.usecases.MainUseCase
import com.fasoh.rxjavadisposables.usecases.MainUseCaseListener

class MainViewModel @ViewModelInject constructor(
    private val mainUseCase: MainUseCase
) : ViewModel() {

    private val _rates = MutableLiveData<List<Rate>>()
    val rates: LiveData<List<Rate>> = _rates

    fun setup(rxDisposer: RxDisposer) {
        mainUseCase.execute(rxDisposer,
            listener = object : MainUseCaseListener {
                override fun onSuccess(rates: List<Rate>) {
                    _rates.value = rates
                }

                override fun onError(error: Throwable) {
                    error.printStackTrace()
                }
            })
    }
}