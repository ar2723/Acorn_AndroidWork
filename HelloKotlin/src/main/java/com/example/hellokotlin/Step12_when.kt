package com.example.hellokotlin

import java.util.Scanner

fun main(){
    val scan = Scanner(System.`in`)
    println("Gun:1, Sword:2")
    println("무기를 선택하세요(1 or 2):")
    val weapon = scan.nextInt()
    // weapon 안에 들어 있는 숫자를 이용해서 분기하기
    when(weapon){
        1-> println("총으로 공격해요")
        2-> println("칼로 공격해요")
        else -> println("주먹으로 공격해요")
    }

    //when 구문도 리턴값을 가질 수 있다.
    val selectedWeapon = when(weapon){
        1 -> {
            //실행할 구문이 많다면 블럭으로 구성할수도 있다.
            println("총 장전중...")
            "총"
        }
        2 -> "칼"
        else -> "주먹" // 또는 null 값이든 무엇이든 들어가야 한다.
    }
    println("선택한 무기 : $selectedWeapon")
}