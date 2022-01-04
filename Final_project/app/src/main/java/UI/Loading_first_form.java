package UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.final_project.R;

import java.io.FileNotFoundException;
import java.io.IOException;

import DataLoad.InputQuestionData;
import DataLoad.InputSA_photos;
import DataLoad.InputTrafficSignData;
import Database.QuestionDatabase;

public class Loading_first_form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_first_form);

        //
        //AsyncTask
        QuestionDatabase.getInstance(this);
        boolean IsAdded = true;
        //if(QuestionDatabase.getInstance(this).question_query().getAllQuestion() == null){
        //input Data
        //IsAdded=
        try {

            //theory question
            InputQuestionData inputQuestionData = new InputQuestionData();
            inputQuestionData.execute(this);

            //SA_photos question
            InputSA_photos inputSA_photos = new InputSA_photos();
            inputSA_photos.execute(this);

            //traffic question
            InputTrafficSignData inputTrafficSignData = new InputTrafficSignData();
            inputTrafficSignData.execute(this);

            IsAdded = true;
        } catch (Exception e) {
            e.printStackTrace();
            IsAdded = false;
        }
        //}

        if(IsAdded){
            Thread ready = new Thread() {
                public void run() {
                    try {

                        sleep(3000);
                    } catch (Exception e) {

                    } finally {
                        Go_to_main_Activity();
                    }
                }
            };

            ready.start();
        }
        else
            finish();

    }
    //sau khi chuyển sang màn hình chính, kết thúc màn hình chào
    protected void onPause(){
        super.onPause();
        finish();
    }

    public void Go_to_main_Activity(){
        Intent activitymoi=new Intent(this,MainActivity.class);
        startActivity(activitymoi);
    }

}