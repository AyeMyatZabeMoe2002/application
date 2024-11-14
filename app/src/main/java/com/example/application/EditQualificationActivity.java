package com.example.application;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class EditQualificationActivity extends AppCompatActivity {

    EditText qualificationTitle,qualificationYear;
    Button btnUpdateQualification;
    DataBaseHelper dataBaseHelper;
    int q_id =0;
    String q_title,q_year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_qualification);
        qualificationTitle = findViewById(R.id.update_q_title);
        qualificationYear  = findViewById(R.id.update_q_year);
        btnUpdateQualification = findViewById(R.id.btnUpdateQualification);
        
        dataBaseHelper = new DataBaseHelper(this);

        Intent intent = getIntent();
        q_id = intent.getExtras().getInt("q_id");
        q_title = intent.getExtras().getString("q_title");
        q_year  = intent.getExtras().getString("q_year");
        
        qualificationTitle.setText(q_title);
        qualificationYear.setText(q_year);

        btnUpdateQualification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQualification();
            }
        });
    }

    private void updateQualification() {
        String qt  = qualificationTitle.getText().toString();
        String qy  = qualificationYear.getText().toString();

        Log.d("Qualification", qt+"**********"+q_id);
        Qualification qualification = new Qualification(q_id,qt,qy);

       dataBaseHelper.updateQualification(qualification);

        Intent intent = new Intent(EditQualificationActivity.this,QualificationListActivity.class);
        startActivity(intent);
    }
}