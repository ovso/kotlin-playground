@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch")

// 패키지를 다르게 하여 다른 클래스와의 컴파일 에러를 막습니다.
package io.github.ovso.playground.flow11

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

object Flow11 {
    /*
        map, reduce 연산자의 조합힙니다.
        map 에 2 가 들어올 때부터 reduce 가 실행됩니다. reduce 는 축적된 값과 새로 들어온 값을 실행하기 떄문입니다.
     */
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val sum = (1..5).asFlow()
            .map {
                it * it
            }
            .reduce { accumulator, value ->
                accumulator + value
            }
        println(sum)
    }
}