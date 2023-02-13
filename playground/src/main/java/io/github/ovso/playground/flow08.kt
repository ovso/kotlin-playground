@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch")

// 패키지를 다르게 하여 다른 클래스와의 컴파일 에러를 막습니다.
package io.github.ovso.playground.flow08

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking


suspend fun performRequest(request: Int): String {
    delay(1000) // 장기 실행하는 비동기 작업
    return "response $request"
}

object Flow08 {

    /*
    중간 흐름 연산자 입니다.
    flow 에서의 map 연산자는 내부에서 suspend 함수를 호출하고 있습니다. 이는 정기기능(suspend) 을 사용할 수 있다는 것입니다.
    플로우 연산 중간에 비동기 요청을 map 연산자를 통해 결과에 반영할 수 있습니다.
     */
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        (1..3).asFlow() // a flow of requests
            .map { request -> performRequest(request) }
            .collect { response -> println(response) }
    }
}