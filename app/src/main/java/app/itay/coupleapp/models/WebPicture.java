package app.itay.coupleapp.models;

/**
 * Created by isaturina on 3/10/17.
 */


public class WebPicture {

    private String mWebformatURL;
    private String mPreviewURL;

    public WebPicture(String webformatURL, String previewURL) {
        mPreviewURL = previewURL;
        mWebformatURL = webformatURL;
    }

    public String getWebformatURL() {
        return mWebformatURL;
    }

    public String getPreviewURL() {
        return mPreviewURL;
    }
}
