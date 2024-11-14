package com.example.application;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ModifyUserActivity extends AppCompatActivity {

    EditText modifyUserName;
    EditText modifyUserEmail;
    EditText modifyUserRemark;
    Button btnUpdate, btnDelete;
    ImageButton btnSaveQualification,btnQList;
    String id,name,email,remark;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        modifyUserName   = findViewById(R.id.modifyUserName);
        modifyUserEmail  = findViewById(R.id.modifyUserEmail);
        modifyUserRemark = findViewById(R.id.modifyUserRemark);
        btnDelete        = findViewById(R.id.btnDelete);
        btnUpdate        = findViewById(R.id.btnUpdate);
        btnSaveQualification = findViewById(R.id.btn_save_qualification);
        dataBaseHelper = new DataBaseHelper(this);
        btnQList = findViewById(R.id.btn_q_list);

        Intent intent = getIntent();
        id = intent.getExtras().getString("id");
        name = intent.getExtras().getString("name");
        email = intent.getExtras().getString("email");
        remark = intent.getExtras().getString("remark");

        modifyUserName.setText(name);
        modifyUserEmail.setText(email);
        modifyUserRemark.setText(remark);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User(Integer.parseInt(id),
                        modifyUserName.getText().toString(),
                        modifyUserEmail.getText().toString(),
                        modifyUserRemark.getText().toString());

                dataBaseHelper.updateUser(user);

                Intent listIntent = new Intent(ModifyUserActivity.this,ListUserActivity.class);
                startActivity(listIntent);
            }
        });//btnUpdate

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseHelper.deleteUser(Integer.parseInt(id));
                Intent listIntent = new Intent(ModifyUserActivity.this,ListUserActivity.class);
                startActivity(listIntent);
            }
        });//btnDelete

        btnSaveQualification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("User ID","******"+id);
                Intent intent = new Intent(ModifyUserActivity.this,AddQualificationActivity.class);
                intent.putExtra("user_id",Integer.parseInt(id));
                startActivity(intent);
            }
        });

        btnQList.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {
                Log.d("User ID","******"+id);
                Intent intent = new Intent(ModifyUserActivity.this,QualificationListActivity.class);
                intent.putExtra("user_id",Integer.parseInt(id));
                startActivity(intent);
            }
        });

    }
}