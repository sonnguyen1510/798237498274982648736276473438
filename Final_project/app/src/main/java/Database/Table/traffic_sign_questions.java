package Database.Table;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class traffic_sign_questions implements Serializable {
    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "QuestionID")
    private String traffic_sign_QuestionID;
    @ColumnInfo(name = "QuestionName")
    private String traffic_sign_QuestionName;
    @ColumnInfo(name = "Picture1")
    private String traffic_sign_id1;
    @ColumnInfo(name = "Picture2")
    private String traffic_sign_id2;
    @ColumnInfo(name = "Picture3")
    private String traffic_sign_id3;
    @ColumnInfo(name = "AnswerA")
    private String traffic_sign_AnswerA;
    @ColumnInfo(name = "AnswerB")
    private String traffic_sign_AnswerB;
    @ColumnInfo(name = "AnswerC")
    private String traffic_sign_AnswerC;
    @ColumnInfo(name = "AnswerD")
    private String traffic_sign_AnswerD;
    @ColumnInfo(name = "QuestionType")
    private String QuestionType;

    public traffic_sign_questions(String traffic_sign_QuestionID,
                                  String traffic_sign_QuestionName,
                                  String traffic_sign_id1,
                                  String traffic_sign_id2,
                                  String traffic_sign_id3,
                                  String traffic_sign_AnswerA,
                                  String traffic_sign_AnswerB,
                                  String traffic_sign_AnswerC,
                                  String traffic_sign_AnswerD,
                                  String QuestionType){
        this.traffic_sign_QuestionID = traffic_sign_QuestionID;
        this.traffic_sign_QuestionName = traffic_sign_QuestionName;
        this.traffic_sign_id1 = traffic_sign_id1;
        this.traffic_sign_id2 = traffic_sign_id2;
        this.traffic_sign_id3 = traffic_sign_id3;
        this.traffic_sign_AnswerA = traffic_sign_AnswerA;
        this.traffic_sign_AnswerB = traffic_sign_AnswerB;
        this.traffic_sign_AnswerC = traffic_sign_AnswerC;
        this.traffic_sign_AnswerD = traffic_sign_AnswerD;
        this.QuestionType =QuestionType;
    }

    //getter


    public String getTraffic_sign_AnswerA() {
        return traffic_sign_AnswerA;
    }

    public String getTraffic_sign_AnswerB() {
        return traffic_sign_AnswerB;
    }

    public String getTraffic_sign_AnswerC() {
        return traffic_sign_AnswerC;
    }

    public String getTraffic_sign_AnswerD() {
        return traffic_sign_AnswerD;
    }

    public String getTraffic_sign_id1() {
        return traffic_sign_id1;
    }

    public String getTraffic_sign_id2() {
        return traffic_sign_id2;
    }

    public String getTraffic_sign_id3() {
        return traffic_sign_id3;
    }

    @NonNull
    public String getTraffic_sign_QuestionID() {
        return traffic_sign_QuestionID;
    }

    public String getTraffic_sign_QuestionName() {
        return traffic_sign_QuestionName;
    }

    public String getQuestionType() {
        return QuestionType;
    }

    //setter


    public void setTraffic_sign_AnswerA(String traffic_sign_AnswerA) {
        this.traffic_sign_AnswerA = traffic_sign_AnswerA;
    }

    public void setTraffic_sign_AnswerB(String traffic_sign_AnswerB) {
        this.traffic_sign_AnswerB = traffic_sign_AnswerB;
    }

    public void setTraffic_sign_AnswerC(String traffic_sign_AnswerC) {
        this.traffic_sign_AnswerC = traffic_sign_AnswerC;
    }

    public void setTraffic_sign_AnswerD(String traffic_sign_AnswerD) {
        this.traffic_sign_AnswerD = traffic_sign_AnswerD;
    }

    public void setTraffic_sign_id1(String traffic_sign_id1) {
        this.traffic_sign_id1 = traffic_sign_id1;
    }

    public void setTraffic_sign_id2(String traffic_sign_id2) {
        this.traffic_sign_id2 = traffic_sign_id2;
    }

    public void setTraffic_sign_id3(String traffic_sign_id3) {
        this.traffic_sign_id3 = traffic_sign_id3;
    }

    public void setTraffic_sign_QuestionID(@NonNull String traffic_sign_QuestionID) {
        this.traffic_sign_QuestionID = traffic_sign_QuestionID;
    }

    public void setTraffic_sign_QuestionName(String traffic_sign_QuestionName) {
        this.traffic_sign_QuestionName = traffic_sign_QuestionName;
    }

    public void setQuestionType(String questionType) {
        QuestionType = questionType;
    }
}
