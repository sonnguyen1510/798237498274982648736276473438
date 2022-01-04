package Database.StoreData;


import androidx.room.Ignore;

public class StoreAnswerUser_and_AnswerKey {
    private int UserAnswer;
    private int AnswerKey;

    public StoreAnswerUser_and_AnswerKey(int UserAnswer,int AnswerKey){
        this.AnswerKey = AnswerKey;
        this.UserAnswer = UserAnswer;
    }

    public int getAnswerKey() {
        return AnswerKey;
    }

    public int getUser_Answer() {
        return UserAnswer;
    }

    public void setAnswerKey(int AnswerKey) {
        this.AnswerKey = AnswerKey;
    }

    public void setUserAnswer(int UserAnswer) {
        this.UserAnswer = UserAnswer;
    }
}
