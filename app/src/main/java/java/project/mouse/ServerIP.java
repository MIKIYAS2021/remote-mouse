package java.project.mouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ServerIP extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_ip);

        Button button = findViewById(R.id.sendbutton);
        button.setOnClickListener(this);
    }
    public void openMainActivity()
    {
        EditText edit = findViewById(R.id.serverIP);
        String ip = edit.getText().toString();
        Intent intent = new Intent(this, Home.class);
        intent.putExtra("ip",ip);
        startActivity(intent);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.sendbutton:
                openMainActivity();
        }

    }
}