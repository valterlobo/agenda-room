package com.valterlobo.agenda;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;


class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> users;

    private UserDao userDao;

    private Context context;


    public UserAdapter(List<User> users, UserDao userDao, Context context) {
        this.users = users;
        this.userDao = userDao;
        this.context = context;

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
        public ImageButton btView;

        public ViewHolder(final View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.first_name);
            lastName = itemView.findViewById(R.id.last_name);
            email = itemView.findViewById(R.id.email);
            btDelete = itemView.findViewById(R.id.bt_delete);
            btView = itemView.findViewById(R.id.bt_view);

            btDelete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    Log.i("USER-REOMVE", " REMOVE ON CLICK: " + getAdapterPosition());
                    removeItemAtPosition(getAdapterPosition());

                }
            });


            btView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, ViewUser.class);
                    intent.putExtra("user_id", users.get(getAdapterPosition()).getId()) ;

                    Log.i("USER-GET", "  ID USER : " +  users.get(getAdapterPosition()).getId() );
                    context.startActivity(intent);

                }
            });

        }
    }
}
