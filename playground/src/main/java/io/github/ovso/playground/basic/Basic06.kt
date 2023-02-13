package io.github.ovso.playground.basic

import kotlinx.coroutines.*

object Basic06 {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        repeat(100_000) {
            launch {
                delay(5000L)
                print(".")
            }
        }

        // OutOfMemoryError 발생합니다.
        // msg: unable to create native thread: possibly out of memory or process/resource limits reached
        // msg: 네이티브 스레드를 생성할 수 없습니다,. 메모리가 부족하거나 프로세스/리소스 제한데 도달했을 수 있습니다.
        // 에러 메시지 이후 출력은 됩니다.
/*
        for(i in 1..100_000) {
            thread {
                Thread.sleep(5000L)
                print(".")
            }
        }
*/

    }
}