package com.example.step08gameview

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class GameActivity : AppCompatActivity() {
    //사운드 매니저 객체
    lateinit var sManager:SoundManager
    //클래스명에 . 찍어서
    companion object{
        val SOUND_LASER:Int = 0
        val SOUND_SHOOT:Int = 1
        val SOUND_DIE:Int = 2
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // xml 문서를 전개해서 화면 구성하기
        setContentView(R.layout.activity_game)
        // GameView 객체를 생성해서 참조값을 변수에 담기
        val gameView = findViewById<GameView>(R.id.gameView)
        // SoundManager 객체를 생성해서 필드에 저장하기
        sManager = SoundManager(this)
        // GameView의 필드에 SoundManager 객체 넣어주기
        gameView.sManager = sManager
        // GameOver 버튼의 참조값을 얻어와서
        var overBtn = findViewById<Button>(R.id.gameOverBtn)
        overBtn.isVisible = false
        // GameView에 버튼의 참조값을 넣어주고
        gameView.overBtn = overBtn
        // 버튼에 리스너 등록
        overBtn.setOnClickListener{
            // 버튼을 누르면 액티비티가 종료되도록 하기
            finish() // 액티비티의 finish() 메소드를 호출하면 액티비티가 종료된다.
        }
        //Intent 객체에 담겨진 boolean type Extra 얻어내기
        val isMute = intent.getBooleanExtra("isMute", false)
        //SoundManager 객체에 isMute 값을 넣어준다.
        sManager.isMute = isMute
    }

    override fun onStart() {
        super.onStart()
        //효과음 미리 로딩
        with(sManager){
            addSound(SOUND_LASER, R.raw.laser1)
            addSound(SOUND_SHOOT, R.raw.shoot1)
            addSound(SOUND_DIE, R.raw.birddie)
        }
    }
}