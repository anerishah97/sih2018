package com.example.sih.init;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.sih.R;
import com.example.sih.useless;
import com.example.sih.zxing;
import com.google.zxing.integration.android.IntentIntegrator;
import com.mobsandgeeks.saripaar.Validator;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class slide2 extends Fragment implements BlockingStep
{

    private RadioGroup rg_add_opt;

    SharedPreferences formdata;
    private TextInputLayout first_name_layout, middle_name_layout, last_name_layout, mobile_no_layout, dob_layout, aadharId_layout, bloodGroup_layout, res_address1_layout, res_address2_layout,
            res_pin_layout;

    StepperLayout mStepperLayout;
    private TextInputLayout family_layout,res_address3_layout;

    private IntentIntegrator qrScan;

    RadioGroup gender;
    RadioButton female, male;

    private EditText first_name, middle_name, last_name, dob, mobile_no, family_no, aadharId, bloodGroup, res_address1, res_address2,
            res_address3, res_pin;

    public TextView register_aadhar;
    private int mYear, mMonth, mDay;
    private Validator validator;
    public SharedPreferences.Editor editor;

    public slide2() {
        // Required empty public constructor
    }


    @Override
    public void onResume() {
        super.onResume();
        String s = useless.qrcodeS;
        editor = formdata.edit();

        editor.putString("qrcode", s);
        editor.commit();

        fillform();
      //  Log.d("aaaaaaaaaaaaaaaaaaaaaaa", s);

    }

    public void fillform() {

        String details = formdata.getString("qrcode", null);
        String aadharNum, name, gender, address, state, district, pincode, fname, mname, lname, dateOfBirth;
        Log.d("details2", "" +  details);

        if (!details.equals(" ") || details.isEmpty() || details.equals("")) {
            Log.d("indetails", "" +  details);

           aadharNum = details.split("uid=\"")[1].split("\"")[0];

            name = details.split("name=\"")[1].split("\"")[0];
            fname = name.split(" ")[0];
            mname = name.split(" ")[1];
           lname = name.split(" ")[2];
            Log.d("fname", "" +  fname);
            Log.d("mname", "" +  mname);
            Log.d("lname", "" +  lname);

             gender = details.split("gender=\"")[1].split("\"")[0];
            Log.d("gender", "" +  gender);

            address = details.split("house=\"")[1].split("\"")[0];

            address = address + "," + details.split("street=\"")[1].split("\"")[0];
            district =details.split("dist=\"")[1].split("\"")[0];
            state = details.split("state=\"")[1].split("\"")[0];
            pincode = details.split("pc=\"")[1].split("\"")[0];
            dateOfBirth = details.split("dob=\"")[1].split("\"")[0];
            Log.d("address", "" +  address);
            Log.d("district", "" +  district);
            Log.d("state", "" +  state);
            Log.d("pincode", pincode);

            editor = formdata.edit();

            editor.putString("uid", aadharNum);
            editor.commit();

            editor.putString("first_name", fname);
            editor.commit();

            editor.putBoolean("byAadhar", true);
            editor.commit();

            editor.putString("middle_name", mname);
            editor.commit();

            editor.putString("last_name", lname);
            editor.commit();

            editor.putString("dob", dateOfBirth);
            editor.commit();

            editor.putString("gender", gender);
            editor.commit();

            editor.putString("address", address);
            editor.commit();

            editor.putString("state", state);
            editor.commit();

            editor.putString("district", district);
            editor.commit();

            editor.putString("pincode", pincode);
            editor.commit();

            first_name.setText(fname);
            first_name.setEnabled(false);
            middle_name.setText(mname);
            middle_name.setEnabled(false);
            last_name.setText(lname);
            last_name.setEnabled(false);
            dob.setText(dateOfBirth);
            dob.setEnabled(false);
            if(gender.equals("F")) {
                female.setChecked(true);
                female.setEnabled(false);
                male.setEnabled(false);
            }
            else
            {
                male.setChecked(true);
                female.setEnabled(false);
                male.setEnabled(false);
            }



        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rv =  inflater.inflate(R.layout.fragment_slide2, container, false);
       // String s = useless.qrcodeS;
      //  Log.d("nnnnnnnnn", " " + s);

        gender = (RadioGroup) rv.findViewById(R.id.rg_gender);
        male = rv.findViewById(R.id.radio_m);
        female = rv.findViewById(R.id.radio_f);
        first_name_layout = (TextInputLayout) rv.findViewById(R.id.first_name_layout);
        middle_name_layout = (TextInputLayout) rv.findViewById(R.id.middle_name_layout);
        last_name_layout = (TextInputLayout) rv.findViewById(R.id.last_name_layout);
        dob_layout = (TextInputLayout) rv.findViewById(R.id.dob_layout);


        bloodGroup_layout = (TextInputLayout) rv.findViewById(R.id.bloodGroup_layout);

        first_name = (EditText) rv.findViewById(R.id.first_name);
        middle_name = (EditText) rv.findViewById(R.id.middle_name);
        last_name = (EditText) rv.findViewById(R.id.last_name);
        dob = (EditText) rv.findViewById(R.id.dob);

        bloodGroup = (EditText) rv.findViewById(R.id.bloodGroup);
        family_no = (EditText) rv.findViewById(R.id.family_no);
        register_aadhar = rv.findViewById(R.id.register_with_picture);


        register_aadhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  qrScan.initiateScan();
                startActivity(new Intent(getActivity(), zxing.class));

            }
        });

       // SharedPreferences Sh;
       formdata = getActivity().getPreferences(Context.MODE_PRIVATE);
     /*  String s = formdata.getString("barcode", null);

        Log.d("qrscaner", " " + s);*/

        {
            //Date Picker

            final Button pickDate = (Button) rv.findViewById(R.id.pick_date);
            final Calendar myCalendar = Calendar.getInstance();
            final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    // myCalendar.add(Calendar.DATE, 0);
                    String myFormat = "yyyy-MM-dd"; //In which you need put here
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                    dob.setText(sdf.format(myCalendar.getTime()));
                }


            };

            pickDate.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);

                    // Launch Date Picker Dialog
                    DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    // Display Selected date in textbox

                                    if (year < mYear)
                                        view.updateDate(mYear, mMonth, mDay);

                                    if (monthOfYear < mMonth && year == mYear)
                                        view.updateDate(mYear, mMonth, mDay);

                                    if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                                        view.updateDate(mYear, mMonth, mDay);

                                    dob.setText(dayOfMonth + "/"
                                            + (monthOfYear + 1) + "/" + year);

                                }
                            }, mYear, mMonth, mDay);
                    //dpd.getDatePicker().setMinDate(System.currentTimeMillis());
                    dpd.show();
                }
            });
        }


        return rv;
    }

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
        String family_s = family_no.getText().toString();
                String first_name_s =  first_name.getText().toString();
                String middle_name_s =  middle_name.getText().toString();
                String last_name_s  = last_name.getText().toString();
                String dob_s = dob.getText().toString();
                String gender_S =((RadioButton)getView().findViewById(gender.getCheckedRadioButtonId())).getText().toString();

                String blood_group_s = bloodGroup.getText().toString();
               // Log.d (family_s + " " + first_name_s, middle_name_s + " " + dob_s + " " + gender_S + " " + blood_group_s);
        editor = formdata.edit();

        editor.putString("first_name", first_name_s);
        editor.commit();

        editor.putString("family_no", family_s);
        editor.commit();

        editor.putString("middle_name", middle_name_s);
        editor.commit();

        editor.putString("last_name", last_name_s);
        editor.commit();

        editor.putString("dob", dob_s);
        editor.commit();

        editor.putString("gender", gender_S);
        editor.commit();

        String qrcode =formdata.getString("qrcode", null);
        Log.d("test222"," lets see "+ qrcode);

        callback.goToNextStep();
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {

    }

}
