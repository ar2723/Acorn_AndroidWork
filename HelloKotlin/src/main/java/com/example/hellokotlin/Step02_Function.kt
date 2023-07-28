package com.example.hellokotlin

// run 했을 때 실행의 흐름이 시작되는 main 함수 만들기
fun main(){
    println("main 함수가 시작 되었습니다.")

    /*
        in java => public void a(){}
        in kotlin => fun a():Unit() or fun a(){}
        코틀린에서 Unit은 원시 type 이라고 지칭하고 java의 void와 비슷한 역할을 한다.
     */
    // 함수명 (): 리턴 type { }
    fun a():Unit{
        println("a 함수 호출됨!")
    }
    a()

    // 이름이 없는 함수를 만들어서 그 참조값을 변수에 담기
    var b = fun(){}
    /*
        대입연산자의 우측에 있는 함수를 관찰해보면
        리턴 type : 없다(Unit)
        함수에 전달해야 하는 인자 : 없다

        함수도 타입이 여러가지이며 정확히 동일한 타입의 함수가 되려면
        매개변수와 리턴타입이 모두 동일해야 한다.
     */

    var c:()->Unit = fun():Unit{}

    var d:(String)->Unit = fun(str:String):Unit{}
    // 매개변수는 1개 type은 String, 리턴 type은 String
    var e:(String)->String = fun(str:String):String{
        return "hello"
    }
    // 매개변수 2개 type은 Int, Int, 리턴 type도 Int
    var sum:(Int, Int)->Int = fun(a:Int, b:Int):Int{
        return a+b
    }

    var myName:String = "kim"
    var myNum = 10

    println("main 함수가 종료 됩니다.")
}