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

public class login extends AppCompatActivity {
    EditText editText3, editText4;
    SQLiteDatabase database;
    String id, pwd, tableName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String databaseName = "membership.db";
        database = openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
        tableName = "member";
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
    }

    public void onButton1Clicked(View v) {
        id = editText3.getText().toString();
        pwd = editText4.getText().toString();

        if (id.length() == 0) {
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
        } else if (id.length() < 6) {
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
        } else if (pwd.length() == 0) {
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
        } else if (pwd.length() < 4) {
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
        } else {
            Cursor cursor = database.rawQuery("SELECT id, pwd FROM " + tableName, null);

            int count = cursor.getCount();
            int idtrue = 0;

            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                String id = cursor.getString(0);
                String pwd = cursor.getString(1);

                if (editText3.getText().toString().equals(id) && editText4.getText().toString().equals(pwd)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);

                    MyApplication myApp = (MyApplication) getApplication();
                    myApp.setGlobalString(editText3.getText().toString()); // 전역변수로 로그인 아이디 설정

                    builder.setTitle("안내");
                    builder.setMessage("로그인에 성공했습니다.");
                    builder.setIcon(android.R.drawable.ic_dialog_alert);

                    builder.setNegativeButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    idtrue = 1;
                }
                if (idtrue == 0 && i == count - 1) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);

                    builder.setTitle("안내");
                    builder.setMessage("아이디, 비밀번호를 확인해주세요.");
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
            cursor.close();
        }
    }
}
