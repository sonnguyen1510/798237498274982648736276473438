package Database.TableDAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import Database.Table.TrueAndFalse_Answer;

@Dao
public interface TrueAndFalse_Answer_Query {
    @Query("Select * from TrueAndFalse_Answer ")
    List<TrueAndFalse_Answer> getAllTrueAnswer();

    @Insert
    void insertTrueAnswer(TrueAndFalse_Answer trueAnswer);

    @Query("Select * from TrueAndFalse_Answer where QuestionType =:Type and QuestionID = :ID")
    TrueAndFalse_Answer CheckUNIQUE(String Type , String ID);

    @Query("Select * from TrueAndFalse_Answer where IsTrueAnswer = :Type")
    List<TrueAndFalse_Answer> getAnswer_by_type(boolean Type );

    @Query("Update TrueAndFalse_Answer Set IsTrueAnswer = :check where QuestionID = :QuestionID and QuestionType = :QuestionType ")
    void UpdateQuestion(String QuestionID , String QuestionType , boolean check);


    @Query("Select * from TrueAndFalse_Answer where QuestionType =:QuestionType and IsTrueAnswer =:IsTrue")
    List<TrueAndFalse_Answer> getAllTrueAnswerByQuestionType(String QuestionType , boolean IsTrue);
}
