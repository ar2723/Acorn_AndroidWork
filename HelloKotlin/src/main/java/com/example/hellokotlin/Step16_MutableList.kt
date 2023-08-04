package com.example.hellokotlin

/*
    순서가 중요한 데이터를 사용하고, 중간에 수정도 가능하게 하려면
    MutableList를 사용하면 된다.
 */

fun main(){
    val names:MutableList<String> = mutableListOf<String>("김구라", "해골")
    // type 추론이 가능하기 때문에 type을 생략하면 아래와 같다.
    val foods = mutableListOf("라면", "김밥")
    // mutable 이기 때문에 List에 아이템 추가 가능
    foods.add("쫄면")
    foods.add("떡볶이")
    foods.add("오뎅")
    // List의 아이템 수정도 가능
    foods.set(0, "신라면")
    foods[1] = "참치김밥"
    // List의 아이템 삭제도 가능
    foods.removeAt(2) // 2번 인덱스의 아이템 삭제
    foods.removeLast() // 마지막 인덱스의 아이템 삭제
    foods.forEach {
        println(it)
    }
}