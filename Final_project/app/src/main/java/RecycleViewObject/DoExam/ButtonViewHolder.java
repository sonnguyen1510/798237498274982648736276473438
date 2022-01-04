package RecycleViewObject.DoExam;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.R;

public class ButtonViewHolder extends RecyclerView.ViewHolder {
    public Button QuestionNumber;
    public ButtonViewHolder(@NonNull View itemView) {
        super(itemView);
        QuestionNumber = itemView.findViewById(R.id.DoExan_buttonQuestion);
    }
}
