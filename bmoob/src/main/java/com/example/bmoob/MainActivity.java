package com.example.bmoob;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "62b816d2ac871b665b5a5b39cc0a738d");
        mName = ((EditText) findViewById(R.id.name));
        mFeedback = ((EditText) findViewById(R.id.feedback));

    }

    public void submit(View view) {
        String name = this.mName.getText().toString();
        String feedback = mFeedback.getText().toString();
        if(name.equals("")||feedback.equals("")){
            return;
        }
        Feedback feedbackobj = new Feedback();
        feedbackobj.setName(name);
        feedbackobj.setFeedback(feedback);
        feedbackobj.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e == null){
                    Toast.makeText(MainActivity.this,"提交数据成功"+s, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"失败"+e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void query(View view){
        BmobQuery<Feedback>  query = new BmobQuery<>();
        query.findObjects(new FindListener<Feedback>() {
            @Override
            public void done(List<Feedback> list, BmobException e) {
                if(e == null){
                    AlertDialog.Builder builder  = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Query");
                    String str = "";
                    for(Feedback fb: list){
                        str += fb.getName()+":"+fb.getFeedback()+"\n";
                    }
                    builder.setMessage(str);
                    builder.create().show();
                }else{
                    Toast.makeText(MainActivity.this,"查询失败", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
