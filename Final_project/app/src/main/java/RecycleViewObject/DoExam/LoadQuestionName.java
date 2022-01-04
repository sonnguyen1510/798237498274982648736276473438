package RecycleViewObject.DoExam;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.R;

public class LoadQuestionName extends RecyclerView.ViewHolder {
    public TextView QuestionName;
    public ImageView traffic_sign_img1;
    public ImageView traffic_sign_img2;
    public ImageView traffic_sign_img3;
    public ImageView SA_photo_img;

    public LoadQuestionName(View itemView) {
        super(itemView);
        QuestionName = itemView.findViewById(R.id.DoExam_QuestionName);
        traffic_sign_img1 = itemView.findViewById(R.id.DoExam_traffic_sign_img1);
        traffic_sign_img2 = itemView.findViewById(R.id.DoExam_traffic_sign_img2);
        traffic_sign_img3 = itemView.findViewById(R.id.DoExam_traffic_sign_img3);

        SA_photo_img = itemView.findViewById(R.id.Do_Exam_SA_img);
    }
}
