package com.example.kamal.incomingcallspecial;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

public class RemarkActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSend;
    private EditText remarkText;
    private String remark;
    private String phone_number;
    private String currentDateandTime;

    public static final String PREFS_NAME = "MyPrefsFile";

    SqliteHelper myDb;
    private Button buttonShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle b = getIntent().getExtras();
        phone_number = (String) b.getString("phone_number");
        currentDateandTime = (String) b.getString("call_date");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remark);

        if (CheckInternet()) {
            remarkText = (EditText) findViewById(R.id.remark);
            buttonSend = (Button) findViewById(R.id.send);
            buttonSend.setOnClickListener(RemarkActivity.this);

        } else {

            myDb = new SqliteHelper(this);
            remarkText = (EditText) findViewById(R.id.remark);
            buttonSend = (Button) findViewById(R.id.send);
            buttonShow = (Button) findViewById(R.id.show);
            AddData();
            viewAll();
        }

    }

    public void AddData(){
        buttonSend.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        remark = remarkText.getText().toString();
                        boolean invalid = false;
                        if (remark.equals("")) {
                            invalid = true;
                            Toast.makeText(getApplicationContext(), "Please Enter your Remark", Toast.LENGTH_SHORT).show();
                        }else{
                            boolean isinserted = myDb.insertLead(phone_number, currentDateandTime, remark);
                            if (isinserted = true)
                                Toast.makeText(RemarkActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(RemarkActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void viewAll(){
        buttonShow.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       Cursor res =myDb.selectRecords();
                        if(res.getCount() == 0) {
                            showMessage("Error","Nothing Found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("Id :"+res.getString(0)+"\n");
                            buffer.append("Phone_number :"+res.getString(1)+"\n");
                            buffer.append("Call_date :"+res.getString(2)+"\n");
                            buffer.append("Remark :"+res.getString(3)+"\n\n");
                        }

//                        String bufferstring = buffer.toString();
//                        Intent intent = new Intent(RemarkActivity.this, Display.class);
//                        intent.putExtra("bufferstring", bufferstring);
//                        startActivity(intent);

                       showMessage("Data",buffer.toString());

                    }
                }
        );
    }
    public  void showMessage (String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

        @Override
        public void onClick(View v) {
            postRemark();
        }

        private void postRemark() {
            remark = remarkText.getText().toString();

            boolean invalid = false;
            if (remark.equals("")) {
                invalid = true;
                Toast.makeText(getApplicationContext(), "Please Enter your Remark", Toast.LENGTH_SHORT).show();
            }else {

                try {
                    makeHTTPPOSTRequest();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (HttpException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(RemarkActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }, 500);
            }
        }

    private boolean CheckInternet() {
            ConnectivityManager connec = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
            android.net.NetworkInfo wifi = connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (wifi.isConnected()) {
                return true;
            } else if (mobile.isConnected()) {
                return true;
            }
            return false;
    }

    private void makeHTTPPOSTRequest() throws URISyntaxException, IOException, HttpException {
        RequestParams params = new RequestParams();
        params.put("phone_number", phone_number);
        params.put("call_date", currentDateandTime);
        params.put("remark", remark);
        AsyncHttpClient client = new AsyncHttpClient();
        RequestHandle post = client.post("http://kamal.stag.webnish.com/users.json", params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {

            }
        });
    }
}
