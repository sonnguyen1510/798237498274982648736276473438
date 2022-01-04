package Database.Table;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FalseAnswer {
    @PrimaryKey(autoGenerate = true)
    private int ID ;
    private String QuestionID;
    private String QuestionType;
    private int answerKey;
    private int UserAnswerA;
    private int UserAnswerB;
    private int UserAnswerC;
    private int UserAnswerD;

    public FalseAnswer(String QuestionID,
                       String QuestionType,
                       int answerKey,
                       int UserAnswerA,
                       int UserAnswerB,
                       int UserAnswerC,
                       int UserAnswerD){
        this.QuestionID = QuestionID;
        this.QuestionType = QuestionType;
        this.answerKey = answerKey;
        this.UserAnswerA = UserAnswerA;
        this.UserAnswerB = UserAnswerB;
        this.UserAnswerC = UserAnswerC;
        this.UserAnswerD = UserAnswerD;
    }

    //getter

    public String getQuestionID() {
        return QuestionID;
    }

    public int getUserAnswerA() {
        return UserAnswerA;
    }

    public int getUserAnswerB() {
        return UserAnswerB;
    }

    public int getUserAnswerC() {
        return UserAnswerC;
    }

    public int getUserAnswerD() {
        return UserAnswerD;
    }

    public int getAnswerKey() {
        return answerKey;
    }

    public int getID() {
        return ID;
    }
    public String getQuestionType() {
        return QuestionType;
    }

    //setter


    public void setID(int ID) {
        this.ID = ID;
    }

    public void setQuestionType(String questionType) {
        QuestionType = questionType;
    }


    public void setUserAnswerA(int userAnswerA) {
        UserAnswerA = userAnswerA;
    }

    public void setUserAnswerB(int userAnswerB) {
        UserAnswerB = userAnswerB;
    }

    public void setAnswerKey(int answerKey) {
        this.answerKey = answerKey;
    }

    public void setQuestionID(String questionID) {
        QuestionID = questionID;
    }

    public void setUserAnswerC(int userAnswerC) {
        UserAnswerC = userAnswerC;
    }

    public void setUserAnswerD(int userAnswerD) {
        UserAnswerD = userAnswerD;
    }


}
