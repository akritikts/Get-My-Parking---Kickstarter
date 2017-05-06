package silive.in.kiskstarter.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import silive.in.kiskstarter.R;
import silive.in.kiskstarter.adapters.ProjectListAdapter;
import silive.in.kiskstarter.network.HttpHandler;

public class MainActivity extends AppCompatActivity {


    Context context;
    ListView list_pro;
    EditText main_backdrop;
    public String jstr;
    ArrayList<String> name_pro = new ArrayList<>();
    ArrayList<String> backer_pro = new ArrayList<>();
    ArrayList<String> pleadage_pro = new ArrayList<>();
    ArrayList<String> days_pro = new ArrayList<>();
    ArrayList<String> fund_pro = new ArrayList<>();
    ArrayList<String> loc_pro = new ArrayList<>();
    ArrayList<String> by_pro = new ArrayList<>();
    Bundle detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        main_backdrop = (EditText)findViewById(R.id.main_backdrop);
        list_pro = (ListView)findViewById(R.id.list_pro);
        list_pro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(context,"Item click : "+position,Toast.LENGTH_SHORT).show();
                detail = new Bundle();
                detail.putString("Name",name_pro.get(position));
                detail.putString("Backer",backer_pro.get(position));
                detail.putString("Plead",pleadage_pro.get(position));
                detail.putString("Days",days_pro.get(position));
                detail.putString("Loc",loc_pro.get(position));
                detail.putString("Fund",fund_pro.get(position));
                detail.putString("By",by_pro.get(position));
                Intent intent = new Intent(context,DetailsActivity.class);
                intent.putExtra("DETAILS",detail);
                startActivity(intent);
            }
        });
        new KickStart(this).execute();
    }
    public class KickStart extends AsyncTask<Void, Void, String> {
        public Context context;
        public URL H_url;
        public HttpURLConnection H_connection;
        public BufferedReader H_bufferedReader;
        public StringBuilder H_response;
        public ProgressDialog progressDialog;

        public KickStart(Context c) {
            context = c;
            this.progressDialog = new ProgressDialog(c);
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                H_url = new URL("http://starlord.hackerearth.com/kickstarter");
                H_connection = (HttpURLConnection) H_url.openConnection();
                Log.d("TAG", "url : " + H_url);
                Log.d("TAG", "connection");
                H_connection.setRequestMethod("GET");
                H_connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                Log.d("TAG", "connection req property get");
                H_connection.connect();
                Log.d("TAG", "connection estb");
                H_bufferedReader = new BufferedReader(new InputStreamReader(H_connection.getInputStream()));
                Log.d("TAG", "buff readr");
                HttpHandler sh = new HttpHandler();
                jstr = sh.makeServiceCall("http://starlord.hackerearth.com/kickstarter");
            } catch (Exception e) {
                Log.d("TAG", "NO connection");
                e.printStackTrace();
            }
            return jstr;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Fetching Data ");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s)

        {
            // super.onPostExecute(s);

            parsing_pro(s);
            //notify data set changed;
            progressDialog.dismiss();
            ProjectListAdapter projectListAdapter = new ProjectListAdapter(context,name_pro,backer_pro,days_pro,pleadage_pro);
            list_pro.setAdapter(projectListAdapter);


        }

        public void parsing_pro(String s) {
            try {
                Log.d("TAG", "try parsing");
                JSONArray List_of_kings = new JSONArray(jstr);
                Log.d("TAG", "JSON obj created : from : "+jstr);

                Log.d("TAG", "JSON array fetched");
                for (int i = 0; i < List_of_kings.length(); i++) {
                    JSONObject k_list = List_of_kings.getJSONObject(i);
                    name_pro.add(k_list.getString("title"));
                    pleadage_pro.add(k_list.getString("amt.pledged"));
                    backer_pro.add(k_list.getString("num.backers"));
                    days_pro.add(k_list.getString("by"));
                    fund_pro.add(k_list.getString("percentage.funded"));
                    loc_pro.add(k_list.getString("location"));
                    by_pro.add(k_list.getString("by"));

                    Log.d("TAG", "Item added");

                }
            } catch (Exception e) {
                Log.d("TAG", "Printing stack trace : "+"\n\n");
                e.printStackTrace();
                Log.d("TAG", "Parsing not working");
            }

        }
    }
}

