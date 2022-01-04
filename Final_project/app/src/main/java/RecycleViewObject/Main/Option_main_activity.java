package RecycleViewObject.Main;

;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.R;

public class Option_main_activity extends RecyclerView.ViewHolder {
    public TextView Name;
    public ImageView img_link;


    public Option_main_activity(@NonNull View itemView) {
        super(itemView);
        Name = itemView.findViewById(R.id.Main_option_name);
        img_link = itemView.findViewById(R.id.Main_option_img);
    }
}
