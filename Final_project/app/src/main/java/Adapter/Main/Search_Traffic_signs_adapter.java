package Adapter.Main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.R;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import RecycleViewObject.SearchTrafficSign.Traffic_sign_activity;
import Database.Table.traffic_signs;

public class Search_Traffic_signs_adapter extends RecyclerView.Adapter<Traffic_sign_activity> {
    private Context context;
    private List<traffic_signs> data;
    private HashMap<Integer, Traffic_sign_activity> holderlist;

    public Search_Traffic_signs_adapter(Context context, List<traffic_signs> traffic_signs_data) {
        this.context = context;
        this.data = traffic_signs_data;
        holderlist = new HashMap<>();
    }

    @NonNull
    @Override
    public Traffic_sign_activity onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(context).inflate(
                R.layout.search_traffic_signs_item,
                parent,
                false
        );
        return new Traffic_sign_activity(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull Traffic_sign_activity holder, int position) {
        if(!holderlist.containsKey(position)){
            holderlist.put(position,holder);
        }

        traffic_signs item = data.get(position);
        holder.traffic_img.setImageResource(item.getTraffic_img_link());
        holder.traffic_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onButtonShowPopupWindowClick(view,item);


                /*AlertDialog.Builder Dialog = new AlertDialog.Builder(context)
                        .setTitle("Thông tin biển báo")
                        .setMessage(item.getTraffic_info())
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog dia = Dialog.create();
                dia.show();*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public Traffic_sign_activity getViewByPosition(int position) {
        return holderlist.get(position);
    }

    public void onButtonShowPopupWindowClick(View view , traffic_signs show) {

        // inflate the layout of the popup window
        LayoutInflater inflater = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        View popupView = inflater.inflate(R.layout.searchtraffic_popup, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        ImageView setImg = popupView.findViewById(R.id.searchtraffic_popup_img);
        TextView setType = popupView.findViewById(R.id.searchtraffic_popup_type);
        TextView setInfo = popupView.findViewById(R.id.searchtraffic_popup_info);


        setImg.setImageResource(show.getTraffic_img_link());
        setType.setText(show.getTraffic_type());
        setInfo.setText(show.getTraffic_info());

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupWindow.setFocusable(true);
        //popupWindow.setAnimationStyle(R.anim.animation);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}
