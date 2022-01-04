package UI.DoExam;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.R;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import Adapter.DoQuestion.DoExam_result_LoadQuestion_And_Answerkey_Adapter;
import Database.QuestionDatabase;
import Database.StoreData.StoreAnswerUser_and_AnswerKey;
import Database.Table.FalseAnswer;
import Database.Table.TrueAndFalse_Answer;
import Database.Table.User_Answers;
import Object.DoExam.QuestionAndAnswer;
import Object.DoExam.QuestionData;
import Object.DoExam.SaveCheckAnswerQuestion;
import UI.MainActivity;
import Object.DoExam.ChoseQuestion_button;
import RecycleViewObject.DoExam.ButtonViewHolder;

public class Do_exam_result extends AppCompatActivity {
//declaring references
    //private QuestionAndAnswer qa = new QuestionAndAnswer(null, null);
    //public static int[] rightWrongUnselected = new int[20];
    //public static List<Questions> questionCollection = new ArrayList<>();
    public TextView tvPassOrFail, tvRight, tvWrong, tvNonselected;
    public List<QuestionAndAnswer> DataUser;
    //Load userAnswerFromList;
    public RecyclerView QuestionAndAnswerCheck;
    public DoExam_result_LoadQuestion_And_Answerkey_Adapter adapter;

    //goto button
    Button Again , goToMain;

    //chose question button
    public DoExam_result_Button_GotoQuestionAdapter ButtonAdapter;
    public RecyclerView choseQuestion;
    //answer
    public int trueAnswer ,falseAnswer ,NotDoAnswer ;
    List<SaveCheckAnswerQuestion> store;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.do_exam_resutl);

        DataUser = (List<QuestionAndAnswer>) getIntent().getSerializableExtra("ResutlData");
        String type = getIntent().getStringExtra("DoType");

        tvPassOrFail = findViewById(R.id.DoExam_result_tv_pass);
        tvRight = findViewById(R.id.DoExam_result_tv_RightAnswer);
        tvWrong = findViewById(R.id.DoExam_result_tv_WrongAnswer);
        tvNonselected = findViewById(R.id.DoExam_result_tv_NotDo);
        choseQuestion = findViewById(R.id.DoExam_result_ButtonChoseQuestion);

        Again = findViewById(R.id.DoExam_result_doQuestionAgain);
        goToMain = findViewById(R.id.DoExam_result_BacktoMain);
        //
        QuestionAndAnswerCheck = findViewById(R.id.DoExam_result_recyclerviewAnswer);

        //Action Bar
        getSupportActionBar().setTitle("Kết Quả");

        //
        Load_User_AnswerToDataBase(DataUser,this);
        ShowResult(DataUser,this);
        tvRight.setText("Câu đúng :"+trueAnswer+"");
        tvRight.setBackgroundColor(Color.parseColor("#99FF99"));

        tvWrong.setText("Câu sai :"+falseAnswer+"");
        tvWrong.setBackgroundColor(Color.parseColor("#ff0000"));
        tvWrong.setTextColor(Color.parseColor("#ffffff"));

        tvNonselected.setText("Chưa làm :"+NotDoAnswer+"");
        tvNonselected.setBackgroundColor(Color.parseColor("#ffff00"));

        if(type.equalsIgnoreCase("DoExam")){
            if(trueAnswer < DataUser.size()-2){
                tvPassOrFail.setVisibility(View.VISIBLE);
                tvPassOrFail.setText("BẠN ĐÃ THI RỚT");
                tvPassOrFail.setBackgroundColor(Color.parseColor("#ff0000"));
            }
            else{
                tvPassOrFail.setVisibility(View.VISIBLE);
                tvPassOrFail.setText("BẠN ĐÃ THI ĐẬU");
                tvPassOrFail.setBackgroundColor(Color.parseColor("#99FF99"));
            }
        }
        else{
            tvPassOrFail.setVisibility(View.INVISIBLE);
        }



        adapter = new DoExam_result_LoadQuestion_And_Answerkey_Adapter(DataUser,this);
        QuestionAndAnswerCheck.setAdapter(adapter);
        QuestionAndAnswerCheck.setLayoutManager(new LinearLayoutManager(this));

        Again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent again = new Intent(view.getContext(), ChoseExam.class);
                view.getContext().startActivity(again);
                finish();
            }
        });

        goToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Load_User_AnswerToDataBase(DataUser,view.getContext());
                Intent gotomain = new Intent(view.getContext(), MainActivity.class);
                view.getContext().startActivity(gotomain);
                finish();
            }
        });

        //set button chose question
        ButtonAdapter = new DoExam_result_Button_GotoQuestionAdapter(this,createButtonData_bySize(DataUser.size()),DataUser);
        choseQuestion.setAdapter(ButtonAdapter);
        choseQuestion.setLayoutManager(new GridLayoutManager(this,7));

    }
    public void ShowResult(List<QuestionAndAnswer> questionAndAnswer ,Context context){
        store = new ArrayList<>();
        for(QuestionAndAnswer temp : questionAndAnswer){
            SaveCheckAnswerQuestion StoreCheckQuestionAnswer = new SaveCheckAnswerQuestion();
            StoreCheckQuestionAnswer.QuestionID = temp.data.QuestionID;
            StoreCheckQuestionAnswer.QuestionType = temp.data.QuestionType;
            StoreCheckQuestionAnswer.User_AnswersA = temp.getUser_AnswersA();
            StoreCheckQuestionAnswer.User_AnswersB = temp.getUser_AnswersB();
            StoreCheckQuestionAnswer.User_AnswersC = temp.getUser_AnswersC();
            StoreCheckQuestionAnswer.User_AnswersD = temp.getUser_AnswersD();

            if(temp.getUser_AnswersA()==0
                    && temp.getUser_AnswersB() ==0
                    && temp.getUser_AnswersC() == 0
                    && temp.getUser_AnswersD() == 0){
                StoreCheckQuestionAnswer.isDone = false;
            }
            else{
                StoreCheckQuestionAnswer.isDone = true;
            }

            StoreAnswerUser_and_AnswerKey storeTemp = QuestionDatabase.getInstance(context)
                    .user_answer_query()
                    .Answer_Checked(temp.data.QuestionID,temp.data.QuestionType);
            StoreCheckQuestionAnswer.data = storeTemp;

            store.add(StoreCheckQuestionAnswer);

        }
        //Count
        int countTrueAnswer =0;
        int countFalseAnswer = 0;
        int countNotDoAnswer = 0;
        for(SaveCheckAnswerQuestion check : store){
            if(check.isDone){
                if(check.data.getAnswerKey() == check.data.getUser_Answer()){
                    countTrueAnswer++;
                    if(QuestionDatabase.getInstance(context).trueAndFalse_answer_query().CheckUNIQUE(check.QuestionType,check.QuestionID) == null){
                        QuestionDatabase.getInstance(context).trueAndFalse_answer_query().insertTrueAnswer(
                                new TrueAndFalse_Answer(check.QuestionID,check.QuestionType,true)
                        );
                        QuestionDatabase.getInstance(context).falseAnswer_query().DeleteFalseAnswer(check.QuestionID, check.QuestionType);
                    }
                    else{
                        QuestionDatabase.getInstance(context).trueAndFalse_answer_query().UpdateQuestion(
                           check.QuestionID,check.QuestionType,true
                        );
                        QuestionDatabase.getInstance(context).falseAnswer_query().DeleteFalseAnswer(check.QuestionID, check.QuestionType);

                    }
                }
                else{
                    countFalseAnswer++;
                    if(QuestionDatabase.getInstance(context).falseAnswer_query().CheckUNIQUE(check.QuestionID,check.QuestionType) == null){
                        QuestionDatabase.getInstance(context).falseAnswer_query().InsertFalseAnswer(
                                new FalseAnswer(check.QuestionID, check.QuestionType,
                                        check.data.getAnswerKey(),
                                        check.User_AnswersA,
                                        check.User_AnswersB,
                                        check.User_AnswersC,
                                        check.User_AnswersD)
                        );
                    }
                    else{
                        QuestionDatabase.getInstance(context).falseAnswer_query().UpdateFalseAnswer(
                                check.QuestionID,
                                check.QuestionType,
                                check.User_AnswersA,
                                check.User_AnswersB,
                                check.User_AnswersC,
                                check.User_AnswersD
                        );
                    }
                }

            }
            else{
                countNotDoAnswer++;
            }
        }

        trueAnswer = countTrueAnswer;
        falseAnswer = countFalseAnswer;
        NotDoAnswer = countNotDoAnswer;
    }

    public List<ChoseQuestion_button> createButtonData_bySize(int size){
        List<ChoseQuestion_button> data = new ArrayList<>();
        for(int i = 0 ; i < size ; i++){
            data.add(new ChoseQuestion_button(i));
        }

        return data;
    }

    public class DoExam_result_Button_GotoQuestionAdapter extends RecyclerView.Adapter<ButtonViewHolder>{
        Context context;
        List<ChoseQuestion_button> ButtonPosistion;
        List<QuestionAndAnswer> dataUS;

        public DoExam_result_Button_GotoQuestionAdapter(Context context, List<ChoseQuestion_button> buttonData_bySize , List<QuestionAndAnswer> dataUS) {
            this.context =context;
            this.ButtonPosistion = buttonData_bySize;
            this.dataUS = dataUS;
        }


        @NonNull
        @Override
        public ButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(
                    R.layout.do_exam_chose_question_button_item,
                    parent,
                    false
            );
            return new ButtonViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ButtonViewHolder holder, int position) {
           ChoseQuestion_button data = ButtonPosistion.get(position);
           QuestionAndAnswer QuestionData = dataUS.get(position);
           holder.QuestionNumber.setText((data.position +1)+"");
           SaveCheckAnswerQuestion answerCheckStore  = store.get(data.position);


           if(answerCheckStore.isDone){
                if(answerCheckStore.data.getUser_Answer() == answerCheckStore.data.getAnswerKey()){
                    holder.QuestionNumber.setBackgroundColor(Color.parseColor("#99FF99"));
                    holder.QuestionNumber.setTextColor(Color.parseColor("#000000"));
                }
                else{
                    holder.QuestionNumber.setBackgroundColor(Color.parseColor("#ff0000"));
                    holder.QuestionNumber.setTextColor(Color.parseColor("#ffffff"));
                }
           }
           else{
               holder.QuestionNumber.setBackgroundColor(Color.parseColor("#ffff00"));
               holder.QuestionNumber.setTextColor(Color.parseColor("#000000"));
           }


           holder.QuestionNumber.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   QuestionAndAnswerCheck.scrollToPosition(data.position);
               }
           });
        }

        @Override
        public int getItemCount() {
            return ButtonPosistion.size();
        }
    }

    private void Load_User_AnswerToDataBase(List<QuestionAndAnswer> dataUser, Context context) {
        for(QuestionAndAnswer data : dataUser){
            if(data.getUser_AnswersA() == 1){
                if(QuestionDatabase.getInstance(context).user_answer_query().IsChecked(data.data.QuestionID,data.data.QuestionType)==null){
                    QuestionDatabase.getInstance(context).user_answer_query().Insert_UserAnswer(
                            new User_Answers(
                                    data.data.QuestionID,
                                    1,
                                    data.data.QuestionType
                            )
                    );
                }
                else{
                    QuestionDatabase.getInstance(context).user_answer_query().Update_User_Answer(
                            data.data.QuestionID,
                            1,
                            data.data.QuestionType
                    );
                }
            }
            if(data.getUser_AnswersB() == 1){
                if(QuestionDatabase.getInstance(context).user_answer_query().IsChecked(data.data.QuestionID,data.data.QuestionType)==null){
                    QuestionDatabase.getInstance(context).user_answer_query().Insert_UserAnswer(
                            new User_Answers(
                                    data.data.QuestionID,
                                    2,
                                    data.data.QuestionType
                            )
                    );
                }
                else{
                    QuestionDatabase.getInstance(context).user_answer_query().Update_User_Answer(
                            data.data.QuestionID,
                            2,
                            data.data.QuestionType
                    );
                }

            }
            if(data.getUser_AnswersC() == 1){
                if(QuestionDatabase.getInstance(context).user_answer_query().IsChecked(data.data.QuestionID,data.data.QuestionType)==null){
                    QuestionDatabase.getInstance(context).user_answer_query().Insert_UserAnswer(
                            new User_Answers(
                                    data.data.QuestionID,
                                    3,
                                    data.data.QuestionType
                            )
                    );
                }else{
                    QuestionDatabase.getInstance(context).user_answer_query().Update_User_Answer(
                            data.data.QuestionID,
                            3,
                            data.data.QuestionType
                    );
                }
            }
            if(data.getUser_AnswersD() == 1){
                if(QuestionDatabase.getInstance(context).user_answer_query().IsChecked(data.data.QuestionID,data.data.QuestionType)==null){
                    QuestionDatabase.getInstance(context).user_answer_query().Insert_UserAnswer(
                            new User_Answers(
                                    data.data.QuestionID,
                                    4,
                                    data.data.QuestionType
                            )
                    );
                }
                else{
                    QuestionDatabase.getInstance(context).user_answer_query().Update_User_Answer(
                            data.data.QuestionID,
                            4,
                            data.data.QuestionType
                    );
                }
            }
        }
    }
}

/*//indent: ctrl + shift + enter
//assigning view to references
        tvPassOrFail = findViewById(R.id.tv_pass);
        tvRight = findViewById(R.id.tv_RightAnswer);
        tvWrong = findViewById(R.id.tv_WrongAnswer);
        tvNonselected = findViewById(R.id.tv_KhongChon);
        RecyclerView rvButtonQuestions = (RecyclerView) findViewById(R.id.recyclerViewQuestion);
        RecyclerView rvAnswer = (RecyclerView) findViewById(R.id.recyclerviewAnswer);
        qa = (QuestionAndAnswer) getIntent().getExtras().getSerializable("list");
        questionCollection = new ArrayList<>();
        for(int i : qa.questionIDlist)
        {
            questionCollection.add(Do_exam.questions.get(i));
        }

//checking user answers
        for(int i = 0 ; i < 20; i++)
        {
            if(qa.userChoice[i] == 0)
            {
                rightWrongUnselected[i] = 2;
            }
            else
            {
                //if(qa.userChoice[i] == QuestionDatabase.getInstance(this).questionAnswer_query().getAnswerKey(qa.questionIDlist[i]))
                //{
                    rightWrongUnselected[i] = 1;
                //}
                //else
                {
                    rightWrongUnselected[i] = 0;
                }
            }
        }

//populating recyclerview button
        Do_exam_chose_question_button_Adapter adapter = new Do_exam_chose_question_button_Adapter(Do_exam.randomQuestion);
        rvButtonQuestions.setAdapter(adapter);
        rvButtonQuestions.setLayoutManager(new GridLayoutManager(this, 8));

//displaying interface
        int right = 0;
        int wrong = 0;
        int unselected = 0;
        for(int i : rightWrongUnselected)
        {
            if(i == 1)
            {
                right++;
            }
            else if(i == 0)
            {
                wrong++;
            }
            else if(i == 2)
            {
                unselected++;
            }
        }
        String isPassed = "Fail";
        isPassed = right >= 19? "Đậu":"Rớt";
        tvPassOrFail.setText(isPassed);
        tvRight.setText("Số câu đúng: " + right);
        tvWrong.setText("Số câu sai: " + wrong);
        tvNonselected.setText("Số câu chưa chọn: " + unselected);

//populating recycler view questions
        questionAdapter adapterAnswer = new questionAdapter(Do_exam.randomQuestion, this);
        rvAnswer.setAdapter(adapterAnswer);
        rvAnswer.setLayoutManager(new LinearLayoutManager(this));
    }*/