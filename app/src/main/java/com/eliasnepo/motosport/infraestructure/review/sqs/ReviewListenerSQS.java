package com.eliasnepo.motosport.infraestructure.review.sqs;

import com.eliasnepo.motosport.infraestructure.review.jpa.ReviewEntity;
import com.eliasnepo.motosport.infraestructure.review.jpa.ReviewRepositoryJpa;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.io.IOException;

@Service
public class ReviewListenerSQS {

    private final static Logger log = LoggerFactory.getLogger(ReviewListenerSQS.class);
    private ObjectMapper objectMapper;
    private ReviewRepositoryJpa repository;

    @Autowired
    public ReviewListenerSQS(ObjectMapper objectMapper, ReviewRepositoryJpa repository) {
        this.objectMapper = objectMapper;
        this.repository = repository;
    }

    @JmsListener(destination = "producer-review")
    public void receiveProductEvent(TextMessage textMessage) throws JMSException, IOException {
        log.info("Message received: {}", textMessage.getText());

        ReviewEntity review = new ReviewEntity();
        review.setText(textMessage.getText());
        repository.save(review);
    }
}
