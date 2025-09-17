class Solution {
    public long solution(int w, int h) {
        long gcd = gcd(w, h);
        long answer = (long)w * h - (w + h - gcd);
        
        return answer;
    }
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
// 최대공약수 사용하자.
