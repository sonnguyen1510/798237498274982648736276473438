package Database.Table;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class AnswerCheck {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int ID;
    private String QuestionID;
    private String QuestionType;
    private int AnswerKey;

    public AnswerCheck(String QuestionID, int AnswerKey,String QuestionType){
        this.QuestionID = QuestionID;
        this.QuestionType = QuestionType;
        this.AnswerKey = AnswerKey;
    }

    //getter
    public String getQuestionID(){
        return  this.QuestionID;
    }

    public String getQuestionType() {
        return QuestionType;
    }

    @NonNull
    public int getID() {
        return ID;
    }

    public int getAnswerKey() {
        return AnswerKey;
    }

    //setter

    public void setQuestionID(@NonNull String questionID) {
        QuestionID = questionID;
    }

    public void setQuestionType(String questionType) {
        QuestionType = questionType;
    }

    public void setID(@NonNull int ID) {
        this.ID = ID;
    }

    public void setAnswerKey(int answerKey) {
        AnswerKey = answerKey;
    }
}
