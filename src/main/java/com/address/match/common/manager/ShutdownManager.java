package com.address.match.common.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.PreDestroy;

/**
 * @创建人 fbk
 * @创建时间 2020/6/7 16:46
 * @描述 确保应用退出时能关闭后台线程
 **/
public class ShutdownManager {

    private static final Logger logger = LoggerFactory.getLogger("sys-user");

    @PreDestroy
    public void destroy() {
        shutdownAsyncManager();
    }

    /**
     * 停止异步执行任务
     */
    private void shutdownAsyncManager() {
        try {
            logger.info("====关闭后台任务任务线程池====");
            AsyncManager.me().shutdown();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
