package Database.TableDAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import Database.Table.Questions;

@Dao
public interface Question_Query {
    @Insert
    void insertQuestion(Questions qt);

    @Query("Select * from Questions")
    List<Questions> getAllQuestion();

    @Query("Select * from Questions where QuestionID= :QuestionID ")
    Questions CheckUNIQUE(String QuestionID);

    @Query("Select * from Questions where QuestionID= :QuestionID ")
    Questions getQuestions_by_ID(String QuestionID);

    @Query("Select * from Questions Where QuestionType = :Type")
    List<Questions> getQuestion_by_QuestionType(String Type);
}
