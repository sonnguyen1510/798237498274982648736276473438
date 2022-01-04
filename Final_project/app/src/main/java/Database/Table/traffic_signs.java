package Database.Table;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class traffic_signs implements Serializable {
    @PrimaryKey
    @NonNull
    private String traffic_sign_id;
    private String traffic_sign_name;
    private String traffic_info;
    private String traffic_type;
    private int traffic_img_link;

    public traffic_signs(String traffic_sign_id,String traffic_sign_name,String traffic_info,String traffic_type,int traffic_img_link){
        this.traffic_img_link = traffic_img_link;
        this.traffic_info = traffic_info;
        this.traffic_sign_name = traffic_sign_name;
        this.traffic_sign_id = traffic_sign_id;
        this.traffic_type = traffic_type;
    }

    //getter


    public String getTraffic_type() {
        return traffic_type;
    }

    public String getTraffic_sign_id() {
        return traffic_sign_id;
    }

    public int getTraffic_img_link() {
        return traffic_img_link;
    }

    public String getTraffic_info() {
        return traffic_info;
    }

    public String getTraffic_sign_name() {
        return traffic_sign_name;
    }

    //setter


    public void setTraffic_type(String traffic_type) {
        this.traffic_type = traffic_type;
    }

    public void setTraffic_img_link(int traffic_img_link) {
        this.traffic_img_link = traffic_img_link;
    }

    public void setTraffic_info(String traffic_info) {
        this.traffic_info = traffic_info;
    }

    public void setTraffic_sign_id(String traffic_sign_id) {
        this.traffic_sign_id = traffic_sign_id;
    }

    public void setTraffic_sign_name(String traffic_sign_name) {
        this.traffic_sign_name = traffic_sign_name;
    }
}
