package RecycleViewObject.SearchTrafficSign;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.R;

public class Traffic_sign_activity extends RecyclerView.ViewHolder{
    public ImageView traffic_img;
    public Traffic_sign_activity(@NonNull View itemView) {
        super(itemView);
        traffic_img = itemView.findViewById(R.id.traffic_sign_img);
    }
}
