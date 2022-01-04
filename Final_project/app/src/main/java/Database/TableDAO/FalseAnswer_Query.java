package Database.TableDAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import Database.Table.FalseAnswer;

@Dao
public interface FalseAnswer_Query {
    @Insert
    void InsertFalseAnswer(FalseAnswer falseAnswer);

    @Query("Select * from FalseAnswer where QuestionID = :QuestionID and QuestionType =:QuestionType")
    FalseAnswer CheckUNIQUE(String QuestionID , String QuestionType);

    @Query("Update FalseAnswer set UserAnswerA = :UserAnswerA and UserAnswerB =:UserAnswerB and UserAnswerC = :UserAnswerC and UserAnswerD =:UserAnswerD where QuestionID =:QuestionID and QuestionType =:QuestionType ")
    void UpdateFalseAnswer(String QuestionID , String QuestionType , int UserAnswerA ,int UserAnswerB ,int UserAnswerC ,int UserAnswerD );

    @Query("Delete from FalseAnswer where QuestionID =:QuestionID and QuestionType =:QuestionType")
    void DeleteFalseAnswer(String QuestionID , String QuestionType);

    @Query("Select * from FalseAnswer where QuestionType = :questionType")
    List<FalseAnswer> getFalseAnswerbyQuestionType(String questionType);

    @Query("Select * from FalseAnswer")
    List<FalseAnswer> getAllFalseAnswerQuestion();
}
