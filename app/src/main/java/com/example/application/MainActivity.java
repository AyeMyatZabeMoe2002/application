package com.example.application;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBaseHelper = new DataBaseHelper(this);

    }//end of onCreate

    //Menu build to use on create options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //load the menu bar use inflate
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }//end of onCreateOptions

    //work menu bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        if(item.getItemId()==R.id.itemAdd){
            Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
            startActivity(intent);
            return true;
        }
        else if(item.getItemId()==R.id.itemShow){
            Intent intent = new Intent(MainActivity.this, ListUserActivity.class);
            startActivity(intent);
            return true;
        }
        else if(item.getItemId()==R.id.itemSearch){
            Intent intent = new Intent(MainActivity.this, SearchUserActivity.class);
            startActivity(intent);
            return true;
        }

        else if(item.getItemId()==R.id.itemDeleteAll){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Confirmation")
                        .setMessage("Are you sure want to delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dataBaseHelper.deleteAllUsers();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //do nothing
                            }
                        })
                        .show();
                return  true;

        }
        else return super.onOptionsItemSelected(item);
    }//end of onOptionsItemSelected

}//end of class




