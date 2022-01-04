package UI.DoExam;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import Adapter.DoQuestion.LoadQuestionNameAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import RecycleViewObject.DoExam.ButtonViewHolder;

import Object.DoExam.ChoseQuestion_button;
import Object.DoExam.QuestionAndAnswer;
import Object.DoExam.QuestionData;
import UI.DoExam.DoExam_interface.Do_Exam_interface;


public class Do_exam extends AppCompatActivity implements Do_Exam_interface {
    //question bank
    //public static List<Questions> questions = new ArrayList<>();
    //public static List<Questions> randomQuestion = new ArrayList<>();
    //public static int[] userAnswer = new int[20];
    //public static int[] idList = new int[20];
    private QuestionAndAnswer qaList;
    //public static TextView number, content;
    public CheckBox cb1, cb2, cb3, cb4;
    public int soCauHoiDangDuocChon = 1;
    Button btnPrevious, btnNext, btnSubmit;
    //Question name
    public int position;
    public List<QuestionAndAnswer> UserQuestionStore;
    //ChoseQuestion button
    public List<ChoseQuestion_button> buttonPositionList = new ArrayList<>();
    public RecyclerView ChoseQuestionButton_RecycleView;
    public ChoseQuestion_button_Adapter ChoseQuestionButtonAdapter;
    public Context context = this;
    //QuestionData
    private List<QuestionData> data = new ArrayList<>();

    //time
    CountDownTimer cTimer = null;
    TextView Show_countdown ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.do_exam);
        Show_countdown = findViewById(R.id.DoExam_Time);

//Assigning view to references
        //number = findViewById(R.id.textViewSoCauHoi);
        //content = findViewById(R.id.tvContentQuestion1);
        cb1 = findViewById(R.id.DoExamAnswerA);
        cb2 = findViewById(R.id.DoExamAnswerB);
        cb3 = findViewById(R.id.DoExamAnswerC);
        cb4 = findViewById(R.id.DoExamAnswerD);

        btnSubmit = findViewById(R.id.Do_Exam_ButtonSubmit);
        btnPrevious = findViewById(R.id.DoExam_Button_Previous);
        btnNext = findViewById(R.id.DoExam_buttonNext);
        RecyclerView rvButtonQuestions = (RecyclerView) findViewById(R.id.DoExam_button_choseQuestion);
        //soCauHoiDangDuocChon = 1;
        ChoseQuestionButton_RecycleView = findViewById(R.id.DoExam_button_choseQuestion);


        data = (List<QuestionData>) getIntent().getSerializableExtra("QuestionList");
        String type = getIntent().getStringExtra("DoType");
        if(type.equalsIgnoreCase("DoExam")){
            Show_countdown.setVisibility(View.VISIBLE);
            startTimer();
        }
        else
            Show_countdown.setVisibility(View.GONE);

        UserQuestionStore = Create_List_0f_QuestionAndAnswer_by_size(data);
        //-------------ACTIONBAR---------------------------------------------
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //actionBar.setLogo(R.mipmap.ic_launcher);    //Icon muốn hiện thị
        //actionBar.setDisplayUseLogoEnabled(true);

//populating button recycler view
        //Do_exam_chose_question_button_Adapter adapter = new Do_exam_chose_question_button_Adapter(randomQuestion);
        //rvButtonQuestions.setAdapter(adapter);
        //rvButtonQuestions.setLayoutManager(new GridLayoutManager(this, 8));

        //Load first Question
        //---STORE USER ANSWER-----------------------------------------------
        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionAndAnswer getDataUser= UserQuestionStore.get(position);
                if(cb1.isChecked()){

                    getDataUser.ChoseAnswerA();
                }
                else{
                    getDataUser.Not_ChoseAnswerA();
                }

                UserQuestionStore.set(position,getDataUser);
            }
        });

        cb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionAndAnswer getDataUser= UserQuestionStore.get(position);
                if(cb2.isChecked()){

                    getDataUser.ChoseAnswerB();
                }
                else{
                    getDataUser.Not_ChoseAnswerB();
                }

                UserQuestionStore.set(position,getDataUser);
            }
        });

        cb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionAndAnswer getDataUser= UserQuestionStore.get(position);
                if(cb3.isChecked()){

                    getDataUser.ChoseAnswerC();
                }
                else{
                    getDataUser.Not_ChoseAnswerC();
                }

                UserQuestionStore.set(position,getDataUser);

            }
        });


        cb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionAndAnswer getDataUser= UserQuestionStore.get(position);
                if(cb4.isChecked()){

                    getDataUser.ChoseAnswerD();
                }
                else{
                    getDataUser.Not_ChoseAnswerD();

                }

                UserQuestionStore.set(position,getDataUser);
            }
        });


        //---SET QUESTION NAME BASE ON QUESTION TYPE-------------------------
        QuestionData question1 = data.get(0);
        SetQuestionName_baseOn_questionType(question1,this);
        SetAnswer_baseON_questionType(question1,this);
        position = 0;

        //---SET BUTTON CONTROL----------------------------------------------
        btnPrevious.setEnabled(false);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ItemLocation;
                if(position == 1){
                    btnPrevious.setEnabled(false);
                    position--;
                    ItemLocation = position;
                }
                else{
                    position--;
                    ItemLocation = position;
                    btnNext.setEnabled(true);
                }
                QuestionAndAnswer getQuestionData = UserQuestionStore.get(ItemLocation);
                SetQuestionName_baseOn_questionType(getQuestionData.data,view.getContext());
                SetAnswer_baseON_questionType(getQuestionData.data,view.getContext());

                //SetQuestionName_baseOn_questionType(data.get(position),view.getContext());
                //SetAnswer_baseON_questionType(data.get(position),view.getContext());

                SetAnswerChosen_by_user(getQuestionData);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ItemLocation;
                if(position == data.size()-2){
                    btnNext.setEnabled(false);
                    position++;
                    ItemLocation = position;
                }
                else{
                    position++;
                    ItemLocation = position;
                    btnPrevious.setEnabled(true);
                }

                QuestionAndAnswer getQuestionData = UserQuestionStore.get(ItemLocation);
                //SetQuestionName_baseOn_questionType(data.get(position),view.getContext());
                //SetAnswer_baseON_questionType(data.get(position),view.getContext());
                SetQuestionName_baseOn_questionType(getQuestionData.data,view.getContext());
                SetAnswer_baseON_questionType(getQuestionData.data,view.getContext());
                SetAnswerChosen_by_user(getQuestionData);


            }

        });

        //chose question by click button
        ChoseQuestionButtonAdapter = new ChoseQuestion_button_Adapter(this,buttonPositionList);
        ChoseQuestionButton_RecycleView.setAdapter(ChoseQuestionButtonAdapter);
        ChoseQuestionButton_RecycleView.setLayoutManager(new GridLayoutManager(this,7));

        //Submit
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext()).
                        setTitle("Nhắc nhở")
                        .setMessage("Bạn có muốn chắc chắn muốn nộp bài ?")
                        .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //send to show result data
                                Intent sentData = new Intent(view.getContext(),Do_exam_result.class);
                                sentData.putExtra("ResutlData", (Serializable) UserQuestionStore);
                                sentData.putExtra("DoType","DoExam");
                                view.getContext().startActivity(sentData);
                                finish();
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
        });

        //Set Time

    }

    public class ChoseQuestion_button_Adapter extends RecyclerView.Adapter<ButtonViewHolder>{
        Context context;
        List<ChoseQuestion_button> Positiondata;

        public ChoseQuestion_button_Adapter(Context context,List<ChoseQuestion_button> Positiondata){
            this.context = context;
            this.Positiondata =Positiondata;
        }
        @NonNull
        @Override
        public ButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(context).inflate(
              R.layout.do_exam_chose_question_button_item,
              parent,
              false
            );
            return new ButtonViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ButtonViewHolder holder, int positionAdapter) {
            ChoseQuestion_button getItem = Positiondata.get(positionAdapter);
            holder.QuestionNumber.setText(""+(getItem.position+1));
            holder.QuestionNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SetAnswer_baseON_questionType(data.get(getItem.position),view.getContext());
                    SetQuestionName_baseOn_questionType(data.get(getItem.position),view.getContext());
                    if(getItem.position == 0){
                        btnPrevious.setEnabled(false);
                    }
                    else
                        btnPrevious.setEnabled(true);

                    if(getItem.position == data.size() -1){
                        btnNext.setEnabled(false);
                    }
                    else
                        btnNext.setEnabled(true);
                    SetAnswerChosen_by_user(UserQuestionStore.get(getItem.position));
                    position = getItem.position;
                }
            });
        }

        @Override
        public int getItemCount() {
            return Positiondata.size();
        }


    }

    public void SetQuestionName_baseOn_questionType(QuestionData questionData , Context context){
        LoadQuestionNameAdapter questionNameAdapter ;
        RecyclerView LoadQuestionName = findViewById(R.id.DoExam_Load_QuestionName);
        List<QuestionData> QuestionData = new ArrayList<>();
        QuestionData.add(questionData);

        questionNameAdapter = new LoadQuestionNameAdapter(QuestionData,context);
        LoadQuestionName.setAdapter(questionNameAdapter);
        LoadQuestionName.setLayoutManager(new LinearLayoutManager(context));

    }

    //-----------------------------SET COUNT DOWN TIME-------------------------------
    void startTimer() {
        cTimer = new CountDownTimer(1200000, 1000) {
            public void onTick(long millisUntilFinished) {
                long minutesRemaining = millisUntilFinished/60000;
                long secondRemaining = millisUntilFinished/1000 - minutesRemaining*60;
                if(minutesRemaining > 0){
                    Show_countdown.setText("Thời gian còn lại : "+minutesRemaining+":"+secondRemaining);
                    Show_countdown.setBackgroundColor(Color.parseColor("#98F3C5"));
                }
                else{
                    Show_countdown.setText("Thời gian còn lại : "+minutesRemaining+":"+secondRemaining);
                    Show_countdown.setBackgroundColor(Color.parseColor("#F66163"));
                }
            }
            public void onFinish() {
                Intent sentData = new Intent(context,Do_exam_result.class);
                sentData.putExtra("ResutlData", (Serializable) UserQuestionStore);
                context.startActivity(sentData);
                finish();
            }
        };
        cTimer.start();
    }

    void cancelTimer() {
        if(cTimer!=null)
            cTimer.cancel();
    }
    //----------------------------------SET ACTION BAR--------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                        .setTitle("Nhắc nhở")
                        .setMessage("Bạn có chắc chắn muốn ngưng làm bài thi ?")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                onBackPressed();
                            }
                        });
                Dialog dia = dialog.create();
                dia.show();
                return true;
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

//--------------------------------------------------------------------------------
    public void SetAnswerChosen_by_user(QuestionAndAnswer getQuestionData){
        //set next Question Answer
        //AnswerA
        if(getQuestionData.getUser_AnswersA() == 0){
            cb1.setChecked(false);
        }
        else
            cb1.setChecked(true);

        //AnswerB
        if(getQuestionData.getUser_AnswersB() == 0){
            cb2.setChecked(false);
        }
        else
            cb2.setChecked(true);

        //AnswerC
        if(getQuestionData.getUser_AnswersC() == 0){
            cb3.setChecked(false);
        }
        else
            cb3.setChecked(true);

        //AnswerD
        if(getQuestionData.getUser_AnswersD() == 0){
            cb4.setChecked(false);
        }
        else
            cb4.setChecked(true);
    }

    public void SetAnswer_baseON_questionType(QuestionData questionData,Context context){
        //-----------------------------------THEORY QUESTION---------------------------------------------------
        if(questionData.QuestionType.equalsIgnoreCase("LyThuyet")){
            //Answer A
            if(!questionData.TheoryQuestionData.getAnswerA().equalsIgnoreCase("null")){
                cb1.setVisibility(View.VISIBLE);
                cb1.setText(questionData.TheoryQuestionData.getAnswerA());
            }
            else{
                cb1.setVisibility(View.GONE);
            }

            //Answer B
            if(!questionData.TheoryQuestionData.getAnswerB().equalsIgnoreCase("null")){
                cb2.setVisibility(View.VISIBLE);
                cb2.setText(questionData.TheoryQuestionData.getAnswerB());
            }
            else{
                cb2.setVisibility(View.GONE);
            }

            //Answer C
            if(!questionData.TheoryQuestionData.getAnswerC().equalsIgnoreCase("null")){
                cb3.setVisibility(View.VISIBLE);
                cb3.setText(questionData.TheoryQuestionData.getAnswerC());
            }
            else{
                cb3.setVisibility(View.GONE);
            }

            //Answer D
            if(!questionData.TheoryQuestionData.getAnswerD().equalsIgnoreCase("null")){
                cb4.setVisibility(View.VISIBLE);
                cb4.setText(questionData.TheoryQuestionData.getAnswerD());
            }
            else{
                cb4.setVisibility(View.GONE);
            }

        }
        else if(questionData.QuestionType.equalsIgnoreCase("BienBao")){
            //Answer A
            if(!questionData.traffic_sign_questions.getTraffic_sign_AnswerA().equalsIgnoreCase("null")){
                cb1.setVisibility(View.VISIBLE);
                cb1.setText(questionData.traffic_sign_questions.getTraffic_sign_AnswerA());
            }
            else{
                cb1.setVisibility(View.GONE);
            }

            //Answer B
            if(!questionData.traffic_sign_questions.getTraffic_sign_AnswerB().equalsIgnoreCase("null")){
                cb2.setVisibility(View.VISIBLE);
                cb2.setText(questionData.traffic_sign_questions.getTraffic_sign_AnswerB());
            }
            else{
                cb2.setVisibility(View.GONE);
            }

            //Answer Cign_ques
            if(!questionData.traffic_sign_questions.getTraffic_sign_AnswerC().equalsIgnoreCase("null")){
                cb3.setVisibility(View.VISIBLE);
                cb3.setText(questionData.traffic_sign_questions.getTraffic_sign_AnswerC());
            }
            else{
                cb3.setVisibility(View.GONE);
            }

            //Answer D
            if(!questionData.traffic_sign_questions.getTraffic_sign_AnswerD().equalsIgnoreCase("null")){
                cb4.setVisibility(View.VISIBLE);
                cb4.setText(questionData.traffic_sign_questions.getTraffic_sign_AnswerD());
            }
            else{
                cb4.setVisibility(View.GONE);
            }
        }
        else{
            //Answer A
            if(!questionData.sa_photo_question.getAnswerA().equalsIgnoreCase("null")){
                cb1.setVisibility(View.VISIBLE);
                cb1.setText(questionData.sa_photo_question.getAnswerA());
            }
            else{
                cb1.setVisibility(View.GONE);
            }

            //Answer B
            if(!questionData.sa_photo_question.getAnswerB().equalsIgnoreCase("null")){
                cb2.setVisibility(View.VISIBLE);
                cb2.setText(questionData.sa_photo_question.getAnswerB());
            }
            else{
                cb2.setVisibility(View.GONE);
            }

            //Answer C
            if(!questionData.sa_photo_question.getAnswerC().equalsIgnoreCase("null")){
                cb3.setVisibility(View.VISIBLE);
                cb3.setText(questionData.sa_photo_question.getAnswerC());
            }
            else{
                cb3.setVisibility(View.GONE);
            }

            //Answer D
            if(!questionData.sa_photo_question.getAnswerD().equalsIgnoreCase("null")){
                cb4.setVisibility(View.VISIBLE);
                cb4.setText(questionData.sa_photo_question.getAnswerD());
            }
            else{
                cb4.setVisibility(View.GONE);
            }
        }
    }

    public List<QuestionAndAnswer> Create_List_0f_QuestionAndAnswer_by_size(List<QuestionData> data){
        List<QuestionAndAnswer> list = new ArrayList<>();
        for(int i = 0 ; i < data.size() ; i++){
            list.add(new QuestionAndAnswer(i,data.get(i)));
            buttonPositionList.add(new ChoseQuestion_button(i));
        }
        return list;
    }


}




//populate database and question bank
       /* try{
            InputQuestionData input = new InputQuestionData(this);
            input.InputQuestionData_ToDataBase();
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        questions = QuestionDatabase.getInstance(this).question_query().getAllQuestion();
        ArrayList<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < 92; i++) //* possible: for(int i = 0; i < questions.size() -1; i++)
        {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

           randomQuestion.clear();

        for(int i = 0 ; i < 20; i++)
        {
            randomQuestion.add(questions.get(numbers.get(i)));
            idList[i] = Integer.parseInt(String.valueOf(randomQuestion.get(i).getQuestionID()));
        }

//displaying first question on first run
        number.setText(String.valueOf("Câu số " + (soCauHoiDangDuocChon)));
        content.setText(randomQuestion.get(soCauHoiDangDuocChon-1).getQuestionName());
        cb1.setText(randomQuestion.get(soCauHoiDangDuocChon-1).getAnswerA());
        cb2.setText(randomQuestion.get(soCauHoiDangDuocChon-1).getAnswerB());
        cb3.setText(randomQuestion.get(soCauHoiDangDuocChon-1).getAnswerC());
        cb4.setText(randomQuestion.get(soCauHoiDangDuocChon-1).getAnswerD());
        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer[soCauHoiDangDuocChon-1] = Integer.parseInt(cb1.getTag().toString());
            }
        });
        cb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer[soCauHoiDangDuocChon-1] = Integer.parseInt(cb2.getTag().toString());
            }
        });
        cb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer[soCauHoiDangDuocChon-1] = Integer.parseInt(cb3.getTag().toString());
            }
        });
        cb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer[soCauHoiDangDuocChon-1] = Integer.parseInt(cb4.getTag().toString());
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qaList = new QuestionAndAnswer(idList, userAnswer);
                Intent intent = new Intent(getApplicationContext(),Do_exam_result.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("list", qaList);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soCauHoiDangDuocChon == 1) {
                    Toast.makeText(getApplicationContext(), "Không thể quay lại", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    soCauHoiDangDuocChon--;
                    number.setText("Câu số " + (soCauHoiDangDuocChon));
                    content.setText(Do_exam.randomQuestion.get(soCauHoiDangDuocChon-1).getQuestionName());
                    cb1.setText(Do_exam.randomQuestion.get(soCauHoiDangDuocChon-1).getAnswerA());
                    cb2.setText(Do_exam.randomQuestion.get(soCauHoiDangDuocChon-1).getAnswerB());
                    cb3.setText(Do_exam.randomQuestion.get(soCauHoiDangDuocChon-1).getAnswerC());
                    cb4.setText(Do_exam.randomQuestion.get(soCauHoiDangDuocChon-1).getAnswerD());
                    rg.clearCheck();
                    if(Do_exam.userAnswer[Do_exam.soCauHoiDangDuocChon-1] != 0)
                    {
                        Do_exam.checkRadioButton(userAnswer[soCauHoiDangDuocChon-1], cb1, cb2,cb3, cb4);
                    }
                }
                logOut();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView t = findViewById(R.id.textViewSoCauHoi);
                if(soCauHoiDangDuocChon == randomQuestion.size()) {
                    Toast.makeText(getApplicationContext(), "Không thể tiếp tục", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    soCauHoiDangDuocChon++;
                    t.setText(String.valueOf(soCauHoiDangDuocChon));
                    number.setText("Câu số " + (soCauHoiDangDuocChon));
                    content.setText(Do_exam.randomQuestion.get(soCauHoiDangDuocChon-1).getQuestionName());
                    cb1.setText(Do_exam.randomQuestion.get(soCauHoiDangDuocChon-1).getAnswerA());
                    cb2.setText(Do_exam.randomQuestion.get(soCauHoiDangDuocChon-1).getAnswerB());
                    cb3.setText(Do_exam.randomQuestion.get(soCauHoiDangDuocChon-1).getAnswerC());
                    cb4.setText(Do_exam.randomQuestion.get(soCauHoiDangDuocChon-1).getAnswerD());
                    rg.clearCheck();
                    if(Do_exam.userAnswer[Do_exam.soCauHoiDangDuocChon-1] != 0)
                    {
                        Do_exam.checkRadioButton(userAnswer[soCauHoiDangDuocChon-1], cb1, cb2,cb3, cb4);
                    }
                }
                logOut();
            }
        });
    }

//additional functions
    public static void checkRadioButton(int radioButtonOrder, RadioButton... rb)
    {
            if(radioButtonOrder == 1)
            {
                rg.check(cb1.getId());
            }
            else if(radioButtonOrder == 2)
            {
                rg.check(cb2.getId());
            }
            else if(radioButtonOrder == 3)
            {
                rg.check(cb3.getId());
            }
            else if(radioButtonOrder == 4)
            {
                rg.check(cb4.getId());
            }
    }
    public void logOut()
    {
        Log.println(Log.INFO,"i", String.valueOf("số câu " + soCauHoiDangDuocChon));
        Log.println(Log.INFO,"i", String.valueOf("lựa chọn "+userAnswer[soCauHoiDangDuocChon-1]));
        Log.println(Log.INFO,"i", String.valueOf("ID " + randomQuestion.get(soCauHoiDangDuocChon-1).getQuestionID()));
        Log.println(Log.INFO,"i", String.valueOf("nội dung "+randomQuestion.get(soCauHoiDangDuocChon-1).getQuestionName()));
        Log.println(Log.INFO,"i", String.valueOf("array "+Arrays.toString(userAnswer)));
        Log.println(Log.INFO,"i", String.valueOf("_______________________________________________________________________"));
    }

        */