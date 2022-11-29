package edu.uga.cs.athensrideshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class homeScreen extends AppCompatActivity {

    public static final String TAG = "SigningIn";

    private FirebaseAuth mAuth;
    private Button logoutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen_layout);

        //find button
        logoutButton = findViewById(R.id.logoutButton);

        //make listeners
        logoutButton.setOnClickListener(new LogOutClickListener());

    }

    /**
     * This class defines the onClickListener class for the logout button.
     * Signs the current user out and returns to MainActivity.
     */
    private class LogOutClickListener implements View.OnClickListener {

        /**
         * Signs out the current user and returns to the MainActivity login/register screen.
         *
         * @param v the button view that has called the method
         */
        @Override
        public void onClick(View v) {
            Log.d(TAG, "Logged out");
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            v.getContext().startActivity(intent);
        }
    }





}