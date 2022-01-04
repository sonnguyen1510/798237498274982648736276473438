package UI.Setting;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Query;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.final_project.R;

public class Setting extends AppCompatActivity {
    private Button Renew;
    private Activity activity = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        getSupportActionBar().setTitle("Cài đặt");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Renew = findViewById(R.id.Setting_New);
        Renew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder subdia = new AlertDialog.Builder(view.getContext())
                        .setTitle("Nhắc nhở")
                        .setMessage("Bạn có chắc chắn muốn xóa toàn bộ ? Sau khi xóa , app sẽ tự động thoát .")
                        .setPositiveButton("Xóa toàn bộ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                view.getContext().deleteDatabase("Question_db");
                                activity.finishAffinity();
                                System.exit(0);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface2, int i) {
                                dialogInterface2.cancel();
                            }
                        });
                Dialog dia2 = subdia.create();
                dia2.show();
            }
        });
    }
}