package Level2;

import java.util.*;

public class LifeBoat {
    public static void main(String[] args) {
        /*
            구명보트
            문제 설명
            무인도에 갇힌 사람들을 구명보트를 이용하여 구출하려고 합니다.
            구명보트는 작아서 한 번에 최대 2명씩 밖에 탈 수 없고, 무게 제한도 있습니다.

            예를 들어, 사람들의 몸무게가 [70kg, 50kg, 80kg, 50kg]이고 구명보트의
            무게 제한이 100kg이라면 2번째 사람과 4번째 사람은 같이 탈 수 있지만
            1번째 사람과 3번째 사람의 무게의 합은 150kg이므로 구명보트의 무게 제한을 초과하여 같이 탈 수 없습니다.

            구명보트를 최대한 적게 사용하여 모든 사람을 구출하려고 합니다.

            사람들의 몸무게를 담은 배열 people과 구명보트의 무게 제한 limit가 매개변수로 주어질 때,
            모든 사람을 구출하기 위해 필요한 구명보트 개수의 최솟값을 return 하도록 solution 함수를 작성해주세요.

            제한사항
            무인도에 갇힌 사람은 1명 이상 50,000명 이하입니다.
            각 사람의 몸무게는 40kg 이상 240kg 이하입니다.
            구명보트의 무게 제한은 40kg 이상 240kg 이하입니다.
            구명보트의 무게 제한은 항상 사람들의 몸무게 중 최댓값보다 크게 주어지므로 사람들을 구출할 수 없는 경우는 없습니다.
            입출력 예
                 people	        limit	return
            [70, 50, 80, 50]	 100	  3
            [70, 80, 50]	     100	  3
         */

        /* TC 1 */
        //int[] people = {70, 50, 80, 50};
        //int limit = 100;

        /* TC 2 */
        //int[] people = {70, 80, 50};
        //int limit = 100;

        /* TC 3 */
        //int[] people = {40, 50, 150, 160};
        //int limit = 200;

        /* TC 4 */
        int[] people = {100, 500, 500, 900, 950};
        int limit = 1000;

        int answer = 0;
        Deque<Integer> deque = new LinkedList<>(); // 양방향 입출력이 가능한 Deque 선언

        Arrays.sort(people); // 오름차순
        for (int i : people) deque.add(i); // 오름차순 정렬된 값을 Deque에다 넣기.

        while (true) {
            answer++;; //보트읙 개수를 추가한다.
            int firstNum = deque.pollFirst(); // 현재 deque의 값들 중 몸무게가 가장 작은 사람을 뽑아낸다.

            while (true) {
                if (deque.isEmpty()) break; // 더이상 사람이 없을 경우 종료
                int lastNum = deque.pollLast(); // 현재 deque의 값들 중, 몸무게가 가장 큰 사람을 뽑아낸다.

                if (firstNum + lastNum > limit) {
                    //몸무게가 가장 큰 사람이 몸무게와 가장 작은 사람의 몸무게를 더한 값이 무게제한보다 클 경우, 몸무게가 가장 큰 사람은 무조건 혼자서 보트를 타야한다.
                    answer++;
                    continue;
                }
                break;
            }

            if(deque.isEmpty()) break;
        }

        System.out.println(answer);


    }
}
