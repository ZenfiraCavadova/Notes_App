package com.abbtech.simpleauthenticationapp

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.runBlocking

fun main()= runBlocking {

    makeCar()
        .onStart {
            println("Start")
        }
        .onCompletion {
            println("Finish")
        }
        .collect {
            println("Collected: $it")
        }
    delay(5000)


}

data class Car (val number:String)
suspend fun makeCar(): Flow<Car> = flow {
    repeat(10) {
        delay(1000)
        val car = Car("Car ${it+1}")
        emit(car)
    }
}


