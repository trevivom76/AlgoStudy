class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
        // a의 최대값
        int maxA = d / k;
        
        for (int a = 0; a <= maxA; a++) {
            // 각 a에 대한 b의 최대값 계산
            long remain = (long)d * d - (long)a * k * a * k;
            int maxB = (int)(Math.sqrt(remain) / k);
            
            // b = 0부터 maxB까지 모든 점들이 조건을 만족
            answer += (maxB + 1);  // +1은 b=0 포함
        }
        
        return answer;
    }
}


// k, d 는 두 양의 정수
// a * k 는 x 축 방향을
// b * k 는 y 축 방향을
// 이렇게 해서 점을 찍는다.
// 원점과의 거리가 d 를 넘는 위치엔 점을 찍지 못함.
// 입출력 에시 풀어보면
// 2, 4 는 6
// 0,0 0,2 0,4, 2,0, 2,2 4,4
// 이래서 6인듯?
