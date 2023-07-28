package com.example.hellokotlin

val nums = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

fun main(){
    println("main 함수가 시작됩니다.")
    // nums 배열을 이용해서 모든 item에 2를 곱한 item을 가지고 있는
    // 새로운 배열 얻어내기
    val result = nums.map {
        it*2
    }
    // nums 배열을 이용해서 5보타 큰 item만 남겨놓은 새로운 배열 얻어내기
    val result2 = nums.filter {
        it > 5
    }
    // nums 배열을 이용해서 item에 모두 3을 곱한 후 10보다 큰 item만 남겨높은 새로운 배열
    val result3 = nums.map { it*3 }.filter { it>10 }
    println("main 함수가 종료 됩니다.")
}