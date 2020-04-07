package tech.mstava.skeera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button startScoreKeeperBtn;
    private EditText firstTeamNameInput, secondTeamNameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startScoreKeeperBtn = findViewById(R.id.start_score_keeper);
        firstTeamNameInput = findViewById(R.id.first_team_name_input);
        secondTeamNameInput = findViewById(R.id.second_team_name_input);

        startScoreKeeperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScoreKeeper();
            }
        });
    }

    private void startScoreKeeper() {
        String firstTeamName = firstTeamNameInput.getText().toString();
        String secondTeamName = secondTeamNameInput.getText().toString();

        Intent intent  = new Intent(MainActivity.this, ScoreKeeperActivity.class);

        intent.putExtra("firstTeamNameValue", firstTeamName);
        intent.putExtra("secondTeamNameValue", secondTeamName);

        startActivity(intent);
    }
}
