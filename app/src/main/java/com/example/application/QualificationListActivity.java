package com.example.application;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
public class QualificationListActivity extends AppCompatActivity {

    TextView qList;
    DataBaseHelper dataBaseHelper;
    RecyclerView qRecyclerView;
    int user_id =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qualification_list);
        qList = findViewById(R.id.q_list_textView);

        qRecyclerView = findViewById(R.id.q_recyclerview);

        dataBaseHelper = new DataBaseHelper(this);

        Intent intent = getIntent();

        user_id = intent.getExtras().getInt("user_id");

        ArrayList<Qualification> q_array_list = dataBaseHelper.getAllQualifications(user_id);
        //Recyclerview
        if(q_array_list.size()!=0){
            qList.setVisibility(View.GONE);
            qRecyclerView.setVisibility(View.VISIBLE);
            qRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            qRecyclerView.setHasFixedSize(true);
            qRecyclerView.setAdapter(new QualificationRecyclerViewAdapter(this,q_array_list));
        }
        else{
            qList.setVisibility(View.VISIBLE);
            qRecyclerView.setVisibility(View.GONE);
            qList.setText("No qualification record!");
        }

    }//end of onCreate
}//end of class


 /* TextView
        if(q_array_list.size()==0) qList.setText("No qualification record!");
        else{
            qList.setText("Qualification List\n");
            for (int i=0;i<q_array_list.size();i++){
                qList.append(q_array_list.get(i)+"\n");
            }
        }
        */