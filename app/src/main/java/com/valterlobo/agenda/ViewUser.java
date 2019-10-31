package com.valterlobo.agenda;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ViewUser extends AppCompatActivity {


    AppDatabase db;

    EditText firstName;
    EditText lastName;
    EditText email;

    User user;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);



        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "agenda-room")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();


        Bundle extras = getIntent().getExtras();
        Integer user_id = extras.getInt("user_id");
        loadUser(db , user_id);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User userUpdate = new User(firstName.getText().toString(), lastName.getText().toString(), email.getText().toString());
                userUpdate.setId(user.getId());
                db.userDao().updateAll(userUpdate);
                startActivity(new Intent(ViewUser.this, MainActivity.class));
            }
        });
    }


    public void loadUser(AppDatabase db , int id){

        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        button = findViewById(R.id.button);

        this.user = db.userDao().getById(id);

        firstName.setText(this.user.firstName);
        lastName.setText(this.user.lastName);
        email.setText(this.user.email);


    }
}
