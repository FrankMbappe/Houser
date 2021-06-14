package cm.btech2021.houser.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;
import java.util.UUID;

import cm.btech2021.houser.R;

public class AddHouseActivity extends AppCompatActivity {
    public static final int PERMISSION_FINE_LOCATION = 3309;
    public static final int PERMISSION_WRITE_EXTERNAL = 3310;
    public static final int REQUEST_PICK = 1900;
    public static final int REQUEST_IMAGE_CAPTURE = 1901;

    FusedLocationProviderClient fusedLocationProviderClient;
    CardView pickPhotoCardView;
    ImageView imageView;
    CardView locationCardView;
    TextView txtLongitudeLatitude;
    OutputStream imageOutputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_house);

        pickPhotoCardView = findViewById(R.id.addHouseActivityPickPhotoCardView);
        imageView = findViewById(R.id.addHouseActivityImage);
        locationCardView = findViewById(R.id.addHouseActivityLocationCardView);
        txtLongitudeLatitude = findViewById(R.id.addHouseTxtLongitudeLatitude);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        pickPhotoCardView.setOnClickListener(event -> {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                displayImagePicker();

            } else {
                requestStoragePermission();
            }
        });

        locationCardView.setOnClickListener(event -> {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                displayLocation();
                
            } else {
                requestLocationPermission();
            }
        });
    }

    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed to get your current location")
                    .setPositiveButton("Ok", (dialogInterface, i) -> {
                        ActivityCompat.requestPermissions(this, new String[]{
                                Manifest.permission.ACCESS_FINE_LOCATION
                        }, PERMISSION_FINE_LOCATION);
                    })
                    .setNegativeButton("Cancel", (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                    }).create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, PERMISSION_FINE_LOCATION);
        }
    }

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed to save your pictures to local storage")
                    .setPositiveButton("Ok", (dialogInterface, i) -> {
                        ActivityCompat.requestPermissions(this, new String[]{
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        }, PERMISSION_WRITE_EXTERNAL);
                    })
                    .setNegativeButton("Cancel", (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                    }).create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, PERMISSION_WRITE_EXTERNAL);
        }
    }

    @SuppressLint("MissingPermission")
    private void displayLocation(){
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(task -> {
            Location location = task.getResult();

            if (location != null) {
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();

                txtLongitudeLatitude.setText(String.format(
                        Locale.getDefault(), "(%f, %f)", longitude, latitude)
                );
            }
        });
    }

    private void displayImagePicker(){
        String[] options = {"🍉  Gallery", "🎞  Camera", "❌  Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Take a photo");
        builder.setItems(options, (dialogInterface, i) -> {
            if (options[i].equals(options[0])) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_PICK);
            } else if (options[i].equals(options[1])) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            } else {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_FINE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted."
                        , Toast.LENGTH_LONG).show();
                displayLocation();
            }
        }
        else if (requestCode == PERMISSION_WRITE_EXTERNAL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted."
                        , Toast.LENGTH_LONG).show();
                displayImagePicker();
            }
        }
        else {
            Toast.makeText(this, "Permission denied."
                    , Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && null != data) {
            if (requestCode == REQUEST_PICK) {
                Uri selectedImage = data.getData();
                if (selectedImage != null) {
                    imageView.setImageURI(selectedImage);
                }
            } else {
                // Photo has been taken
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imageView.setImageBitmap(imageBitmap);

                // Save to the gallery
                File filePath = Environment.getExternalStorageDirectory();
                File directory = new File(filePath.getAbsolutePath() + "/Houser/");
                boolean directoryIsValid = directory.isDirectory();

                if (!directory.exists()) directoryIsValid = directory.mkdir();

                if (directoryIsValid){
                    String imageName = "img-" + System.currentTimeMillis() + ".jpg";
                    File file = new File(directory, imageName);
                    try {
                        imageOutputStream = new FileOutputStream(file);
                        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, imageOutputStream);

                        Toast.makeText(this, "Your picture has been saved to "
                                + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
                        imageOutputStream.flush();
                        imageOutputStream.close();

                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}