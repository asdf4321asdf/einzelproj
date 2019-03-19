package com.example.myapplication;

import android.app.DialogFragment;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private class SendTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            TCPHelper tcpHelper = new TCPHelper(strings[0], getString(R.string.hostname), 53212);
            String result;
            try {
                result = tcpHelper.sendAndRetrieve();
            } catch (IOException e) {
                e.printStackTrace();
                result = getString(R.string.network_error);
            }
            return result;
        }

        @Override
        protected void onPostExecute(String resultMessage) {
            DialogFragment f = ShowResultFragment.newInstance(resultMessage);
            f.show(getFragmentManager(), "dialog");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view) {
        EditText editText = findViewById(R.id.editText);
        String message = editText.getText().toString();
        new SendTask().execute(message);
    }

    public void calculate(View view) {
        EditText editText = findViewById(R.id.editText);
        String message = editText.getText().toString();

        /* calculation */
        long digitSum = message.chars().mapToLong(c -> c-'0').sum();
        String digitSumBinary = Long.toBinaryString(digitSum);

        DialogFragment f = ShowResultFragment.newInstance(digitSumBinary);
        f.show(getFragmentManager(), "dialog");
    }
}
