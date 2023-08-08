package com.example.step08gameview
/*
    미사일의 x 좌표, y 좌표, 화면에서 제거할 지 여부
*/
data class Missile (var x:Int, var y:Int, var isDead:Boolean=false)