@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch")

// 패키지를 다르게 하여 다른 클래스와의 컴파일 에러를 막습니다.
package io.github.ovso.playground.flow07

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.runBlocking

object Flow07 {

    /*
     정수의 범위를 flow 로 변환하여 출력합니다.
     */
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        (1..3).asFlow().collect { value -> println(value) }
    }
}