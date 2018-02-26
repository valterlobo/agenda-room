package com.valterlobo.agenda;

import android.arch.persistence.room.Room;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> users;

    private UserDao userDao;


    public UserAdapter(List<User> users , UserDao userDao) {
        this.users = users;
        this.userDao = userDao;
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder holder, final int position) {
        holder.firstName.setText(users.get(position).firstName);
        holder.lastName.setText(users.get(position).lastName);
        holder.email.setText(users.get(position).email);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void removeItemAtPosition(int position) {
        userDao.removeAll(users.get(position));
        users.remove(position);
       notifyDataSetChanged();
        //DataBase Remover


    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView firstName;
        public TextView lastName;
        public TextView email;
        public ImageButton btDelete;

        public ViewHolder(final View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.first_name);
            lastName = itemView.findViewById(R.id.last_name);
            email = itemView.findViewById(R.id.email);
            btDelete = itemView.findViewById(R.id.bt_delete);

            btDelete.setOnClickListener( new View.OnClickListener(){

                @Override
                public void onClick(View view) {

                    Log.i("USER-REOMVE"," REMOVE ON CLICK: " + getAdapterPosition());
                    removeItemAtPosition( getAdapterPosition() ) ;

                }
            });

        }
    }
}
