package cn.edu.nuc.acmicpc.model;

import cn.edu.nuc.acmicpc.common.enums.JudgeReturnType;
import cn.edu.nuc.acmicpc.common.util.DateUtil;

import java.util.*;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/6/13
 */
public class RankListDto {

    private final List<RankListProblem> problemList;
    private final List<RankListUser> userList;
    private final Map<String, RankListProblem> problemMap;
    private final Map<String, RankListUser> userMap;
    private final Map<String, Integer> problemIndexMap;

    public RankListDto() {
        problemList = new LinkedList<>();
        userList = new LinkedList<>();
        problemMap = new HashMap<>();
        userMap = new HashMap<>();
        problemIndexMap = new HashMap<>();
    }

    @Override
    public String toString() {
        return "RankListDto{" +
                "problemList=" + problemList +
                ", userList=" + userList +
                '}';
    }

    public RankList toRankList() {
        RankList result = new RankList();
        result.setProblemList(problemList.toArray(new RankListProblem[problemList.size()]));
        result.setRankList(userList.toArray(new RankListUser[userList.size()]));
        Arrays.sort(result.getRankList());
        for (int index = 0; index < result.getRankList().length; ++index) {
            result.getRankList()[index].setRank(index + 1);
        }
        result.setLastFetch(DateUtil.getCurrentTime());
        return result;
    }

    public void addRankListProblem(String title) {
        RankListProblem problem = new RankListProblem();
        problem.setTitle(title);
        problem.setSolved(0);
        problem.setTried(0);

        problemIndexMap.put(title, problemList.size());
        problemList.add(problem);
        problemMap.put(title, problem);
    }

    public void addStatus(RankListStatus status) {
        Integer problemIndex = getRankListProblemIndex(status.getProblemTitle());
        if (problemIndex == null) {
            return;
        }
        RankListProblem problem = problemList.get(problemIndex);

        RankListUser user = getRankListUser(status.getUserName(), status.getNickName(), status.getEmail());
        if (user == null) {
            return;
        }

        RankListItem item = user.getItemList()[problemIndex];
        if (item.getSolved()) {
            return;
        }

        problem.setTried(problem.getTried() + 1);
        item.setTried(item.getTried() + 1);
        user.setTried(user.getTried() + 1);

        if (status.getResult() == JudgeReturnType.JUDGE_AC.ordinal()) {
            problem.setSolved(problem.getSolved() + 1);
            if (problem.getSolved() == 1) {
                item.setFirstBlood(true);
            }
            item.setSolved(true);
            item.setSolvedTime(status.getTime());
            item.setTried(item.getTried() - 1);
            item.setPenalty(item.getSolvedTime() / 1000 + item.getTried() * 20 * 60);;
            user.setSolved(user.getSolved() + 1);
            user.setPenalty(user.getPenalty() + item.getPenalty());
        }
    }

    private RankListUser getRankListUser(String username, String nickName, String email) {
        RankListUser user = userMap.get(username);
        if (user == null) {
            user = new RankListUser();
            user.setName(username);
            user.setNickName(nickName);
            user.setEmail(email);
            user.setPenalty(0L);
            user.setRank(0);
            user.setSolved(0);
            user.setTried(0);
            user.setItemList(new RankListItem[problemList.size()]);
            for (int index = 0; index < problemList.size(); ++index) {
                RankListItem rankListItem = new RankListItem();
                rankListItem.setSolvedTime(0L);
                rankListItem.setSolved(false);
                rankListItem.setPenalty(0L);
                rankListItem.setTried(0);
                rankListItem.setFirstBlood(false);
                user.getItemList()[index] = rankListItem;
            }
            userList.add(user);
            userMap.put(username, user);
        }
        return user;
    }

    private Integer getRankListProblemIndex(String title) {
        return problemIndexMap.get(title);
    }
}
