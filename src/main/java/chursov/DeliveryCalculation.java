package chursov;

public class DeliveryCalculation {

    public static double calculateDeliveryCost(double distance, boolean isLarge, boolean isFragile, Workload workload) throws NegativeDistanceException, FragileLongDistanseException {

        final double MIN_DELIVER_COST = 400;
        double deliveryCost = 0;

        if (distance < 0){
            throw new NegativeDistanceException ("Расстояние не может быть отрицательным");
        }
        if (isFragile == true && distance > 30){
            throw new FragileLongDistanseException ("Хрупкие грузы нельзя возить на расстояние более 30 км");
        }

        if (distance <= 2){
            deliveryCost = deliveryCost + 50;
        } else if (distance <= 10) {
            deliveryCost = deliveryCost + 100;
        } else if (distance <= 30) {
            deliveryCost = deliveryCost + 200;
        } else {
            deliveryCost = deliveryCost + 300;
        }

        deliveryCost = (isLarge == true) ? deliveryCost + 200 : deliveryCost + 100 ; // не ругайтесь на "== true". мне так понятнее

        deliveryCost = (isFragile == true) ? deliveryCost + 300 : deliveryCost ;

        deliveryCost = deliveryCost * workload.getDeliveryRate();

        if (deliveryCost < MIN_DELIVER_COST) {
            deliveryCost = MIN_DELIVER_COST;
        }

        return deliveryCost;
    }
}
