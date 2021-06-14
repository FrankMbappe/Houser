package cm.btech2021.houser.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

import cm.btech2021.houser.activities.AddHouseActivity;
import cm.btech2021.houser.adapters.HouseAdapter;
import cm.btech2021.houser.models.HouseModel;
import cm.btech2021.houser.repos.HouseService;
import cm.btech2021.houser.R;

public class HomeFragment extends Fragment{
    RecyclerView bestHousesRecyclerView;
    RecyclerView newHousesRecyclerView;
    FloatingActionButton addHouseFab;
    HouseService houseService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        initRecyclerViews(view);

        addHouseFab = view.findViewById(R.id.homeFragmentAddHouseFab);
        addHouseFab.setOnClickListener(event -> {
            startActivity(new Intent(view.getContext(), AddHouseActivity.class));
        });

        return view;
    }

    void initRecyclerViews(View view) {
        bestHousesRecyclerView = view.findViewById(R.id.bestHousesRecyclerView);
        newHousesRecyclerView = view.findViewById(R.id.newHousesRecyclerView);
        houseService = new HouseService();

//        Toast.makeText(view.getContext(), String.format(
//                Locale.ENGLISH, "The size of the list is: %d", houseService.getHouseCount()
//        ), Toast.LENGTH_LONG).show();

        bestHousesRecyclerView.setAdapter(new HouseAdapter(houseService.getBestHouses(),
                R.layout.card_best_house));
        newHousesRecyclerView.setAdapter(new HouseAdapter(houseService.getNewHouses(),
                R.layout.card_new_house));
    }
}
