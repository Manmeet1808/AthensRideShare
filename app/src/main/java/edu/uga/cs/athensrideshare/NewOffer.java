package edu.uga.cs.athensrideshare;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class NewOffer extends AppCompatActivity {

    public static final String DEBUG_TAG = "NewOffer";

    private EditText nameText;
    private EditText ageText;
    private EditText numberText;
    private EditText startText;
    private EditText destinationText;
    private EditText timeText;
    private EditText Date;
    private EditText seatsText;
    private EditText socialText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_offer);

        nameText = findViewById( R.id.nameText);
        ageText = findViewById( R.id.ageText );
        numberText = findViewById( R.id.phoneText );
        startText = findViewById( R.id.meetingText );
        destinationText = findViewById( R.id.destinationText );
        Date = findViewById( R.id.dateText );
        timeText = findViewById(R.id.timeText);
        seatsText = findViewById(R.id.seatsText);
        socialText = findViewById(R.id.socialText);



        Button saveButton = findViewById(R.id.button);

        saveButton.setOnClickListener( new ButtonClickListener()) ;
    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String name = nameText.getText().toString();
            String age = ageText.getText().toString();
            String num = numberText.getText().toString();
            String start = startText.getText().toString();
            String dest = destinationText.getText().toString();
            String date = Date.getText().toString();
            String time = timeText.getText().toString();
            String seat = seatsText.getText().toString();
            String social = socialText.getText().toString();
            final Offer offer = new Offer(name, age, num, start, dest, date, time, seat, social );

            // Add a new element (JobLead) to the list of job leads in Firebase.
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("jobleads");

            // First, a call to push() appends a new node to the existing list (one is created
            // if this is done for the first time).  Then, we set the value in the newly created
            // list node to store the new job lead.
            // This listener will be invoked asynchronously, as no need for an AsyncTask, as in
            // the previous apps to maintain job leads.
            myRef.push().setValue( offer )
                    .addOnSuccessListener( new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Show a quick confirmation
                            Toast.makeText(getApplicationContext(), "Offer created for " + offer.getName(),
                                    Toast.LENGTH_SHORT).show();

                            // Clear the EditTexts for next use.
                            nameText.setText("");
                            ageText.setText("");
                            numberText.setText("");
                            startText.setText("");
                            destinationText.setText("");
                            Date.setText("");
                            timeText.setText("");
                            seatsText.setText("");
                            socialText.setText("");
                        }
                    })
                    .addOnFailureListener( new OnFailureListener() {
                        @Override
                        public void onFailure( @NonNull Exception e ) {
                            Toast.makeText( getApplicationContext(), "Failed to create a Job lead for " + offer.getName(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    @Override
    protected void onResume() {
        Log.d( DEBUG_TAG, "NewOffer.onResume()" );
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d( DEBUG_TAG, "NewOffer.onPause()" );
        super.onPause();
    }


}
