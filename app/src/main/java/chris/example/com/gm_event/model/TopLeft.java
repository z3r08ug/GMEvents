
package chris.example.com.gm_event.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TopLeft implements Serializable{

    @SerializedName("x")
    @Expose
    private Integer x;
    @SerializedName("y")
    @Expose
    private Integer y;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

}
