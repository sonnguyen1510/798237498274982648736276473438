package UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.final_project.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.Main.Option_main_adapter;
import Database.QuestionDatabase;
import Database.Table.TrueAndFalse_Answer;
import Object.Main.Set_main_option;
import Object.Main.Learning_Base_On_QuestionType_OBJ;
import UI.DoExam.ChoseExam;
import UI.Option.Learning_SA_photo_question;
import UI.Option.Learning_theory_question;
import UI.Option.Learning_traffic_signs_question;
import UI.Option.Search_traffic_signs;
import UI.Option.WrongAnswer;
import UI.Setting.Setting;


public class MainActivity extends AppCompatActivity {
    //Phần ôn câu trả lời sai ,làm đề , xem biển báo

    private RecyclerView Main_option ;
    private Option_main_adapter Main_Adapter;
    private List<Set_main_option>Main_Data;
    //Phần ôn tập theo chủ đề
    private RecyclerView Another_option;
    private Adapter.Main.Learning_Base_On_QuestionType_adapter Another_option_adapter;
    private List<Learning_Base_On_QuestionType_OBJ> Another_Option_Data;
    //Tiến độ học tập
    private ProgressBar progress_Show;
    private TextView total_question;
    private TextView progress;
    //
    private DrawerLayout drawerLayout;
    private ImageView menu;
    private ImageView logo;
    private TextView aboutus;
    private LinearLayout layout;
    private TextView Setting;
    private Button Logout;
    private LinearLayout mainLinear;
    private Activity activity = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        drawerLayout = findViewById(R.id.MainActiviy_Drawer_layout);
        //---------------Học tập chung-----------------------------------------------
        Main_option = findViewById(R.id.Main_Option);
        Main_Data = new ArrayList<Set_main_option>();
        //Load_layout_main_Activity
        Main_Data = load_Main_Activity();
        //
        layout = findViewById(R.id.MainActivity_Show);
        menu = findViewById(R.id.MainActivity_menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.setVisibility(View.VISIBLE);
            }
        });

        logo = findViewById(R.id.MainActivity_Logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //closeDrawerlayout(drawerLayout);
                layout.setVisibility(View.GONE);
            }
        });
        mainLinear = findViewById(R.id.MainActiviTy_MainLayout);
        mainLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.setVisibility(View.GONE);
            }
        });


        Setting = findViewById(R.id.MainActivity_setting);
        Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Setting.class);
                view.getContext().startActivity(intent);
            }
        });

        Logout = findViewById(R.id.MainActivity_logout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout(activity);
            }
        });


        //set_Adapter
        Main_Adapter = new Option_main_adapter(this,Main_Data);
        Main_option.setAdapter(Main_Adapter);
        Main_option.setLayoutManager(new GridLayoutManager(this,3));

        //---------------Tiến độ học tập-----------------------------------------------
        progress = findViewById(R.id.Progress);
        progress_Show = findViewById(R.id.Overal_processBar);
        total_question = findViewById(R.id.total_question);
        LoadProgress_tien_do_hoc_tap(this);

        //--------------Phần ôn tập theo chủ đề-----------------------------------------
        Another_option = findViewById(R.id.Option_main_Another_choice);
        Another_Option_Data = new ArrayList<Learning_Base_On_QuestionType_OBJ>();
        //Load_layout_option_Activity
        Another_Option_Data = load_Option_Activity();

        //set Adapter
        Another_option_adapter = new Adapter.Main.Learning_Base_On_QuestionType_adapter(this,Another_Option_Data);
        Another_option.setAdapter(Another_option_adapter);
        Another_option.setLayoutManager(new LinearLayoutManager(this));



    }

    private void logout(Activity activity) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity)
                .setTitle("Nhắc nhở")
                .setMessage("Bạn có muốn thoát ứng dụng hay không ?")
                .setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        activity.finishAffinity();
                        System.exit(0);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        Dialog dia = dialog.create();
        dia.show();
    }

    @Override
    protected void onPause() {
        closeDrawerlayout(drawerLayout);
        super.onPause();
    }

    private static void closeDrawerlayout(DrawerLayout drawerLayout) {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    private void LoadProgress_tien_do_hoc_tap(Context context) {
        List<TrueAndFalse_Answer> trueAndFalse_answer = new ArrayList<>();
        trueAndFalse_answer = QuestionDatabase.getInstance(context)
                .trueAndFalse_answer_query().getAnswer_by_type(true);
        int countNumberOfTrueAnswer = trueAndFalse_answer.size();

        int countNumberOfQuestion =
                QuestionDatabase.getInstance(context).question_query().getAllQuestion().size()+
                        QuestionDatabase.getInstance(context).traffic_sign_questions_query().getAllQuestion().size()+
                        QuestionDatabase.getInstance(context).sa_photo_question_query().getAllQuestion().size();


        if (countNumberOfTrueAnswer == 0){
            progress.setText("0%");
            progress_Show.setProgress(0);
            total_question.setText("Tổng số câu hỏi :"+countNumberOfQuestion);
        }
        else{
            if((countNumberOfTrueAnswer/countNumberOfQuestion*1.0)*100.0 >= 0.1){
                progress.setText((countNumberOfTrueAnswer/countNumberOfQuestion*1.0)*100.0 + "%");
                progress_Show.setProgress(countNumberOfTrueAnswer/countNumberOfQuestion*100);
                total_question.setText("Tổng số câu hỏi :"+countNumberOfQuestion);
            }
            else{
                progress.setText(0.1 + "%");
                progress_Show.setProgress((int) 0.1);
                total_question.setText("Tổng số câu hỏi :"+countNumberOfQuestion);
            }

        }



    }


    private static void openDrawerLayout(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }


    public List<Learning_Base_On_QuestionType_OBJ> load_Option_Activity(){
        List<Learning_Base_On_QuestionType_OBJ> DATA = new ArrayList<Learning_Base_On_QuestionType_OBJ>();
        Learning_Base_On_QuestionType_OBJ Lythuyet = new Learning_Base_On_QuestionType_OBJ<Learning_theory_question>("Ôn tập lý thuyết",R.drawable.lythuyet,QuestionDatabase.getInstance(this).question_query().getAllQuestion().size(), Learning_theory_question.class);
        Learning_Base_On_QuestionType_OBJ BienBao = new Learning_Base_On_QuestionType_OBJ<Learning_traffic_signs_question>("Ôn tập Biển Báo",R.drawable.bienbao,QuestionDatabase.getInstance(this).traffic_sign_questions_query().getAllQuestion().size(),Learning_traffic_signs_question.class);
        Learning_Base_On_QuestionType_OBJ SaHinh = new Learning_Base_On_QuestionType_OBJ<Learning_SA_photo_question>("Ôn tập Sa Hình",R.drawable.sahinh,QuestionDatabase.getInstance(this).sa_photo_question_query().getAllQuestion().size(),Learning_SA_photo_question.class);
        DATA.add(Lythuyet);
        DATA.add(BienBao);
        DATA.add(SaHinh);

        return DATA;

    }

    public List<Set_main_option> load_Main_Activity() {
        List<Set_main_option> data= new  ArrayList<Set_main_option>();

        data.add( new Set_main_option("Giải Đề",R.drawable.giaide, ChoseExam.class));
        data.add(new Set_main_option("Câu Sai",R.drawable.cauhoisai, WrongAnswer.class));
        data.add(new Set_main_option("Biển Báo", R.drawable.tracuubienbao,Search_traffic_signs.class));

        return data;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
}