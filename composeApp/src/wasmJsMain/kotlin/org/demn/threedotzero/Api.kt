package org.demn.threedotzero

import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

interface Api {
	suspend fun receiveData(b: Int, m: Int): String
}

fun createApi(serverUrl: String): Api {
	val client = HttpClient(Js)

	return object: Api {
		override suspend fun receiveData(b: Int, m: Int): String {
			println("eeh")

			val result = client.get("$serverUrl/distribute-containers?m=$m&b=$b")

			println(result.bodyAsText())

			return "-1"
		}
	}
}