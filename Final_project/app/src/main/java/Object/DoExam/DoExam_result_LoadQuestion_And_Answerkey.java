package Object.DoExam;

import android.view.View;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.R;

public class DoExam_result_LoadQuestion_And_Answerkey extends RecyclerView.ViewHolder {
    //public TextView cau;
    //public TextView noiDung;
    public CheckBox AnswerA;
    public CheckBox AnswerB;
    public CheckBox AnswerC;
    public CheckBox AnswerD;
    public RecyclerView QuestionName;        //public TextView giaithich;
    //public RadioGroup rg;
    public DoExam_result_LoadQuestion_And_Answerkey(View itemView) {
        super(itemView);
        //cau = (TextView) itemView.findViewById(R.id.tvQuestionID1);
        //noiDung = (TextView) itemView.findViewById(R.id.tvContentQuestionAdapter2);
        AnswerA =  itemView.findViewById(R.id.Do_exam_result_item_AnswerA);
        AnswerB =  itemView.findViewById(R.id.Do_exam_result_item_AnswerB);
        AnswerC =  itemView.findViewById(R.id.Do_exam_result_item_AnswerC);
        AnswerD =  itemView.findViewById(R.id.Do_exam_result_item_AnswerD);
        QuestionName = itemView.findViewById(R.id.Do_exam_result_item_QuestionName);
        //giaithich = (TextView) itemView.findViewById(R.id.textViewGiaiThich);
        //rg = (RadioGroup) itemView.findViewById(R.id.rgAnswer);
    }
}
