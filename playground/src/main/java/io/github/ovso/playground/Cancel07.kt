@file:Suppress("SpellCheckingInspection")

package io.github.ovso.playground

import kotlinx.coroutines.*

object Cancel07 {
    /*
    설정한 시간이 지나면 코루틴을 취소합니다.
    TimeCancellationException 을 발생시킵니다.
    withTimeoutOrNull 을 사용할 수도 있습니다. withTimeoutOrNull 은 익셉션이 아닌 null 을 반환합니다.
    withTimeoutOrNull 안에서 TimeCancellationException 예외처리를 하기 때문입니다.
     */
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        withTimeout(1300L) {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        }
    }
}