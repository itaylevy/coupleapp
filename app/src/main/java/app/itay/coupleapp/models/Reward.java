package app.itay.coupleapp.models;

/**
 * Created by itayl on 10/03/2017.
 */

public class Reward {
    protected String mImgPath;
    protected String mTitle;
    protected String mCoins;
    protected String mCreator;
    protected int mImgSrc;

    public Reward(String mTitle, String mCoins, String mCreator, int mImgSrc) {
        this.mTitle = mTitle;
        this.mCoins = mCoins;
        this.mCreator = mCreator;
        this.mImgSrc = mImgSrc;
    }

    public String getImgPath() {
        return mImgPath;
    }

    public void setImgPath(String mImgPath) {
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

    public void setImgSrc(int mImgSrc) {
        this.mImgSrc = mImgSrc;
    }
}
