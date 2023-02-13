@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch")

// 패키지를 다르게 하여 다른 클래스와의 컴파일 에러를 막습니다.
package io.github.ovso.playground.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking


suspend fun performRequest(request: Int): String {
    delay(1000) // 장기 실행하는 비동기 작업
    return "response $request"
}

object Flow09 {

    /*
    transform 은 변환 연산자 입니다. 포괄적인 의미로 중간흐름연산자 라고도 합니다.
    map 이나 filter 를 모방하지만 더 복잡한 연산을 수행할 수 있습니다.
     */
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        (1..3).asFlow() // a flow of requests
            .transform { request ->
                emit("Making request $request")
                emit(performRequest(request))
            }
            .collect { response -> println(response) }
    }
}