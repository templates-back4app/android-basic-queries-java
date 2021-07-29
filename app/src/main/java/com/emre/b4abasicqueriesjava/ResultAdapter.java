package com.emre.b4abasicqueriesjava;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultHolder> {

    private Context context;
    private List<ParseObject> list;

    public ResultAdapter(Context context, List<ParseObject> list) {
        this.list = list;
        this.context = context;
    }

    public void clearList(){
        list = new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.result_cell,parent,false);
        return new ResultHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ResultHolder holder, int position) {
        ParseObject profile = list.get(position);
        holder.name.setText(profile.getString("name"));
        holder.details.setText("Friend Count: " + profile.getInt("friendCount") + " Birthday: " + profile.getDate("birthDay"));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class ResultHolder extends RecyclerView.ViewHolder {

    TextView name;
    TextView details;

    public ResultHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        details = itemView.findViewById(R.id.details);
    }
}
