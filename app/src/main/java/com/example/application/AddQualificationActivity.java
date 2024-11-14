package com.example.application;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class AddQualificationActivity extends AppCompatActivity {
    EditText qualificationTitle,qualificationYear;
    Button btnAddQualification;
    DataBaseHelper dataBaseHelper;
    int user_id =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_qualification);

        btnAddQualification = findViewById(R.id.btnAddQualification);
        qualificationTitle  = findViewById(R.id.edit_q_title);
        qualificationYear   = findViewById(R.id.edit_q_year);

        dataBaseHelper = new DataBaseHelper(this);

        Intent intent  = getIntent();
        user_id = intent.getExtras().getInt("user_id");

        btnAddQualification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveQualification();
            }
        });
    }

    private void saveQualification() {

        String qt = qualificationTitle.getText().toString();
        String qy = qualificationYear.getText().toString();

        Log.d("Qualification",qt+"*****"+user_id);

        Qualification qualification = new Qualification(qt,qy,user_id);

        long qid = dataBaseHelper.addQualification(qualification);

        Toast.makeText(this,"Saved: "+qid,Toast.LENGTH_LONG).show();

    }
}