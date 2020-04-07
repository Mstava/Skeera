package tech.mstava.skeera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

public class ScoreKeeperActivity extends AppCompatActivity {

    private ImageButton playBtn, pauseBtn, stopBtn;
    private ImageButton addNewBtn, resetBtn, saveBtn;
    private ImageButton firstTeamGoalBtn, firstTeamFoulBtn;
    private ImageButton secondTeamGoalBtn, secondTeamFoulBtn;
    private TextView firstTeamTxt, secondTeamTxt;
    private TextView firstTeamGoalCounter, firstTeamFoulCounter;
    private TextView secondTeamGoalCounter, secondTeamFoulCounter;
    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;

    private int firstTeamGoalScore = 0;
    private int firstTeamFoulScore = 0;
    private int secondTeamGoalScore = 0;
    private int secondTeamFoulScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_keeper);

        chronometer = findViewById(R.id.chronometer);

        playBtn = findViewById(R.id.playBtn);
        pauseBtn = findViewById(R.id.pauseBtn);
        stopBtn = findViewById(R.id.stopBtn);

        addNewBtn = findViewById(R.id.add_new_btn);
        resetBtn = findViewById(R.id.reset_btn);
        saveBtn = findViewById(R.id.save_btn);

        firstTeamTxt = findViewById(R.id.first_team_text);
        firstTeamGoalBtn = findViewById(R.id.first_team_goal_btn);
        firstTeamFoulBtn = findViewById(R.id.first_team_foul_btn);
        firstTeamGoalCounter = findViewById(R.id.first_team_goal_counter);
        firstTeamFoulCounter = findViewById(R.id.first_team_foul_counter);

        secondTeamTxt = findViewById(R.id.second_team_text);
        secondTeamGoalBtn = findViewById(R.id.second_team_goal_btn);
        secondTeamFoulBtn = findViewById(R.id.second_team_foul_btn);
        secondTeamGoalCounter = findViewById(R.id.second_team_goal_counter);
        secondTeamFoulCounter = findViewById(R.id.second_team_foul_counter);

        String firstTeamNameTxt = getIntent().getExtras().getString("firstTeamNameValue");
        String secondTeamNameTxt = getIntent().getExtras().getString("secondTeamNameValue");

        firstTeamTxt.setText(firstTeamNameTxt);
        secondTeamTxt.setText(secondTeamNameTxt);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playChronometer();
            }
        });
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseChronometer();
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopChronometer();
            }
        });

        addNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewScoreKeeper();
            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetScoreKeeper();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveScoreKeeper();
            }
        });

        firstTeamGoalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayScore(firstTeamGoalScore++, firstTeamGoalCounter);
            }
        });

        firstTeamFoulBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayScore(firstTeamFoulScore++, firstTeamFoulCounter);
            }
        });

        secondTeamGoalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayScore(secondTeamGoalScore++, secondTeamGoalCounter);
            }
        });

        secondTeamFoulBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayScore(secondTeamFoulScore++, secondTeamFoulCounter);
            }
        });


    }

    private void displayScore(int score, TextView counter) {
        counter.setText(String.valueOf(score));
    }

    private void addNewScoreKeeper() {
        Intent intent  = new Intent(ScoreKeeperActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void resetScoreKeeper() {
        firstTeamGoalScore = 0;
        firstTeamFoulScore = 0;
        secondTeamGoalScore = 0;
        secondTeamFoulScore = 0;
        firstTeamGoalCounter.setText(String.valueOf(firstTeamGoalScore));
        firstTeamFoulCounter.setText(String.valueOf(firstTeamFoulScore ));
        secondTeamGoalCounter.setText(String.valueOf(secondTeamGoalScore));
        secondTeamFoulCounter.setText(String.valueOf(secondTeamFoulScore));
    }

    private void saveScoreKeeper() {
    }

    private void playChronometer() {
        if(!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }

    private void pauseChronometer() {
        if(running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

    private void stopChronometer() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }
}
