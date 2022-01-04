package Adapter.Main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import Database.QuestionDatabase;
import Database.Table.TrueAndFalse_Answer;
import Object.Main.Learning_Base_On_QuestionType_OBJ;
import RecycleViewObject.Main.ItemClickListener;
import RecycleViewObject.Main.Learning_Base_On_QuestionType;
import UI.Option.LearningOrDoexam_base_on_questionType;
import UI.Option.Learning_SA_photo_question;
import UI.Option.Learning_theory_question;
import UI.Option.Learning_traffic_signs_question;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Learning_Base_On_QuestionType_adapter extends RecyclerView.Adapter<Learning_Base_On_QuestionType> {
    private  List<Learning_Base_On_QuestionType_OBJ> data;
    private Context context;



    public Learning_Base_On_QuestionType_adapter(Context context , List<Learning_Base_On_QuestionType_OBJ> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public Learning_Base_On_QuestionType onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewitem = LayoutInflater.from(context).inflate(
                R.layout.option_main_item_base_on_questiontype,
                parent,
                false
        );


        return new Learning_Base_On_QuestionType(viewitem);
    }

    @Override
    public void onBindViewHolder(@NonNull Learning_Base_On_QuestionType holder, int position) {

        Learning_Base_On_QuestionType_OBJ ItemData = data.get(position);
        holder.imglink.setImageResource(ItemData.img_location);
        holder.OptionName.setText(ItemData.Option_main_name);
        holder.TotalQuestion.setText("Số câu: "+ItemData.Total_question);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(ItemData.go_to == Learning_theory_question.class){
                    Intent intent = new Intent(view.getContext(), LearningOrDoexam_base_on_questionType.class);
                    intent.putExtra("Go_to", (Serializable) ItemData);
                    view.getContext().startActivity(intent);
                }
                else if(ItemData.go_to == Learning_traffic_signs_question.class){
                    Intent intent = new Intent(view.getContext(),LearningOrDoexam_base_on_questionType.class);
                    intent.putExtra("Go_to", (Serializable) ItemData);
                    view.getContext().startActivity(intent);
                }
                else{
                    Intent intent = new Intent(view.getContext(),LearningOrDoexam_base_on_questionType.class);
                    intent.putExtra("Go_to", (Serializable) ItemData);
                    view.getContext().startActivity(intent);
                }
            }
        });

        //loadProgress
        if(ItemData.go_to == Learning_theory_question.class){
            LoadProgress(holder,position,"LyThuyet");
        }
        else if(ItemData.go_to == Learning_theory_question.class){
            LoadProgress(holder,position,"BienBao");
        }
        else
            LoadProgress(holder,position,"SaHinh");

    }

    public void LoadProgress(@NonNull Learning_Base_On_QuestionType holder , int position , String QuestionType){
        List<TrueAndFalse_Answer> trueAndFalse_answer = new ArrayList<>();
        int countNumberOfTrueAnswer;
        int countNumberOfQuestion;

        if(QuestionType.equalsIgnoreCase("Lythuyet")){
            trueAndFalse_answer = QuestionDatabase.getInstance(context)
                    .trueAndFalse_answer_query().getAllTrueAnswerByQuestionType("Lythuyet",true);;
            countNumberOfTrueAnswer = trueAndFalse_answer.size();

            countNumberOfQuestion = QuestionDatabase.getInstance(context).question_query().getAllQuestion().size();

        }
        else if(QuestionType.equalsIgnoreCase("BienBao")){
            trueAndFalse_answer = QuestionDatabase.getInstance(context)
                    .trueAndFalse_answer_query().getAllTrueAnswerByQuestionType("BienBao",true);
            countNumberOfTrueAnswer = trueAndFalse_answer.size();

            countNumberOfQuestion = QuestionDatabase.getInstance(context).traffic_sign_questions_query().getAllQuestion().size();
        }
        else{
            trueAndFalse_answer = QuestionDatabase.getInstance(context)
                    .trueAndFalse_answer_query().getAllTrueAnswerByQuestionType("SaHinh",true);
            countNumberOfTrueAnswer = trueAndFalse_answer.size();

            countNumberOfQuestion = QuestionDatabase.getInstance(context).sa_photo_question_query().getAllQuestion().size();
        }
        holder.TrueQuestion.setText("Số câu đã trả lời đúng : "+countNumberOfTrueAnswer);

        if (countNumberOfTrueAnswer == 0){
            holder.progress.setText("0%");
            holder.progressBar.setProgress(0);
        }
        else{
            if((countNumberOfTrueAnswer/countNumberOfQuestion*1.0)*100.0 >= 0.1){
                holder.progress.setText((countNumberOfTrueAnswer/countNumberOfQuestion*1.0)*100.0 + "%");
                holder.progressBar.setProgress(countNumberOfTrueAnswer/countNumberOfQuestion*100);
            }
            else{
                holder.progress.setText(0.1 + "%");
                holder.progressBar.setProgress((int) 0.1);
            }

        }
    }





    @Override
    public int getItemCount() {
        return data.size();
    }
}
