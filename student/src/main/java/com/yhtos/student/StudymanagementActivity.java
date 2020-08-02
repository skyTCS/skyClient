package com.yhtos.student;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpParams;

/*
 * 函数名：StudymanagementActivity
 * 功能：功能选择（信息维护）
 * */

public class StudymanagementActivity extends AppCompatActivity {
    Button button_insert,button_display;
    static String stu_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studymanagement);

        final Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        stu_id = bundle.getString("stu_id");
        Toast.makeText(StudymanagementActivity.this,"学生:"+stu_id,Toast.LENGTH_SHORT).show();

        findButton();
        setListener();

    }
    private void setListener() {
        button_insert.setOnClickListener(new setButtonOnclick());
        button_display.setOnClickListener(new setButtonOnclick());
    }

    private void findButton()
    {
        button_insert=(Button)findViewById(R.id.bt_info_insert);
        button_display=(Button)findViewById(R.id.bt_info_display);
    }

    private class setButtonOnclick implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.bt_info_insert:
                    Intent intent_insert = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("stu_id",stu_id);
                    intent_insert.putExtras(bundle);
                    intent_insert.setClass(StudymanagementActivity.this, StudentInfoActivity.class);
                    startActivity(intent_insert);
                    break;
                case R.id.bt_info_display:
                    Student student=new Student();
                    student.setStu_id(stu_id);
                    //对象转json
                    Gson gson = new Gson();
                    String stu_json = gson.toJson(student);

                    //发送服务器请求（http）导入kjframe包
                    KJHttp http = new KJHttp();
                    HttpParams httpParams = new HttpParams();
                    httpParams.setContentType("application/json");
                    //加入参数
                    httpParams.putJsonParams(stu_json);
                    Log.e("---->",stu_json);
                    //发送post请求             提交到服务器哪                      把json串提交给服务器     是否要缓存
                    http.jsonPost("http://212.64.70.83:8081/student/servlet/ServletQueryById", httpParams, false, new HttpCallBack() {
                        @Override
                        public void onSuccess(String t) {
                            super.onSuccess(t);
                            Log.e("接收数据=====>", t);

                            Intent intent_display = new Intent();
                            intent_display.putExtra("studentAll",t);
                            intent_display.setClass(StudymanagementActivity.this,DisplayInfoActivity.class);
                            startActivity(intent_display);

                        }
                        @Override
                        public void onFailure(int errorNo, String strMsg) {
                            super.onFailure(errorNo, strMsg);
                            Log.e("失败=====>",strMsg);
                            //不成功
                            Toast.makeText(StudymanagementActivity.this,"服务器链接失败:"+strMsg,Toast.LENGTH_SHORT).show();
                        }
                    });

                    break;
            }
        }
    }
}
