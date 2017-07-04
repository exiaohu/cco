package com.buaa.mooc.entity;

/**
 * Created by huxia on 2017/7/4.
 */
public class GroupScore {
    private int gid;
    private Double groupScore;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public Double getGroupScore() {
        return groupScore;
    }

    public void setGroupScore(Double groupScore) {
        this.groupScore = groupScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupScore that = (GroupScore) o;

        if (gid != that.gid) return false;
        if (groupScore != null ? !groupScore.equals(that.groupScore) : that.groupScore != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = gid;
        result = 31 * result + (groupScore != null ? groupScore.hashCode() : 0);
        return result;
    }
}
