package com.example.hellokotlin

// kotlin에서 인터페이스 만들기
interface Remocon {
    fun up()
    fun down()
}
// 인터페이스를 구현한 클래스 만들기
class MyRemocon : Remocon {
    override fun up() {
        println("무언가를 올려요!")
    }

    override fun down() {
        println("무언가를 내려요!")
    }
}

fun main(){
    val r1 = MyRemocon()
    r1.up()
    r1.down()
    /*
        in java
            Remocon r = new Remocon(){
                @Override
                public void up(){}
                @Override
                public void down(){}
            };

     */
    // in kotlin
    // object 예약어 - 클래스를 정의함과 동시에 객체를 생성하는 키워드이다.
    // 자바에서 익명 내부 클래스를 사용해서 상속 클래스 객체 생성 후 바로 변수 값에 담는 것과 비슷하다.
    val r2:Remocon = object : Remocon {
        override fun up() {
            println("채널을 올려요")
        }
        override fun down() {
            println("채널을 내려요")
        }
    }
    r2.up()
    r2.down()
}