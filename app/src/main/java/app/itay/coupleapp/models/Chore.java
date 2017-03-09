package app.itay.coupleapp.models;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by itayl on 04/03/2017.
 */

public class Chore implements Serializable {

    private String mImgPath;
    private String mTitle;
    private String mCoins;
    private String mCreator;
    private int mImgSrc;

    public Chore(String mTitle, String mCoins, String mCreator, int mImgSrc) {
        this.mTitle = mTitle;
        this.mCoins = mCoins;
        this.mCreator = mCreator;
        this.mImgSrc = mImgSrc;
    }


    public Chore(String mTitle, String mCoins, String mCreator, String mImgPath) {
        this.mTitle = mTitle;
        this.mCoins = mCoins;
        this.mCreator = mCreator;
        this.mImgPath = mImgPath;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getCoins() {
        return mCoins;
    }

    public void setCoins(String mCoins) {
        this.mCoins = mCoins;
    }

    public String getCreator() {
        return mCreator;
    }

    public void setCreator(String mCreator) {
        this.mCreator = mCreator;
    }

    public int getImgSrc() {
        return mImgSrc;
    }

    public void setmImgSrc(int mImgSrc) {
        this.mImgSrc = mImgSrc;
    }

    public String getImgPath() {
        return mImgPath;
    }
}
