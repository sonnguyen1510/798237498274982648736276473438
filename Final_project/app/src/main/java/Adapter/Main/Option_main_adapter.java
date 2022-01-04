package Adapter.Main;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import Database.QuestionDatabase;
import Object.Main.Set_main_option;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.R;

import RecycleViewObject.Main.Option_main_activity;
import UI.DoExam.ChoseExam;
import UI.Option.WrongAnswer;

import java.util.List;

public class Option_main_adapter extends RecyclerView.Adapter<Option_main_activity> {
    private List<Set_main_option> data;
    private Context context;

    public Option_main_adapter(List<Set_main_option> data , Context context){
        this.data = data;
        this.context = context;
    }

    public Option_main_adapter(Context context, List<Set_main_option> main_data) {
        this.data = main_data;
        this.context = context;
    }

    @NonNull
    @Override
    public Option_main_activity onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(context).inflate(
                R.layout.overal_option_item,
                parent,
                false
        );
        return new Option_main_activity(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull Option_main_activity holder, int position) {
        Set_main_option item = data.get(position);
        holder.img_link.setImageResource(item.img_location);
        holder.Name.setText(item.optionName);
        holder.img_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item.Go_to == WrongAnswer.class){
                    if(QuestionDatabase.getInstance(context).falseAnswer_query().getAllFalseAnswerQuestion().size() == 0){
                        AlertDialog.Builder dialog = new AlertDialog.Builder(context)
                                .setTitle("Nhắc nhở")
                                .setMessage("Hiện tại bạn chưa có câu hỏi sai nào , bạn có muốn giải đề ?")
                                .setPositiveButton("Giải đề", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent DoExam = new Intent(context, ChoseExam.class);
                                        context.startActivity(DoExam);
                                    }

                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                        Dialog dia = dialog.create();
                        dia.show();
                    }
                    else {
                        Intent intent = new Intent(view.getContext(),item.Go_to);
                        view.getContext().startActivity(intent);
                    }
                }
                else{
                    Intent intent = new Intent(view.getContext(),item.Go_to);
                    view.getContext().startActivity(intent);
                }
            }
        });
    }




    @Override
    public int getItemCount() {
        return data.size();
    }
}
