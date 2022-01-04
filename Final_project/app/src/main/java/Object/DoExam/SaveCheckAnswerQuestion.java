package Object.DoExam;

import Database.StoreData.StoreAnswerUser_and_AnswerKey;
import Database.Table.User_Answers;

public class SaveCheckAnswerQuestion {
    public StoreAnswerUser_and_AnswerKey data;
    public String QuestionID;
    public String QuestionType;
    //store User Answer
    public int User_AnswersA;
    public int User_AnswersB;
    public int User_AnswersC;
    public int User_AnswersD;
    //answer check bash on data in database
    public boolean isTrue;
    public boolean isDone = true;
}
