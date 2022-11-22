package Level1;

import java.util.Arrays;

public class MinimumRectangle {
    public static void main(String[] args) {
        /*
            최소직사각형
            문제 설명
            명함 지갑을 만드는 회사에서 지갑의 크기를 정하려고 합니다.
            다양한 모양과 크기의 명함들을 모두 수납할 수 있으면서, 작아서 들고 다니기 편한 지갑을 만들어야 합니다.
            이러한 요건을 만족하는 지갑을 만들기 위해 디자인팀은 모든 명함의 가로 길이와 세로 길이를 조사했습니다.

            아래 표는 4가지 명함의 가로 길이와 세로 길이를 나타냅니다.
            명함 번호	가로 길이	세로 길이
                1	       60	       50
                2	       30	       70
                3	       60	       30
                4	       80	       40

            가장 긴 가로 길이와 세로 길이가 각각 80, 70이기 때문에 80(가로) x 70(세로) 크기의 지갑을 만들면 모든 명함들을 수납할 수 있습니다.
            하지만 2번 명함을 가로로 눕혀 수납한다면 80(가로) x 50(세로) 크기의 지갑으로 모든 명함들을 수납할 수 있습니다. 이때의 지갑 크기는 4000(=80 x 50)입니다.

            모든 명함의 가로 길이와 세로 길이를 나타내는 2차원 배열 sizes가 매개변수로 주어집니다.
            모든 명함을 수납할 수 있는 가장 작은 지갑을 만들 때, 지갑의 크기를 return 하도록 solution 함수를 완성해주세요.

            제한사항
            sizes의 길이는 1 이상 10,000 이하입니다.
            sizes의 원소는 [w, h] 형식입니다.
            w는 명함의 가로 길이를 나타냅니다.
            h는 명함의 세로 길이를 나타냅니다.
            w와 h는 1 이상 1,000 이하인 자연수입니다.
            입출력 예
            sizes	                                        result
            [[60, 50], [30, 70], [60, 30], [80, 40]]	     4000
            [[10, 7], [12, 3], [8, 15], [14, 7], [5, 15]]	 120
            [[14, 4], [19, 6], [6, 16], [18, 7], [7, 11]]	 133
            입출력 예 설명
            입출력 예 #1
            문제 예시와 같습니다.

            입출력 예 #2
            명함들을 적절히 회전시켜 겹쳤을 때, 3번째 명함(가로: 8, 세로: 15)이 다른 모든 명함보다 크기가 큽니다. 따라서 지갑의 크기는 3번째 명함의 크기와 같으며, 120(=8 x 15)을 return 합니다.

            입출력 예 #3
            명함들을 적절히 회전시켜 겹쳤을 때, 모든 명함을 포함하는 가장 작은 지갑의 크기는 133(=19 x 7)입니다.
         */

        /*
            문제 이해 및 알고리즘 구현 해야하는 순서
            1. 가로 길이 x, 세로길이 y를 기준으로 가장 작은 값을 구해야한다....
            2. 명함을 회전시켜 겹칠려고 시도할 경우, 해당 명함의 가로 및 세로 값은 서로 뒤바뀌며 바뀐 값이 현재 값을 담고 있는 minX와 minY보다 작거나 같아야한다.. (클 경우 성립X)
            3. 가로 길이(또는 세로 길이)는 먼저 구할 수 가 있다. 전달받은 배열 sizes의 모든 인덱스들의 x,y값을 합친 값이 가장 큰 인덱스의 x,y의 값을 계산해서 큰 값이 가로(또는 세로)길이가 된다.
            (이후 설명을 위해 임시로 가로 길이를 구했다고 가정한다. 알고리즘 구현 시 조건문을 통해 가로일 떄, 세로일 떄 함수를 구현해야 한다.)
            4. 3번에서 가로 길이를 구했으니 세로 길이를 구하면 된다.
            5. 반복문을 이용하여 해당 인덱스 세로 길이 값(colValue라고 정의한다.)이 다른 인덱스들의 세로 길이보다 크거나 같은지 검토한다.
            6. 이떄, 검토는 조건문을 통해 두가지 방식으로 검토한다.
                i) colValue 값이 인덱스 세로 길이보다 크거나 같을 경우 => 조건에 만족하므로 다음 인덱스로 진행한다.
                ii) colValue 값이 인덱스 세로 길이보다 작을 경우 =>
                    colValue 값이 해당 인덱스 가로 길이보다 크거나 같을 경우 조건에 만족하므로 다음 인덱스로 진행한다.
            7. 6번에서 조건을 만족하지 않을 경우, 해당 세로 길이는 모든 명함을 수납할 수 없으므로, 후보에서 제외하고 다음 인덱스를 colValue로 다시 6번 내용으로 검토한다.
            8. 6번 조건에 만족할 경우, 해당 길이가 모든 명함을 수납 할 수 있는 세로 길이이므로 minY에 해당 값을 담는다.
            9. 8번에서 최소 길이 minY에 값을 담더라도 이후 인덱스 세로길이가 6번 조건에 만족하는데 현재 minY보다 값이 작을 경우, 해당 인덱스를 minY값에 담는다.
            10. 이후 해당 최소 가로 세로 길이 minX와 minY값을 곱한 뒤 반환한다.
         */

        //int[][] sizes = {{10, 7}, {12, 3}, {8, 15}, {14, 7}, {5, 15}};
        //int[][] sizes = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
        int[][] sizes = {{14, 4}, {19, 6}, {6, 16}, {18, 7}, {7, 11}};

        int[][] sizesReverse = new int[sizes.length][sizes[0].length];
        int[][] sizesCom = new int[sizes.length * 2][sizes[0].length];
        int minX = 1001; // 최소 가로 길이
        int minY = 1001; // 최소 세로 길이
        int sum = 0; // 최초 minX(또는minY)값을 찾기 위한 변수
        
        //전달받은 배열 sizes의 모든 인덱스들의 x,y값을 합친 값이 가장 큰 인덱스의 x,y의 값을 계산해서 큰 값이 가로(또는 세로)길이가 된다.
        int cnt = findMaxValue(sizes);

        // 찾은 인덱스에서 가로 길이가 세로 길이보다 클 경우 minX에, 세로 길이가 가로 길이보다 클 경우minY에 담는다
        if (sizes[cnt][0] > sizes[cnt][1]) minX = sizes[cnt][0];
        else minY = sizes[cnt][1];

        //이후 해당 변수 cnt를 재사용하기 위해 초기화
        cnt = 0;

        // 전달받은 배열 sizes의 가로 세로 길이를 뒤집은 값을 담는다.
        for (int index = 0; index < sizes.length; index++) {
            sizesReverse[index][0] = sizes[index][1];
            sizesReverse[index][1] = sizes[index][0];
        }

        // 2차원 배열 sizesCom에다가 전달받은 배열 sizes + 가로/세로 뒤집은 변수sizesReverse를 담는다
        for (int i = 0; i < sizesCom.length; i++) {
                if (i < sizesCom.length / 2) {
                    sizesCom[i][0] = sizes[i][0];
                    sizesCom[i][1] = sizes[i][1];
                } else {
                    sizesCom[i][0] = sizesReverse[i - sizesCom.length / 2][0];
                    sizesCom[i][1] = sizesReverse[i - sizesCom.length / 2][1];
                }
        }

        for (int k = 0; k < sizesCom.length; k++) {
            System.out.println(Arrays.toString(sizesCom[k]));
        }
    }

    /*
        전달받은 배열 sizes의 모든 인덱스들의 x,y값을 합친 값이 가장 큰 인덱스의 x,y의 값을 계산해서 큰 값이 가로(또는 세로)길이가 된다.
     */
    private static int findMaxValue(int[][] sizes) {
        int sum = 0;
        int cnt = 0;

        for (int q = 0; q < sizes.length; q++) {
            if (sizes[q][0] + sizes[q][1] > sum) {
                sum = sizes[q][0] + sizes[q][1];
                cnt = q;
            } else if (sizes[q][0] + sizes[q][1] == sum) {
                //가로/세로 길이를 더한 값이 동일한 경우, 가로 또는 세로 길이가 더 큰값을 가지고 있는 인덱스를 기준으로 지정한다.
                if (sizes[q][0] > sizes[cnt][0] && sizes[q][0] > sizes[q][1]) {
                    sum = sizes[q][0] + sizes[q][1];
                    cnt = q;
                } else if (sizes[q][1] > sizes[cnt][1] && sizes[q][1] > sizes[q][0]) {
                    sum = sizes[q][0] + sizes[q][1];
                    cnt = q;
                }
            }
        }

        return cnt;
    }
}
