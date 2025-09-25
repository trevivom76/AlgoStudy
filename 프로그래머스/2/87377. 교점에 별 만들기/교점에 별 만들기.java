import java.util.*;

class Solution {
    public String[] solution(int[][] line) {
        List<Point> stars = new ArrayList<>();
        
        // 모든 직선 쌍의 교점 구하기
        for (int i = 0; i < line.length; i++) {
            for (int j = i + 1; j < line.length; j++) {
                Point intersection = getIntersection(line[i], line[j]);
                if (intersection != null) {
                    stars.add(intersection);
                }
            }
        }
        
        // 최소 격자 크기 구하기
        long minX = stars.stream().mapToLong(p -> p.x).min().orElse(0);
        long maxX = stars.stream().mapToLong(p -> p.x).max().orElse(0);
        long minY = stars.stream().mapToLong(p -> p.y).min().orElse(0);
        long maxY = stars.stream().mapToLong(p -> p.y).max().orElse(0);
        
        int width = (int)(maxX - minX + 1);
        int height = (int)(maxY - minY + 1);
        
        // 격자 초기화
        char[][] grid = new char[height][width];
        for (int i = 0; i < height; i++) {
            Arrays.fill(grid[i], '.');
        }
        
        // 별 위치 표시
        for (Point star : stars) {
            int gridX = (int)(star.x - minX);
            int gridY = (int)(maxY - star.y);  // Y축 뒤집기
            grid[gridY][gridX] = '*';
        }
        
        // 결과 문자열 배열 생성
        String[] result = new String[height];
        for (int i = 0; i < height; i++) {
            result[i] = new String(grid[i]);
        }
        
        return result;
    }
    
    // 두 직선의 교점 구하기 (정수 좌표만 반환)
    private Point getIntersection(int[] line1, int[] line2) {
        long A = line1[0], B = line1[1], C = line1[2];
        long D = line2[0], E = line2[1], F = line2[2];
        
        // 분모 계산: AE - BD
        long denominator = A * E - B * D;
        if (denominator == 0) return null;  // 평행선일 경우.
        
        // 교점 공식
        long numeratorX = B * F - C * E;  // BF - CE
        long numeratorY = C * D - A * F;  // CD - AF
        
        // 정수 좌표 판별
        if (numeratorX % denominator != 0 || numeratorY % denominator != 0) {
            return null;
        }
        
        return new Point(numeratorX / denominator, numeratorY / denominator);
    }
    
    static class Point {
        long x, y;
        
        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}