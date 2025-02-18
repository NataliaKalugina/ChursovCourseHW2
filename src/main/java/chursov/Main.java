package chursov;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        double distance = 50;             //расстояние для доставки
        boolean isLarge = true;             // большие ли габариты
        boolean isFragile = false;          //хрупкий ли груз
        Workload workload = Workload.VERY_HIGH;  //загруженность службы доставки. варианты "very high", "high", "increased" и пр.

        double deliveryCost;

        if (isFragile == true && distance > 30){
            System.out.println("Хрупкие грузы нельзя перевозить на расстояние более 30 км. " +
                    "Наверное, здесь нужно обработать Exception, но я не умею.");
        } else if (distance <= 0){
            System.out.println("Если расстояние меньше или равно нулю, расчет стоимости доставки не производим" +
                    "Наверное, здесь нужно обработать Exception, но я не умею.");
        } else {
            deliveryCost = DeliveryCalculation.calculateDeliveryCost(distance, isLarge, isFragile, workload);
            System.out.println("стоимость доставки: " + deliveryCost);
        }


    }
}