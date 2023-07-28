package com.example.hellokotlin

class Cat constructor(){
    //init 블럭은 primary 생성자의 일부
    init{
        println("야옹이가 생겼어요!")
    }
    // 뒤늦은 초기화가 가능한 필드 Lateinit 예약어를 붙여서 선언하기
    lateinit var name:String
    constructor(name: String) : this() { // this() primary 생성자를 호출하는 표현식이다.
        println("야옹이의 이름은:${name}")
        this.name = name
    }
}

class Dog constructor(){
    init {
        println("멍멍이가 생겼어요!")
    }
    // 필드
    // null 값을 허용하고 싶으면 type 뒤에 ? 를 붙인다.
    var name:String? = null
    constructor(name: String) : this() {
        // null이 가능한 type 공간(name:String?)에
        // null이 불가능한 type 공간(name: String)에 담긴 값을 대입하는 것은 가능하다.
        this.name = name
    }
}

fun main(){
    val c1 = Cat("톰캣")
    val c2 = Cat()
    println(c1.name)
    //lateinit 필드에 값을 넣어주고
    c2.name="키티"
    //필드를 참조해야 한다.
    println(c2.name)

    // null 값이 가능한 data type은 type 뒤에 ? 를 붙여 주어야 한다.
    // String type 과 String? type은 다른 type으로 간주된다.
    var myName:String? = null
    myName = "김구라"
    myName = null

    // java와 다르게 Int type 데이터의 초기화에서 0대신에 null 값을 대입하는 것도 가능하다.
    var myNum:Int? = null
    myNum = 999
    myNum = null

    var d1 = Dog("바둑이")
    var d2 = Dog()
    println(d1.name)
    println(d2.name)
}