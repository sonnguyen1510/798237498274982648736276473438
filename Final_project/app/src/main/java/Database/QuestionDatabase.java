package Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import Database.Table.*;
import Database.TableDAO.*;

@Database(entities = {
                        Questions.class,
                        traffic_sign_questions.class,
                        traffic_signs.class ,
                        User_Answers.class,
                        AnswerCheck.class,
                        SA_photo.class,
                        SA_photo_question.class,
                        TrueAndFalse_Answer.class,
                        FalseAnswer.class
                    },version = 1)
public abstract class QuestionDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "Question_db";
    private static QuestionDatabase instance;

    public static synchronized QuestionDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),QuestionDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract Question_Query question_query();
    public abstract User_Answer_Query user_answer_query();
    public abstract AnswerCheck_Query questionAnswer_query();
    public abstract traffic_signs_query traffic_signs_query();
    public abstract traffic_sign_questions_query traffic_sign_questions_query();
    public abstract SA_photos_query sa_photos_query();
    public abstract SA_photo_question_query sa_photo_question_query();
    public abstract TrueAndFalse_Answer_Query trueAndFalse_answer_query();
    public abstract FalseAnswer_Query falseAnswer_query();

}
