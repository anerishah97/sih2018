package com.example.sih.init;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.sih.MainActivity;
import com.example.sih.R;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class introduction extends AppCompatActivity implements StepperLayout.StepperListener{
    private StepperLayout mStepperLayout;
    public SharedPreferences pref;
    public static int z = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_introduction);
        //  pref = getSharedPreferences("UserData", MODE_PRIVATE);

        mStepperLayout = (StepperLayout) findViewById(R.id.stepperLayout);
        mStepperLayout.setAdapter(new MyStepperAdapter(getSupportFragmentManager(), this));
        mStepperLayout.setListener(this);
    }

    @Override
    public void onCompleted(View completeButton) {
       // Toast.makeText(this, "onCompleted!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public void onError(VerificationError verificationError) {

    }

    @Override
    public void onStepSelected(int newStepPosition) {

    }

    @Override
    public void onReturn() {

    }

    public static class MyStepperAdapter extends AbstractFragmentStepAdapter {



        public MyStepperAdapter(FragmentManager fm, Context context) {
            super(fm, context);
        }

        @Override
        public Step createStep(int position) {
            final slide1 step1 = new slide1();
            final slide2 step2 = new slide2();
            final slide3 step3 = new slide3();
         /*  if(i.z == 1)

            {
                Log.d("in intro step 2", "test");
                return step2;}*/

           // final slide4 step4 = new slide4();
            switch (position){
                case 0:
                    return step1;
                case 1:
                    return step2;
                case 2:
                    return step3;
             /*   case 3:
                    return step4;*/
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
        @NonNull
        @Override
        public StepViewModel getViewModel(@IntRange(from = 0) int position) {
            StepViewModel.Builder builder = new StepViewModel.Builder(context);
            switch (position) {
                case 0:
                    builder
                            .setNextButtonLabel("Next")
                            .setBackButtonLabel("Skip")
                            .setBackButtonStartDrawableResId(StepViewModel.NULL_DRAWABLE);
                    break;
                case 1:
                    builder
                            .setNextButtonLabel("Next")
                            .setBackButtonStartDrawableResId(R.drawable.ms_ic_chevron_left);
                    break;
                case 2:
                    builder.setBackButtonLabel("");
                  //  .setBackButtonStartDrawableResId(R.drawable.ms_ic_chevron_left);
                    break;
               /* case 3:
                    builder.setBackButtonLabel("");
                    break;*/
                default:
                    throw new IllegalArgumentException("Unsupported position: " + position);
            }
            return builder.create();
        }


    }
}
