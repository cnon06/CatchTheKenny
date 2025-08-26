package cnon.software.catchthekenny;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView scoreText;
    TextView timeText;
    int score = 0;

    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        scoreText = (TextView) findViewById(R.id.scoreTextView2);
        timeText = (TextView) findViewById(R.id.timeTextView);
        scoreText.setText("Score: " + score);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView4 = (ImageView) findViewById(R.id.imageView4);
        imageView5 = (ImageView) findViewById(R.id.imageView5);
        imageView6 = (ImageView) findViewById(R.id.imageView6);
        imageView7 = (ImageView) findViewById(R.id.imageView7);
        imageView8 = (ImageView) findViewById(R.id.imageView8);
        imageView9 = (ImageView) findViewById(R.id.imageView9);
        imageArray = new ImageView[] {imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9};

        hideImages();

        new CountDownTimer(10000,1000)
        {
            public void onTick(long millisUntilFinished)
            {
             timeText.setText("Time: " + millisUntilFinished / 1000);
            }
            public void onFinish()
            {
                timeText.setText("Time Off");
                handler.removeCallbacks(runnable);

                 for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }


                 AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                 alert.setTitle("Restart?");
                 alert.setMessage("Are you sure to restart game?");
                 alert.setPositiveButton("Yes", (dialog, which) -> {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                 }).setNegativeButton("No",  (dialog, which) -> {
                     Toast.makeText(MainActivity.this, "Game Over!", Toast.LENGTH_SHORT).show();
                 });
                 alert.show();

            }
        }.start();
    }

    public void increaseScore(View view)
    {

       // score = Integer.parseInt(scoreText.getText().toString());
        score++;
        scoreText.setText("Score: " + score);
    }

    public void hideImages()
    {
        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);
            }
        };
        handler.post(runnable);
     //   handler.postDelayed(runnable,500);

    }
}