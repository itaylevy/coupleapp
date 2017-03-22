package app.itay.coupleapp.models;

import java.io.Serializable;

/**
 * Created by itayl on 09/03/2017.
 */

public class Goal implements Serializable {

    protected String mImgPath;
    protected String mTitle;
    protected String mCoins;
    protected String mCreator;
    protected int mImgSrc;


    protected int badge;
    private String imgPath;

    public Goal(String mTitle, String mCoins, String mCreator, int mImgSrc) {
        this.mTitle = mTitle;
        this.mCoins = mCoins;
        this.mCreator = mCreator;
        this.mImgSrc = mImgSrc;
    }

    public Goal(String mTitle, String mCoins, int badge,int mImgSrc) {
        this.mTitle = mTitle;
        this.mCoins = mCoins;
        this.mImgSrc = mImgSrc;
        this.badge = badge;
    }

    public Goal(String mTitle, String mCoins, String mCreator, String mImgPath) {
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

    public int getBadge() {
        return badge;
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

    public void setImgSrc(int mImgSrc) {
        this.mImgSrc = mImgSrc;
    }

    public String getImgPath() {
        return mImgPath;
    }

    public void setmImgPath(String mImgPath) {
        this.mImgPath = mImgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
