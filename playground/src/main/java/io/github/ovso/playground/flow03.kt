@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch")

// 패키지를 다르게 하여 다른 클래스와의 컴파일 에러를 막습니다.
package io.github.ovso.playground.flow03

import kotlinx.coroutines.*

suspend fun simple(): List<Int> {
    delay(1000)
    return listOf(1, 2, 3)
}

object Flow03 {

    /*
    suspend 함수를 사용하여 기본스레드를 차단하지 않고 결과 목록을 반환합니다.
     */
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        simple().forEach { value -> println(value) }
    }
}