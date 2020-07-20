package com.example.todo_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<String> mList;
    private Context context;
    private List<Boolean> booleanList=new ArrayList<>();
    static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkBox;
        public ViewHolder(View view){
            super(view);
            checkBox=(CheckBox)view.findViewById(R.id.check_item);
        }
    }
    public ItemAdapter(List<String> mlist){
        mList=mlist;
        for(int i=0;i<mlist.size();i++){
            booleanList.add(false);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate
                (R.layout.item,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        String str=mList.get(position);
        holder.checkBox.setVisibility(View.VISIBLE);
        holder.checkBox.setText(str);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                booleanList.set(position,b);
            }
        });
        //holder.checkBox.setChecked(booleanList.get(position));
    }
    @Override
    public int getItemCount(){
        return mList.size();
    }

    public List<Boolean> getBooleanList(){
        return booleanList;
    }
}
