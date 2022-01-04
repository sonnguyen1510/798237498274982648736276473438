package Object.DoExam;
import java.io.Serializable;

import Database.Table.*;

public class QuestionData implements Serializable {
    public String QuestionID;
    public Questions TheoryQuestionData;
    public traffic_sign_questions traffic_sign_questions;
    public SA_photo_question sa_photo_question;
    public String QuestionType;
    public int position;

    public QuestionData(Questions questions ,String QuestionType,int position){
        this.TheoryQuestionData = questions;
        this.QuestionType = questions.getQuestionType();
        this.QuestionID = questions.getQuestionID();
        this.position=position;
    }

    public QuestionData(traffic_sign_questions traffic_sign_questions, String QuestionType,int position){
        this.traffic_sign_questions = traffic_sign_questions;
        this.QuestionType = traffic_sign_questions.getQuestionType();
        this.QuestionID = traffic_sign_questions.getTraffic_sign_QuestionID();
        this.position=position;
    }

    public QuestionData(SA_photo_question sa_photo_question, String QuestionType,int position){
        this.sa_photo_question = sa_photo_question;
        this.QuestionType = sa_photo_question.getQuestionType();
        this.QuestionID = sa_photo_question.getQuestionID();
        this.position=position;
    }
    //
}
