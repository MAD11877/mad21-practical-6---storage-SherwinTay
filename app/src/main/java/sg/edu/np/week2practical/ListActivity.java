package sg.edu.np.week2practical;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

import static android.media.CamcorderProfile.get;

public class ListActivity extends AppCompatActivity {
    private final String TAG = "List Activity";

    public static ArrayList<User> UserList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Log.v(TAG, "On Create!");


        MyDbHandler dbHandler = new MyDbHandler(this, null, null, 1);
        //Populating list with User Data
        for (int i = 0; i < 20; i++){
           User user = new User("Name " + String.valueOf(randomnumber()), "Description: " + String.valueOf(randomnumber()), i, followedgenerator());
           UserList.add(user);
           dbHandler.addUser(user);

        }

        RecyclerView recyclerView = findViewById(R.id.recycleview);
        UserAdapter mAdapter = new UserAdapter(UserList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "On Pause!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "On Resume!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "On Stop!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "On Destroy!");
    }

    private int randomnumber(){
        Random random = new Random();
        int value = random.nextInt();
        return value;
    }

    private boolean followedgenerator(){
        Random rd = new Random();
        return rd.nextBoolean();
    }





}