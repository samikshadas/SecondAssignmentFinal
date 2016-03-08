package com.example.android.secondassignment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get ListView object from xml
        final ListView listView = (ListView) findViewById(R.id.listView);

        // Create a new Adapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1);

        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // Use Firebase to populate the list.
        Firebase.setAndroidContext(this);

        new Firebase("https://torrid-torch-322.firebaseio.com/")
                .addChildEventListener(new ChildEventListener() {
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        adapter.add((String) dataSnapshot.child("text").getValue());
                    }

                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                        adapter.remove((String) dataSnapshot.child("text").getValue());
                    }

                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    }

                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                    }

                    public void onCancelled(FirebaseError firebaseError) {
                    }
                });
    } @Override
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
}




