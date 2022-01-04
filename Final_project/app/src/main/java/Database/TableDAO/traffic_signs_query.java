package Database.TableDAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
import Database.Table.traffic_signs;

@Dao
public interface traffic_signs_query {
    @Insert
    void insertTrafficSigndata(traffic_signs traffic_signs);

    @Query("Select traffic_sign_name from traffic_signs where traffic_sign_id = :id")
    public String checkUnique(String id);

    @Query("Select * from traffic_signs")
    List<traffic_signs> getAlldata();

    @Query("Select * from traffic_signs where traffic_type = :type")
    List<traffic_signs> getTraffic_signs_bytype(String type);

    @Query("Select traffic_img_link from traffic_signs where traffic_sign_id = :ID")
    int getTrafficSignlink(String ID);
}
