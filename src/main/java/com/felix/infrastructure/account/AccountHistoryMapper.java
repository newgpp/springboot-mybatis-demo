package com.felix.infrastructure.account;

import com.felix.domain.account.AccountHistory;
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
     * @param fromId 开始id
     * @param limit 条数
     * @param direction 方向 up：向前，down：向后
     * @return
     */
    List<AccountHistory> getPage(@Param("accountId") Long accountId, @Param("startTs") Long startTs
            , @Param("endTs") Long endTs, @Param("fromId") Long fromId, @Param("limit") Integer limit
            , @Param("direction") String direction);

    /**
     * 保存动账流水
     * @param history
     * @return
     */
    int save(AccountHistory history);
}
