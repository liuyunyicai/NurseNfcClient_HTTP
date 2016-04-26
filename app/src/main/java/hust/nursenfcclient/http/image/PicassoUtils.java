package hust.nursenfcclient.http.image;

import android.content.Context;
import android.media.Image;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import hust.nursenfcclient.http.HttpUtils;
import hust.nursenfcclient.utils.LogUtils;

/**
 * Created by admin on 2016/4/26.
 */
public class PicassoUtils {
    private static final String IMAGE_PATH_PREX = "TestServer/picture/local/nurse_pic/";

    private volatile static PicassoUtils picassoUtils;
    private Picasso picasso;

    private PicassoUtils(Context context) {
        picasso = Picasso.with(context);
    }

    public static PicassoUtils getInstance(Context context) {
        if (picassoUtils == null) {
            synchronized (PicassoUtils.class) {
                if (picassoUtils == null) {
                    picassoUtils = new PicassoUtils(context);
                }
            }
        }
        return picassoUtils;
    }

    public void loadImage(String url, final ImageView imageView) {
        picasso.load(getAbsolutePath(url)).into(imageView);

        LogUtils.w(getAbsolutePath(url));
    }

    public Picasso picasso() {
        return picasso;
    }

    // 获取图片路径
    public String getAbsolutePath(String url) {
        return HttpUtils.BASE_URL + IMAGE_PATH_PREX + url;
    }
}
