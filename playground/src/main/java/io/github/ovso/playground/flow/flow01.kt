@file:Suppress("SpellCheckingInspection")

package io.github.ovso.playground.flow

import kotlinx.coroutines.*

fun simple(): List<Int> = listOf(1, 2, 3)

object Flow01 {

    /*
     컬렉션의 값을 인쇄하는 코틀린 함수 입니다.
     */
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        simple().forEach { value -> println(value) }
    }
}