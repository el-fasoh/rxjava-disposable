package com.fasoh.rxjavadisposables

import com.fasoh.rxjavadisposables.models.Rate
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

object TestResponse {

    fun parseJson(): List<Rate> {
        val type: Type = Types.newParameterizedType(
            MutableList::class.java,
            Rate::class.java
        )
        val adapter: JsonAdapter<List<Rate>> = Moshi.Builder()
            .build().adapter(type)
        return adapter.fromJson(RESPONSE).orEmpty()

    }

    val RESPONSE = "[\n" +
            "  {\n" +
            "    \"currency\": \"CHN.TECH\",\n" +
            "    \"rate\": \"3189.0975\",\n" +
            "    \"bid\": \"3178.239\",\n" +
            "    \"ask\": \"3199.956\",\n" +
            "    \"high\": \"3254.212\",\n" +
            "    \"low\": \"3145.806\",\n" +
            "    \"open\": \"3167.7015\",\n" +
            "    \"close\": \"n/a\",\n" +
            "    \"timestamp\": \"1593568232263\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"currency\": \"CHN.ECOMM\",\n" +
            "    \"rate\": \"4102.193\",\n" +
            "    \"bid\": \"4100.442\",\n" +
            "    \"ask\": \"4103.944\",\n" +
            "    \"high\": \"4200.157\",\n" +
            "    \"low\": \"4075.406\",\n" +
            "    \"open\": \"4113.582\",\n" +
            "    \"close\": \"n/a\",\n" +
            "    \"timestamp\": \"1593568232268\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"currency\": \"USEquities\",\n" +
            "    \"rate\": \"2494.948\",\n" +
            "    \"bid\": \"2493.948\",\n" +
            "    \"ask\": \"2495.948\",\n" +
            "    \"high\": \"2500.543\",\n" +
            "    \"low\": \"2489.859\",\n" +
            "    \"open\": \"2466.609\",\n" +
            "    \"close\": \"n/a\",\n" +
            "    \"timestamp\": \"1593568232272\"\n" +
            "  }\n" +
            "]"

}