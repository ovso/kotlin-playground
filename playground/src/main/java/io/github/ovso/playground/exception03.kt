@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch", "OPT_IN_USAGE")

// 패키지를 다르게 하여 다른 클래스와의 충돌(컴파일 에러)을 막습니다.
package io.github.ovso.playground.exception03

import kotlinx.coroutines.*

/*
코루틴은 내부적으로 취소를 위해 CancellationException을 사용합니다.
이러한 예외는 모든 핸들러에서 무시되므로 catch 블록에서얻을 수 있는 추가 디버그 정보의 소스로만 사용해야 합니다.
job.cancel() 을 사용하여 코루틴을 취소하면 종료되지만 부모 루틴은 취소 되지 않습니다.
 */
fun main() = runBlocking {
    val job = launch {
        val child = launch {
            try {
                delay(Long.MAX_VALUE)
            } finally {
                println("Child is cancelled")
            }
        }
        yield()
        println("Cancelling child")
        child.cancel()
        child.join()
        yield()
        println("Parent is not cancelled")
    }
    job.join()
}