package eastmeet.splearn.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class EmailTest {

    @Test
    void equality() {
        Email email1 = new Email("ldw@gmail.com");
        Email email2 = new Email("ldw@gmail.com");
        Assertions.assertThat(email1).isEqualTo(email2);
    }


}