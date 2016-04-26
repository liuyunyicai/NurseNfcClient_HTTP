package hust.nursenfcclient.login;

import nurse_db.NurseInfo;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by admin on 2016/4/8.
 */
public interface LoginService {

    @POST("TestServer/{path}")
    Observable<NurseInfo> login(@Path("path") String path, @Body UserInfo params);

    @POST("TestServer/{path}")
    Observable<String> loginRaw(@Path("path") String path, @Body UserInfo params);
}
