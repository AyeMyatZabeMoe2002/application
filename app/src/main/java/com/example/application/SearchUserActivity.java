package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchUserActivity extends AppCompatActivity {
    TextView searchUserView;
    Button btnSearchUser;
    EditText searchKey;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        dataBaseHelper = new DataBaseHelper(this);
        btnSearchUser  = findViewById(R.id.btnSearchUser);
        searchKey     = findViewById(R.id.searchKey);
        searchUserView = findViewById(R.id.searchUserView);

        btnSearchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchUserName();
            }
        });
    }//end of create

    private void searchUserName() {
        String key = searchKey.getText().toString();
        ArrayList<String> searchUserArray = dataBaseHelper.getUser(key);
        if(searchUserArray.size()==0){
            searchUserView.setText("No user found!");
        }
        else{
            String user ="";
            for (int i=0; i<searchUserArray.size();i++)
            {
                user += searchUserArray.get(i)+"\n";
            }
            searchUserView.setText(user);
        }
    }//end of search user name

}//end of the class