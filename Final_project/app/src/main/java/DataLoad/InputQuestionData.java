package DataLoad;



import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.final_project.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import Database.QuestionDatabase;
import Database.Table.AnswerCheck;
import Database.Table.Questions;


public class InputQuestionData extends AsyncTask<Context , Context , Boolean> {

    @Override
    protected Boolean doInBackground(Context... contexts) {
        Context context = contexts[0];
        try {
            InputStream file = context.getResources().openRawResource(R.raw.question);
            BufferedReader br = new BufferedReader(new InputStreamReader(file));

            String readLine;
            while ((readLine = br.readLine()) != null) {
                String[] info = readLine.split("-");
                if(info.length == 8) {
                    Questions questions = new Questions(info[0], info[1], info[2], info[3], info[4], info[5], info[6]);
                    AnswerCheck answerCheck = new AnswerCheck(info[0], Integer.parseInt(info[7]),info[6]);
                    //sb.append(readLine).append("\n");
                    //A = new TextObj(a);
                    /**/
                    //insert to database ( table name = Questions)
                    AddQuestion_To_DataBase(questions,context);
                    //insert to database ( table name = Answer_Questions)
                    AddAnswerKey_To_DataBase(answerCheck,context);

                }

            }
            br.close();
            return true;

        } catch (IOException ex) {
            Toast.makeText(context, "Load data failed",Toast.LENGTH_SHORT);
            ex.printStackTrace();
            return false;

        }

    }



    //insert Question to database
    //public boolean InputQuestionData_ToDataBase() {
        //StringBuilder sb = new StringBuilder();




    private void AddAnswerKey_To_DataBase(AnswerCheck answerCheck , Context context) {
        if(QuestionDatabase.getInstance(context).questionAnswer_query().CheckUNIQUE(answerCheck.getQuestionID(),answerCheck.getQuestionType())== null){
            QuestionDatabase.getInstance(context).questionAnswer_query().InsertAnswerKey(answerCheck);
        }
    }

    private void AddQuestion_To_DataBase(Questions questions, Context context) {
        if(QuestionDatabase.getInstance(context).question_query().CheckUNIQUE(questions.getQuestionID()) == null) {
            QuestionDatabase.getInstance(context).question_query().insertQuestion(questions);
        }
    }


}
