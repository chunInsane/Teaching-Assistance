package cn.edu.nuc.acmicpc.model;

import java.util.Arrays;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/6/13
 */
public class RankListUser implements Comparable<RankListUser> {
    
    private String name;
    private String nickName;
    private String reallyName;
    private String email;
    private Integer rank;
    private Integer solved;
    private Integer tried;
    private Long penalty;
    private RankListItem[] itemList;

    public RankListUser() {
    }

    @Override
    public String toString() {
        return "RankListUser{" +
                "name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", reallyName='" + reallyName + '\'' +
                ", email='" + email + '\'' +
                ", rank=" + rank +
                ", solved=" + solved +
                ", tried=" + tried +
                ", penalty=" + penalty +
                ", itemList=" + Arrays.toString(itemList) +
                '}';
    }

    @Override
    public int compareTo(RankListUser b) {
        if (b == null) {
            return -1;
        }
        if (this.solved > b.solved) {
            return -1;
        } else if (this.solved < b.solved) {
            return 1;
        } else {
            return this.penalty.compareTo(b.penalty);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getReallyName() {
        return reallyName;
    }

    public void setReallyName(String reallyName) {
        this.reallyName = reallyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getSolved() {
        return solved;
    }

    public void setSolved(Integer solved) {
        this.solved = solved;
    }

    public Integer getTried() {
        return tried;
    }

    public void setTried(Integer tried) {
        this.tried = tried;
    }

    public Long getPenalty() {
        return penalty;
    }

    public void setPenalty(Long penalty) {
        this.penalty = penalty;
    }

    public RankListItem[] getItemList() {
        return itemList;
    }

    public void setItemList(RankListItem[] itemList) {
        this.itemList = itemList;
    }
}
