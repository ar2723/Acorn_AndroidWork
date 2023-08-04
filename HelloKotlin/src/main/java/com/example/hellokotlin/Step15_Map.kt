package com.example.hellokotlin

fun main(){
    // 수정 불가능한 Map       mapof( key to value, key to value, ... )
    // java = HashMap 이지만 Kotlin 에선 Map
    val mem:Map<String, Any> =
        mapOf<String, Any>("num" to 1, "name" to "김구라", "isMan" to true)
    // Map에 저장된 데이터 참조하는 방법1
    val num:Any? = mem.get("num")
    val name:Any? = mem.get("name")
    val addr:Any? = mem.get("isMan")

    // Map에 저장된 데이터 참조하는 방법2
    val num2:Int = mem["num"] as Int // Int type으로 casting 하기
    val name2:String = mem["name"] as String // String type으로 casting 하기
    val addr2:Boolean = mem["isMan"] as Boolean // Boolean type으로 casting
}