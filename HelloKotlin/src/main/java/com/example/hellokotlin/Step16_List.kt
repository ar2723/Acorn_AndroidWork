package com.example.hellokotlin
/*
    어떤 데이터를 index로 관리를 하고 ReadOnly로 사용하고 싶다면
    List를 사용하면 된다.
 */
fun main(){
    val names:List<String> = listOf<String>("kim", "lee", "park")
    // value의 generic이 추론 가능하기 때문에 생략 가능
    // names2의 type이 추론 가능하기 때문에 생략 가능
    //            type (       value        )
    val names2 = listOf("kim", "lee", "park")

    val nums:List<Int> = listOf(10, 20, 30)

    val info:List<Any> = listOf(10, "kim", true)

    // names list의 0번 index 참조하는 방법1
    val a = names.get(0)
    // names list의 0번 index 참조하는 방법2
    val b = names[0]
    // names의 item 갯수 참조
    val c = names.size
    // names에 저장된 item을 순서대로 순회하면서 참조
    names.forEach (fun(item){
        println(item)
    })
    println("------")
    //위의 forEach() 함수를 줄인 표현으로 사용하면 아래와 같다.
    names.forEach {
        println(it)
    }
    //List의 인덱스들만 모아놓은 IntRange type 얻어내기
    val result:IntRange = names.indices
    //for 문에서 index를 하나씩 참조해보기
    for (index in result){
        println(index)
    }
    println("------")
    for (index in names.indices){
        println("$index : ${names[index]}")
    }

    println("List의 item을 역순으로 참조하고 싶다면")
    for (index in 2 downTo 0){
        println(names[index])
    }
    println("위의 코드를 일반화 시키면")
    for(index in names.lastIndex downTo 0){
        println(names[index])
    }
}