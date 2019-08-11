package car;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CarTest {
    @Test
    void initTest() {
        new Sonata(150);
    }

    @Test
    void chargeQuantityTest() {
        assertThat(new Avante(255).chargeQuantity()).isEqualTo(17);
    }

    @Test
    void nameTest() {
        assertThat(new K5(123).name()).isEqualTo("K5");
    }
}