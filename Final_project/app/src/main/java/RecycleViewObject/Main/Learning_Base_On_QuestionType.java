package RecycleViewObject.Main;



import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.R;

public class Learning_Base_On_QuestionType extends RecyclerView.ViewHolder implements View.OnClickListener{
    public ImageView imglink;
    public TextView TotalQuestion;
    public TextView OptionName;
    public TextView progress;
    public ProgressBar progressBar;
    public TextView TrueQuestion;
    private ItemClickListener itemClickListener;

    public Learning_Base_On_QuestionType(@NonNull View itemView) {
        super(itemView);
        imglink = itemView.findViewById(R.id.Option_img);
        TotalQuestion = itemView.findViewById(R.id.Option_total_question);
        OptionName = itemView.findViewById(R.id.OptionName);
        progress = itemView.findViewById(R.id.OptionProgress);
        progressBar = itemView.findViewById(R.id.OptionProgressBar);
        TrueQuestion = itemView.findViewById(R.id.Option_True_Question);
        itemView.setOnClickListener(this);

    }
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
