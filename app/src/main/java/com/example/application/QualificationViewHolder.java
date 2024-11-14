package com.example.application;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class QualificationViewHolder extends RecyclerView.ViewHolder{

    ImageView qImageView;
    TextView qTitleTextView;
    TextView qYearTextView;
    ImageButton btnQEdit,btnQDelete;

    public QualificationViewHolder(View itemView) {
        super(itemView);
        qImageView      = itemView.findViewById(R.id.q_imageView);
        qTitleTextView  = itemView.findViewById(R.id.q_title_view);
        qYearTextView   = itemView.findViewById(R.id.q_year_view);
        btnQEdit        = itemView.findViewById(R.id.btn_q_edit);
        btnQDelete      = itemView.findViewById(R.id.btn_q_delete);
    }//end of onCreate
}//end of class
