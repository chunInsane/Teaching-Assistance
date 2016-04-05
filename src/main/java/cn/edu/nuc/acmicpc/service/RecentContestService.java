package cn.edu.nuc.acmicpc.service;

import java.util.List;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/5
 * Recent contest service interface.
 */
public interface RecentContestService {

    /**
     * get recent contest list.
     * @return
     */
    public List<RecentContestService> getRecentContestList();
}
