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

import com.pine.populay_options.mvp.model.entity.CommentsEntity;
import com.pine.populay_options.mvp.model.entity.Login;
import com.pine.populay_options.mvp.model.entity.PageInfo;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.entity.Topics;
import com.pine.populay_options.mvp.model.entity.User;
import com.pine.populay_options.mvp.model.entity.VestSignEntity;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;
import io.rx_cache2.internal.RxCache;
import okhttp3.ResponseBody;

import java.util.List;
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

    @LifeCache(duration = 1, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<Request<String>>> getRegistered(Observable<Request<String>> users, DynamicKey idLastUserQueried, EvictProvider evictProvider);

    @LifeCache(duration = 3, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<ResponseBody>> getdownload(Observable<ResponseBody> users, DynamicKey idLastUserQueried, EvictProvider evictProvider);

    @LifeCache(duration = 4, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<ResponseBody>> chart(Observable<ResponseBody> users, DynamicKey idLastUserQueried, EvictProvider evictProvider);

    @LifeCache(duration = 5, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<Request<VestSignEntity>>> vestSign(Observable<Request<VestSignEntity>> users, DynamicKey idLastUserQueried, EvictProvider evictProvider);

    @LifeCache(duration = 6, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<Request<Login>>> doLogin2(Observable<Request<Login>> users, DynamicKey idLastUserQueried, EvictProvider evictProvider);

    @LifeCache(duration = 7, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<Request<String>>> logOut(Observable<Request<String>> users, DynamicKey idLastUserQueried, EvictProvider evictProvider);

    @LifeCache(duration = 8, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<Request<PageInfo<Topics>>>> initData(Observable<Request<PageInfo<Topics>>> users, DynamicKey idLastUserQueried, EvictProvider evictProvider);

    @LifeCache(duration = 9, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<Request<String>>> addDetails(Observable<Request<String>> listObservable, DynamicKey dynamicKey, EvictDynamicKey evictDynamicKey);

    @LifeCache(duration = 10, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<Request<Boolean>>> isUserExists(Observable<Request<Boolean>> listObservable, DynamicKey dynamicKey, EvictDynamicKey evictDynamicKey);

    @LifeCache(duration = 11, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<Request<User>>> codeLogin(Observable<Request<User>> listObservable, DynamicKey dynamicKey, EvictDynamicKey evictDynamicKey);

    @LifeCache(duration = 12, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<Request<Boolean>>> changePassword(Observable<Request<Boolean>> listObservable, DynamicKey dynamicKey, EvictDynamicKey evictDynamicKey);

    @LifeCache(duration = 13, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<Request<Boolean>>> Unlike(Observable<Request<Boolean>> listObservable, DynamicKey dynamicKey, EvictDynamicKey evictDynamicKey);

    @LifeCache(duration = 14, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<Request<Boolean>>> like(Observable<Request<Boolean>> listObservable, DynamicKey dynamicKey, EvictDynamicKey evictDynamicKey);

    @LifeCache(duration = 15, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<Request<Boolean>>> shield(Observable<Request<Boolean>> listObservable, DynamicKey dynamicKey, EvictDynamicKey evictDynamicKey);

    @LifeCache(duration = 16, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<Request<List<CommentsEntity>>>> comment(Observable<Request<List<CommentsEntity>>> listObservable, DynamicKey dynamicKey, EvictDynamicKey evictDynamicKey);

    @LifeCache(duration = 17, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<Request<Boolean>>> SubmitComments(Observable<Request<Boolean>> listObservable, DynamicKey dynamicKey, EvictDynamicKey evictDynamicKey);

    @LifeCache(duration = 18, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<Request<Boolean>>> delete_topics(Observable<Request<Boolean>> listObservable, DynamicKey dynamicKey, EvictDynamicKey evictDynamicKey);

    @LifeCache(duration = 19, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<Request<User>>> modifyAvatar(Observable<Request<User>> listObservable, DynamicKey dynamicKey, EvictDynamicKey evictDynamicKey);
}
