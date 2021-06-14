package cm.btech2021.houser.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cm.btech2021.houser.R;

public class SettingsFragment extends Fragment {
    ImageButton changePicBtn;
    ImageView userPic;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        changePicBtn = view.findViewById(R.id.settingsChangePicBtn);
        userPic = view.findViewById(R.id.settingsUserPicture);


        ActivityResultLauncher<String> getContent = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    userPic.setImageURI(uri);
                }
        );

        changePicBtn.setOnClickListener(clickedView -> getContent.launch("image/*"));

        return view;
    }
}
