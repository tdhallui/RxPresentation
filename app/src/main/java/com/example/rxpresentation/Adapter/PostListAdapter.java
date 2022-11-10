package com.example.rxpresentation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxpresentation.Model.UserModel;
import com.example.rxpresentation.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.MyViewHolder> {

    private Context context;
    private List<UserModel> userModelList;
    private UserModel userModel;

    public PostListAdapter(Context context, List<UserModel> userModelList) {
        this.context = context;
        this.userModelList = userModelList;
    }

    public PostListAdapter(Context context, UserModel userModel) {
        this.context = context;
        this.userModel = userModel;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_post_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtTitle.setText(String.valueOf(userModelList.get(position).getTitle()));
        holder.txtBody.setText(new StringBuilder(userModelList.get(position).getBody().substring(0, 5)).append("...").toString());
        holder.txtUserId.setText(String.valueOf(userModelList.get(position).getUserId()));
    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtBody, txtUserId;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtBody = itemView.findViewById(R.id.txtBody);
            txtUserId = itemView.findViewById(R.id.txtUserId);
        }
    }

    public void clear() {
        userModelList.clear();
        notifyDataSetChanged();
    }
}
