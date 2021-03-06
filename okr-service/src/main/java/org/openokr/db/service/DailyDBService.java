package org.openokr.db.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.zzheng.framework.exception.BusinessException;
import com.zzheng.framework.mybatis.dao.pojo.Page;
import com.zzheng.framework.mybatis.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.openokr.task.vo.DailyVO;
import org.openokr.task.request.DailySearchVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuxinzh
 * @create 2019/3/1
 */
@Service
@Transactional
public class DailyDBService extends BaseServiceImpl implements IDailyDBService {

    private static final String MAPPER_NAMESPACE = "org.openokr.task.sqlmapper.DailyMapper.";
    private static final String CONDITION = "condition";

    @Override
    public List<DailyVO> getDailyList(DailySearchVO condition,Page page) throws BusinessException {
        String methodName = "getDailyList-根据条件查询日报列表";
        try {
            Map<String,Object> params = Maps.newHashMap();
            params.put(CONDITION,condition);
            if (page != null) {
                params.put("page",page);
            }
            return this.getMyBatisDao().selectListBySql(MAPPER_NAMESPACE + "getDailyList",params);
        } catch (BusinessException e) {
            logger.error("{} 失败，[condition]->{}",methodName, JSON.toJSONString(condition));
            throw e;
        } catch (Exception e) {
            logger.error("{} 异常，[condition]->{}",methodName, JSON.toJSONString(condition));
            throw new BusinessException(e);
        }
    }

    @Override
    public Integer countDailyList(DailySearchVO condition) throws BusinessException {
        String methodName = "countDailyList-根据条件统计日报分页数量";
        try {
            Map<String,Object> params = Maps.newHashMap();
            params.put(CONDITION,condition);
            return this.getMyBatisDao().selectOneBySql(MAPPER_NAMESPACE + "countDailyList",params);
        } catch (BusinessException e) {
            logger.error("{} 失败，[condition]->{}",methodName, JSON.toJSONString(condition));
            throw e;
        } catch (Exception e) {
            logger.error("{} 异常，[condition]->{}",methodName, JSON.toJSONString(condition));
            throw new BusinessException(e);
        }
    }

    @Override
    public BigDecimal getTotalWorkingHoursByTaskId(String taskId) throws BusinessException {
        try {
            if (StringUtils.isBlank(taskId)) {
                throw new BusinessException("用户ID为空");
            }
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("taskId",taskId);
            return this.getMyBatisDao().selectOneBySql(MAPPER_NAMESPACE+"getTotalWorkingHoursByTaskId",paramMap);
        } catch (BusinessException e) {
            logger.error("统计任务占用总工时 busi-error:{}-->[userId]={}", e.getMessage(),taskId, e);
            throw e;
        } catch (Exception e) {
            logger.error("统计任务占用总工时 error:{}-->[userId]={}", e.getMessage(),taskId, e);
            throw new BusinessException("统计任务占用总工时 失败");
        }
    }
}
