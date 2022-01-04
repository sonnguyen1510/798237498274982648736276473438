package UI.DoExam.DoExam_interface;

import android.content.Context;

import java.util.List;

import Object.DoExam.QuestionAndAnswer;
import Object.DoExam.QuestionData;

public interface Do_Exam_interface {
    //Load Question Data
    public void SetQuestionName_baseOn_questionType(QuestionData questionData , Context context);
    public void SetAnswer_baseON_questionType(QuestionData questionData,Context context);

    //Load UserData
    public List<QuestionAndAnswer> Create_List_0f_QuestionAndAnswer_by_size(List<QuestionData> data);
    public void SetAnswerChosen_by_user(QuestionAndAnswer getQuestionData);
    
}
