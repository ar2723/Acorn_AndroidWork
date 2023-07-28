package com.example.hellokotlin

//클래스 정의하기
class MyCar

class YourCar {
    //멤버 함수
    fun drive(){
        println("달려요!")
    }
}
// 생성자
class ship constructor(){
    init {
        //객체를 생성하는 시점에 초기화 하고 싶은 작업이 있으면 여기서 한다.
        println("Ship 클래스의 init")
    }
}
// constructor 예약어 생략 가능
class ship2 (){
    init {
        //객체를 생성하는 시점에 초기화 하고 싶은 작업이 있으면 여기서 한다.
        println("Ship2 클래스의 init")
    }
}

// 생성자의 인자로 전달 받을게 없으면 ()도 생략 가능
class ship3 {
    init {
        //객체를 생성하는 시점에 초기화 하고 싶은 작업이 있으면 여기서 한다.
        println("Ship3 클래스의 init")
    }
}


fun main(){
    //MyCar 클래스로 객체를 생성해서 참조값을 c1이라는 변수에 담기
    var c1:MyCar = MyCar()
    //YourCar 클래스로 객체를 생성해서 참조값을 c2이라는 변수에 담기
    var c2:YourCar = YourCar()
    c2.drive()
    ship()
}