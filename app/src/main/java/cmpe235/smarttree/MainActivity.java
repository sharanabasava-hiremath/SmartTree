package cmpe235.smarttree;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by hiremath on 9/19/2017.
 */

public class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Nearby = (Button) findViewById(R.id.Nearby);
        Button About=(Button) findViewById(R.id.About);
        Button Comment=(Button) findViewById(R.id.Comment);
        Button photo_video=(Button)findViewById(R.id.photo_video);
        Button Interact=(Button)findViewById(R.id.Interact);
        Nearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);



            }
        });
        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(i);
            }
        });
        Comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ReviewComments.class);
                startActivity(i);
            }
        });

        Interact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CommentActivity.class);
                startActivity(i);
            }
        });


        photo_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(i);
            }
        });

        Button btnLogout = (Button) findViewById(R.id.button_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go back to home screen
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button btnExit = (Button) findViewById(R.id.button_exit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go back to home screen
                //android.os.Process.killProcess(android.os.Process.myPid());
                finish();
                System.exit(0);
            }
        });

    }
    }


