package com.example.step08gameview

data class Enemy (
    var x:Int = 0, // x 좌표
    var y:Int = 0, // y 좌표
    var type:Int = 0, // 적기의 type 0은 siliver, 1은 gold
    var isDead:Boolean = false, // 화면에서 제거할지 여부
    var energy:Int = 0, // 에너지
    var imageIndex:Int = 0, // 적기의 이미지 인덱스(애니메이션 효과를 위해)
    var isFall:Boolean = false, //추락중인지 여부
    var angle:Int = 0, // 현재 회전 각도
    var size:Int = 0 // 현재 크기
)