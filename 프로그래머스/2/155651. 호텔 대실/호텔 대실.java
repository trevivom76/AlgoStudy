import java.util.*;

class Solution {
    private int timeToMinutes(String time) {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        return hour * 60 + minute;
    }
    
    public int solution(String[][] book_time) {
        // 🔍 핵심 패턴: "시간 구간 겹침" → 이벤트 기반 처리
        // 동시 사용 중인 객실 수의 최댓값을 구하는 것이 핵심
        
        // 이벤트 리스트 생성
        ArrayList<int[]> events = new ArrayList<>();
        
        // book_time을 순회하면서 입실/퇴실 이벤트 추가
        for (String[] booking : book_time) {
            int startTime = timeToMinutes(booking[0]);  // 입실 시간
            int endTime = timeToMinutes(booking[1]) + 10;  // 퇴실 + 청소 10분
            
            events.add(new int[]{startTime, 1});   // 입실: +1
            events.add(new int[]{endTime, -1});    // 퇴실: -1
        }
        
        // 시간 순으로 정렬 (같은 시간이면 퇴실을 먼저 처리)
        Collections.sort(events, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1]; // 같은 시간이면 -1(퇴실)이 먼저
            }
            return a[0] - b[0];
        });
        
        // 최대 객실 수 계산
        int currentRooms = 0;  // 현재 사용 중인 객실 수
        int maxRooms = 0;      // 최대 객실 수 (답)
        
        for (int[] event : events) {
            currentRooms += event[1];  // +1(입실) 또는 -1(퇴실)
            maxRooms = Math.max(maxRooms, currentRooms);
        }
        
        return maxRooms;
    }
}