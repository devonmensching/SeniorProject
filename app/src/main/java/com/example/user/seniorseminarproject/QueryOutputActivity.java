package com.example.user.seniorseminarproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class QueryOutputActivity extends AppCompatActivity {
    private TextView insertResultTextView;
    private TextView searchResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_output);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        if(bundle != null){
            insertResultTextView = (TextView)findViewById(R.id.insertQueryTextView);
            insertResultTextView.setText(bundle.getString("insertResult"));
            searchResultTextView = (TextView)findViewById(R.id.searchQueryTextView);
            searchResultTextView.setText(bundle.getString("searchResult"));
        }
    }
}
