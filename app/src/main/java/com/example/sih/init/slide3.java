package com.example.sih.init;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.example.sih.R;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class slide3 extends Fragment implements BlockingStep {
    SharedPreferences formdata;
    private EditText  mobile_no, aadharId, res_address1, res_address2,
            res_address3, res_pin;

    private EditText res_area, res_city, res_state;
    private JSONObject spinnersData;
    private TextInputLayout res_area_layout, res_city_layout, res_state_layout;
    private OkHttpClient client;
    private ArrayList<String> sList, cList, aList;
    private ArrayAdapter<String> stateadapter, cityadapter, areaadapter;
    private JSONObject cities;
    private JSONArray areas;

    public slide3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rv =  inflater.inflate(R.layout.fragment_slide3, container, false);
        formdata = this.getActivity().getSharedPreferences("formdata", MODE_PRIVATE);
        //String first_name =formdata.getString("first_name", null);
       // Log.d("test"," "+ first_name);


        client = new OkHttpClient();
        mobile_no = (EditText) rv.findViewById(R.id.mobile_no);
        aadharId = (EditText) rv.findViewById(R.id.aadhar_id);

        res_address1 = (EditText) rv.findViewById(R.id.res_address1);
        res_address2 = (EditText) rv.findViewById(R.id.res_address2);
        res_address3 = (EditText) rv.findViewById(R.id.res_address3);

        res_area =  rv.findViewById(R.id.res_area);
        res_city = rv.findViewById(R.id.res_city);
        res_state = rv.findViewById(R.id.res_state);

        res_pin = (EditText) rv.findViewById(R.id.res_pin);

        Boolean isAadhar = getActivity().getSharedPreferences("formdata", MODE_PRIVATE)
                .getBoolean("byAadhar", true);

        if (isAadhar) {
            //show start activity

            Log.d("ttttttttttttttttt", isAadhar.toString());
            String address =formdata.getString("address", null);
            String city =formdata.getString("city", null);
            String state =formdata.getString("state", null);
            String district =formdata.getString("district", null);
            String pincode =formdata.getString("pincode", null);
            res_address1.setText(address);
            res_area.setText(district);
            res_city.setText(city);
            res_state.setText(state);
            res_pin.setText(pincode);



        }


        //  populatespinners();

        return rv;
    }

   /* private void populatespinners() {
       // Toast.makeText(getActivity().getApplicationContext(), "Hey in populate spinners", Toast.LENGTH_SHORT).show();
        Request request = new Request.Builder()
                .url("http://msgt.co.in/vorat/getAreasCitesStates.php")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("onFailure: ", "Error Loading Spinners");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                Log.e("onResponse: ", res);
                try {
                    spinnersData = new JSONObject(res);
                    sList = new ArrayList<String>();


                    Iterator<?> keys = spinnersData.keys();
                    while (keys.hasNext()) {
                        String state = (String) keys.next();
                        if (spinnersData.get(state) instanceof JSONObject) {
                            sList.add(state);

                        }
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            stateadapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinner_item, sList);

                            res_state.setAdapter(stateadapter);
                            res_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    cList = new ArrayList<String>();
                                    try {
                                        Log.e("onStateSelected: ", String.valueOf(cities));
                                        cities = spinnersData.getJSONObject((String) parent.getItemAtPosition(position));
                                        Iterator<?> keys = cities.keys();
                                        while (keys.hasNext()) {
                                            String city = (String) keys.next();
                                            if (spinnersData.getJSONObject((String) parent.getItemAtPosition(position)).get(city) instanceof JSONArray) {
                                                cList.add(city);
                                            }
                                        }
                                        cityadapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinner_item, cList);
                                        res_city.setAdapter(cityadapter);
                                        res_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                aList = new ArrayList<String>();
                                                try {
                                                    areas = cities.getJSONArray((String) parent.getItemAtPosition(position));
                                                    Log.e("onCitySelected: ", "" + areas);

                                                    for (int i = 0; i < areas.length(); i++) {
                                                        aList.add(areas.getString(i));
                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                                areaadapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinner_item, aList);
                                                res_area.setAdapter(areaadapter);
                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {
                                            }
                                        });
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                }
                            });

                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }*/



        @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {
        //update UI when selected
    }

    @Override
    public void onError(@NonNull VerificationError error) {
        //handle error inside of the fragment, e.g. show error on EditText
    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {


        String mobile_s = mobile_no.getText().toString();
        String aadhar_num = aadharId.getText().toString();
        String res_addr_1s = res_address1.getText().toString();
        String res_addr_2s = res_address2.getText().toString();
        String res_addr_3s = res_address3.getText().toString();

        String pin_s,city_s, state_s, area_s;
        pin_s = res_pin.getText().toString();


        String first_name =formdata.getString("first_name", null);
        Log.d("last name"," "+ first_name);
        callback.complete();

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }
}
