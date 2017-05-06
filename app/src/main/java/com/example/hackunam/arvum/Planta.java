package com.example.hackunam.arvum;

import android.graphics.Bitmap;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by rocko on 06/05/2017.
 */

public class Planta {
    String name, description;
    //Bitmap bitmap;

    public Planta(String name, String description/*, Bitmap bitmap*/) {
        this.name = name;
        this.description = description;
        //this.bitmap = bitmap;
    }



   /* public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
