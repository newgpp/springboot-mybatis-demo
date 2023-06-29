package com.felix.infra.account;

import com.felix.infra.models.account.AccountHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author felix
 * @description some desc
 * @date 2023/6/26 13:14
 */
@Mapper
public interface AccountHistoryMapper {

    /**
     * 无页码分页查询
     *
     * @param accountId 账户id
     * @param startTs 开始时间戳
     * @param endTs 结束时间戳
     * @param from 开始id
     * @param size 条数
     * @param direct 方向 next：向前，prev：向后
     * @return
     */
    List<AccountHistory> getPage(@Param("accountId") Long accountId, @Param("startTs") Long startTs
            , @Param("endTs") Long endTs, @Param("from") Long from, @Param("size") Integer size
            , @Param("direct") String direct);

    /**
     * 保存动账流水
     * @param history
     * @return
     */
    int save(AccountHistory history);
}
