package com.example.hellokotlin
/*
    in java

    class Util{
    public static int number = 10
    public String version = "1.0"
    public static void get(){ }
        public void send(){ }
    }
    Util.number;
    new Util.version;
    Util.get();
    new Util().send();
 */
class Util {
    //Util 클래스와 함께하는 동반 객체, java에서 static의 기능을 비슷하게 구현할 수 있다.
    companion object{
        //동반 객체의 필드와 메소드(함수)를 정의하면 된다.
        val version:String = "1.0"
        fun send(){
            println("전송합니다")
        }
    }
}

fun main(){
    println(Util.version)
    Util.send()
}