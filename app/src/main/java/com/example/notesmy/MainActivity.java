package com.example.notesmy;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingId;
    RecyclerView recyclerView;
    ArrayList<NoteModel> arrayList = new ArrayList<>();
    NoteHelper noteHelper;
    NoteAdapter adapter; // Adapter reference

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        floatingId = findViewById(R.id.floatingId);
        recyclerView = findViewById(R.id.recyclerView);
        noteHelper = new NoteHelper(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadData(); // Load data initially

        adapter = new NoteAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);

        floatingId.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivityForResult(intent, 1);
        });
    }

    private void loadData() {
        arrayList.clear(); // Clear existing data
        Cursor cursor = noteHelper.showData();
        while (cursor.moveToNext()) {
            arrayList.add(new NoteModel(cursor.getString(1), cursor.getString(2), cursor.getInt(0)));
        }
        cursor.close(); // Close cursor
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            loadData(); // Reload data after returning
            adapter.notifyDataSetChanged(); // Notify adapter of change
        }
    }
}
