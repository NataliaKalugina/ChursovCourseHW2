package chursov;

public class DeliveryCalculation {

    public static double calculateDeliveryCost(double distance, boolean isLarge, boolean isFragile, Workload workload){

        final double MIN_DELIVER_COST = 400;
        double deliveryCost = 0;

        if (distance <= 2){
            deliveryCost = deliveryCost + 50;
        } else if (distance <= 10) {
            deliveryCost = deliveryCost + 100;
        } else if (distance <= 30) {
            deliveryCost = deliveryCost + 200;
        } else {
            deliveryCost = deliveryCost + 300;
        }

        deliveryCost = (isLarge) ? deliveryCost + 200 : deliveryCost + 100 ;

        deliveryCost = (isFragile) ? deliveryCost + 300 : deliveryCost ;

        deliveryCost = deliveryCost * workload.getDeliveryRate();

        if (deliveryCost < MIN_DELIVER_COST) {
            deliveryCost = MIN_DELIVER_COST;
        }

        return deliveryCost;
    }
}
