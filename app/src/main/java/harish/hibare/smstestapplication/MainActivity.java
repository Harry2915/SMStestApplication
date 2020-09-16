package harish.hibare.smstestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    private EditText editTextTo, editTextMessage;
    RelativeLayout activity_main;
    Button button;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity_main = (RelativeLayout) findViewById(R.id.activity_main);
        editTextTo = (EditText) findViewById(R.id.editTextTo);
        editTextMessage = (EditText) findViewById(R.id.editTextMessage);
        button = (Button) findViewById(R.id.btn);
        requestQueue = Volley.newRequestQueue(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone=editTextTo.getText().toString();
                String msgtext=editTextMessage.getText().toString();
                try {
                    // Construct data
                    String apiKey = "apikey=" + "+m5O/mXwn3o-GIw8CvUBTNYElEFHrAITMQ3MTX9y8M";
                    String message = "&message=" + msgtext;
                    String sender = "&sender=" + "TXTLCL";
                    String numbers = "&numbers=" + phone;

                    // Send data
                    HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
                    String data = apiKey + numbers + message + sender;
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                    conn.getOutputStream().write(data.getBytes("UTF-8"));
                    final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    final StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        stringBuffer.append(line);
                    }
                    rd.close();


                } catch (Exception e) {
                    System.out.println("Error SMS "+e);

                }
            }
        });
        StrictMode.ThreadPolicy st = new StrictMode.ThreadPolicy.Builder().build();
        StrictMode.setThreadPolicy(st);
    }}