package com.woodpecker.dao.loandb;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.loandb.ChannelPeroidLimitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈t_channel_peroid_limit表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface ChannelPeroidLimitDao extends JpaRepository<ChannelPeroidLimitEntity, Integer> {

}
