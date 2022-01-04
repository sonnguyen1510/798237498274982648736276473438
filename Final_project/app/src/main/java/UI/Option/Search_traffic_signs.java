package UI.Option;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.final_project.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.Main.Search_Traffic_signs_adapter;
import Database.QuestionDatabase;
import Database.Table.traffic_signs;

public class Search_traffic_signs extends AppCompatActivity {

    private RecyclerView Showtrafficsign;
    private List<traffic_signs> traffic_signs_data;
    private List<traffic_signs> Alltraffic_signs_data;
    private Search_Traffic_signs_adapter adapter;

    //check bok
    private CheckBox Bienbaocam;
    private CheckBox Bienbaohieulenh;
    private CheckBox Bienbaochidan;
    private CheckBox Bienbaonguyhiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_traffic_signs);
        Showtrafficsign = findViewById(R.id.Search_Traffic_show_RV);
        //load traffic sign from database
        traffic_signs_data = traffic_signs_data_load();
        Alltraffic_signs_data = traffic_signs_data_load();

        //
        adapter = new Search_Traffic_signs_adapter(this,traffic_signs_data);
        Showtrafficsign.setAdapter(adapter);
        Showtrafficsign.setLayoutManager(new GridLayoutManager(this,3));

        //set check box event
        Bienbaocam = findViewById(R.id.BienBaoCam);
        Bienbaochidan = findViewById(R.id.BienBaoChiDan);
        Bienbaohieulenh = findViewById(R.id.BienBaoHieuLenh);
        Bienbaonguyhiem = findViewById(R.id.BienBaoNguyHiem);

        //Action bar
        getSupportActionBar().setTitle("Tra Cứu Biển Báo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bienbaocam.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    if(Bienbaochidan.isChecked()){
                        Bienbaochidan.setChecked(false);
                    }
                    if(Bienbaonguyhiem.isChecked()){
                        Bienbaonguyhiem.setChecked(false);
                    }

                    if(Bienbaohieulenh.isChecked()){
                        Bienbaohieulenh.setChecked(false);
                    }
                    List<traffic_signs> datatemp = QuestionDatabase.getInstance(Search_traffic_signs.this).traffic_signs_query().getTraffic_signs_bytype("cam");
                    Search_Traffic_signs_adapter tempAdapter = new Search_Traffic_signs_adapter(Search_traffic_signs.this,datatemp);
                    Showtrafficsign.setAdapter(tempAdapter);
                    Showtrafficsign.setLayoutManager(new GridLayoutManager(Search_traffic_signs.this,3));

                }
                else{
                    Showtrafficsign.setAdapter(adapter);
                    Showtrafficsign.setLayoutManager(new GridLayoutManager(Search_traffic_signs.this,3));

                }
            }
        });


        Bienbaochidan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    if(Bienbaonguyhiem.isChecked()){
                        Bienbaonguyhiem.setChecked(false);
                    }
                    if(Bienbaocam.isChecked()){
                        Bienbaocam.setChecked(false);
                    }

                    if(Bienbaohieulenh.isChecked()){
                        Bienbaohieulenh.setChecked(false);
                    }
                    List<traffic_signs> datatemp = QuestionDatabase.getInstance(Search_traffic_signs.this).traffic_signs_query().getTraffic_signs_bytype("chidan");
                    Search_Traffic_signs_adapter tempAdapter = new Search_Traffic_signs_adapter(Search_traffic_signs.this, datatemp);
                    Showtrafficsign.setAdapter(tempAdapter);
                    Showtrafficsign.setLayoutManager(new GridLayoutManager(Search_traffic_signs.this, 3));
                }
                else{
                    Showtrafficsign.setAdapter(adapter);
                    Showtrafficsign.setLayoutManager(new GridLayoutManager(Search_traffic_signs.this,3));
                }
            }
        });

        Bienbaonguyhiem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (Bienbaochidan.isChecked()) {
                        Bienbaochidan.setChecked(false);
                    }
                    if (Bienbaocam.isChecked()) {
                        Bienbaocam.setChecked(false);
                    }

                    if (Bienbaohieulenh.isChecked()) {
                        Bienbaohieulenh.setChecked(false);
                    }
                    List<traffic_signs> datatemp = QuestionDatabase.getInstance(Search_traffic_signs.this).traffic_signs_query().getTraffic_signs_bytype("nguyhiem");
                    Search_Traffic_signs_adapter tempAdapter = new Search_Traffic_signs_adapter(Search_traffic_signs.this, datatemp);
                    Showtrafficsign.setAdapter(tempAdapter);
                    Showtrafficsign.setLayoutManager(new GridLayoutManager(Search_traffic_signs.this, 3));
                }
                else{
                    Showtrafficsign.setAdapter(adapter);
                    Showtrafficsign.setLayoutManager(new GridLayoutManager(Search_traffic_signs.this,3));
                }
            }
        });
        
        Bienbaohieulenh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (Bienbaochidan.isChecked()) {
                        Bienbaochidan.setChecked(false);
                    }
                    if (Bienbaonguyhiem.isChecked()) {
                        Bienbaonguyhiem.setChecked(false);
                    }

                    if (Bienbaocam.isChecked()) {
                        Bienbaocam.setChecked(false);
                    }
                    List<traffic_signs> datatemp = QuestionDatabase.getInstance(Search_traffic_signs.this).traffic_signs_query().getTraffic_signs_bytype("hieulenh");
                    Search_Traffic_signs_adapter tempAdapter = new Search_Traffic_signs_adapter(Search_traffic_signs.this, datatemp);
                    Showtrafficsign.setAdapter(tempAdapter);
                    Showtrafficsign.setLayoutManager(new GridLayoutManager(Search_traffic_signs.this, 3));
                }
                else{
                    Showtrafficsign.setAdapter(adapter);
                    Showtrafficsign.setLayoutManager(new GridLayoutManager(Search_traffic_signs.this,3));

                }
            }
        });


    }

    private List<traffic_signs> traffic_signs_data_load() {
        List<traffic_signs> data = new ArrayList<traffic_signs>();

        data = QuestionDatabase.getInstance(this).traffic_signs_query().getAlldata();
        return data;
    }

    //---------------------ACTION BAR-------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }



}