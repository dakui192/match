package com.address.match.common.manager.factory;

import com.address.match.common.utils.SpringUtils;
import com.address.match.demo.data.dto.MatchOperaLog;
import com.address.match.demo.mapper.MatchMapper;

import java.util.List;
import java.util.TimerTask;

/**
 * @创建人 fbk
 * @创建时间 2020/6/7 16:48
 * @描述 异步工厂（产生任务用）
 **/
public class AsyncFactory {

    /**
     * 查询操作日志
     * @param matchOperaLogList 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final List<MatchOperaLog> matchOperaLogList){
        return new TimerTask() {
            @Override
            public void run() {
                // 远程查询操作地点
                SpringUtils.getBean(MatchMapper.class).batchAddOperaLog(matchOperaLogList);
            }
        };
    }
}
