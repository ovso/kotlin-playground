@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch")

// 패키지를 다르게 하여 다른 클래스와의 충돌(컴파일 에러)을 막습니다.
package io.github.ovso.playground.flow21

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val nums = (1..3).asFlow().onEach { delay(300) } // numbers 1..3 every 300 ms
    val strs = flowOf("one", "two", "three").onEach { delay(400) } // strings every 400 ms
    val startTime = System.currentTimeMillis() // remember the start time
    nums.combine(strs) { a, b -> "$a -> $b" } // compose a single string with "combine"
        .collect { value -> // collect and print
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}
// 1one
// 2one
// 3two
// 3three
// https://kotlinlang.org/docs/flow.html#combine