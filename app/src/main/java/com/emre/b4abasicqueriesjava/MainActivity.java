package com.emre.b4abasicqueriesjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {

    private RecyclerView resultList;
    private Button queryByName;
    private Button queryByFriendCount;
    private Button queryByOrdering;
    private Button queryByAll;
    private Button clearResults;

    private ResultAdapter adapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultList = findViewById(R.id.resultList);
        queryByName = findViewById(R.id.queryByName);
        queryByFriendCount = findViewById(R.id.queryByFriendCount);
        queryByOrdering = findViewById(R.id.queryByOrdering);
        queryByAll = findViewById(R.id.queryByAll);
        clearResults = findViewById(R.id.clearResults);
        progressDialog = new ProgressDialog(this);

        queryByName.setOnClickListener(view -> {
            doQueryByName();
        });
        queryByFriendCount.setOnClickListener(view -> {
            doQueryByFriendCount();
        });
        queryByOrdering.setOnClickListener(view -> {
            doQueryByOrdering();
        });
        queryByAll.setOnClickListener(view -> {
            doQueryByAll();
        });
        clearResults.setOnClickListener(view -> {
            if (adapter != null)
                adapter.clearList();
        });
    }

    private void doQueryByName() {
        ParseQuery<ParseObject> query = new ParseQuery<>("Profile");
        query.whereContains("name", "Adam");
        progressDialog.show();
        query.findInBackground((objects, e) -> {
            progressDialog.hide();
            if (e == null) {
                adapter = new ResultAdapter(this, objects);
                resultList.setLayoutManager(new LinearLayoutManager(this));
                resultList.setAdapter(adapter);
            } else {
                Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void doQueryByFriendCount() {
        ParseQuery<ParseObject> query = new ParseQuery<>("Profile");
        query.whereGreaterThan("friendCount", 20);
        progressDialog.show();
        query.findInBackground((objects, e) -> {
            progressDialog.hide();
            if (e == null) {
                adapter = new ResultAdapter(this, objects);
                resultList.setLayoutManager(new LinearLayoutManager(this));
                resultList.setAdapter(adapter);
            } else {
                Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void doQueryByOrdering() {
        ParseQuery<ParseObject> query = new ParseQuery<>("Profile");
        query.orderByDescending("birthDay");
        progressDialog.show();
        query.findInBackground((objects, e) -> {
            progressDialog.hide();
            if (e == null) {
                adapter = new ResultAdapter(this, objects);
                resultList.setLayoutManager(new LinearLayoutManager(this));
                resultList.setAdapter(adapter);
            } else {
                Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void doQueryByAll() {
        ParseQuery<ParseObject> query = new ParseQuery<>("Profile");
        query.whereContains("name", "Adam");
        query.whereGreaterThan("friendCount", 20);
        query.orderByDescending("birthDay");
        progressDialog.show();

        query.findInBackground((objects, e) -> {
            progressDialog.hide();

            if (e == null) {
                adapter = new ResultAdapter(this, objects);
                resultList.setLayoutManager(new LinearLayoutManager(this));
                resultList.setAdapter(adapter);
                resultList.setNestedScrollingEnabled(false);
            } else {
                Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}