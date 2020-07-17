package com.fasoh.rxjavadisposables

import com.fasoh.rxjavadisposables.models.Rate
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface FxApi {

    @GET("rates")
    fun getRates(): Single<List<Rate>>
}