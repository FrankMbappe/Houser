package cm.btech2021.houser.repos;

import android.os.Build;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import cm.btech2021.houser.models.HouseModel;
import cm.btech2021.houser.R;
import cm.btech2021.houser.utils.Utils;

public class HouseService {
    ArrayList<HouseModel> houseModels;

    public HouseService() {
        if (houseModels == null) {
            houseModels = new ArrayList<>();

            houseModels.add(new HouseModel("1", true, R.drawable.house7, 58,
                    "1855 Prairie Ave", "Park Ridge, Illinois(IL), 60068",
                    Utils.parseDate("2020-08-05")
            ));
            houseModels.add(new HouseModel("2", false, R.drawable.house_alt_6, 254,
                    "21638 Draycott Way", "Land O Lakes, Florida(FL), 34637",
                    Utils.parseDate("2020-12-24")
            ));
            houseModels.add(new HouseModel("3", true, R.drawable.house_alt_5, 21,
                    "105 Della Valle Dr", "Amsterdam, New York(NY), 12010",
                    Utils.parseDate("2020-11-13")
            ));
            houseModels.add(new HouseModel("4", true, R.drawable.house_alt_4, 856,
                    "57 Commercial Park Lane", "Cicero, New York(NY), 13039",
                    Utils.parseDate("2020-01-09")
            ));
            houseModels.add(new HouseModel("5", false, R.drawable.house_alt_3, 354,
                    "1421 S Division St #121", "Forrest City, Arkansas(AR), 72335",
                    Utils.parseDate("2020-03-19")
            ));
            houseModels.add(new HouseModel("6", false, R.drawable.house_alt_1, 235,
                    "3873 NYS Route 22, Donaugh", "Avenue Street, Illinois(IL)",
                    Utils.parseDate("2020-05-31")
            ));
            houseModels.add(new HouseModel("7", true, R.drawable.house_alt_2, 687,
                    "160 W Washington St", "Le Center, Minnesota(MN), 56057",
                    Utils.parseDate("2020-03-21")
            ));
        }
    }

    public ArrayList<HouseModel> getAllHouses(){
        return houseModels;
    }

    public ArrayList<HouseModel> getBestHouses(){
        if (houseModels != null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                return houseModels.stream().filter(x -> x.getLikeCount() >= 200)
                    .collect(Collectors.toCollection(ArrayList::new));
            else return houseModels;
        }
        return new ArrayList<>();
    }

    public ArrayList<HouseModel> getNewHouses(){
        if (houseModels != null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                return houseModels.stream().sorted(Comparator.comparing(HouseModel::getPostedDate))
                        .collect(Collectors.toCollection(ArrayList::new));
            else return houseModels;
        }
        return new ArrayList<>();
    }

    public ArrayList<HouseModel> getFavoriteHouses(){
        if (houseModels != null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                return houseModels.stream().filter(HouseModel::isLiked)
                        .collect(Collectors.toCollection(ArrayList::new));
            else return houseModels;
        }
        return new ArrayList<>();
    }

    public int getHouseCount(){
        if (houseModels != null){
            return houseModels.size();
        }
        return -1;
    }
}
