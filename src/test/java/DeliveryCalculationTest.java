import chursov.DeliveryCalculation;
import chursov.FragileLongDistanseException;
import chursov.NegativeDistanceException;
import chursov.Workload;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeliveryCalculationTest {

    @Test   // единичная проверка. был сделан для отладки, оставлен для статистики, что знаю @Test
    @Tag("positive")
    void calculateDeliveryCostTest() throws NegativeDistanceException, FragileLongDistanseException {
        double countedCost = DeliveryCalculation.calculateDeliveryCost(1, true, true, Workload.VERY_HIGH);
        assertEquals(880, countedCost);
    }

    @ParameterizedTest
    @Tag("positive")
    @DisplayName("Позитивные. Правильность расчета. Pairwise плюс граничные значения")
    @CsvSource({
            "0.01, true, true, VERY_HIGH, 880",
            "2, false, false, HIGH, 400",
            "1.99, true, true, INCREASED, 660",
            "0.01, false, false, NORMAL, 400",
            "5, false, true, NORMAL, 500",
            "2.01, true, false, VERY_HIGH, 480",
            "9.99, false, true, HIGH, 700",
            "10, true, false, INCREASED, 400",
            "10.01, true, true, INCREASED, 840",
            "15, false, false, NORMAL, 400",
            "30, true, true, VERY_HIGH, 1120",
            "29.99, false, false, HIGH, 420",
            "30.01, true, false, INCREASED, 600",
            "999999999, true, false, VERY_HIGH, 800",
    })
    void calculateDeliveryCostTest(double distance, boolean isLarge, boolean isFragile, Workload workload, double expectedCost) throws NegativeDistanceException, FragileLongDistanseException {
        double countedCost = DeliveryCalculation.calculateDeliveryCost (distance, isLarge, isFragile, workload);
        assertEquals(expectedCost, countedCost);
    }

    @Test
    @Tag("negative")
    @DisplayName("Негативный. Отрицательное расстояние")
    void CalculateDeliveryCostNegativeDistanceTest() {
        double distance = -10;      // отрицательное расстояние
        boolean isLarge = false;
        boolean isFragile = false;
        Workload workload= Workload.VERY_HIGH;

        NegativeDistanceException actualException = assertThrows (NegativeDistanceException.class, () ->
                DeliveryCalculation.calculateDeliveryCost(distance, isLarge, isFragile, workload));
        assertEquals("Расстояние не может быть отрицательным", actualException.getMessage());
    }

    @Test
    @Tag("negative")
    @DisplayName("Негативный. Хрупкий груз на 30+ км")
    void CalculateDeliveryCostFragleLongDistanceTest() {
        double distance = 31;      // расстояние 30+ и хрупкий грузо
        boolean isLarge = false;
        boolean isFragile = true;   // расстояние 30+ и хрупкий грузо
        Workload workload= Workload.VERY_HIGH;

        FragileLongDistanseException exception = assertThrows(FragileLongDistanseException.class, () ->
                DeliveryCalculation.calculateDeliveryCost(distance, isLarge, isFragile, workload));
        assertEquals("Хрупкие грузы нельзя возить на расстояние более 30 км", exception.getMessage());
    }
}


