package java.project.mouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Power extends AppCompatActivity implements View.OnClickListener {

    private BottomNavigationView bottomNavigationView;
    private Intent intents;
    private String serverip;
    private Button shutdown,restart,sleep;
    private SenderClient sender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.power);

        restart=findViewById(R.id.restart);
        sleep=findViewById(R.id.sleep);
        shutdown=findViewById(R.id.shutdown);

        shutdown.setOnClickListener(this);
        sleep.setOnClickListener(this);
        restart.setOnClickListener(this);

        Intent in = getIntent();
        serverip = in.getExtras().getString("ip");
        Toast.makeText(this, "ip is "+serverip, Toast.LENGTH_SHORT).show();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        intents = new Intent(getApplicationContext(), Home.class);
                        intents.putExtra("ip",serverip);
                        startActivity(intents);
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.keys:
                        intents = new Intent(getApplicationContext(), Keys.class);
                        intents.putExtra("ip",serverip);
                        startActivity(intents);
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.power:
                        return true;
                }
                return false;
            }
        });
    }
    @Override
    public void onClick(View view) {
        sender = new SenderClient(serverip);
        switch (view.getId())
        {
            case R.id.shutdown:
                sender.PRINT("shutdown");
                break;
            case R.id.sleep:
                sender.PRINT("sleep");
                 break;
            case R.id.restart:
                sender.PRINT("restart");
                break;

        }
    }
}