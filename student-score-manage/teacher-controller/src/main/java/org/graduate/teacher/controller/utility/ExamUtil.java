package org.graduate.teacher.controller.utility;

import org.graduate.subject.repository.model.Exam;
import org.graduate.subject.service.entity.ExamEntity;
import org.springframework.beans.BeanUtils;

public class ExamUtil {

    public static Exam toExam(ExamEntity examEntity) {
        Exam exam = new Exam();
        BeanUtils.copyProperties(examEntity, exam);
        return exam;
    }

    public static ExamEntity toExamEntity(Exam exam) {
        ExamEntity examEntity = new ExamEntity();
        BeanUtils.copyProperties(exam, examEntity);
        return examEntity;
    }
}
