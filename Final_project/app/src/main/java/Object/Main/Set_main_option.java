package Object.Main;

import android.content.Intent;

import UI.Option.Search_traffic_signs;

public class Set_main_option<T> {
    public String optionName;
    public int img_location;
    public Class<T> Go_to;

    public Set_main_option(String optionName , int img_location , Class<T> go_to){
        this.optionName = optionName;
        this.img_location = img_location;
        this.Go_to=go_to;
    }
}
