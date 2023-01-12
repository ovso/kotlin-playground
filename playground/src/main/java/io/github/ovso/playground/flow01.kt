@file:Suppress("SpellCheckingInspection")

package io.github.ovso.playground

import kotlinx.coroutines.*

fun simple(): List<Int> = listOf(1, 2, 3)

object Flow01 {

    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        simple().forEach { value -> println(value) }
    }
}