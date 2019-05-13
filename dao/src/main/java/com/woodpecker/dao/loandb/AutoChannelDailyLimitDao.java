package com.woodpecker.dao.loandb;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.loandb.AutoChannelDailyLimitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈qs_auto_channel_daily_limit表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface AutoChannelDailyLimitDao extends JpaRepository<AutoChannelDailyLimitEntity, Long> {

}
