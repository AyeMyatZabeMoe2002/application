package com.example.application;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class AddUserActivity extends AppCompatActivity {
    EditText editName;
    EditText editEmail;
    EditText editRemark;
    Button btnAddUser;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        editName   = findViewById(R.id.editName);
        editEmail  = findViewById(R.id.editEmail);
        editRemark = findViewById(R.id.editRemark);
        btnAddUser = findViewById(R.id.btnAddUser);

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }//end of onclick
        });

    }//end of onCreate

    private void addUser() {
        dataBaseHelper = new DataBaseHelper(this);
        String name = editName.getText().toString();
        String email = editEmail.getText().toString();
        String remark = editRemark.getText().toString();

        User user = new User(name,email,remark);
        //long user_id = dataBaseHelper.saveUser(name,email,remark);
        long user_id = dataBaseHelper.saveUser(user);
        Toast.makeText(this,"Added: "+user_id,Toast.LENGTH_LONG).show();
    }//end of addUser

}//end of class