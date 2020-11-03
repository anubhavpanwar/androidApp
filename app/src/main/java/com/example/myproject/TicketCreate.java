package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class TicketCreate extends AppCompatActivity {




    public void btn_createSubmit(View view) throws JSONException {
        EditText problem = (EditText) findViewById(R.id.ProblemType);
        String problemType = problem.getText().toString();

        EditText desc = (EditText) findViewById(R.id.Description);
        String description = problem.getText().toString();

//        RequestQueue requestQueue;
//        requestQueue = Volley.newRequestQueue(this);

//        {"Ticket":{"Title":"Security Threat", "Type": "Incident::Major", "Queue":"network","State":"open",
//                "Priority":"5 very high","CustomerUser":"ap"},
//            "Article":{"Subject":"Security Threat","Body":"There is a security related issue",
//                "ContentType":"text/plain; charset=utf8"}}

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final String url ="https://0f34d6e3c739.ngrok.io/otrs/nph-genericinterface.pl/Webservice/GenericTicketConnectorREST/Ticket?UserLogin=network&Password=root";


        JSONObject jsonCreateTicket = new JSONObject();

        JSONObject jsonTicket = new JSONObject();

        JSONObject jsonArticle = new JSONObject();
        try {

            jsonTicket.put("Title", "Security Threat");
            jsonTicket.put("Type", "Incident");
            jsonTicket.put("Queue", "network");
            jsonTicket.put("State", "open");
            jsonTicket.put("Priority", "5 very high");
            jsonTicket.put("CustomerUser", "ap");

            jsonArticle.put("Subject", "Android Ticket");
            jsonArticle.put("Body", "Android app");
            jsonArticle.put("ContentType", "text/plain");
            jsonArticle.put("charset", "utf8");


        } catch (JSONException e) {
            e.printStackTrace();
        }


        jsonCreateTicket.put("Ticket", jsonTicket);
        jsonCreateTicket.put("Article", jsonArticle);


        final String requestBody = jsonCreateTicket.toString();




        Map<String,Object> params = new HashMap<String, Object>();
        params.put("MobileNumber", "+97333765439");
        params.put("EmailAddress", "danish.hussain4@das.com");
        params.put("FirstName", "Danish2");
        params.put("LastName", "Hussain2");
        params.put("Country", "BH");
        params.put("Language", "EN");
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(requestBody),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));
                            Log.d("myapp", "The response is " + response.getJSONObject("TicketID"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                Log.d("myapp", "Something went wrong" + error);
            }


        });
        requestQueue.add(req);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_create);
        getSupportActionBar().setTitle("Create Ticket");
    }

}