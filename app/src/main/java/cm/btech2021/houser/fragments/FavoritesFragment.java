package cm.btech2021.houser.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import cm.btech2021.houser.R;
import cm.btech2021.houser.adapters.HouseAdapter;
import cm.btech2021.houser.repos.HouseService;

public class FavoritesFragment extends Fragment {
    RecyclerView recyclerView;
    HouseService houseService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        initRecyclerView(view);

        return view;
    }

    void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.favoritesRecyclerView);
        houseService = new HouseService();

        recyclerView.setAdapter(new HouseAdapter(houseService.getFavoriteHouses(),
                R.layout.card_new_house));
    }}
