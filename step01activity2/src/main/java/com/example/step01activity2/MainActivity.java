package com.example.step01activity2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 이동 버튼의 참조값 얻어오기
        Button moveBtn = findViewById(R.id.moveBtn);

        // 버튼을 눌렀을 때 동작하기 위한 리스너 등록

        // 1) 구현된 인터페이스 객체의 참조값(추상 메소드 또한 오버라이드 되어 있음)을 인자로 전달하는 방식
        // moveBtn.setOnClickListener(this);

        /*
            2) 구현한 인터페이스의 추상 메소드가 단 1개이기 때문에 이너 클래스로 다음과 같이 작성하는 방식
            view.OnclickListener = new view.OnclickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(this, SubActivity.class);
                    startActivity(intent);
                }
            };
            moveBtn.setOnClickListener(listener);
        */

        // 3) 2)번의 코드를 람다식으로 더 간소화 하여 작성하는 방식
        moveBtn.setOnClickListener(view->{
            //SubActivity를 활성화 시키겠다는 의도(Intent) 객체 생성하기
            Intent intent = new Intent(this, SubActivity.class);
            // startActivity() 메소드를 호출하면서 Intent 객체를 전달하기
            startActivity(intent);
        });
    }
    /*
        안드로이드 앱 개발에서의 4대 객체
        1. Activity
        2. Service
        3. BroadcastReceiver
        4. Content Provider
    */
    @Override
    public void onClick(View view) {
        //SubActivity를 활성화 시키겠다는 의도(Intent) 객체 생성하기
        Intent intent = new Intent(this, SubActivity.class);
        // startActivity() 메소드를 호출하면서 Intent 객체를 전달하기
        startActivity(intent);
    }

}