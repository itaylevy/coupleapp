package app.itay.coupleapp.models;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.Serializable;

/**
 * Created by itayl on 04/03/2017.
 */

public class Chore extends Goal {

    private String mDeadline;

    public Chore(String mTitle, String mCoins, String mCreator, String mDeadline, int mImgSrc) {
        super(mTitle, mCoins, mCreator, mImgSrc);
        setDeadline(mDeadline);
    }


    public Chore(String mTitle, String mCoins, String mCreator, String mDeadline, String mImgPath) {
        super(mTitle, mCoins, mCreator, mImgPath);
        setDeadline(mDeadline);
    }

    public String getDeadline() {
        return mDeadline;
    }

    public void setDeadline(String mDeadline) {
        this.mDeadline = mDeadline;
    }
}
