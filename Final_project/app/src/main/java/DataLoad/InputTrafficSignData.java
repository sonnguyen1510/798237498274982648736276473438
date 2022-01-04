package DataLoad;
import android.content.Context;
import android.os.AsyncTask;

import com.example.final_project.R;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import Database.QuestionDatabase;
import Database.Table.*;

public class InputTrafficSignData extends AsyncTask<Context,Object,Boolean> {

    @Override
    protected Boolean doInBackground(Context... contexts) {
        //get context
        Context context = contexts[0];
        //input data
        List<traffic_signs> data = new ArrayList<traffic_signs>();
        //input a list of traffic sign
        try {
            data = add_data();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        //input list of traffic sign to database
        for(traffic_signs check : data){
            if(QuestionDatabase.getInstance(context).traffic_signs_query().checkUnique(check.getTraffic_sign_id())==null){
                QuestionDatabase.getInstance(context).traffic_signs_query().insertTrafficSigndata(check);
            }
        }

        //question data load
        InputStream file = context.getResources().openRawResource(R.raw.traffic_signs_question);
        BufferedReader br = new BufferedReader(new InputStreamReader(file));

        try {
            String readLine;
            while ((readLine = br.readLine()) != null) {
                String[] info = readLine.split("-");

                if(info.length == 11){
                    traffic_sign_questions QuestionData = new traffic_sign_questions(info[0], info[1], info[2], info[3], info[4], info[5], info[6],info[7],info[8],info[9]);
                    if(QuestionDatabase.getInstance(context).traffic_sign_questions_query().CheckInsert(info[0])==null){
                        QuestionDatabase.getInstance(context).traffic_sign_questions_query().Insert_traffic_sign_question_data(QuestionData);
                    }

                    AnswerCheck AnswerCheck = new AnswerCheck(info[0],Integer.parseInt(info[10]),info[9]);
                    if(QuestionDatabase.getInstance(context).questionAnswer_query().CheckUNIQUE(info[0],info[9])==null){
                        QuestionDatabase.getInstance(context).questionAnswer_query().InsertAnswerKey(AnswerCheck);
                    }
                }
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public List<traffic_signs> add_data() throws FileNotFoundException {
        List<traffic_signs> data = new ArrayList<traffic_signs>();
        try{
            data.add(new traffic_signs("1","traffic_sign1","Nhường cho người đi ngược chiều qua đường hẹp","cam", R.drawable.trafficsign1));
            data.add(new traffic_signs("2","traffic_sign2","Được ưu tiên qua đường hẹp","chidan", R.drawable.trafficsign2));
            data.add(new traffic_signs("3","traffic_sign3","Cấm xe máy lưu thông","cam", R.drawable.trafficsign3));
            data.add(new traffic_signs("4","traffic_sign4","Cấm ô tô lưu thông","cam", R.drawable.trafficsign4));
            data.add(new traffic_signs("5","traffic_sign5","Cấm xe tải lưu thông","cam", R.drawable.trafficsign5));
            data.add(new traffic_signs("6","traffic_sign6","Cấm máy kéo lưu thông","cam", R.drawable.trafficsign6));
            data.add(new traffic_signs("7","traffic_sign7","Cấm tất cả các phương tiện lưu thông khác ngoài xe máy","cam", R.drawable.trafficsign7));
            data.add(new traffic_signs("8","traffic_sign8","Cấm tất cả các phương tiện lưu thông khác ngoài xe tải","cam", R.drawable.trafficsign8));
            data.add(new traffic_signs("9","traffic_sign9","Cấm tất cả các phương tiện lưu thông khác ngoài xe ô tô","cam", R.drawable.trafficsign9));
            data.add(new traffic_signs("10","traffic_sign10","Cấm tất cả các phương tiện lưu thông khác ngoài máy kéo","cam", R.drawable.trafficsign10));
            data.add(new traffic_signs("11","traffic_sign11","Cấm rẽ trái","cam", R.drawable.trafficsign11));
            data.add(new traffic_signs("12","traffic_sign12","Cấm quay xe","cam", R.drawable.trafficsign12));
            data.add(new traffic_signs("13","traffic_sign13","Cấm tất cả các loại phương tiện lưu thông","cam", R.drawable.trafficsign13));
            data.add(new traffic_signs("14","traffic_sign14","Buộc các loại phương tiện phải dừng lại trước biển","cam", R.drawable.trafficsign14));
            data.add(new traffic_signs("15","traffic_sign15","Cấm đi ngược chiều, trừ các xe được ưu tiên","cam", R.drawable.trafficsign15));
            data.add(new traffic_signs("16","traffic_sign16","Cấm xe máy và xe ô tô lưu thông , được rẽ trái , phải","cam", R.drawable.trafficsign16));
            data.add(new traffic_signs("17","traffic_sign17","Các phương tiện lưu thông chỉ được rẽ trái","hieulenh", R.drawable.trafficsign17));
            data.add(new traffic_signs("18","traffic_sign18","Các phương tiện lưu thông chỉ được rẽ trái , phải","hielenh", R.drawable.trafficsign18));
            data.add(new traffic_signs("19","traffic_sign19","Các phương tiện lưu thông chỉ được đi thẳng , rẽ phải","hieulenh", R.drawable.trafficsign19));
            data.add(new traffic_signs("20","traffic_sign20","Báo hiệu chuẩn bị có cầu vượt liên thông","chidan", R.drawable.trafficsign20));
            data.add(new traffic_signs("21","traffic_sign21","Tuyến đường có cầu vượt cắt qua","hieulenh", R.drawable.trafficsign21));
            data.add(new traffic_signs("22","traffic_sign22","Báo hiệu chuẩn bị có cầu vượt liên thông","chidan", R.drawable.trafficsign22));
            data.add(new traffic_signs("23","traffic_sign23","Đoạn đường hay xảy ra tai nạn","nguyhiem", R.drawable.trafficsign23));
            data.add(new traffic_signs("24","traffic_sign24","Đoạn đường dễ trơn , trượt","nguyhiem", R.drawable.trafficsign24));
            data.add(new traffic_signs("25","traffic_sign25","Tuyến đường có cầu vượt cắt qua","hieulenh", R.drawable.trafficsign25));
            data.add(new traffic_signs("26","traffic_sign26","Tuyến đường có người đi bộ cắt ngang","nguyhiem", R.drawable.trafficsign26));
            data.add(new traffic_signs("27","traffic_sign27","Tuyến đường sắp tới công trình có độ vồng lớn , cản trở tầm nhìn","nguyhiem", R.drawable.trafficsign27));
            data.add(new traffic_signs("28","traffic_sign28","Khu vực cấm đỗ xe","cam", R.drawable.trafficsign28));
            data.add(new traffic_signs("29","traffic_sign29","Khu vực cấm đỗ xe  trong 1 khoảng thời gian ( 6g giờ đên s8 giờ)","cam", R.drawable.trafficsign29));
            data.add(new traffic_signs("30","traffic_sign30","Khu vục tên AH112","chidan", R.drawable.trafficsign30));
            data.add(new traffic_signs("31","traffic_sign31","Tuyến đường cấm người đi bộ","cam", R.drawable.trafficsign31));
            data.add(new traffic_signs("32","traffic_sign32","Tuyến đường dành cho người đi bộ","hieulenh", R.drawable.trafficsign32));
            data.add(new traffic_signs("33","traffic_sign33","Tuyến đường 2 chiều","nguyhiem", R.drawable.trafficsign33));
            data.add(new traffic_signs("34","traffic_sign34","Tuyến đường giao nhau với đường ưu tiên","nguyhiem", R.drawable.trafficsign34));
            data.add(new traffic_signs("35","traffic_sign35","Tuyến đường giao nhau có đèn tín hiệu","nguyhiem", R.drawable.trafficsign35));
            data.add(new traffic_signs("36","traffic_sign36","Tuyến đường giao nhau với đường sắt có rào chắn","nguyhiem", R.drawable.trafficsign36));
            data.add(new traffic_signs("37","traffic_sign37","Tuyến đường giao nhau với đường 2 chiều","nguyhiem", R.drawable.trafficsign37));
            data.add(new traffic_signs("38","traffic_sign38","Chỗ đường sắt cắt đường bộ","phu", R.drawable.trafficsign38));
            data.add(new traffic_signs("39","traffic_sign39","Tuyến đường giao nhau với đường sắt không có rào chắn","nguyhiem", R.drawable.trafficsign39));
            data.add(new traffic_signs("40","traffic_sign40","Tuyến đường giao nhau với đường không ưu tiên","nguyhiem", R.drawable.trafficsign40));
            data.add(new traffic_signs("41","traffic_sign41","Tuyến đường được ưu tiên","phu", R.drawable.trafficsign41));
            data.add(new traffic_signs("42","traffic_sign42","Tuyến đường không được ưu tiên","phu", R.drawable.trafficsign42));
            data.add(new traffic_signs("43","traffic_sign43","Tuyến đường sắp đến chỗ giao nhau của đường đồng cấp","nguyhiem", R.drawable.trafficsign43));

        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return data;
    }



}
