package com.example.step01example;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 전송 버튼의 참조값 얻어오기
        Button sendBtn = findViewById(R.id.sendBtn);
        // 버튼에 리스너 등록하기
        sendBtn.setOnClickListener(this);
        // 다른 형식으로 구현한 전송2 버튼의 동작 (람다식, 추상메소드 오버라이드를 내부 클래스로ㄴ 작성)
        Button sendBtn2 = findViewById(R.id.sendBtn2);
        sendBtn2.setOnClickListener(view -> {
            // 1. EditText에 입력한 문자열을 읽어와서
            // 여기서 findViewById(R.id.editText)를 EditText 타입으로 선언된 변수 값에 담지 않고
            // 바로 getText() 메소드를 호출하는 것은 불가능하다.
            EditText editText = findViewById(R.id.editText);
            // editText.getText() 메소드는 String이 아닌 Editable type을 반환하기 때문에 String type으로
            // 바꿔주어야 한다.
            String msg = editText.getText().toString();
            //2. TextView에 출력하기
            TextView textView = findViewById(R.id.textView);
            textView.setText(msg);
        });
    }

    @Override
    public void onClick(View view) {
        //1. EditText에 입력한 문자열을 읽어와서
        EditText editText = findViewById(R.id.editText);
        String msg = editText.getText().toString();
        //2. TextView에 출력하기
        TextView textView = findViewById(R.id.textView);
        textView.setText(msg);
    }
}