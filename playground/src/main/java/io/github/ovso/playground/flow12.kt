@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch")

// 패키지를 다르게 하여 다른 클래스와의 충돌(컴파일 에러)을 막습니다.
package io.github.ovso.playground.flow12

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    /*
        Flow 는 순차적 입니다.
        컬렉션은 터미널 연산자를 호출하는 코루틴에서 작동합니다.
        각각의 내보낸 값은 업스트림에서 다운스트림까지 모든 중간 연산자에 의해 처리된 후 터미널 연산자로 전달됩니다.
        collect, reduce 등이 터미널 연산자 입니다.
     */

    (1..5).asFlow()
        .filter {
            println("Filter $it")
            it % 2 == 0
        }
        .map {
            println("Map $it")
            "string $it"
        }
        .collect {
            println("Collect $it")
        }
    // Filter 1
    // Filter 2
    // Map 2
    // Collect string 2
    // Filter 3
    // Filter 4
    // Map 4
    // Collect string 4
    // Filter 5
}
