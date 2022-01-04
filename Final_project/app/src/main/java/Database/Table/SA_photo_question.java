package Database.Table;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class SA_photo_question implements Serializable {
    @PrimaryKey()
    @NonNull
    private String QuestionID;
    private String QuestionName;
    private String QuestionImgID;
    private String AnswerA;
    private String AnswerB;
    private String AnswerC;
    private String AnswerD;
    private String QuestionType;

    public SA_photo_question(String QuestionID,String QuestionName , String AnswerA ,String AnswerB , String AnswerC,String AnswerD,String QuestionImgID,String QuestionType){
        this.QuestionID = QuestionID;
        this.QuestionName = QuestionName;
        this.AnswerA = AnswerA;
        this.AnswerB = AnswerB;
        this.AnswerC = AnswerC;
        this.AnswerD = AnswerD;
        this.QuestionImgID = QuestionImgID;
        this.QuestionType =QuestionType;
    }
    //setter


    public void setAnswerA(String answerA) {
        AnswerA = answerA;
    }

    public void setAnswerB(String answerB) {
        AnswerB = answerB;
    }

    public void setAnswerC(String answerC) {
        AnswerC = answerC;
    }

    public void setAnswerD(String answerD) {
        AnswerD = answerD;
    }

    public void setQuestionID(@NonNull String questionID) {
        QuestionID = questionID;
    }

    public void setQuestionName(String questionName) {
        QuestionName = questionName;
    }

    public void setQuestionImgID(String questionImgID) {
        QuestionImgID = questionImgID;
    }

    public void setQuestionType(String questionType) {
        QuestionType = questionType;
    }

    //getter
    public String getAnswerA() {
        return AnswerA;
    }

    public String getAnswerB() {
        return AnswerB;
    }

    public String getAnswerC() {
        return AnswerC;
    }

    public String getAnswerD() {
        return AnswerD;
    }

    public String getQuestionID() {
        return QuestionID;
    }

    public String getQuestionImgID() {
        return QuestionImgID;
    }

    public String getQuestionName() {
        return QuestionName;
    }

    public String getQuestionType() {
        return QuestionType;
    }
}
