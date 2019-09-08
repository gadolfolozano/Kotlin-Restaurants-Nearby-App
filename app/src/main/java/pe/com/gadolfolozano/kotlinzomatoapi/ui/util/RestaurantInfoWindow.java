package pe.com.gadolfolozano.kotlinzomatoapi.ui.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import pe.com.gadolfolozano.kotlinzomatoapi.R;
import pe.com.gadolfolozano.kotlinzomatoapi.ui.model.RestaurantMarker;

public class RestaurantInfoWindow implements GoogleMap.InfoWindowAdapter {

    private View view;
    private Context context;

    public RestaurantInfoWindow(Context context) {
        this.context = context;

        LayoutInflater inflater = (LayoutInflater) this.context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.layout_info_window,
                null);
    }


    @Override
    public View getInfoWindow(Marker marker) {
        if (marker != null
                && marker.isInfoWindowShown()) {
            marker.hideInfoWindow();
            marker.showInfoWindow();
        }
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        final ImageView imgThumb = view.findViewById(R.id.img_thumb);
        final TextView txtTitle = view.findViewById(R.id.txt_title);
        final TextView txtDetail = view.findViewById(R.id.txt_detail);

        if (marker.getTag() instanceof RestaurantMarker) {
            RestaurantMarker restaurantMarker = (RestaurantMarker) marker.getTag();

            txtTitle.setText(restaurantMarker.getName());
            txtDetail.setText(restaurantMarker.getAddress());
            Glide.with(context).load(restaurantMarker.getThumb()).into(imgThumb);
        }

        return view;
    }
}
