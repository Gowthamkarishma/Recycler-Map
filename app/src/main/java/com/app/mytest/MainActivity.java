package com.app.mytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Adapter.onSubmitListener {

    private Activity getActivity;

    private RecyclerView recyclerView;
    ArrayList<PojoClass> pojoList = new ArrayList<>();
    ArrayList<PojoClass> bundlepojoList = new ArrayList<>();

    private Adapter recyclerAdapter;

    private JSONArray jsonArray;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivity = this;

        try {
            jsonArray = new JSONArray(loadJSONFromAssetCountry());
            for (int i = 0; i < jsonArray.length(); i++) {

                pojoList.add(new PojoClass(jsonArray.getJSONObject(i).getString("name"),
                        jsonArray.getJSONObject(i).getString("location_image")
                        , jsonArray.getJSONObject(i).getDouble("latitude"),
                        jsonArray.getJSONObject(i).getDouble("longitude"), false));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < pojoList.size(); i++) {
                    if (pojoList.get(i).isaBoolean()){

                        bundlepojoList.add(pojoList.get(i));
                        Log.e("AAAAAAAAAA", pojoList.get(i).getLat() + " <---> " + pojoList.get(i).getLng());
                    }
                }

                Intent intent = new Intent(getActivity, MapsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("value", bundlepojoList);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerAdapter = new Adapter(getActivity, pojoList, this);
        recyclerView.setAdapter(recyclerAdapter);

        LinearLayoutManager llManager = new LinearLayoutManager(getActivity);
        llManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llManager);
    }

    public String loadJSONFromAssetCountry() {
        String json = null;
        try {
            InputStream is = getActivity.getAssets().open("Address.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public void setOnSubmitListener(int pos, boolean outid) {
        Log.e("setOnSubmitListener", pos + " <---> " + outid);
        pojoList.get(pos).setaBoolean(outid);

    }
}
