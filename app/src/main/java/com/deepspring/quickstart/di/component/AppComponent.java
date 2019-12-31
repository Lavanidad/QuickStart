package com.deepspring.quickstart.di.component;



import com.deepspring.quickstart.MyApplication;
import com.deepspring.quickstart.di.module.ActivityBindingModule;
import com.deepspring.quickstart.di.module.FragmentBindingModule;
import com.deepspring.quickstart.di.module.MyAppModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author fzy
 * @date 2019/12/23.
 * description：
 */

@Singleton
@Component(modules = {MyAppModule.class,
        ActivityBindingModule.class,
        FragmentBindingModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<MyApplication> {

}
