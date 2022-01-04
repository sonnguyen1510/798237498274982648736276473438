package Database.TableDAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import Database.Table.SA_photo_question;
@Dao
public interface SA_photo_question_query {
    @Insert
    void InsertSA_photo_question(SA_photo_question sa_photo_question);

    @Query("Select QuestionName from SA_photo_question where QuestionID = :ID")
    String CheckUNIQUE(String ID);

    @Query("Select * from SA_photo_question")
    List<SA_photo_question> getAllQuestion();

    @Query("Select * from SA_photo_question where QuestionID = :ID")
    SA_photo_question getQuestion_by_id(String ID);
}
