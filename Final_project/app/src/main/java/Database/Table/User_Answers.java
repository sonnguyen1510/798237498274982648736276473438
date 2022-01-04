package Database.Table;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

@Entity(tableName = "User_Answer")
//@Relation(entity = QuestionAnswerCheck.class,parentColumn = "QuestionID",entityColumn = "QuestionID")
public class User_Answers {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int ID;
    private String QuestionID;
    private String QuestionType;
    private int UserAnswer;

    public User_Answers(@NonNull String QuestionID ,int UserAnswer,String QuestionType){
        this.QuestionID = QuestionID;
        this.UserAnswer = UserAnswer;
        this.QuestionType = QuestionType;
    }

    //getter
    public int getUserAnswer() {
        return UserAnswer;
    }

    public String getQuestionType() {
        return QuestionType;
    }

    public int getID() {
        return ID;
    }

    public String getQuestionID() {
        return QuestionID;
    }
    //setter

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setQuestionID(@NonNull String questionsID) {
        QuestionID = questionsID;
    }
    public void UserAnswer(int answer){
        this.UserAnswer = answer;
    }

    public void setQuestionType(String questionType) {
        QuestionType = questionType;
    }
}
