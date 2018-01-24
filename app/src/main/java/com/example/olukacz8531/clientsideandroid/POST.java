package com.example.olukacz8531.clientsideandroid;

import android.os.AsyncTask;
import android.util.Log;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import static android.content.ContentValues.TAG;
/*
public class POST extends AsyncTask<String, String, String> {

    public POST(){
        //set context variables if required
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected String doInBackground(String... params) {

        String urlString = params[0]; // URL to call

        String data = params[1]; //data to post

        OutputStream out = null;
        try {

            URL url = new URL(urlString);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            out = new BufferedOutputStream(urlConnection.getOutputStream());

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));

            writer.write(data);

            writer.flush();

            writer.close();

            out.close();

            urlConnection.connect();


        } catch (Exception e) {

            System.out.println(e.getMessage());



        }

        return "end";

    }
}
*/
/*
public class POST extends AsyncTask<String, String, String> {

    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected String doInBackground(String... params) {


        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(params[0]);
            String data = params[1];
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();


            OutputStream stream = connection.getOutputStream();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));

            writer.write(data);

            writer.flush();

            writer.close();

            stream.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }

        }
        return null;
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

    }
}
*/
class POST extends AsyncTask<Void,Void,Void>
{

    //HttpURLConnection urlConnection;
    byte[] outputBytes;
    String query = "{\n" +
            "\t\"message\": \"Hello Android\"\n" +
            "\t\n" +
            "}";
    //String ResponseData;



    @Override
    protected Void doInBackground(Void... params) {


        URL url = null;
        try {
            url = new URL("http://10.46.16.238:8080/post");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConnection = null;

        // Send data
        try {

        /* forming th java.net.URL object */

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);

            OutputStream os = new BufferedOutputStream(urlConnection.getOutputStream());
            outputBytes = query.getBytes("UTF-8");
            os.write(outputBytes);
            os.flush();
            os.close();

            /* pass post data*/

            Log.d(TAG, urlConnection.getContentType());

            Log.d(TAG, "doInBackground: got here");


            /*BufferedOutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            writer.write(query);

            writer.flush();

            writer.close();

            out.close();

            urlConnection.disconnect();

            //urlConnection.connect();
               */




            //Get Response and execute WebService request
            //int statusCode = urlConnection.getResponseCode();
            //Log.d(TAG,"http code" + statusCode);
        /* 200 represents HTTP OK
            if (statusCode == HttpsURLConnection.HTTP_OK) {

                inputStream = new BufferedInputStream(urlConnection.getInputStream());
                ResponseData= convertStreamToString(inputStream);

            } else {

                ResponseData = null;
            }
               */

        } catch(MalformedURLException error) {
            //Handles an incorrectly entered URL
        }
        catch(SocketTimeoutException error) {
//Handles URL access timeout.
        }
        catch (IOException error) {
//Handles input and output errors
        } finally {
            if(urlConnection != null) // Make sure the connection is not null.
                urlConnection.disconnect();
        }



        return null;
    }
}

