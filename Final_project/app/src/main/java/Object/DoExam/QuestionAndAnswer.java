package Object.DoExam;

import java.io.Serializable;
import java.util.List;

import Database.Table.User_Answers;

public class QuestionAndAnswer implements Serializable {
   public int position;
   public QuestionData data;
   private int User_AnswersA = 0;
   private int User_AnswersB = 0;
   private int User_AnswersC = 0;
   private int User_AnswersD = 0;

   public QuestionAndAnswer(int position,QuestionData data ) {
      this.data = data;
      this.position = position;
   }

   //AnswerA
   public void ChoseAnswerA(){
      User_AnswersA = 1;
   }

   public void Not_ChoseAnswerA(){
      User_AnswersA = 0;
   }

   //AnswerB
   public void ChoseAnswerB(){
      User_AnswersB = 1;
   }

   public void Not_ChoseAnswerB(){
      User_AnswersB = 0;
   }
   //AnswerC
   public void ChoseAnswerC(){
      User_AnswersC = 1;
   }

   public void Not_ChoseAnswerC(){
      User_AnswersC = 0;
   }

   //AnswerD
   public void ChoseAnswerD(){
      User_AnswersD = 1;
   }

   public void Not_ChoseAnswerD(){
      User_AnswersD = 0;
   }

   //getter

   public int getUser_AnswersA() {
      return User_AnswersA;
   }

   public int getUser_AnswersB() {
      return User_AnswersB;
   }

   public int getUser_AnswersC() {
      return User_AnswersC;
   }

   public int getUser_AnswersD() {
      return User_AnswersD;
   }
}
