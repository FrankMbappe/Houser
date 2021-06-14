package cm.btech2021.houser.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import cm.btech2021.houser.R;
import cm.btech2021.houser.activities.DetailsActivity;
import cm.btech2021.houser.models.HouseModel;

public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.ViewHolder> {
    public static final String EXTRA_HOUSE_MODEL = "EXTRA_HOUSE_MODEL";
    final ArrayList<HouseModel> houseModels;
    int layoutID;

    public HouseAdapter(ArrayList<HouseModel> houseModels, int layoutID) {
        this.houseModels = houseModels;
        this.layoutID = layoutID;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(layoutID,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HouseModel houseModel = houseModels.get(position);

        final View view = holder.itemView;
        view.setOnClickListener(event -> {
            Context context = view.getContext();
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putStringArrayListExtra(EXTRA_HOUSE_MODEL, houseModel.toShortStringArrayList());
            context.startActivity(intent);
        });

        if (holder.txtLikeCount != null)
            holder.txtLikeCount.setText(String.valueOf(houseModel.getLikeCount()));

        if (holder.imageHouseView != null)
            holder.imageHouseView.setImageResource(houseModel.getImageResourceId());

        if (holder.imageHeartView != null) {
            if (houseModel.isLiked())
                holder.imageHeartView.setImageResource(R.drawable.ic_heart_fill);
            else
                holder.imageHeartView.setImageResource(R.drawable.ic_heart_outline);
        }

        if (holder.txtTitle != null)
            holder.txtTitle.setText(houseModel.getTitle());

        if (holder.txtSubTitle != null)
            holder.txtSubTitle.setText(houseModel.getAddress());

        if (holder.txtPostedDate != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            holder.txtPostedDate.setText(simpleDateFormat.format(houseModel.getPostedDate()));
        }
    }

    @Override
    public int getItemCount() {
        return houseModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageHouseView;
        ImageView imageHeartView;
        TextView txtLikeCount;
        TextView txtTitle;
        TextView txtSubTitle;
        TextView txtPostedDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageHouseView = itemView.findViewById(R.id.cardHouseImageView);
            imageHeartView = itemView.findViewById(R.id.cardHouseImageHeartView);
            txtLikeCount = itemView.findViewById(R.id.cardHouseTxtLikeCount);
            txtTitle = itemView.findViewById(R.id.cardNewHouseTitle);
            txtSubTitle = itemView.findViewById(R.id.cardNewHouseSubTitle);
            txtPostedDate = itemView.findViewById(R.id.cardNewHouseTxtDate);
        }
    }
}
