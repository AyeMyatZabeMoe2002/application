package com.example.application;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class QualificationRecyclerViewAdapter extends RecyclerView.Adapter<QualificationViewHolder> {
    Context context;
    ArrayList<Qualification> arrayList;
    DataBaseHelper dataBaseHelper;

    public QualificationRecyclerViewAdapter(Context context,ArrayList<Qualification> arrayList){
            this.context = context;
            this.arrayList = arrayList;
            dataBaseHelper = new DataBaseHelper(context);
    }

    @Override
    public QualificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //to create view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.qualification_list_item_layout,parent,false);
        QualificationViewHolder q_view = new QualificationViewHolder(view);
        return q_view;
    }

    @Override
    public void onBindViewHolder(QualificationViewHolder holder, int position) {
        Qualification q_record = arrayList.get(position);
        holder.qImageView.setImageResource(R.drawable.ic_launcher_foreground);
        holder.qTitleTextView.setText(q_record.getTitle());
        holder.qYearTextView.setText(q_record.getYear());
        
        holder.btnQEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editQualification(q_record);
            }
        });
        
        holder.btnQDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteQualification(q_record);
            }
        });

    }

    private void deleteQualification(Qualification q) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirmation")
        .setMessage("Are you sure you want to delete it?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataBaseHelper.deleteQualification(q.get_id());
                        ((Activity)context).finish();
                        context.startActivity(((Activity) context).getIntent());
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do nothing
                    }
                })
                .show();
    }//delete Qualification

    private void editQualification(Qualification q) {

        Intent intent = new Intent(context,EditQualificationActivity.class);
        intent.putExtra("q_id",q.get_id());
        intent.putExtra("q_title",q.getTitle());
        intent.putExtra("q_year",q.getYear());
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
