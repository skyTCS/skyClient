package com.yhtos.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/*
 * 函数名：DisplayInfoActivity
 * 功能：显示所有信息
 * */
public class DisplayInfoActivity extends AppCompatActivity {

    EditText editText_name,editText_pwd,editText_native_,editText_number,editText_major;

    Button button_return;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_info);

        final Intent intent = this.getIntent();
        String student_all = intent.getStringExtra("studentAll");
        System.out.println("student_all=======>"+student_all);

        FindKongjian();

        Gson gson = new Gson();
        final List<StudentAll> list = gson.fromJson(student_all,new TypeToken<List<StudentAll>>(){}.getType());
        for(StudentAll stu : list){
            editText_number.setText(list.get(0).getStu_id());
            editText_pwd.setText(list.get(0).getStu_pwd());
            editText_name.setText(list.get(0).getName());
            editText_native_.setText(list.get(0).getNative_());
            editText_major.setText(list.get(0).getMajor());
            System.out.println(stu);
        }
        button_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("stu_id",list.get(0).getStu_id());
                intent.putExtras(bundle);
                intent.setClass(DisplayInfoActivity.this, StudymanagementActivity.class);
                startActivity(intent);
            }
        });
    }
    private void FindKongjian() {
        editText_number = findViewById(R.id.ed_number);
        editText_pwd = findViewById(R.id.ed_pwd);
        editText_name = findViewById(R.id.ed_name);
        editText_native_ = findViewById(R.id.ed_native_);
        editText_major = findViewById(R.id.ed_major);
        button_return = findViewById(R.id.bt_return);
    }

}
