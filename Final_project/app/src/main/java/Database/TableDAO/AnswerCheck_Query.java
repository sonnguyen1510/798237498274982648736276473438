package Database.TableDAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import Database.Table.AnswerCheck;

@Dao
public interface AnswerCheck_Query {
    @Insert
    void InsertAnswerKey(AnswerCheck Answer);

    @Query("Select AnswerKey from AnswerCheck where QuestionID = :QuestionID and QuestionType =:QuestionType ")
    int getAnswerKey(String QuestionID,String QuestionType);

    @Query("Select * from AnswerCheck where QuestionID = :QuestionID and QuestionType =:QuestionType")
    AnswerCheck CheckUNIQUE(String QuestionID,String QuestionType);
}
