package com.yash.todolistbyfragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    View view;
    LayoutInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListByItem();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
public void addListByItem(){


    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    final EditText addItems=(EditText)findViewById(R.id.addItemTxt);
    final ListView myListview=(ListView)findViewById(R.id.listView);

    final ArrayList<String> todoItems=new ArrayList<String>();
    final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,todoItems);
    myListview.setAdapter(adapter);
    addItems.setOnKeyListener(new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {

            if (event.getAction() == KeyEvent.ACTION_DOWN)
                if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER) || (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    todoItems.add(0, addItems.getText().toString());
                    adapter.notifyDataSetChanged();
                    addItems.setText("");
                    Fragment fragment = new ItemsDetail();
                    Fragment fragmentList= new FragmentList();
                    FragmentManager manager= getSupportFragmentManager();
                    FragmentTransaction transaction= manager.beginTransaction();
                    transaction.replace(R.id.fragment, fragmentList);
                    transaction.remove(fragment);
                    transaction.commit();
                    return true;

                }
            return false;
        }


    });
    myListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           /* StringBuffer result= new StringBuffer();
            result.append("Hello");

            Toast.makeText(MainActivity.this,result,Toast.LENGTH_LONG).show();*/;
            TextView setItem=(TextView)findViewById(R.id.txtView);
            String getItemClicked=parent.getItemAtPosition(position).toString();

            Toast.makeText(MainActivity.this,getItemClicked,Toast.LENGTH_LONG).show();
            Fragment fragment = new ItemsDetail();
            Fragment fragmentList= new FragmentList();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
           /* inflater.inflate(R.layout.fragment_items_detail,container,false);
           */

            transaction.replace(R.id.fragment, fragment);
            transaction.remove(fragmentList);
            transaction.commit();

        }
    });
    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    });
}

}
