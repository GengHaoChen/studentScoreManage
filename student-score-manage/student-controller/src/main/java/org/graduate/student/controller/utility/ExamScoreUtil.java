package org.graduate.student.controller.utility;

import org.graduate.student.repository.model.ExamScore;
import org.graduate.student.service.entity.ExamScoreEntity;
import org.springframework.beans.BeanUtils;

public class ExamScoreUtil {

    public static ExamScoreEntity toExamScoreEntity(ExamScore examScore) {
        ExamScoreEntity examScoreEntity = new ExamScoreEntity();
        BeanUtils.copyProperties(examScore, examScoreEntity);
        return examScoreEntity;
    }

    public static ExamScore toExamScore(ExamScoreEntity examScoreEntity) {
        ExamScore examScore = new ExamScore();
        BeanUtils.copyProperties(examScoreEntity, examScore);
        return examScore;
    }
}
