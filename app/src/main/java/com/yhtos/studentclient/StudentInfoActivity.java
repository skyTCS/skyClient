package com.yhtos.studentclient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpParams;

/*
 * 函数名：StudentInfoActivity
 * 功能：提交个人信息
 * 创建者：巴莹、高雨鑫
 * 创建时间：2020.07.13
 * */
public class StudentInfoActivity extends AppCompatActivity {

    EditText editText_name,editText_native_,editText_number,editText_major;

    Button button_submit;

    static String stu_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        final Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        stu_id = bundle.getString("stu_id");

        FindKongjian();
        this.editText_number.setText(stu_id);



        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editText_name.getText().toString().trim();
                String native_ = editText_native_.getText().toString().trim();
                String number = editText_number.getText().toString().trim();
                String major = editText_major.getText().toString().trim();

                StudentBean studentBean = new StudentBean();
                studentBean.setName(name);
                studentBean.setNative_(native_);
                studentBean.setStu_id(number);
                studentBean.setMajor(major);

                //对象转json
                Gson gson = new Gson();
                String stu_info = gson.toJson(studentBean);

                //发送服务器请求（http）导入kjframe包
                KJHttp http_submit = new KJHttp();
                HttpParams httpParams_submit = new HttpParams();
                httpParams_submit.setContentType("application/json");
                //加入参数
                httpParams_submit.putJsonParams(stu_info);
                Log.e("---->",stu_info);
                //发送post请求             提交到服务器哪                      把json串提交给服务器     是否要缓存
                http_submit.jsonPost("http://212.64.70.83:8081/student/servlet/ServletSaveInfo", httpParams_submit, false, new HttpCallBack() {
                    @Override
                    public void onSuccess(String t) {
                        super.onSuccess(t);
                        Log.e("接收数据=====>", t);
                        if (t.equals("ok")){
                            Toast.makeText(StudentInfoActivity.this,"提交成功！",Toast.LENGTH_SHORT).show();
                            //带参数传值
                            Intent intent = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putString("stu_id",stu_id);
                            intent.putExtras(bundle);
                            intent.setClass(StudentInfoActivity.this, StudymanagementActivity.class);
                            startActivity(intent);
                        }
                    }
                    @Override
                    public void onFailure(int errorNo, String strMsg) {
                        super.onFailure(errorNo, strMsg);
                        Log.e("失败=====>",strMsg);
                        //不成功
                        Toast.makeText(StudentInfoActivity.this,"链接失败:"+strMsg,Toast.LENGTH_SHORT).show();
                    }
                });//1提交给谁4回调函数

            }
        });

    }
    private void FindKongjian() {
        editText_name = findViewById(R.id.ed_name);
        editText_native_ = findViewById(R.id.ed_native_);
        editText_number = findViewById(R.id.ed_number);
        editText_major = findViewById(R.id.ed_major);
        button_submit = findViewById(R.id.bt_submit);
    }
}
