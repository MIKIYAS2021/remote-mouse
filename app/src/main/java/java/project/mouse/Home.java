package java.project.mouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    private TextView mousePad;
    private Button left, right;
    private String serverip;
    private ToggleButton PorR;
    private SenderClient sender;
    private float initX = 0;
    private float initY = 0;
    private float disX = 0;
    private float disY = 0;
    private BottomNavigationView bottomNavigationView;
    private Intent intents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        left = findViewById(R.id.left);
        right = findViewById(R.id.right);
        mousePad = findViewById(R.id.mousePad);
        PorR = findViewById(R.id.pressOrRelease);

        //this activity extends View.OnClickListener, set this as onClickListener
        //for all buttons
        left.setOnClickListener(this);
        right.setOnClickListener(this);
        PorR.setOnClickListener(this);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        mousePad.setOnTouchListener(this);

        Intent in = getIntent();
        serverip = in.getExtras().getString("ip");

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.home);



            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.home:
                            return true;
                        case R.id.keys:
                            intents = new Intent(getApplicationContext(), Keys.class);
                            intents.putExtra("ip", serverip);
                            startActivity(intents);
                            overridePendingTransition(0, 0);
                            finish();
                            return true;
                        case R.id.power:
                            intents = new Intent(getApplicationContext(), Power.class);
                            intents.putExtra("ip", serverip);
                            startActivity(intents);
                            overridePendingTransition(0, 0);
                            finish();
                            return true;
                    }
                    return false;
                }
            });



    }
        @Override
    public void onClick(View view)
    {
        sender = new SenderClient(serverip);


        switch (view.getId())
        {
            case R.id.left:
                sender.PRINT("left");
                break;
            case R.id.right:
                sender.PRINT("right");
                break;
            case R.id.pressOrRelease:
                switch (String.valueOf(PorR.getText()))
                {
                    case "press":
                        sender.PRINT("press");
                        break;
                    case "release":
                        sender.PRINT("release");
                        break;
                }
                break;

        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //save X and Y positions when user touches the TextView
                initX = motionEvent.getX();
                initY = motionEvent.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                disX = motionEvent.getX() - initX; //Mouse movement in x direction
                disY = motionEvent.getY() - initY; //Mouse movement in y direction
                            /*set init to new position so that continuous mouse movement
                            is captured*/
                initX = motionEvent.getX();
                initY = motionEvent.getY();
                if (disX != 0 || disY != 0) {

                    sender.PRINT(disX + "," + disY); //send mouse movement to server
                }
                break;

        }
        return true;
    }
}