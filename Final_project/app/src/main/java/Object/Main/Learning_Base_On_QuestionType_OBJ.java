package Object.Main;

import java.io.Serializable;

public class Learning_Base_On_QuestionType_OBJ<T> implements Serializable {
    public String Option_main_name;
    public int img_location;
    public int Total_question;
    public Class<T> go_to;

    public Learning_Base_On_QuestionType_OBJ(String Option_main_name, int img_location, int Total_question, Class<T> go_to){
        this.img_location =img_location;
        this.Option_main_name = Option_main_name;
        this.Total_question = Total_question;
        this.go_to = go_to;
    }
}
