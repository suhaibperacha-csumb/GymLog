package com.example.gymlog;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymlog.database.entities.GymLog;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private GymLogViewModel viewModel;
    private EditText exerciseInput, weightInput, repsInput;
    private Button logButton;
    private GymLogAdapter adapter;
    private int loggedInUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        loggedInUserId = intent.getIntExtra("USER_ID", -1);
        if (loggedInUserId == -1) {
            finish();
            return;
        }

        exerciseInput = findViewById(R.id.exercise_input);
        weightInput = findViewById(R.id.weight_input);
        repsInput = findViewById(R.id.reps_input);
        logButton = findViewById(R.id.log_button);

        RecyclerView recyclerView = findViewById(R.id.log_display);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GymLogAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(GymLogViewModel.class);
        viewModel.getLogsForUser(loggedInUserId).observe(this, adapter::setGymLogs);

        logButton.setOnClickListener(v -> {
            String name = exerciseInput.getText().toString().trim();
            String weightStr = weightInput.getText().toString().trim();
            String repsStr = repsInput.getText().toString().trim();

            if (!name.isEmpty() && !weightStr.isEmpty() && !repsStr.isEmpty()) {
                double weight = Double.parseDouble(weightStr);
                int reps = Integer.parseInt(repsStr);
                GymLog log = new GymLog(name, weight, reps, new Date(), loggedInUserId);
                viewModel.insert(log);
                exerciseInput.setText("");
                weightInput.setText("");
                repsInput.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            new AlertDialog.Builder(this)
                    .setTitle("Logout")
                    .setMessage("Are you sure you want to logout?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        startActivity(new Intent(this, LoginActivity.class));
                        finish();
                    })
                    .setNegativeButton("No", null)
                    .show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
