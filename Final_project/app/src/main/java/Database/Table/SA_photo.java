package Database.Table;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SA_photo {
    @PrimaryKey
    @NonNull
    private String SA_photo_ID;
    private int SA_photo_img_Link;

    public SA_photo(String SA_photo_ID,int SA_photo_img_Link){
        this.SA_photo_ID = SA_photo_ID;
        this.SA_photo_img_Link =SA_photo_img_Link;
    }

    //getter

    @NonNull
    public String getSA_photo_ID() {
        return SA_photo_ID;
    }

    public int getSA_photo_img_Link() {
        return SA_photo_img_Link;
    }

    //setter

    public void setSA_photo_ID(@NonNull String SA_photo_ID) {
        this.SA_photo_ID = SA_photo_ID;
    }

    public void setSA_photo_img_Link(int SA_photo_img_Link) {
        this.SA_photo_img_Link = SA_photo_img_Link;
    }
}
