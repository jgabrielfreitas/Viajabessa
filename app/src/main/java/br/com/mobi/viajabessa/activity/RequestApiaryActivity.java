package br.com.mobi.viajabessa.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import br.com.mobi.viajabessa.R;
import br.com.mobi.viajabessa.marketing.PhoneInformations;

public class RequestApiaryActivity extends ActionBarActivity {

    private static final String URL = "http://private-a7fa7-viajabessa14.apiary-mock.com/marketing";
    PhoneInformations userPhone;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_apiary);

        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        userPhone = new PhoneInformations();

        HttpAsyncTask request = new HttpAsyncTask();
        request.execute();

    }

    private class HttpAsyncTask extends AsyncTask <String, Void, String> {

        protected String doInBackground(String... url) {
            String data;

            try {

                String urlRequest = (URL + userPhone).replace(" ", "%20");
                Log.d("URL_REQUEST", urlRequest);

                HttpGet get = new HttpGet(urlRequest);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(get);

                data = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);

                Log.i("RESPONSE_JSON", data);

                onPostExecute(data);

            } catch (Exception e) {
                e.printStackTrace();
                onPostExecute(null);
                return null;
            }

            return  data;
        }

        protected void onPostExecute(String result) {
            if(result != null) {
                Intent intent = new Intent(RequestApiaryActivity.this, PackageListActivity.class);
                intent.putExtra("jsonResponse", result);
                startActivity(intent);
                RequestApiaryActivity.this.finish();
            } else {
                Toast.makeText(RequestApiaryActivity.this, getString(R.string.check_connection), Toast.LENGTH_LONG).show();
                RequestApiaryActivity.this.finish();
            }
        }

    }
}
