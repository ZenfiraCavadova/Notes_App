package com.abbtech.simpleauthenticationapp

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val list = listOf(1, 2, 3, 4, 5,6,7,8,9,10)
    val channels = Channel<Int>()
    GlobalScope.launch {
        list.forEach {
            channels.send(it)
        }
    }
    GlobalScope.launch {
        channels.consumeEach {
            produceCar(it, "Channel 1")
        }
    }
    GlobalScope.launch {
        channels.consumeEach {
            produceCar(it, "Channel 2")
        }
    }
    GlobalScope.launch {
        channels.consumeEach {
            produceCar(it, "Channel 3")
        }
    }
    delay(10000)
}


suspend fun produceCar(making: Int, channelName: String) {
    println("$making  Started by $channelName")
    println("Car was produced")
    delay(3000)
}

