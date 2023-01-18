@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch")

// 패키지를 다르게 하여 다른 클래스와의 충돌(컴파일 에러)을 막습니다.
package io.github.ovso.playground.flow20

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

/** 두 flow 를 결합하여 순차적으로 나타냅니다. */
fun main() = runBlocking {
    val nums = (1..3).asFlow() // numbers 1..3
//    val nums = flowOf(1, 2, 3) // numbers 1..3
    val strs = flowOf("one", "two", "three") // strings
    nums.zip(strs) { a, b -> "$a -> $b" } // compose a single string
        .collect { println(it) } // collect and print
}

// https://kotlinlang.org/docs/flow.html#zip