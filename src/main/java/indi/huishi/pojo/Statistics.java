package indi.huishi.pojo;

/**
 * @Author: Huishi
 * @Date: 2021/4/17 22:31
 */

/**
 * 统计
 * @author Huishi
 */
public class Statistics {
    private String className;
    private Double maxScore;
    private Double minScore;
    private Double avgScore;
    private Integer countStudent;// 学生人数

    public Statistics(String className, Double maxScore, Double minScore, Double avgScore, Integer countStudent) {
        this.className = className;
        this.maxScore = maxScore;
        this.minScore = minScore;
        this.avgScore = avgScore;
        this.countStudent = countStudent;
    }

    public Statistics() {
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Double maxScore) {
        this.maxScore = maxScore;
    }

    public Double getMinScore() {
        return minScore;
    }

    public void setMinScore(Double minScore) {
        this.minScore = minScore;
    }

    public Double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }

    public Integer getCountStudent() {
        return countStudent;
    }

    public void setCountStudent(Integer countStudent) {
        this.countStudent = countStudent;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "className='" + className + '\'' +
                ", maxScore=" + maxScore +
                ", minScore=" + minScore +
                ", avgScore=" + avgScore +
                ", countStudent=" + countStudent +
                '}';
    }
}
