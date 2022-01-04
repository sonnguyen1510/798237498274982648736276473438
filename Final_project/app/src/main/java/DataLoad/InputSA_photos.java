package DataLoad;

import android.content.Context;
import android.os.AsyncTask;

import com.example.final_project.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import Database.QuestionDatabase;
import Database.Table.AnswerCheck;
import Database.Table.SA_photo;
import Database.Table.SA_photo_question;

public class InputSA_photos extends AsyncTask<Context ,Object ,Boolean> {
    @Override
    protected Boolean doInBackground(Context... contexts) {
        Context context = contexts[0];
        //input SA_photo_infomation to database
        List<SA_photo> temp = null;
        try {
            temp = Load_SA_photos();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(SA_photo tp : temp){
            if(QuestionDatabase.getInstance(context).sa_photos_query().CheckUNIQUE(tp.getSA_photo_ID())==null){
                QuestionDatabase.getInstance(context).sa_photos_query().InsertSA_photos(tp);
            }
        }

        //input SA_photo_question_to DATABASE
        InputStream file = context.getResources().openRawResource(R.raw.sa_photos_question);
        BufferedReader br = new BufferedReader(new InputStreamReader(file));
        String readLine;

        try {
            while ((readLine = br.readLine()) != null) {
                String[] info = readLine.split("-");
                if(info.length == 9){
                    SA_photo_question question = new SA_photo_question(info[0],info[1],info[2],info[3],info[4],info[5],info[6],info[7]);
                    if(QuestionDatabase.getInstance(context).sa_photo_question_query().CheckUNIQUE(info[0]) == null){
                        QuestionDatabase.getInstance(context).sa_photo_question_query().InsertSA_photo_question(question);
                    }

                    AnswerCheck answerCheck = new AnswerCheck(info[0],Integer.parseInt(info[8]),info[7]);
                    if(QuestionDatabase.getInstance(context).questionAnswer_query().CheckUNIQUE(info[0],info[7])==null){
                        QuestionDatabase.getInstance(context).questionAnswer_query().InsertAnswerKey(answerCheck);
                    }
                }

            }
        }catch (Exception e){
            return false;
        }
        return true;
    }


    public List<SA_photo> Load_SA_photos() throws  Exception{
        List<SA_photo> data = new ArrayList<>();

        data.add(new SA_photo("1",R.drawable.sa_photos1));
        data.add(new SA_photo("2",R.drawable.sa_photos2));
        data.add(new SA_photo("3",R.drawable.sa_photos3));
        data.add(new SA_photo("4",R.drawable.sa_photos4));
        data.add(new SA_photo("5",R.drawable.sa_photos5));
        data.add(new SA_photo("6",R.drawable.sa_photos6));
        data.add(new SA_photo("7",R.drawable.sa_photos7));
        data.add(new SA_photo("8",R.drawable.sa_photos8));
        data.add(new SA_photo("9",R.drawable.sa_photos9));
        data.add(new SA_photo("10",R.drawable.sa_photos10));
        data.add(new SA_photo("11",R.drawable.sa_photos11));
        data.add(new SA_photo("12",R.drawable.sa_photos12));
        data.add(new SA_photo("13",R.drawable.sa_photos13));
        data.add(new SA_photo("14",R.drawable.sa_photos14));
        data.add(new SA_photo("15",R.drawable.sa_photos15));
        data.add(new SA_photo("16",R.drawable.sa_photos16));
        data.add(new SA_photo("17",R.drawable.sa_photos17));
        data.add(new SA_photo("18",R.drawable.sa_photos18));
        data.add(new SA_photo("19",R.drawable.sa_photos19));
        data.add(new SA_photo("20",R.drawable.sa_photos20));
        data.add(new SA_photo("21",R.drawable.sa_photos21));
        data.add(new SA_photo("22",R.drawable.sa_photos22));
        data.add(new SA_photo("23",R.drawable.sa_photos23));
        data.add(new SA_photo("24",R.drawable.sa_photos24));
        data.add(new SA_photo("25",R.drawable.sa_photos25));
        data.add(new SA_photo("26",R.drawable.sa_photos26));
        data.add(new SA_photo("27",R.drawable.sa_photos27));
        data.add(new SA_photo("28",R.drawable.sa_photos28));
        data.add(new SA_photo("29",R.drawable.sa_photos29));
        data.add(new SA_photo("30",R.drawable.sa_photos30));
        data.add(new SA_photo("31",R.drawable.sa_photos31));
        data.add(new SA_photo("32",R.drawable.sa_photos32));
        data.add(new SA_photo("33",R.drawable.sa_photos33));
        data.add(new SA_photo("34",R.drawable.sa_photos34));
        data.add(new SA_photo("35",R.drawable.sa_photos35));

        return data;
    }


}
