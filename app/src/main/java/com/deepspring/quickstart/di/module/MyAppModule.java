package com.deepspring.quickstart.di.module;



import com.deepspring.libcommon.http.DataClient;
import com.deepspring.libcommon.http.helper.IHttpHelperImpl;
import com.deepspring.libcommon.http.helper.NetworHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author fzy
 * @date 2019/12/23.
 * description：
 */
@Module
public class MyAppModule {

    @Provides
    @Singleton
    public DataClient providerDataClient(IHttpHelperImpl iHttpHelper) {
        return new DataClient(iHttpHelper);
    }


    @Provides
    @Singleton
    public NetworHelper providerNetworkUtils(){
        return new NetworHelper();
    }

}
