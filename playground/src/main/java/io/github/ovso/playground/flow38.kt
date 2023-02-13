@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch", "OPT_IN_USAGE")

// 패키지를 다르게 하여 다른 클래스와의 충돌(컴파일 에러)을 막습니다.
package io.github.ovso.playground.flow38

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

// 플로우 성능상의 이슈로 함수가 반환되지 전까지 취소되지 않습니다.
//
fun main() = runBlocking {
    (1..5).asFlow().collect { value ->
        if (value == 3) cancel()
        println(value)
    }
}

/*
1
2
3
4
5
Exception in thread "main" kotlinx.coroutines.JobCancellationException: BlockingCoroutine was cancelled
 */