package com.ptcintadamai.costomerapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.InputStreamReader;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class adapterNote extends RecyclerView.Adapter<adapterNote.ViewData> {
    private final ArrayList<note> listNotes = new ArrayList<>();
    Context context;

    public adapterNote( Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new ViewData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewData holder,final int position) {
        holder.bind(position);
        holder.cvNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NoteAddUpdateActivity.class);
                intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, listNotes.get(position));
                context.startActivity(intent);
            }
        });
    }
    public ArrayList<note> getListNotes() {
        return listNotes;
    }

    public void setListNotes(ArrayList<note> listNotes) {

        if (listNotes.size() > 0) {
            this.listNotes.clear();
        }
        this.listNotes.addAll(listNotes);

        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return listNotes.size();
    }

    class ViewData extends RecyclerView.ViewHolder{
        final TextView tvTitle, tvDescription, tvDate;
        final CardView cvNote;
        public ViewData(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            cvNote = itemView.findViewById(R.id.cv_item_note);
        }
        private void bind(final int position){
            tvTitle.setText(listNotes.get(position).getTitle());
            tvDate.setText(listNotes.get(position).getDate());
            tvDescription.setText(listNotes.get(position).getDescription());
        }
    }


}
