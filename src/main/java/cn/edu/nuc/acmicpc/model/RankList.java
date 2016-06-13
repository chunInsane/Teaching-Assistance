package cn.edu.nuc.acmicpc.model;


import java.sql.Timestamp;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/6/13
 */
public class RankList {

    private RankListProblem[] problemList;

    private RankListUser[] rankList;

    private Timestamp lastFetch;

    public RankListProblem[] getProblemList() {
        return problemList;
    }

    public void setProblemList(RankListProblem[] problemList) {
        this.problemList = problemList;
    }

    public RankListUser[] getRankList() {
        return rankList;
    }

    public void setRankList(RankListUser[] rankList) {
        this.rankList = rankList;
    }

    public Timestamp getLastFetch() {
        return lastFetch;
    }

    public void setLastFetch(Timestamp lastFetch) {
        this.lastFetch = lastFetch;
    }


}
