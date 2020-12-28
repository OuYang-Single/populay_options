/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pine.populay_options.mvp.model.api.cache;

import com.pine.populay_options.mvp.model.entity.Login;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.entity.User;
import com.pine.populay_options.mvp.model.entity.VestSignEntity;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;
import io.rx_cache2.internal.RxCache;
import okhttp3.ResponseBody;

import java.util.concurrent.TimeUnit;


/**
 * ================================================
 * 展示 {@link RxCache#using(Class)} 中需要传入的 Providers 的使用方式
 * <p>
 * Created by JessYan on 08/30/2016 13:53
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public interface CommonCache {
    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<Request<User>>> getLogin(Observable<Request<User>> users, DynamicKey idLastUserQueried, EvictProvider evictProvider);
  @LifeCache(duration = 3, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<ResponseBody>> getdownload(Observable<ResponseBody> users, DynamicKey idLastUserQueried, EvictProvider evictProvider);
    @LifeCache(duration = 4, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<ResponseBody>> chart(Observable<ResponseBody> users, DynamicKey idLastUserQueried, EvictProvider evictProvider);
    @LifeCache(duration = 5, timeUnit = TimeUnit.MINUTES)
   Observable<Reply<Request<VestSignEntity>>> vestSign( Observable<Request<VestSignEntity>>  users, DynamicKey idLastUserQueried, EvictProvider evictProvider);
    @LifeCache(duration =6, timeUnit = TimeUnit.MINUTES)
   Observable<Reply<Request<Login>>> doLogin2(Observable<Request<Login>>  users, DynamicKey idLastUserQueried, EvictProvider evictProvider);
}
