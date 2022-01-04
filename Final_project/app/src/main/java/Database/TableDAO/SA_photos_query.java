package Database.TableDAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import Database.Table.SA_photo;

@Dao
public interface SA_photos_query {
    @Insert
    void InsertSA_photos(SA_photo sa_photo);

    @Query("Select * from SA_photo where SA_photo_ID =:ID")
    SA_photo CheckUNIQUE(String ID);

    @Query("select SA_photo_img_Link from SA_photo where SA_photo_ID = :ID")
    int getImgLink(String ID);
}
