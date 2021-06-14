package cm.btech2021.houser.activities;

import static cm.btech2021.houser.adapters.HouseAdapter.EXTRA_HOUSE_MODEL;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cm.btech2021.houser.R;
import cm.btech2021.houser.models.HouseModel;

public class DetailsActivity extends AppCompatActivity {
    ImageView imageView;
    TextView txtTitle;
    TextView txtAddress;
    HouseModel houseModel;
    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imageView = findViewById(R.id.detailsActivityImage);
        txtTitle = findViewById(R.id.detailsActivityTitle);
        txtAddress = findViewById(R.id.detailsActivityAddress);
        backButton = findViewById(R.id.detailsActivityBackBtn);

        backButton.setOnClickListener(event -> {
            onBackPressed();
        });

        ArrayList<String> params = getIntent().getExtras().getStringArrayList(EXTRA_HOUSE_MODEL);
        if (params != null){
            houseModel = new HouseModel(params);

            imageView.setImageResource(houseModel.getImageResourceId());
            txtTitle.setText(houseModel.getTitle());
            txtAddress.setText(houseModel.getAddress());
        }

    }
}