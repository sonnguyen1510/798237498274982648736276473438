package Database.TableDAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import Database.Table.traffic_sign_questions;

@Dao
public interface traffic_sign_questions_query {
    @Insert
    void Insert_traffic_sign_question_data(traffic_sign_questions traffic_sign_questions);

    @Query("Select * from traffic_sign_questions where QuestionID = :ID")
    traffic_sign_questions CheckInsert(String ID);

    @Query("Select * from traffic_sign_questions ")
    List<traffic_sign_questions> getAllQuestion();

    @Query("Select * from traffic_sign_questions where QuestionID = :ID")
    traffic_sign_questions getQuestion_by_id(String ID);
}
