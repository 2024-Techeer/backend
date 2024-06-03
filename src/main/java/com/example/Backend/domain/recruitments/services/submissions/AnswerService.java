package com.example.Backend.domain.recruitments.services.submissions;

import com.example.Backend.domain.recruitments.dtos.submissions.AnswerCreateDto;
import com.example.Backend.domain.recruitments.entities.applications.Option;
import com.example.Backend.domain.recruitments.entities.applications.Question;
import com.example.Backend.domain.recruitments.entities.submissions.Answer;
import com.example.Backend.domain.recruitments.entities.submissions.Choice;
import com.example.Backend.domain.recruitments.entities.submissions.Submission;
import com.example.Backend.domain.recruitments.repositorties.applications.OptionRepository;
import com.example.Backend.domain.recruitments.repositorties.applications.QuestionRepository;
import com.example.Backend.domain.recruitments.repositorties.submissions.AnswerRepository;
import com.example.Backend.domain.recruitments.repositorties.submissions.ChoiceRepository;
import com.example.Backend.domain.recruitments.repositorties.submissions.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private ChoiceRepository choiceRepository;
    @Autowired
    private OptionRepository optionRepository;
//submission : 제출서
    public Answer createAnswer(AnswerCreateDto dto, Long questionId, Long submissionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 질문입니다."));

        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 제출서입니다."));

        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setSubmission(submission);
        if(dto.getContent() != null) {
            answer.setContent(dto.getContent());
        }

        answerRepository.save(answer);

        if("multiple".equals(question.getType())) {
             for(Long optionId : dto.getOptionIds()) {
                 Option option = optionRepository.findById(optionId)
                         .orElseThrow(() -> new RuntimeException("존재하지 않는 선택지입니다."));
                 Choice choice = new Choice();
                 choice.setOption(option);
                 choice.setAnswer(answer);
                 choiceRepository.save(choice);
             }
        }

        return answer;

    }
}
