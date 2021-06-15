package com.service.mail;

import com.BoardGameAtlas.BoardGameAtlasClient;
import com.config.AdminConfig;
import com.domain.dto.AtlasForumTopicDto;
import com.exceptions.NoRelatedTopicException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class MailScheduler {
    private final SimpleEmailService emailService;
    private final BoardGameAtlasClient atlasClient;
    private final AdminConfig adminConfig;

    @Scheduled(cron = "0 0 14 * * MON")
    public void sendEmailWhatDidYouPlay() throws NoRelatedTopicException {
        LocalDate topicDate = calculateTopicCreationDate();
        String dateFormatted = formatDateForHttpRequest(topicDate);
        AtlasForumTopicDto whatDidYouPlay = atlasClient.getForumTopics("what did you play this week? " + dateFormatted).get(0);
        emailService.send(
                new Mail(
                        adminConfig.getAdminMail(),
                        "What did you play?",
                        "Hey there check this week discussion about board games! \n" + whatDidYouPlay.getPost_url()
                )
        );
    }

    private String formatDateForHttpRequest(LocalDate topicDate) {
        return "(" + topicDate.getMonthValue() + "/" + topicDate.getDayOfMonth() + "/" + topicDate.getYear() + ")";
    }

    private LocalDate calculateTopicCreationDate() {
        LocalDate creationDate = LocalDate.of(2021, 06, 14);
        while (creationDate.isBefore(LocalDate.now().plusDays(7))) {
            creationDate = creationDate.plusDays(7);
        }

        return creationDate;
    }
}
