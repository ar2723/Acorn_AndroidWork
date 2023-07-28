package com.example.step07sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    //생성자
    public DBhelper(@Nullable Context context, @Nullable String name,
                    @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //App에서 DB를 처름 사용할 때 호출되는 메소드
    @Override
    public void onCreate(SQLiteDatabase db) {
        //사용할 테이블을 만들면 된다.
        String sql = "CREATE TABLE todo " +
                     "(num INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     "content TEXT, regdate TEXT)";
        //SQLiteDataBase 객체를 이용해서 실행한다.
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //업그레이드할 내용을 작성하면 된다.
        db.execSQL("DROP TABLE IF EXISTS todo");
        //다시 만들어질 수 있도록 onCreate() 메소드를 호출한ㅇ다.
        onCreate(db);
    }
}
