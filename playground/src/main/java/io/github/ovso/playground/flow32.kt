@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch", "OPT_IN_USAGE")

// 패키지를 다르게 하여 다른 클래스와의 충돌(컴파일 에러)을 막습니다.
package io.github.ovso.playground.flow32

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun simple(): Flow<Int> = (1..3).asFlow()

// 선언적 접근 방식의 경우 Flow가 완전히 Collect(수집) 되었을 때 호출되는 onCompletion 중간 연산자가 있습니다.
fun main() = runBlocking {
    simple()
        .onCompletion { println("Done") }
        .collect { value -> println(value) }}

/*
1
2
3
Done
 */