package com.example.step01_activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // 카운트를 셀 필드
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // activity_main.xml 문서를 전개해서 화면 구성하기
        setContentView(R.layout.activity_main);
    }

    // 버튼을 클릭했을 때 실행되는 메소드
    public void clicked(View v){ // View type은 모든 layout 요소의 부모 타입이다.
        // 필드의 값을 1 증가 시키기
        count++;
        // 필드의 값을 TextView에 출력하기
        // (res/layout/activity_main.xml 문서를 전개해서 레이아웃을 구성했는데
        // 거기에서 TextView의 참조값(textView라는 이름의 static field 값)을 얻어와야 한다.
        TextView textView = findViewById(R.id.textView);
        textView.setText(Integer.toString(count));
    }

    // 리셋 버튼을 클릭했을 때 실행되는 메소드
    public void resetClicked(View v){
        count = 0;
        /*
            현재 활성화 되어 있는 액티비티가 구성한 화면에서
            textView라는 아이디를 가지고 있는 UI의 참조값을 얻어와서
            textView type의 a라는 지역 변수에 담기
         */
        TextView textView = findViewById(R.id.textView);
        textView.setText(Integer.toString(count));
    }
}