package dev.morvan.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.morvan.model.TriagedReview;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface MyAiService {

    @SystemMessage("You are a professional poet")

    @UserMessage("Write a poem about {topic}. The poem should be {length} lines long.")
    String writePoem(String topic, int length);

    @SystemMessage("""
        You are working for a bank, processing reviews about
        financial products. Triage reviews into positive and
        negative ones, responding with a JSON document.
        """
    )
    @UserMessage("""
        Your task is to process the review delimited by ---.
        Apply sentiment analysis to the review to determine
        if it is positive or negative, considering various languages.

        For example:
        - `I love your bank, you are the best!` is a 'POSITIVE' review
        - `J'adore votre banque` is a 'POSITIVE' review
        - `I hate your bank, you are the worst!` is a 'NEGATIVE' review

        Respond with a JSON document containing:
        - the 'evaluation' key set to 'POSITIVE' if the review is
        positive, 'NEGATIVE' otherwise
        - the 'message' key set to a message thanking or apologizing
        to the customer. These messages must be polite and match the
        review's language.

        ---
        {review}
        ---
    """)
    TriagedReview triage(String review);
}
