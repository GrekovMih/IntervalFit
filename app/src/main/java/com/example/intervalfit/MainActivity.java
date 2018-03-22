package com.example.intervalfit;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.CountDownTimer;


public class MainActivity extends AppCompatActivity {

    private int mTimeToGo, round, timeFight=0,timeRelax,countRound;
    private String status="Бой";

    MediaPlayer mPlayer;
    private CountDownTimer mCountDownTimer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayer=MediaPlayer.create(this, R.raw.gong);

    }



    public void start (View view){


        if(timeFight == 0) {

            EditText editText = (EditText) findViewById(R.id.timer);
            timeFight = Integer.parseInt(editText.getText().toString());
            mTimeToGo = timeFight;

            EditText editText2 = (EditText) findViewById(R.id.timeRelaxid);
            timeRelax = Integer.parseInt(editText2.getText().toString());

            TextView timer = (TextView) findViewById(R.id.timeRelaxid);
            timer.setText("" + status);

            TextView statusid = (TextView) findViewById(R.id.statusid);
            View q = findViewById(R.id.statusid);
            q.setVisibility(View.GONE);


            TextView countroundid = (TextView) findViewById(R.id.countroundid);
            countroundid.setText("Осталось раундов");

            TextView timeid = (TextView) findViewById(R.id.timeid);
            timeid.setText("Осталось времени");


            EditText editText3 = (EditText) findViewById(R.id.roundid);
            countRound = round = Integer.parseInt(editText3.getText().toString());

        }


        View s = findViewById(R.id.start);
        s.setVisibility(View.GONE);

        View p = findViewById(R.id.pause);
        p.setVisibility(View.VISIBLE);


        mCountDownTimer  = new CountDownTimer(timeFight * timeRelax * round * 1000, 1000) {
            public void onTick(long millisUntilFinished) {

                if (round > 0){

                    mTimeToGo -= 1;
                    TextView timer = (TextView)findViewById(R.id.timer);
                    timer.setText(""+mTimeToGo);
                    //  if (round > 0)

                    if (mTimeToGo == 0){
                        alertFinish();
             //           vibrator.cancel();

                        if ("Бой".equals(status)) {
                            status = "отдых";
                            mTimeToGo = timeRelax;
                            round--;

                            TextView roundid = (TextView)findViewById(R.id.roundid);
                            roundid.setText(""+round);

                        }else {
                            mTimeToGo = timeFight;
                            status = "Бой";
                        }

                        TextView timeRelaxid = (TextView)findViewById(R.id.timeRelaxid);
                        timeRelaxid.setText(""+status);

                    }

                }




            }



            public void onFinish() {


                replayLayout();

                alertFinish();
              //  vibrator.cancel();

            }
        }.start();




    }


    public void replayLayout (){


        TextView timer = (TextView)findViewById(R.id.timer);
        timer.setText(""+timeFight);


        TextView timeRelaxid = (TextView)findViewById(R.id.timeRelaxid);
        timeRelaxid.setText(""+timeRelax);

        TextView roundid = (TextView)findViewById(R.id.roundid);
        roundid.setText(""+countRound);


    }


    public void Stop (View view){

        mCountDownTimer.cancel();

        View s = findViewById(R.id.pause);
        s.setVisibility(View.GONE);

        View p = findViewById(R.id.start);
        p.setVisibility(View.VISIBLE);

        replayLayout();
        timeFight=0;

        View q = findViewById(R.id.statusid);
        q.setVisibility(View.VISIBLE);


    }

    public void pause (View view){

        mCountDownTimer.cancel();

        View s = findViewById(R.id.pause);
        s.setVisibility(View.GONE);

        View p = findViewById(R.id.start);
        p.setVisibility(View.VISIBLE);

    }



    public void alertFinish (){


        mPlayer.start();

        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        long mills = 2000L;
        vibrator.vibrate(mills);

      //  vibrator.cancel();


    }


}
