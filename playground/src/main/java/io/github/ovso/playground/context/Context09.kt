@file:Suppress("SpellCheckingInspection")

package io.github.ovso.playground.context

import kotlinx.coroutines.*

object Context09 {
    /*
    코루틴 컨텍스트를 + 연산자로 결합하여 사용할 수 있습니다.
    이를 `컨텍스트 요소 결합` 이라고 합니다.
     */
    @JvmStatic
    fun main(args: Array<String>) = runBlocking<Unit> {
        launch(Dispatchers.Default + CoroutineName("test")) {
            println("I'm working in thread ${Thread.currentThread().name}")
        }
    }
}