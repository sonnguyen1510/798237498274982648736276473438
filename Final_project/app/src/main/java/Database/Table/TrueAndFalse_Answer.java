package Database.Table;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TrueAndFalse_Answer {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    private String QuestionID;
    private String QuestionType;
    private boolean IsTrueAnswer;

    public TrueAndFalse_Answer(String QuestionID , String QuestionType,boolean IsTrueAnswer){
        this.QuestionID =QuestionID;
        this.QuestionType = QuestionType;
        this.IsTrueAnswer = IsTrueAnswer;
    }

    //getter

    public int getID() {
        return ID;
    }

    public String getQuestionType() {
        return QuestionType;
    }

    public boolean getIsTrueAnswer() {
        return IsTrueAnswer;
    }

    public String getQuestionID() {
        return QuestionID;
    }

    //setter

    public void setQuestionType(String QuestionType) {
        this.QuestionType = QuestionType;
    }

    public void setQuestionID(String QuestionID) {
        this.QuestionID = QuestionID;
    }

    public void setIsTrueAnswer(boolean IsTrueAnswer) {
        this.IsTrueAnswer = IsTrueAnswer;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
