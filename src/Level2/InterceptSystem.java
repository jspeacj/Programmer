package Level2;

import java.util.PriorityQueue;
import java.util.Arrays;

public class InterceptSystem {
    private static boolean[] check = new boolean[100000001];
    private static boolean[][] interceptCheck;
    private static PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) -> a[1] - b[1]);

    public static void main(String[] args) {
        /*
            요격 시스템

            문제 설명
            A 나라가 B 나라를 침공하였습니다.
            B 나라의 대부분의 전략 자원은 아이기스 군사 기지에 집중되어 있기 때문에 A 나라는 B 나라의 아이기스 군사 기지에 융단폭격을 가했습니다.
            A 나라의 공격에 대항하여 아이기스 군사 기지에서는 무수히 쏟아지는 폭격 미사일들을 요격하려고 합니다.
            이곳에는 백발백중을 자랑하는 요격 시스템이 있지만 운용 비용이 상당하기 때문에 미사일을 최소로 사용해서 모든 폭격 미사일을 요격하려 합니다.
            A 나라와 B 나라가 싸우고 있는 이 세계는 2 차원 공간으로 이루어져 있습니다.
            A 나라가 발사한 폭격 미사일은 x 축에 평행한 직선 형태의 모양이며 개구간을 나타내는 정수 쌍 (s, e) 형태로 표현됩니다.
            B 나라는 특정 x 좌표에서 y 축에 수평이 되도록 미사일을 발사하며, 발사된 미사일은 해당 x 좌표에 걸쳐있는 모든 폭격 미사일을 관통하여 한 번에 요격할 수 있습니다.
            단, 개구간 (s, e)로 표현되는 폭격 미사일은 s와 e에서 발사하는 요격 미사일로는 요격할 수 없습니다. 요격 미사일은 실수인 x 좌표에서도 발사할 수 있습니다.
            각 폭격 미사일의 x 좌표 범위 목록 targets이 매개변수로 주어질 때, 모든 폭격 미사일을 요격하기 위해 필요한 요격 미사일 수의 최솟값을 return 하도록 solution 함수를 완성해 주세요.

            제한 사항
            1 ≤ targets의 길이 ≤ 500,000
            targets의 각 행은 [s,e] 형태입니다.
            이는 한 폭격 미사일의 x 좌표 범위를 나타내며, 개구간 (s, e)에서 요격해야 합니다.
            0 ≤ s < e ≤ 100,000,000

            입출력 예
                                targets	                      result
            [[4,5],[4,8],[10,14],[11,13],[5,12],[3,7],[1,4]]	3

            입출력 예 설명
            그림.png
            위 그림과 같이 최소 세 번의 요격 미사일 발사로 전부 방어할 수 있습니다.
         */

        /* TC 1 result : 3 */
        int[][] targets = {{4, 5}, {4, 8}, {10, 14}, {11, 13}, {5, 12}, {3, 7}, {1, 4}};

        /* 문제 풀이 방법 :
           1. 우선순위큐를 이용하여, 시작점과 종료점이 가장 작은 좌표를 우선적으로 선택하여 미사일을 발사한다.
           2. 해당 좌표를 기준으로 미사일을 발사하기 떄문에, 해당 좌표를 포함하는 모든 좌표를 검사한다.
           3. 이때 가장 작은 좌표 범위가 1이 아닐 수 있기 때문에, 해당 가장 작은 좌표의 각 범위를 기준으로 체크한다.
           => (ex. 가장 작은 범위가 (11,13)일 경우, 11 <= n < 13으로 경우의 수를 찾기
           => 11일떄 = 작은 범위(왼쪽)범위가 11보다 작거나 같고, 큰 범위(오른쪽)범위가 11보다 클 경우 (ex. (10,14))
           4. 각 범위를 기준으로 체크했을 떄, 가장 횟수가 많은 범위를 기준으로 좌표를 삭제 처리한다.
           => 좌표를 삭제 처리하기에는 시간이 많이 소모되고 처리 과정도 복잡해지기 떄문에, 임의의 배열에 해당 발사한 미사일의 좌표 값을 담아두었다가,
              다음 우선순위 큐에 값을 받아올떄, 해당 좌표 값에 포함되는 케이스일 경우 이미 요격한 좌표이기떄문에 별다른 로직없이 넘어가도록 적용한다.
              (ex. (11,13) 범위일 떄 위 (2) ~ (3)번 과정을 통해 발사한 미사일의 좌표가 11일 경우 선언해둔 배열 인덱스에 해당 값을 담아둔다.
              이후 다음 좌표가 해당 범위에 포함되어 있을경우 넘어간다.)

           주어진 테스트 케이스 1번을 예시로 문제 풀이 적용 :
            1. 가장 작은 (4,5) 선택 => 포함되는 모든 좌표 삭제
            => (4,5), (4,8), (3,7) (남은 좌표 : {10,14}, {11,13}, {5,12}, {1,4}
            => 미사일 발사 수 : 1

            2. 가장 작은 {11,13} 선택 => 포함되는 모든 좌표 삭제
            => (11,13), (5,12), (10,14) (남은 좌표 :  {1,4}
            => 미사일 발사 수 : 2

            3. 가장 작은 {1,4} 선택 => 포함되는 모든 좌표 삭제
            => (1,4) (남은 좌표 : X)
            => 미사일 발사 수 : 3

            4. 종료
            => 최종 미사일 발사 수 : 3
        */

        int answer = 0;
        Arrays.sort(targets, (int[] a, int[] b) -> a[1] - b[1]);
        int row = 0;
        int col = 0;
        for (int[] arr : targets) {
            if (arr[0] > row) row = arr[0];
            if (arr[1] > col) col = arr[1];
            pq.add(arr);
        }

        interceptCheck = new boolean[row + 1][col + 1];

        while (!pq.isEmpty()) {
            int[] target = pq.poll();
            if (checkLocation(target)) continue;
            System.out.println(Arrays.toString(target));
            checkTarget(target, targets);
            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
            answer++;
        }

        System.out.println(answer);
    }

    public static boolean checkLocation(int[] target) {
        for (int i = target[0]; i < target[1]; i++) {
            // 넣어둔 좌표는 실제로는 해당 인덱스보다 좀더 큰 값(예를 들어서 check[11]일 경우, 11 ~ 12사이에 미사일을 발사한 것이다.)이므로, 해당 값을 기준으로 한다.
            if (check[i]) {
                interceptCheck[target[0]][target[1]] = true;
                return true;
            }
        }

        return false;
    }

    public static void checkTarget(int[] target, int[][] targets) {
        interceptCheck[target[0]][target[1]] = true;
        if (target[1] - target[0] == 1) {
            // 좌표가 하나일 경우 해당 좌표를 등록 후 종료
            check[target[0]] = true;
            System.out.println("단건 최종 위치 : " + target[0]);
            return;
        }

        // 좌표가 여러개인 경우의 수는 반복문을 통해 가장 경우의 수가 많은 값을 등록
        int locate = 0; // 저장할 좌표
        int maxCnt = 0; // 포함될 개수

        for (int i = target[0]; i < target[1]; i++) {
            int cnt = 0;

            for (int[] arr : targets) {
                if (!interceptCheck[arr[0]][arr[1]] && arr[0] <= i && arr[1] > i) cnt++;
                if (arr[0] > i) break;
            }

            System.out.println(i + "일 떄 : " + cnt);
            if (cnt > maxCnt) {
                maxCnt = cnt;
                locate = i;
            }
        }

        System.out.println("다건 최종 위치 : " + locate);
        check[locate] = true;
    }
}
