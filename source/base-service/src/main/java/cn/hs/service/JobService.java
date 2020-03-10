package cn.hs.service;


import cn.hs.bean.JobBean;

import java.util.List;

/**
 * 登录信息缓存管理
 * @author 宋文涛
 */
public interface JobService {

    /**
     * 开始任务
     * @param name              任务名称
     * @param nextUpdateTime    下次更新时间
     * @return      操作结果
     */
    boolean start(String name, long nextUpdateTime);

    /**
     * 完成任务
     * @param name  任务名称
     * @return      操作结果
     */
    boolean end(String name);

    /**
     * 异常
     * @param name  任务名称
     * @param latestException  异常信息
     * @return      操作结果
     */
    boolean exception(String name, String latestException);

    /**
     * 是否可以执行
     * @param name  任务名称
     * @return      操作结果
     */
    boolean canExecute(String name);

    /**
     * 累加影响记录数
     * @param name  任务名称
     * @param row   新增行数
     */
    void addRow(String name, Integer row);

    /**
     * 返回任务列表
     * @return			任务列表
     */
    List<JobBean> list();

}
