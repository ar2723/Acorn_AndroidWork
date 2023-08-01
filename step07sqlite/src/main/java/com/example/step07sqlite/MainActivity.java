package com.example.step07sqlite;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.step07sqlite.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    DBhelper dbhelper;
    List<Todo> list;
    TodoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //view binding 설정
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        /*
            DBHelper 객체를 생성해서 참조값을 필드에 저장하기
            name은 DB의 이름을 마음대로 정해서 전달하면 된다.
         */
        dbhelper = new DBhelper(this, "MyDB.sqlite",
                                null, 1);

        TodoDao todoDao = new TodoDao(dbhelper);
        //할일 목록을 담을 list
        list = todoDao.getList();
        //listView에 연결할 어댑터 객체 생성
        adapter = new TodoAdapter(this, R.layout.listview_cell, list);
        //어댑터를 ListView에 연결
        binding.listView.setAdapter(adapter);

        //할일 추가 버튼에 리스너 연결
        binding.addBtn.setOnClickListener(view->{
            //1. EditText에 입력한 문자열을 읽어와서
            String content = binding.inputText.getText().toString();
            //2. Todo에 담고
            Todo todo = new Todo();
            todo.setContent(content);
            //3. TodoDao를 이용해서 DB에 저장하고
            todoDao.insert(todo);
            //4. 할일 목록을 새로 읽어와서
            list = todoDao.getList();
            //5. 어댑터에 넣어주고 (덮어쓰기)
            adapter.list = list;
            //6. 모델의 내용이 바뀌었다고 어댑터에 알려서 listView가 업데이트 되도록 한다.
            adapter.notifyDataSetChanged();
            //7. listView의 가장 아래쪽이 화면에 보일 수 있도록 부드럽게 스크롤 시키기
            binding.listView.smoothScrollToPosition(adapter.getCount());
        });
        //listView의 cell을 오랫동안 클릭하고 있으면 호출되는 메소드
        binding.listView.setOnItemLongClickListener((adapterView, view,i,id)->{
            new AlertDialog.Builder(this)
                    .setTitle("알림")
                    .setMessage("삭제 하시겠습니까?")
                    .setPositiveButton("네", (dialogInterface, i1) -> {
                        //클릭한 셀의 id를 이용해서 삭제
                        todoDao.delete((int)id);
                        //목록을 새로 얻어와서 어댑터에 넣어주고
                        adapter.list = todoDao.getList();
                        //모델의 내용이 바뀌었다고 어댑터에 알려서 listView가
                        adapter.notifyDataSetChanged();
                    })
                    .setNegativeButton("아니요", null)
                    .create()
                    .show();
            return false;
        });
    }
}