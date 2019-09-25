package com.michael.university.domain;

import java.util.List;
import java.util.ListIterator;

public class BonusAverage implements AvgCalc {

    private long maxGrade;

    private long bonusMaxGrade;

    private long minGrade;

    private long bonusMinGrade;

    public BonusAverage(long maxGrade,long bonusMaxGrade,long minGrade, long bonusMinGrade){
        this.maxGrade = maxGrade;
        this.bonusMaxGrade = bonusMaxGrade;
        this.minGrade = minGrade;
        this.bonusMinGrade = bonusMinGrade;
    }

    @Override
    public Long calcAverage(List<Course> course_list) {
        long sum = 0;
        long numOfGrades = 0;
        for (ListIterator<Course> iter = course_list.listIterator(); iter.hasNext(); ) {
            Course course = iter.next();
            List<Enrollment> enrollment_list = course.getEnrollments();
            for (ListIterator<Enrollment> enrollment_iter = enrollment_list.listIterator(); enrollment_iter.hasNext(); ) {
                Enrollment enrollment = enrollment_iter.next();
                Long long_val = enrollment.getGrade();
                if (long_val==null){
                    continue;
                }
                sum += long_val.longValue();
                numOfGrades++;

            }
        }
        if (numOfGrades==0)
        {
            System.out.println("No grades were found");
            return null;
        }

        long avg = sum/numOfGrades;
        if (avg>maxGrade){
            avg += bonusMaxGrade;
        }
        else if (avg<minGrade){
            avg += bonusMinGrade;
        }
        return new Long(avg);

    }

    public long getMaxGrade() {
        return maxGrade;
    }

    public void setMaxGrade(long maxGrade) {
        this.maxGrade = maxGrade;
    }

    public long getBonusMaxGrade() {
        return bonusMaxGrade;
    }

    public void setBonusMaxGrade(long bonusMaxGrade) {
        this.bonusMaxGrade = bonusMaxGrade;
    }

    public long getMinGrade() {
        return minGrade;
    }

    public void setMinGrade(long minGrade) {
        this.minGrade = minGrade;
    }

    public long getBonusMinGrade() {
        return bonusMinGrade;
    }

    public void setBonusMinGrade(long bonusMinGrade) {
        this.bonusMinGrade = bonusMinGrade;
    }
}
