package com.example.step07_sqlite2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/*
    1. SQLiteOpenHelper 클래스는 추상 클래스 이기 때문에 추상 메소드를 오버라이드 해야한다.
    2. DBHelper 클래스의 대표 생성자에서 부모 생성자에 전달할 값을 받아야한다.
    3.
 */
class DBHelper (
    context : Context?,
    name : String?,
    factory : SQLiteDatabase.CursorFactory?,
    version : Int
) : SQLiteOpenHelper(context, name, factory, version){

    override fun onCreate(db: SQLiteDatabase?) {
        /*
            //사용할 테이블을 만들면 된다.
            String sql = "CREATE TABLE todo " +
                     "(num INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     "content TEXT, regdate TEXT)";
            //SQLiteDataBase 객체를 이용해서 실행한다.
            db.execSQL(sql);
         */
        val sql = """
            CREATE TABLE todo
            (num INTEGER PRIMARY KEY AUTOINCREMENT, content TEXT, regdate TEXT)
        """.trimIndent()
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS todo")
        onCreate(db)
    }

}