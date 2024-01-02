import java.util.*;

class Solution {
    
    private int defaultTime;
    private int defaultCost;
    private int unitTime;
    private int unitCost;
    
    class Car {
        private final String number;
        private int prevTime;
        private int totalTime;
        
        public Car(String number) {
            this.number = number;
        }
        
        public void in(int prevTime) {
            this.prevTime = prevTime;
        }
        
        public void out(int outTime) {
            int timeVal = outTime - prevTime;
            totalTime += timeVal;
            prevTime = -1;
            
        }
        
        private int calcFee() {
            if (totalTime <= defaultTime)
                return defaultCost;
            int exceedUnit = (int)Math.ceil((totalTime - defaultTime) / (double)unitTime);
            return exceedUnit * unitCost + defaultCost;
        }
        
        public int getTotalFee() {
            if (prevTime >= 0) {
                out(23 * 60 + 59);
            }
            return calcFee();
        }
        
        public String getNumber() {
            return number;
        }
    }
    
    public int[] solution(int[] fees, String[] records) {
        defaultTime = fees[0];
        defaultCost = fees[1];
        unitTime = fees[2];
        unitCost = fees[3];
        
        Map<String, Car> cars = new HashMap<>();
        
        for (String record : records) {
            String[] splitRecord = record.split(" ");
            int time = convertTime(splitRecord[0]);
            String carNumber = splitRecord[1];
            Car car = cars.getOrDefault(carNumber, new Car(carNumber));
            if (splitRecord[2].equals("IN")) {
                car.in(time);
                cars.put(carNumber, car);
                continue;
            }
            car.out(time);
        }
        
        List<Car> sortedCars = new ArrayList<>(cars.values());
        Collections.sort(sortedCars, Comparator.comparing(Car::getNumber));
        return sortedCars.stream()
            .mapToInt(Car::getTotalFee)
            .toArray();
    }
    
    private int convertTime(String time) {
        String[] splitTime = time.split(":");
        return Integer.parseInt(splitTime[0]) * 60 + Integer.parseInt(splitTime[1]);
    }
}