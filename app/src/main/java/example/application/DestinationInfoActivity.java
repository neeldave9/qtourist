package example.application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import example.data.StaticUserDAO;
import example.data.User;

public class DestinationInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_information);

        // Get the text for the marker from the intent
        Intent intent = getIntent();
        String markerText = intent.getStringExtra("marker_text");

        // Set the text in a TextView
        TextView textView = findViewById(R.id.marker_text_view);
        textView.setText(markerText);
    }
}
