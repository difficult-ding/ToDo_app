package com.example.todo_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<String> mList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkBox;
        public ViewHolder(View view){
            super(view);
            checkBox=(CheckBox)view.findViewById(R.id.check_item);
        }
    }
    public ItemAdapter(List<String> mlist){
        mList=mlist;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate
                (R.layout.item,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        String str=mList.get(position);
        holder.checkBox.setVisibility(View.VISIBLE);
        holder.checkBox.setText(str);
    }
    @Override
    public int getItemCount(){
        return mList.size();
    }
}
