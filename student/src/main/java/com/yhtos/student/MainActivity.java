package com.yhtos.student;

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
* 函数名：MainActivity
* 功能：主界面显示
* */

public class MainActivity extends AppCompatActivity {
    EditText editText_sno,editText_password;
    Button button_dl,button_zc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FindKongjian();
        setListener();

    }

    private void FindKongjian() {
        editText_sno=(EditText)findViewById(R.id.bt_edit_sno);
        editText_password=(EditText)findViewById(R.id.bt_edit_password);
        button_dl=(Button)findViewById(R.id.bt_dl);
        button_zc=(Button)findViewById(R.id.bt_zc);
    }
    private void setListener() {
        button_dl.setOnClickListener(new ButtonOnclickListener());
        button_zc.setOnClickListener(new ButtonOnclickListener());
    }



    private class ButtonOnclickListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            final String number=editText_sno.getText().toString().trim();
            final String password=editText_password.getText().toString().trim();

            Student student=new Student();
            student.setStu_id(number);
            student.setStu_pwd(password);

            //对象转json
            Gson gson = new Gson();
            String stu_json = gson.toJson(student);

            switch (v.getId()) {
                case R.id.bt_dl:
                    if(number.equals("")||password.equals(""))
                    {
                        Toast.makeText(MainActivity.this,"学号或密码为空，登陆失败",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //发送服务器请求（http）导入kjframe包
                    KJHttp http_dl = null;
                    HttpParams httpParams_dl = null;
                    try {
                        http_dl = new KJHttp();
                        httpParams_dl = new HttpParams();
                       ////// httpParams_dl.setContentType("application/json");
                        //加入参数
                        httpParams_dl.putJsonParams(stu_json);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.e("---->",stu_json);
                    //发送post请求             提交到服务器哪                      把json串提交给服务器     是否要缓存
                    http_dl.jsonPost("http://212.64.70.83:8081//student/servlet/ServletLogin", httpParams_dl, false, new HttpCallBack(){
                        @Override
                        public void onSuccess(String t) {
                            super.onSuccess(t);
                            Log.e("服务器接收数据=====>", t);
                            if (t.equals("0")){
                                Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();

                                //带参数传值
                                Intent intent = new Intent();
                                Bundle bundle = new Bundle();
                                bundle.putString("stu_id",number);
                                intent.putExtras(bundle);
                                intent.setClass(MainActivity.this, StudymanagementActivity.class);
                                startActivity(intent);
                            }
                            else if (t.equals("1")){
                                Toast.makeText(MainActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                            }
                            else if(t.equals("2")){
                                Toast.makeText(MainActivity.this,"暂无此用户，请先注册",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(MainActivity.this,"未知错误",Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(int errorNo, String strMsg) {
                            super.onFailure(errorNo, strMsg);
                            Log.e("服务器接收数据=====>",strMsg);
                            //不成功
                            Toast.makeText(MainActivity.this,"服务器链接失败:"+strMsg,Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                case R.id.bt_zc:
                    //发送服务器请求（http）导入kjframe包
                    KJHttp http_zc = new KJHttp();
                    HttpParams httpParams_zc = new HttpParams();
                    httpParams_zc.setContentType("application/json");
                    //加入参数
                    httpParams_zc.putJsonParams(stu_json);
                    Log.e("---->",stu_json);
                    //发送post请求             提交到服务器哪                      把json串提交给服务器     是否要缓存
                    http_zc.jsonPost("http://212.64.70.83:8081/student/servlet/ServletStu", httpParams_zc, false, new HttpCallBack() {
                        @Override
                        public void onSuccess(String t) {
                            super.onSuccess(t);
                            Log.e("服务器接收数据=====>", t);
                            if (t.equals("ok")){
                                Toast.makeText(MainActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(int errorNo, String strMsg) {
                            super.onFailure(errorNo, strMsg);
                            Log.e("失败=====>",strMsg);
                            //不成功
                            Toast.makeText(MainActivity.this,"服务器链接失败:"+strMsg,Toast.LENGTH_SHORT).show();
                        }
                    });
            }

        }
    }
}
