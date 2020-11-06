package com.xing.main.apiservice;

import com.xing.commonbase.base.BaseResponse;
import com.xing.main.bean.BannerResult;
import com.xing.main.bean.FavoriteAddResult;
import com.xing.main.bean.FavoriteResult;
import com.xing.main.bean.HomeArticleResult;
import com.xing.main.bean.ProjectResult;
import com.xing.main.bean.ProjectTabItem;
import com.xing.main.bean.SearchHotKey;
import com.xing.main.bean.SearchResult;
import com.xing.main.bean.SystemArticleResult;
import com.xing.main.bean.SystemResult;
import com.xing.main.bean.xboot.ProcessNodeVo;
import com.xing.main.bean.xboot.User;
import com.xing.main.bean.WeChatArticleResult;
import com.xing.main.bean.WeChatAuthorResult;
import com.xing.main.bean.xboot.ActPage;
import com.xing.main.bean.xboot.TodoResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainApiService {

    /**
     * 获取首页 banner 数据
     *
     * @return
     */
    @GET("banner/json")
    Observable<BaseResponse<List<BannerResult>>> getBanner();


    /**
     * 获取公众号列表
     *
     * @return
     */
    @GET("wxarticle/chapters/json")
    Observable<BaseResponse<List<WeChatAuthorResult>>> getWeChatAuthors();


    @GET("wxarticle/list/{id}/{page}/json")
    Observable<BaseResponse<WeChatArticleResult>> getWeChatArticles(@Path("id") int id, @Path("page") int page);


    /**
     * 获取首页文章列表
     *
     * @param page
     * @return
     */
    @GET("article/list/{page}/json")
    Observable<BaseResponse<HomeArticleResult>> getHomeArticles(@Path("page") int page);


    /**
     * 搜索
     *
     * @param page
     * @return
     */
    @POST("article/query/{page}/json")
    @FormUrlEncoded
    Observable<BaseResponse<SearchResult>> getSearchResult(@Path("page") int page, @Field("k") String keyword);


    /**
     * Project 指定栏目下的列表
     *
     * @param page
     * @param id
     * @return
     */
    @GET("project/list/{page}/json")
    Observable<BaseResponse<ProjectResult>> getProjects(@Path("page") int page, @Query("cid") int id);

    /**
     * Project 栏目分类
     *
     * @return
     */
    @GET("project/tree/json")
    Observable<BaseResponse<List<ProjectTabItem>>> getProjectTabs();


    /**
     * 收藏站内文章
     *
     * @param id
     * @return
     */
    @POST("lg/collect/{id}/json")
    Observable<BaseResponse<FavoriteAddResult>> addFavorite(@Path("id") int id);

    /**
     * 收藏站外文章
     *
     * @return
     */
    @POST("lg/collect/add/json")
    @FormUrlEncoded
    Observable<BaseResponse<FavoriteAddResult>> addFavorite(@Field("title") String title,
                                                            @Field("author") String author,
                                                            @Field("link") String link);


    /**
     * 获取搜索热词
     *
     * @return
     */
    @GET("hotkey/json")
    Observable<BaseResponse<List<SearchHotKey>>> getSearchHotKey();

    /**
     * 获取体系列表
     *
     * @return
     */
    @GET("tree/json")
    Observable<BaseResponse<List<SystemResult>>> getSystemList();

    /**
     * 获取体系下分级下文章
     *
     * @param cid
     * @return
     */
    @GET("/article/list/{page}/json")
    Observable<BaseResponse<SystemArticleResult>> getSystemArticles(@Path("page") int page,
                                                                    @Query("cid") int cid);


    /**
     * 获取收藏文章列表
     */
    @GET("lg/collect/list/{page}/json")
    Observable<BaseResponse<FavoriteResult>> getFavoriteList(@Path("page") int page);


    /**
     * 获取用户详情
     * GET /xboot/app/consumer/user/userinfo
     */
    @GET("xboot/app/consumer/user/userinfo")
    Observable<BaseResponse<User>> getUserInfo();

    /**
     * 获取带办列表
     */
    @GET("xboot/actTask/todoList")
    Observable<BaseResponse<ActPage<TodoResult>>> getTodoList(@Query("pageNumber") int pageNumber, @Query("pageSize") int pageSize);

    /**
     * 已办列表
     */
    @GET("xboot/actTask/doneList")
    Observable<BaseResponse<ActPage<TodoResult>>> getDoneList(@Query("pageNumber") int pageNumber, @Query("pageSize") int pageSize);


    /**
     * 任务节点审批通过
     *
     * @param id          任务id
     * @param procInstId  流程实例id
     * @param assignees   流程实例id
     * @param priority    下个节点审批人
     * @param comment     优先级
     * @param sendMessage 是否发送站内消息
     * @param sendSms     是否发送短信通知
     * @param sendEmail   是否发送邮件通知
     * @return
     */
    @POST("xboot/actTask/pass")
    @FormUrlEncoded
    Observable<BaseResponse<Object>> pass(@Field("id") String id,
                                          @Field("procInstId") String procInstId,
                                          @Field("assignees") String[] assignees,
                                          @Field("priority") Integer priority,
                                          @Field("comment") String comment,
                                          @Field("sendMessage") Boolean sendMessage,
                                          @Field("sendSms") Boolean sendSms,
                                          @Field("sendEmail") Boolean sendEmail);

    /**
     * 任务节点审批驳回至发起人
     *
     * @param id          任务id
     * @param procInstId  流程实例id
     * @param comment     意见评论
     * @param sendMessage 是否发送站内消息
     * @param sendSms     是否发送短信通知
     * @param sendEmail   是否发送邮件通知
     * @return
     */
    @POST("xboot/actTask/back")
    @FormUrlEncoded
    Observable<BaseResponse<Object>> back(@Field("id") String id,
                                          @Field("procInstId") String procInstId,
                                          @Field("comment") String comment,
                                          @Field("sendMessage") Boolean sendMessage,
                                          @Field("sendSms") Boolean sendSms,
                                          @Field("sendEmail") Boolean sendEmail);

    /**
     * 委托他人代办
     *
     * @param id          任务id
     * @param userId      委托用户id
     * @param procInstId  流程实例id
     * @param comment     意见评论
     * @param sendMessage 是否发送站内消息
     * @param sendSms     是否发送短信通知
     * @param sendEmail   是否发送邮件通知
     * @return
     */
    @POST("xboot/actTask/delegate")
    @FormUrlEncoded
    Observable<BaseResponse<Object>> delegate(@Field("id") String id,
                                              @Field("userId") String userId,
                                              @Field("procInstId") String procInstId,
                                              @Field("comment") String comment,
                                              @Field("sendMessage") Boolean sendMessage,
                                              @Field("sendSms") Boolean sendSms,
                                              @Field("sendEmail") Boolean sendEmail);

    /**
     * 通过当前节点定义id获取下一个节点
     */
    @GET("xboot/actTask/historicFlow/{id}")
    Observable<BaseResponse<List<Object>>> historicFlow(@Path("id") String id);

    /**
     * 通过当前节点定义id获取下一个节点
     */
    @GET("xboot/actProcess/getNextNode/{procDefId}/{currActId}")
    Observable<BaseResponse<ProcessNodeVo>> getNextNode(@Path("procDefId") String procDefId, @Path("currActId") String currActId);
}
