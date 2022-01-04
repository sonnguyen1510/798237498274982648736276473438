package Database.TableDAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import Database.StoreData.StoreAnswerUser_and_AnswerKey;
import Database.Table.User_Answers;

@Dao
public interface User_Answer_Query {
    @Insert
    void Insert_UserAnswer(User_Answers user_answers);

    @Query("Select UserAnswer,AnswerKey " +
            "from User_Answer " +
            "Inner join AnswerCheck " +
            "on User_Answer.QuestionID = AnswerCheck.QuestionID and User_Answer.QuestionType = AnswerCheck.QuestionType " +
            "where User_Answer.QuestionID = :ID and User_Answer.QuestionType = :type")
    StoreAnswerUser_and_AnswerKey Answer_Checked(String ID,String type);

    //update User Answer
    @Query("Update User_Answer " +
            "Set UserAnswer = :UserAnswer " +
            "where QuestionID = :id and QuestionType = :type ")
    void Update_User_Answer(String id , int UserAnswer , String type);

    //checked
    @Query("Select * from User_Answer where QuestionID = :CheckID and QuestionType =:type ")
    User_Answers IsChecked(String CheckID,String type);

}
