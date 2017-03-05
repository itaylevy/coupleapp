package app.itay.coupleapp;

/**
 * Created by itayl on 04/03/2017.
 */

public class Chore {
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

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmCoins() {
        return mCoins;
    }

    public void setmCoins(String mCoins) {
        this.mCoins = mCoins;
    }

    public String getmCreator() {
        return mCreator;
    }

    public void setmCreator(String mCreator) {
        this.mCreator = mCreator;
    }

    public int getmImgSrc() {
        return mImgSrc;
    }

    public void setmImgSrc(int mImgSrc) {
        this.mImgSrc = mImgSrc;
    }
}
