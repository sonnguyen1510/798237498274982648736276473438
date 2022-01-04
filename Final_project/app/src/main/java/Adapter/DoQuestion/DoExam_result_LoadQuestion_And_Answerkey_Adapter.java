package Adapter.DoQuestion;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import Database.QuestionDatabase;
import Object.DoExam.DoExam_result_LoadQuestion_And_Answerkey;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.R;

import java.util.ArrayList;
import java.util.List;
import Database.Table.Questions;
import Object.DoExam.QuestionAndAnswer;
import Object.DoExam.QuestionData;

public class DoExam_result_LoadQuestion_And_Answerkey_Adapter extends RecyclerView.Adapter<DoExam_result_LoadQuestion_And_Answerkey> {
//declaring references
    private List<QuestionAndAnswer> Questiondata;
    private List<QuestionAndAnswer> Answer;
    private Context context;
    private LoadQuestionNameAdapter adapter;


//constructor
    public DoExam_result_LoadQuestion_And_Answerkey_Adapter(List<QuestionAndAnswer> data, Context con) {
        this.Questiondata = data;
        this.context = con;
        this.Answer = data;
    }

    @Override
    public DoExam_result_LoadQuestion_And_Answerkey onCreateViewHolder(ViewGroup parent, int viewType) {
        //Context context = parent.getContext();
        //LayoutInflater inflater = LayoutInflater.from(context);
        //View contactView = inflater.inflate(R.layout.do_exam_result_item, parent, false);
        //ViewHolder viewHolder = new ViewHolder(contactView);
        View ItemView = LayoutInflater.from(context).inflate(
                R.layout.do_exam_result_item,
                parent,
                false
        );

        return new DoExam_result_LoadQuestion_And_Answerkey(ItemView);
    }

// Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(DoExam_result_LoadQuestion_And_Answerkey holder, int position) {
        QuestionAndAnswer GetquestionAndAnswer = Questiondata.get(position);
        QuestionAndAnswer questionAndAnswer = Answer.get(GetquestionAndAnswer.position);
        QuestionData item = questionAndAnswer.data;
        //AnswerName
        holder.AnswerA.setEnabled(false);
        holder.AnswerB.setEnabled(false);
        holder.AnswerC.setEnabled(false);
        holder.AnswerD.setEnabled(false);
        Log.println(Log.INFO, String.valueOf(1),position+"befor"+questionAndAnswer.getUser_AnswersB()+"");

        //-------------------------------SET CHECKED------------------------------------


        if(questionAndAnswer.getUser_AnswersA() == 1){
            holder.AnswerA.setChecked(true);
        }
        else
            holder.AnswerA.setChecked(false);
        if(questionAndAnswer.getUser_AnswersB()==1){
            holder.AnswerB.setChecked(true);
        }else
            holder.AnswerB.setChecked(false);

        if(questionAndAnswer.getUser_AnswersC()==1){
            holder.AnswerC.setChecked(true);
        }
        else
            holder.AnswerC.setChecked(false);

        if(questionAndAnswer.getUser_AnswersD() == 1){
            holder.AnswerD.setChecked(true);
        }
        else
            holder.AnswerD.setChecked(false);

        Log.println(Log.INFO, String.valueOf(1),position+"after"+questionAndAnswer.getUser_AnswersB()+"");

        //Question Name
        List<QuestionData> LoadQuestionName = new ArrayList<>();
        LoadQuestionName.add(item);
        adapter = new LoadQuestionNameAdapter(LoadQuestionName, context.getApplicationContext());
        holder.QuestionName.setAdapter(adapter);
        holder.QuestionName.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));


        //-------------------------------SET COLOR ANSWER--------------------------------
        int Answer_key = QuestionDatabase
                .getInstance(context.getApplicationContext())
                .questionAnswer_query().getAnswerKey(item.QuestionID, item.QuestionType);

        if(Answer_key == 1){
            holder.AnswerA.setBackgroundColor(Color.parseColor("#99FF99"));
            //holder.AnswerA.setTextColor(Color.parseColor("#339900"));
            if(questionAndAnswer.getUser_AnswersB() == 1){
                holder.AnswerB.setBackgroundColor(Color.parseColor("#ff0000"));
                holder.AnswerB.setTextColor(Color.parseColor("#000000"));
            }
            else{
                holder.AnswerB.setBackgroundColor(Color.parseColor("#ffffff"));
                holder.AnswerB.setTextColor(Color.parseColor("#000000"));
            }


            if(questionAndAnswer.getUser_AnswersC() == 1){
                holder.AnswerC.setBackgroundColor(Color.parseColor("#ff0000"));
                holder.AnswerC.setTextColor(Color.parseColor("#000000"));
            }
            else{
                holder.AnswerC.setBackgroundColor(Color.parseColor("#ffffff"));
                holder.AnswerC.setTextColor(Color.parseColor("#000000"));
            }

            if(questionAndAnswer.getUser_AnswersD() == 1){
                holder.AnswerD.setBackgroundColor(Color.parseColor("#ff0000"));
                holder.AnswerD.setTextColor(Color.parseColor("#000000"));
            }
            else{
                holder.AnswerD.setBackgroundColor(Color.parseColor("#ffffff"));
                holder.AnswerD.setTextColor(Color.parseColor("#000000"));
            }

        }

        if(Answer_key == 2){
            holder.AnswerB.setBackgroundColor(Color.parseColor("#99FF99"));
            //holder.AnswerB.setTextColor(Color.parseColor("#339900"));
            if(questionAndAnswer.getUser_AnswersA() == 1){
                holder.AnswerA.setBackgroundColor(Color.parseColor("#ff0000"));
                holder.AnswerA.setTextColor(Color.parseColor("#000000"));
            }
            else{
                holder.AnswerA.setBackgroundColor(Color.parseColor("#ffffff"));
                holder.AnswerA.setTextColor(Color.parseColor("#000000"));
            }

            if(questionAndAnswer.getUser_AnswersC() == 1){
                holder.AnswerC.setBackgroundColor(Color.parseColor("#ff0000"));
                holder.AnswerC.setTextColor(Color.parseColor("#000000"));
            }
            else{
                holder.AnswerC.setBackgroundColor(Color.parseColor("#ffffff"));
                holder.AnswerC.setTextColor(Color.parseColor("#000000"));
            }

            if(questionAndAnswer.getUser_AnswersD() == 1){
                holder.AnswerD.setBackgroundColor(Color.parseColor("#ff0000"));
                holder.AnswerD.setTextColor(Color.parseColor("#000000"));
            }
            else{
                holder.AnswerD.setBackgroundColor(Color.parseColor("#ffffff"));
                holder.AnswerD.setTextColor(Color.parseColor("#000000"));
            }

        }else



        if(Answer_key == 3){
            holder.AnswerC.setBackgroundColor(Color.parseColor("#99FF99"));
            //holder.AnswerC.setTextColor(Color.parseColor("#339900"));
            if(questionAndAnswer.getUser_AnswersB() == 1){
                holder.AnswerB.setBackgroundColor(Color.parseColor("#ff0000"));
                holder.AnswerB.setTextColor(Color.parseColor("#000000"));
            }
            else{
                holder.AnswerB.setBackgroundColor(Color.parseColor("#ffffff"));
                holder.AnswerB.setTextColor(Color.parseColor("#000000"));
            }

            if(questionAndAnswer.getUser_AnswersA() == 1){
                holder.AnswerA.setBackgroundColor(Color.parseColor("#ff0000"));
                holder.AnswerA.setTextColor(Color.parseColor("#000000"));
            }
            else{
                holder.AnswerA.setBackgroundColor(Color.parseColor("#ffffff"));
                holder.AnswerA.setTextColor(Color.parseColor("#000000"));
            }

            if(questionAndAnswer.getUser_AnswersD() == 1){
                holder.AnswerD.setBackgroundColor(Color.parseColor("#ff0000"));
                holder.AnswerD.setTextColor(Color.parseColor("#000000"));
            }
            else{
                holder.AnswerD.setBackgroundColor(Color.parseColor("#ffffff"));
                holder.AnswerD.setTextColor(Color.parseColor("#000000"));
            }

        }



        if(Answer_key == 4){
            holder.AnswerD.setBackgroundColor(Color.parseColor("#99FF99"));
            //holder.AnswerD.setTextColor(Color.parseColor("#339900"));
            if(questionAndAnswer.getUser_AnswersB() == 1){
                holder.AnswerB.setBackgroundColor(Color.parseColor("#ff0000"));
                holder.AnswerB.setTextColor(Color.parseColor("#000000"));
            }
            else{
                holder.AnswerB.setBackgroundColor(Color.parseColor("#ffffff"));
                holder.AnswerB.setTextColor(Color.parseColor("#000000"));
            }

            if(questionAndAnswer.getUser_AnswersC() == 1){
                holder.AnswerC.setBackgroundColor(Color.parseColor("#ff0000"));
                holder.AnswerC.setTextColor(Color.parseColor("#000000"));
            }
            else{
                holder.AnswerC.setBackgroundColor(Color.parseColor("#ffffff"));
                holder.AnswerC.setTextColor(Color.parseColor("#000000"));
            }

            if(questionAndAnswer.getUser_AnswersA() == 1){
                holder.AnswerA.setBackgroundColor(Color.parseColor("#ff0000"));
                holder.AnswerA.setTextColor(Color.parseColor("#000000"));
            }
            else{
                holder.AnswerA.setBackgroundColor(Color.parseColor("#ffffff"));
                holder.AnswerA.setTextColor(Color.parseColor("#000000"));
            }

        }
        /*else
        {
            holder.AnswerB.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.AnswerB.setTextColor(Color.parseColor("#000000"));

            holder.AnswerC.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.AnswerC.setTextColor(Color.parseColor("#000000"));

            holder.AnswerA.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.AnswerA.setTextColor(Color.parseColor("#000000"));
        }*/

        //
        if(item.QuestionType.equalsIgnoreCase("LyThuyet")){
            //Answer A
            if(!item.TheoryQuestionData.getAnswerA().equalsIgnoreCase("null")){
                holder.AnswerA.setVisibility(View.VISIBLE);
                holder.AnswerA.setText(item.TheoryQuestionData.getAnswerA());
            }
            else{
                holder.AnswerA.setVisibility(View.GONE);
            }

            //Answer B
            if(!item.TheoryQuestionData.getAnswerB().equalsIgnoreCase("null")){
                holder.AnswerB.setVisibility(View.VISIBLE);
                holder.AnswerB.setText(item.TheoryQuestionData.getAnswerB());
            }
            else{
                holder.AnswerB.setVisibility(View.GONE);
            }

            //Answer C
            if(!item.TheoryQuestionData.getAnswerC().equalsIgnoreCase("null")){
                holder.AnswerC.setVisibility(View.VISIBLE);
                holder.AnswerC.setText(item.TheoryQuestionData.getAnswerC());
            }
            else{
                holder.AnswerC.setVisibility(View.GONE);
            }

            //Answer D
            if(!item.TheoryQuestionData.getAnswerD().equalsIgnoreCase("null")){
                holder.AnswerD.setVisibility(View.VISIBLE);
                holder.AnswerD.setText(item.TheoryQuestionData.getAnswerA());
            }
            else{
                holder.AnswerD.setVisibility(View.GONE);
            }


        }
        else if(item.QuestionType.equalsIgnoreCase("BienBao")){
            //Answer A
            if(!item.traffic_sign_questions.getTraffic_sign_AnswerA().equalsIgnoreCase("null")){
                holder.AnswerA.setVisibility(View.VISIBLE);
                holder.AnswerA.setText(item.traffic_sign_questions.getTraffic_sign_AnswerA());
            }
            else{
                holder.AnswerA.setVisibility(View.GONE);
            }

            //Answer B
            if(!item.traffic_sign_questions.getTraffic_sign_AnswerB().equalsIgnoreCase("null")){
                holder.AnswerB.setVisibility(View.VISIBLE);
                holder.AnswerB.setText(item.traffic_sign_questions.getTraffic_sign_AnswerB());
            }
            else{
                holder.AnswerB.setVisibility(View.GONE);
            }

            //Answer C
            if(!item.traffic_sign_questions.getTraffic_sign_AnswerC().equalsIgnoreCase("null")){
                holder.AnswerC.setVisibility(View.VISIBLE);
                holder.AnswerC.setText(item.traffic_sign_questions.getTraffic_sign_AnswerC());
            }
            else{
                holder.AnswerC.setVisibility(View.GONE);
            }

            //Answer D
            if(!item.traffic_sign_questions.getTraffic_sign_AnswerD().equalsIgnoreCase("null")){
                holder.AnswerD.setVisibility(View.VISIBLE);
                holder.AnswerD.setText(item.traffic_sign_questions.getTraffic_sign_AnswerD());
            }
            else{
                holder.AnswerD.setVisibility(View.GONE);
            }
        }
        else{
            //Answer A
            if(!item.sa_photo_question.getAnswerA().equalsIgnoreCase("null")){
                holder.AnswerA.setVisibility(View.VISIBLE);
                holder.AnswerA.setText(item.sa_photo_question.getAnswerA());
            }
            else{
                holder.AnswerA.setVisibility(View.GONE);
            }

            //Answer B
            if(!item.sa_photo_question.getAnswerB().equalsIgnoreCase("null")){
                holder.AnswerB.setVisibility(View.VISIBLE);
                holder.AnswerB.setText(item.sa_photo_question.getAnswerB());
            }
            else{
                holder.AnswerB.setVisibility(View.GONE);
            }

            //Answer C
            if(!item.sa_photo_question.getAnswerC().equalsIgnoreCase("null")){
                holder.AnswerC.setVisibility(View.VISIBLE);
                holder.AnswerC.setText(item.sa_photo_question.getAnswerC());
            }
            else{
                holder.AnswerC.setVisibility(View.GONE);
            }

            //Answer D
            if(!item.sa_photo_question.getAnswerD().equalsIgnoreCase("null")){
                holder.AnswerD.setVisibility(View.VISIBLE);
                holder.AnswerD.setText(item.sa_photo_question.getAnswerA());
            }
            else{
                holder.AnswerD.setVisibility(View.GONE);
            }

            //Question Name
        }





    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return Questiondata.size();
    }




}
   /* public void ChangeColor(Questions ){
        int AnswerKey = QuestionDatabase.getInstance(context)
                .questionAnswer_query()
                .getAnswerKey(questions.getQuestionID(),questions.getQuestionType());

        if(AnswerKey == 1){//question A true
            //true answer
            AnswerA.setBackgroundColor(Color.parseColor("#99FF99"));
            AnswerA.setTextColor(Color.parseColor("#339900"));
            //False answer
            AnswerB.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnswerB.setTextColor(Color.parseColor("#000000"));

            AnswerC.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnswerC.setTextColor(Color.parseColor("#000000"));

            AnswerD.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnswerD.setTextColor(Color.parseColor("#000000"));

        }
        else if (AnswerKey == 2){//question B true
            //true answer
            AnswerB.setBackgroundColor(Color.parseColor("#99FF99"));
            AnswerB.setTextColor(Color.parseColor("#339900"));
            //False answer
            AnswerA.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnswerA.setTextColor(Color.parseColor("#000000"));

            AnswerC.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnswerC.setTextColor(Color.parseColor("#000000"));

            AnswerD.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnswerD.setTextColor(Color.parseColor("#000000"));
        }
        else if(AnswerKey == 3){//question C true
            //true answer
            AnswerC.setBackgroundColor(Color.parseColor("#99FF99"));
            AnswerC.setTextColor(Color.parseColor("#339900"));
            //False answer
            AnswerB.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnswerB.setTextColor(Color.parseColor("#000000"));

            AnswerA.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnswerA.setTextColor(Color.parseColor("#000000"));

            AnswerD.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnswerD.setTextColor(Color.parseColor("#000000"));
        }
        else{//question D true
            //true answer
            AnswerD.setBackgroundColor(Color.parseColor("#99FF99"));
            AnswerD.setTextColor(Color.parseColor("#339900"));
            //False answer
            AnswerB.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnswerB.setTextColor(Color.parseColor("#000000"));

            AnswerC.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnswerC.setTextColor(Color.parseColor("#000000"));

            AnswerA.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnswerA.setTextColor(Color.parseColor("#000000"));
        }
    }*/


/*Questions q = questions.get(position);
        holder.cau.setText("Câu số: " + String.valueOf(position + 1));
        holder.noiDung.setText(q.getQuestionName());
        holder.traLoi1.setText(q.getAnswerA());
        holder.traLoi2.setText(q.getAnswerB());
        holder.traLoi3.setText(q.getAnswerC());
        holder.traLoi4.setText(q.getAnswerD());
        holder.traLoi1.setEnabled(false);
        holder.traLoi2.setEnabled(false);
        holder.traLoi3.setEnabled(false);
        holder.traLoi4.setEnabled(false);

//changing color of question content according to user's answer.
        int correct = 2;
        //correct = Do_exam_result.rightWrongUnselected[position];
        if(correct == 2)
        {
            holder.noiDung.setBackgroundColor(Color.rgb(219, 167, 11));
        }
        else if(correct == 0)
        {
            holder.noiDung.setBackgroundColor(Color.rgb(199, 16, 6));
            //int rightAnswer = QuestionDatabase.getInstance(context).questionAnswer_query().getAnswerKey(q.getQuestionID());
            //String letter = rightAnswer == 1? "A": rightAnswer == 2? "B" : rightAnswer == 3? "C" : "D";
            //holder.giaithich.setText("Câu trả lời đúng là: " +  letter);
        }
        else if(correct == 1)
        {
            holder.noiDung.setBackgroundColor(Color.rgb(9, 186, 127));
        }

//set user's answer
        /*holder.rg.clearCheck();
        if(Do_exam.userAnswer[position] == 1)
        {
            holder.rg.check(holder.traLoi1.getId());
        }else if(Do_exam.userAnswer[position] == 2)
        {
            holder.rg.check(holder.traLoi2.getId());
        }else if(Do_exam.userAnswer[position] == 3)
        {
            holder.rg.check(holder.traLoi3.getId());
        }else if(Do_exam.userAnswer[position] == 4)
        {
            holder.rg.check(holder.traLoi4.getId());
        }*/
