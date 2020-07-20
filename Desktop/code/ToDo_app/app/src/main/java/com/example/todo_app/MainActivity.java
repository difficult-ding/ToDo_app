package com.example.todo_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = " ";
    private DrawerLayout mDrawerLayout;
    private RecyclerView itemRecyclerView;
    private ItemAdapter adapter;
    private List<String> mList=new ArrayList<>();
    private List<Boolean> booleanList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navView=(NavigationView)findViewById(R.id.nav_view);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_threeline_fill35);
        }
        navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        FloatingActionButton fab= findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(MainActivity.this,add_item.class);
                startActivity(intent);
            }
        });
        itemRecyclerView=(RecyclerView)findViewById(R.id.item_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        itemRecyclerView.setLayoutManager(layoutManager);
        List<Task> tasks= DataSupport.findAll(Task.class);
        for(Task task:tasks){
            mList.add(task.getItem());
        }
        adapter=new ItemAdapter(mList);
        adapter.notifyItemInserted(mList.size()-1);
        itemRecyclerView.scrollToPosition(mList.size()-1);
        itemRecyclerView.setAdapter(adapter);
        booleanList=adapter.getBooleanList();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.delete:
                int y=0;
                //Toast.makeText(this,"You clicked Delete  ",Toast.LENGTH_SHORT).show();
                for(int i=0;i<mList.size();i++)
                {
                    if(booleanList.get(i)!=null&&booleanList.get(i)){
                        DataSupport.deleteAll(Task.class,"item=?",mList.get(i));
                        mList.remove(i);
                        booleanList.remove(i);
                        i--;
                        y++;
                    }
                }
                adapter.notifyDataSetChanged();
                if(y==0){
                    Toast.makeText(this," 没有要删除的数据 ",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.settings:
                Toast.makeText(this,"You clicked Settings  ",Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
            default:
        }
        return true;
    }
}