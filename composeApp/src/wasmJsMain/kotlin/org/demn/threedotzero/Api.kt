package org.demn.threedotzero

import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.delay

interface Api {
	suspend fun receiveData(b: Int, m: Int, type: String): String
}

fun createApi(): Api {
	val client = HttpClient(Js)

	return object: Api {
		override suspend fun receiveData(b: Int, m: Int, type: String): String {
			println("eeh")

			val result = client.get("https://jsonplaceholder.typicode.com/posts")
//
			println(result.bodyAsText())

			delay(4000)

			return "https://mini.ya.ru/"
		}
	}
}