package com.hackingbuzz.jsonexample1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    // this program will end up the NetworkThread Exception.. android doesn't allow you to do Network calls on UI Thread..

    TextView textView;
    Button btn;
    HttpURLConnection connection;

    //we doing this on UI thead..(see OnCreate) no async task..


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {    // when we click on a button...do all of this.. set the output to the textView


                BufferedReader bufferedReader = null;                                                        // gotta initilize bufferReader....why coz..if connection is initilize..n even its null(empty)..n u have to put in ..u get data in it..

                try {
                    URL url = new URL("http://jsonparsing.parseapp.com/jsonData/moviesDemoItem.txt");
                    connection = (HttpURLConnection)url.openConnection();     //open                         //opening browser n putting url..n pressed click..
                    connection.connect();                                       //connect                    // now with this command we are connecting..

                    InputStream inputStream = connection.getInputStream();                                // now we need input stream to get read data comming from server..n getting InputStream n connecting to our connection to server ( connection.getInputStream)

                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));                     // so data comming in input stream..is in byte form (InputStreamReader -- read character from Byte ....BufferederReader --- then read text from character input )
                    String textual = "";
                    StringBuffer sb = new StringBuffer();

                    while((textual = bufferedReader.readLine()) !=null) {
                        sb.append(textual);
                    }

                    textView.setText(textual);


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {     // kuch bhi ho jaye ye call hota hi hota hai..
                    if( connection != null) {                                                             // obviously if connect is null (empty ) how could u disconnect it..
                        connection.disconnect();  } // close
                    try {
                        if (bufferedReader != null) {
                            bufferedReader.close();      }      // if no data in it..means we didnt get data in starting so,,no data no close                                                   // it gives IO Exception...if data that is comming is not in Character from..
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });






    }
}
// something is not initlize...u dont have to close it....
