import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        // 다리를 큐로 표현
        Queue<Integer> bridge = new LinkedList<>();
        for (int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }
        
        int time = 0;
        int currentWeight = 0;
        int truckIndex = 0;
        
        while (truckIndex < truck_weights.length || currentWeight > 0) {
            time++;
            int exitedTruck = bridge.poll();
            currentWeight -= exitedTruck;
            if (truckIndex < truck_weights.length) {
                int nextTruck = truck_weights[truckIndex];
                if (currentWeight + nextTruck <= weight) {
                    bridge.offer(nextTruck);
                    currentWeight += nextTruck;
                    truckIndex++;
                } else {
                    bridge.offer(0);
                }
            } else {
                bridge.offer(0);
            }
        }
        
        return time;
    }
}