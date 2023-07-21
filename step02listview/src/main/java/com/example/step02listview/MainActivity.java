package com.example.step02listview;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    //필드 선언
    List<String> names;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // res/Layout/activity_main.xml 문서를 전개 (layout에 선언되어 있는 객체를 생성)
        setContentView(R.layout.activity_main);

        //ListView의 참조값
        ListView listView = findViewById(R.id.listView);

        // ListView의 출력할 sampleData
        names = new ArrayList<>();
        names.add("김구라");
        names.add("해골");
        names.add("원숭이");
        for(int i=0;i<100;i++){
            names.add("아무개"+i);
        }
        //ListView에 연결할 어댑터 객체
        // new ArrayAdapter<>( Context, Layout resource, 모델)
        adapter = new ArrayAdapter<>(
                this,
                // android.R.layout을 통해 미리 만들어져있는 리스트 객체에 textView가 자동으로 담기게 된다.
                android.R.layout.simple_list_item_1,
                names
        );
        // 어댑터를 ListView에 연결하기
        listView.setAdapter(adapter);

        // 객체에 이벤트 리스너를 등록하는 방식
        // (이벤트 리스너 인터페이스 구현, 오버라이드 함수 작성, 인터페이스 참조값 전달)
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

        //버튼에 리스너 등록
        Button button = findViewById(R.id.addBtn);
        button.setOnClickListener(view -> {
            //1. EditText에 입력한 문자열을 읽어와서
            EditText editText = findViewById(R.id.inputName);
            String input = editText.getText().toString();
            //2. names (모델)에 추가하고
            names.add(input);
            //3. 어댑터 names(모델)이 변경되었다고 알린다.
            adapter.notifyDataSetChanged();
            //4. 마지막 위치까지 부드럽게 스크롤하기
            int position = adapter.getCount(); // 전체 아이템의 갯수
            listView.smoothScrollToPosition(position);
            Toast.makeText(this, input+"이(가) 목록에 추가되었습니다.", Toast.LENGTH_SHORT).show();
        });
    }
    // ListView의 단일 cell을 클릭하면 호출되는 메소드
    // (여기서 인자로 전달되는 i 값은 배열에서 item의 인덱스값이다)
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // i 는 클릭한 Item의 인덱스 값
        Log.d("MainActivity", "i:"+i);
        // 클릭한 Item에 출력된 이름
        // 기본 java 문법에서와 같이 names[i] 처럼 바로 배열의 인덱스값을 불러오는 것은 안되고
        // 배열 객체에 내장된 .get() 메소드를 활용해야 한다.
        String clickedName = names.get(i);
        // 토스트(가벼운 메세지) 출력
        Toast.makeText(this, clickedName, Toast.LENGTH_SHORT).show();
    }

    // ListView의 단일 cell을 오래 클릭하면 호출되는 메소드
    // (여기서 인자로 전달되는 long 값은 item에 primary key를 지정했을 경우에 같이 전달되는 값이다.)
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        /*
        // 알림창에 있는 버튼을 눌렀을 때 호출되는 메소드를 가지고 있는 리스너 객체
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int result) {
                if(result == DialogInterface.BUTTON_POSITIVE){ // 긍정버튼을 눌렀을 때
                    // i번째 인덱스의 아이템을 제거
                    // 1. 모델에서 제거하고
                    names.remove(i);
                    // 2. 모델이 변경되었다고 어댑터에 알리면 listView가 업데이트 된다.
                    adapter.notifyDataSetChanged();
                }
            }
        };
        new AlertDialog.Builder(this)
                .setTitle("알림")
                .setMessage("삭제 할까요?")
                .setPositiveButton("네", listener)
                .setNegativeButton("아니요", listener)
                .create()
                .show();

         */
        // 위의 DialogInterface.OnClickListener 객체의 참조값을 전달하는 것 말고, 람다식을 활용해서
        // 아래와 같이 간단하게 작성하는 것도 가능하다.
        new AlertDialog.Builder(this)
                .setTitle("알림")
                .setMessage("삭제 할까요?")
                .setPositiveButton("네", (a, b)->{
                    // 1. 모델에서 제거하고
                    names.remove(i);
                    // 2. 모델이 변경되었다고 어댑터에 알리면 listView가 업데이트 된다.
                    adapter.notifyDataSetChanged();
                })
                .setNegativeButton("아니요", null)
                .create()
                .show();
        return false;
    }
}