package com.example.application;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ListUserActivity extends AppCompatActivity {

    ListView userListView;
    DataBaseHelper dataBaseHelper;
    String[] from = new String[]{DataBaseHelper.USER_ID,DataBaseHelper.USER_NAME,DataBaseHelper.USER_EMAIL,DataBaseHelper.USER_REMARK};//attributes name
    int[] to= new int[]{R.id.textViewUserID,R.id.textViewUserName,R.id.textViewUserEmail,R.id.textViewUserRemark};//layout id name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        userListView = findViewById(R.id.userListView);
        dataBaseHelper = new DataBaseHelper(this);
        Cursor cursor = dataBaseHelper.fetchAllUsers();
        CursorAdapter cursorAdapter = new SimpleCursorAdapter(this,R.layout.user_list_layout,cursor,from,to,0);
        cursorAdapter.notifyDataSetChanged();
        userListView.setAdapter(cursorAdapter);
        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textViewUserID = view.findViewById(R.id.textViewUserID);
                TextView textViewUserName = view.findViewById(R.id.textViewUserName);
                TextView textViewUserEmail = view.findViewById(R.id.textViewUserEmail);
                TextView textViewUserRemark = view.findViewById(R.id.textViewUserRemark);

                String user_id = textViewUserID.getText().toString();
                String name = textViewUserName.getText().toString();
                String email = textViewUserEmail.getText().toString();
                String remark = textViewUserRemark.getText().toString();

                //String st = user_id+": "+name+", "+email+", "+remark;
                //Toast.makeText(ListUserActivity.this, st, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListUserActivity.this, ModifyUserActivity.class);
                intent.putExtra("id",user_id);
                intent.putExtra("name",name);
                intent.putExtra("email",email);
                intent.putExtra("remark",remark);
                startActivity(intent);
            }
        });

    }//end of onCreate

}//end of class


/*
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        userListView = findViewById(R.id.userListView);

        dataBaseHelper = new DataBaseHelper(this);

        ArrayList<String> user_array = dataBaseHelper.getAllUsers();

        String[] user = user_array.toArray(new String[user_array.size()]);//array list to string[]
        ArrayAdapter<String> userArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,user);
        userListView.setTextFilterEnabled(true);
        userListView.setAdapter(userArrayAdapter);

        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),""+user[position],Toast.LENGTH_LONG).show();
            }
        });

    }//end of onCreate
 */