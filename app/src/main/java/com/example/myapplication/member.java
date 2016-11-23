package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class member extends AppCompatActivity {
    EditText editText, editText2, editText3;
    TextView textView;
    SQLiteDatabase database;
    String id, pwd, pwd2, tableName, tableName2, tableName3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        textView = (TextView) findViewById(R.id.textView);

        String databaseName = "membership.db";
        database = openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);

        tableName = "member";
                database.execSQL("CREATE TABLE if not exists " + tableName + "("
                + "_id integer PRIMARY KEY autoincrement, "
                + "id text, "
                + "pwd text"
                + ")");
        /*Cursor cursor = database.rawQuery("SELECT id, pwd FROM " + tableName, null);

        int count = cursor.getCount();
        println("결과 레코드의 갯수 : " + count);

        for(int i = 0; i < count; i++) {
            cursor.moveToNext();
            String id = cursor.getString(0);
            String pwd = cursor.getString(1);

            println("레코드 #" + i + " :" + id + "," + pwd);
            if(editText.getText().toString().equals(id))
            {
                println("아이디가 중복");
            }
        }

        cursor.close();
        println("데이터 조회했습니다.");*/
    }

    public void onButton1Clicked(View v) {
        id = editText.getText().toString();
        pwd = editText2.getText().toString();
        pwd2 = editText3.getText().toString();
        if (id.length() == 0)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("안내");
            builder.setMessage("아이디를 입력해야됩니다.");
            builder.setIcon(android.R.drawable.ic_dialog_alert);

            builder.setNegativeButton("예", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else if(id.length() < 6) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("안내");
            builder.setMessage("아이디가 너무 짧습니다.");
            builder.setIcon(android.R.drawable.ic_dialog_alert);

            builder.setNegativeButton("예", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else if (pwd.length() == 0)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("안내");
            builder.setMessage("비밀번호를 입력해야됩니다.");
            builder.setIcon(android.R.drawable.ic_dialog_alert);

            builder.setNegativeButton("예", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else if(pwd.length() < 4)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("안내");
            builder.setMessage("비밀번호가 너무 짧습니다.");
            builder.setIcon(android.R.drawable.ic_dialog_alert);

            builder.setNegativeButton("예", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else
        {
            Cursor cursor = database.rawQuery("SELECT id, pwd FROM " + tableName, null);

            int count = cursor.getCount();
            int idhave = 0;

            for(int i = 0; i < count; i++) {
                cursor.moveToNext();
                String id = cursor.getString(0);
                String pwd = cursor.getString(1);

                if (editText.getText().toString().equals(id)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);

                    builder.setTitle("안내");
                    builder.setMessage("이미 존재하는 아이디가 있습니다.");
                    builder.setIcon(android.R.drawable.ic_dialog_alert);

                    builder.setNegativeButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                    idhave = 1;
                }
            }
            if(idhave == 0) {
                if(pwd.equals(pwd2)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);

                    database.execSQL("INSERT INTO " + "member" + "(id, pwd) VALUES"
                            + "(" + "'" + id + "'" + "," + "'" + pwd + "'" + ")");
                    tableName2 = editText.getText().toString();
                    database.execSQL("CREATE TABLE if not exists " + tableName2 + "("
                            + "_id integer PRIMARY KEY autoincrement, "
                            + "id text, "
                            + "scr integer"
                            + ")");
                    database.execSQL("INSERT INTO " + tableName2 + "(id, scr) VALUES"
                            + "(" + "'" + tableName2 + "'" + "," + 0 + ")");
                    tableName3 = "rank1";
                    database.execSQL("CREATE TABLE if not exists " + tableName3 + "("
                            + "_id integer PRIMARY KEY autoincrement, "
                            + "id text, "
                            + "scr integer"
                            + ")");
                    database.execSQL("INSERT INTO " + tableName3 + "(id, scr) VALUES"
                            + "(" + "'" + "asdasdasd" + "'" + "," + 10 + ")");
                    database.execSQL("INSERT INTO " + tableName3 + "(id, scr) VALUES"
                            + "(" + "'" + "zxczxczxc" + "'" + "," + 8 + ")");
                    database.execSQL("INSERT INTO " + tableName3 + "(id, scr) VALUES"
                            + "(" + "'" + "qweqweqwe" + "'" + "," + 5 + ")");
                    database.execSQL("INSERT INTO " + tableName3 + "(id, scr) VALUES"
                            + "(" + "'" + "iopiopiop" + "'" + "," + 3 + ")");
                    database.execSQL("INSERT INTO " + tableName3 + "(id, scr) VALUES"
                            + "(" + "'" + "jkljkljkl" + "'" + "," + 0 + ")");

                    builder.setTitle("안내");
                    builder.setMessage("회원가입이 완료됬습니다.");
                    builder.setIcon(android.R.drawable.ic_dialog_alert);

                    builder.setNegativeButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);

                    builder.setTitle("안내");
                    builder.setMessage("비밀번호가 일치하지 않습니다.");
                    builder.setIcon(android.R.drawable.ic_dialog_alert);

                    builder.setNegativeButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        }
    }
    private void println(String data) {
        textView.append(data + "\n");
    }
}
