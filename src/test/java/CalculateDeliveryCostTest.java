import chursov.DeliveryCalculation;
import chursov.Workload;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateDeliveryCostTest {

    @Test   // единичная проверка. был сделан для отладки, оставлен для статистики, что знаю @Test
    @Tag("positive")
    void calculateDeliveryCostTest() {
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
    void calculateDeliveryCostTest(double distance, boolean isLarge, boolean isFragile, Workload workload, double expectedCost) {
        double countedCost = DeliveryCalculation.calculateDeliveryCost(distance, isLarge, isFragile, workload);
        assertEquals(expectedCost, countedCost);
    }
}
