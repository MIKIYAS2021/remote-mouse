package java.project.mouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Keys extends AppCompatActivity implements View.OnClickListener {

    private BottomNavigationView bottomNavigationView;
    private Button copy,paste,redo,undo,cut,win;
    private Button pg_up,pg_down,home,end,delete,prt_sc,esc,enter,space;
    private ImageButton uparrow,downarrow,rightarrow,leftarrow;
    private Intent intents;
    private String serverip;
    private SenderClient sender;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keys);

        pg_up=findViewById(R.id.pg_up);
        pg_down=findViewById(R.id.pg_dn);
        home=findViewById(R.id.pghome);
        end=findViewById(R.id.end);
        delete=findViewById(R.id.delete);
        prt_sc=findViewById(R.id.prt_sc);
        esc=findViewById(R.id.esc);
        enter=findViewById(R.id.enter);
        space=findViewById(R.id.space);

        uparrow=findViewById(R.id.upbut);
        downarrow=findViewById(R.id.downbut);
        rightarrow=findViewById(R.id.rightbut);
        leftarrow=findViewById(R.id.leftbut);

        pg_up.setOnClickListener(this);
        pg_down.setOnClickListener(this);
        home.setOnClickListener(this);
        end.setOnClickListener(this);
        delete.setOnClickListener(this);
        prt_sc.setOnClickListener(this);
        esc.setOnClickListener(this);
        enter.setOnClickListener(this);
        space.setOnClickListener(this);
        uparrow.setOnClickListener(this);
        downarrow.setOnClickListener(this);
        rightarrow.setOnClickListener(this);
        leftarrow.setOnClickListener(this);

        win=findViewById(R.id.win);
        undo=findViewById(R.id.undo);
        redo=findViewById(R.id.redo);
        cut=findViewById(R.id.copy);
        paste=findViewById(R.id.paste);
        copy=findViewById(R.id.cut);

        undo.setOnClickListener(this);
        redo.setOnClickListener(this);
        cut.setOnClickListener(this);
        copy.setOnClickListener(this);
        paste.setOnClickListener(this);
        win.setOnClickListener(this);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.keys);

        Intent in = getIntent();
        serverip = in.getExtras().getString("ip");

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
                        return true;
                    case R.id.power:
                        intents = new Intent(getApplicationContext(), Power.class);
                        intents.putExtra("ip",serverip);
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
        switch(view.getId())
        {
            case R.id.copy:
                sender.PRINT("copy");
                break;
            case R.id.paste:
                sender.PRINT("paste");
                break;
            case R.id.cut:
                sender.PRINT("cut");
                break;
            case R.id.undo:
                sender.PRINT("undo");
                break;
            case R.id.redo:
                sender.PRINT("redo");
                break;
            case R.id.pg_up:
                sender.PRINT("pgup");
                break;
            case R.id.pg_dn:
                sender.PRINT("pgdn");
                break;
            case R.id.pghome:
                sender.PRINT("home");
                break;
            case R.id.end:
                sender.PRINT("end");
                break;
            case R.id.delete:
                sender.PRINT("delete");
                break;
            case R.id.prt_sc:
                sender.PRINT("prtsc");
                break;
            case R.id.esc:
                sender.PRINT("esc");
                break;
            case R.id.enter:
                sender.PRINT("enter");
                break;
            case R.id.space:
                sender.PRINT("space");
                break;
            case R.id.upbut:
                sender.PRINT("uparrow");
                break;
            case R.id.downbut:
                sender.PRINT("downarrow");
                break;
            case R.id.rightbut:
                sender.PRINT("rightarrow");
                break;
            case R.id.leftbut:
                sender.PRINT("leftarrow");
                break;
            case R.id.win:
                sender.PRINT("window");
                break;
        }

    }
}