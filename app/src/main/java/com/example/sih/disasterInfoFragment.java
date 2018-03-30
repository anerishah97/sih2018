package com.example.sih;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class disasterInfoFragment extends Fragment {

    public CardView disaster_info, first_aid, emergency_contact_c, safety_kit;
    private OnFragmentInteractionListener listener;


    public static disasterInfoFragment newInstance() {
        return new disasterInfoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_disaster_info, container, false);
        disaster_info = (CardView)rootview.findViewById(R.id.disaster_safety_tips);
        first_aid = rootview.findViewById(R.id.first_aid_kits);
        emergency_contact_c= rootview.findViewById(R.id.emergency_contacts);
        safety_kit = rootview.findViewById(R.id.disaster_kit);

        safety_kit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),FirstAidKit.class);
                getContext().startActivity(intent);
            }
        });

        emergency_contact_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),emergency_contact.class);
                getContext().startActivity(intent);
            }
        });

        first_aid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),FirstAid.class);
                getContext().startActivity(intent);            }
        });

        disaster_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return rootview;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnFragmentInteractionListener {
    }
}
