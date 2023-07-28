package com.example.hellokotlin
/*
    Kotlin에서의 배열과 반복문
 */
fun main(){
    // 수정 불가능한 목록(list)
    val names:List<String> = listOf("kim", "lee", "park")
    val animals = listOf<String>("dog", "cat", "bird", "elephant")
    val nums = listOf<Int>(10, 20, 30)
    // 배열의 참조
    println(names[0])
    println(names[1])
    println(names[2])
    println("-----------")
    // 수정 불가
    // names[0] = "김구라"

    // indices는 index의 복수형 ( names 배열의 index로 이루어진 배열 )
    val result = names.indices

    for (i in names.indices){
        println(i)
    }
    println("-----------")
    for(i in names.indices){
        println(names[i])
    }
    println("-----------")
    for(i in names.indices){
        println("$i : ${names[i]}")
    }
    println("-----------")
    for(item in names){
        println(item)
    }
    println("-----------")
    names.forEach{
        println(it)
    }
}