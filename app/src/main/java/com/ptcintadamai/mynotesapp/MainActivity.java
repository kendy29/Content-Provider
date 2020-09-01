package com.ptcintadamai.mynotesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    OpenHelper db;
    ArrayList<note> list;
    private ProgressBar progressBar;
    private RecyclerView rvNotes;
    private adapterNote adapter;
    private FloatingActionButton fabAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList();
        db = new OpenHelper(MainActivity.this);
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("Notes");
        progressBar = findViewById(R.id.progressbar);
        rvNotes = findViewById(R.id.rv_notes);
        rvNotes.setLayoutManager(new LinearLayoutManager(this));
        rvNotes.setHasFixedSize(true);
        adapter = new adapterNote(list, MainActivity.this);
        rvNotes.setAdapter(adapter);
        Cursor c = db.getData("select * from " + OpenHelper.TABLE_NAME);
        while (c.moveToNext()) {
            note _note = new note();
            _note.setId(c.getInt(0));
            _note.setTitle(c.getString(1));
            _note.setDescription(c.getString(2));
            _note.setDate(c.getString(3));
            list.add(_note);
            adapter.notifyDataSetChanged();
        }
        fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NoteAddUpdateActivity.class);
                startActivity(intent);
            }
        });
    }
    private void showSnackbarMessage(String message) {
        Snackbar.make(rvNotes, message, Snackbar.LENGTH_SHORT).show();
    }

}
