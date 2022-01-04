package Adapter.DoQuestion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.R;

import java.util.List;
import java.util.zip.Inflater;

import Database.QuestionDatabase;
import Object.DoExam.QuestionData;
import RecycleViewObject.DoExam.LoadQuestionName;

public class LoadQuestionNameAdapter extends RecyclerView.Adapter<LoadQuestionName> {
     private List<QuestionData> data;
     private Context context;

     public LoadQuestionNameAdapter(List<QuestionData> data, Context context){
         this.data = data;
         this.context = context;
     }

    @NonNull
    @Override
    public LoadQuestionName onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(
                R.layout.question_name_item,
                parent,
                false
        );
         return new LoadQuestionName(item) ;
    }

    @Override
    public void onBindViewHolder(@NonNull LoadQuestionName holder, int position) {
        QuestionData item = data.get(position);
        if(item.QuestionType.equalsIgnoreCase("LyThuyet")){
            holder.SA_photo_img.setVisibility(View.GONE);
            holder.traffic_sign_img1.setVisibility(View.GONE);
            holder.traffic_sign_img2.setVisibility(View.GONE);
            holder.traffic_sign_img3.setVisibility(View.GONE);

            holder.QuestionName.setText("Câu"+item.position+": "+item.TheoryQuestionData.getQuestionName());
        }
        else if(item.QuestionType.equalsIgnoreCase("BienBao")){
            holder.SA_photo_img.setVisibility(View.GONE);
            holder.traffic_sign_img1.setVisibility(View.VISIBLE);
            holder.traffic_sign_img2.setVisibility(View.VISIBLE);
            holder.traffic_sign_img3.setVisibility(View.VISIBLE);

            //get imglink
            if(!item.traffic_sign_questions.getTraffic_sign_id1().equalsIgnoreCase("null")){
                int img1 = QuestionDatabase.getInstance(context).traffic_signs_query().getTrafficSignlink(
                        item.traffic_sign_questions.getTraffic_sign_id1()
                );
                holder.traffic_sign_img1.setImageResource(img1);
            }else
                holder.traffic_sign_img1.setVisibility(View.GONE);

            //get img link

            if(!item.traffic_sign_questions.getTraffic_sign_id2().equalsIgnoreCase("null")){
                int img2 = QuestionDatabase.getInstance(context).traffic_signs_query().getTrafficSignlink(
                        item.traffic_sign_questions.getTraffic_sign_id2()
                );
                holder.traffic_sign_img2.setImageResource(img2);
            }else
                holder.traffic_sign_img2.setVisibility(View.GONE);

            //get img link
            if(!item.traffic_sign_questions.getTraffic_sign_id3().equalsIgnoreCase("null")){
                int img3 = QuestionDatabase.getInstance(context).traffic_signs_query().getTrafficSignlink(
                        item.traffic_sign_questions.getTraffic_sign_id3()
                );
                holder.traffic_sign_img3.setImageResource(img3);
            }
            else
                holder.traffic_sign_img3.setVisibility(View.GONE);


            holder.QuestionName.setText("Câu"+item.position+": "+item.traffic_sign_questions.getTraffic_sign_QuestionName());
        }
        else{
            holder.SA_photo_img.setVisibility(View.VISIBLE);
            holder.traffic_sign_img1.setVisibility(View.GONE);
            holder.traffic_sign_img2.setVisibility(View.GONE);
            holder.traffic_sign_img3.setVisibility(View.GONE);

            int imgLink = QuestionDatabase.getInstance(context).sa_photos_query().getImgLink(
              item.sa_photo_question.getQuestionImgID()
            );
            holder.SA_photo_img.setImageResource(imgLink);

            holder.QuestionName.setText("Câu"+item.position+": "+item.sa_photo_question.getQuestionName());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
