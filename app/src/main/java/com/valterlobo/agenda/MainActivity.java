package com.valterlobo.agenda;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    AppDatabase db;

    FloatingActionButton fab;
    FloatingActionButton fabQrCode;
    RecyclerView recyclerView;

    RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);
        fabQrCode =findViewById(R.id.fabqrcode);

        recyclerView = findViewById(R.id.recycler_view);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "agenda-room")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        adapter = new UserAdapter(db.userDao().getAll() , db.userDao(), this.getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        //eu = new User("Valter", "Lobo", "valterlobo@gmail.com");
        //db.userDao().insertAll(eu);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateUser.class));
            }
        });

        fabQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, QRCodeUser.class));
            }
        });

    }
}
