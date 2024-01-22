import java.util.*;

class FindJudgeSolution {
    public int findJudge(int N, int[][] trust) {
        int ans = -1;
        Map<Integer, List<Integer>> k = new HashMap<Integer, List<Integer>>();

        for (int m = 1; m <= N ; m++){
            k.put(m, new ArrayList<Integer>());
        }

        for (int i = 0; i < trust.length; i++){

            k.get(trust[i][1]).add(trust[i][0]);
        }

        for (int j = 1; j <= N; j++){
            if (k.get(j).size() > 0) {
                if (k.get(j).size() == (N - 1)) {
                    ans = j;
                }
            }
        }

        for (int l = 0; l < trust.length; l++){
            if (trust[l][0] == ans){
                return -1;
            }
        }
        return ans;

    }

    public int findJudgeBetter(int N, int[][] trust){
        int[] count = new int[N+1];
        int ans = -1;
        for (int i = 0; i < trust.length; i++){
            count[trust[i][0]]--;
            count[trust[i][1]]++;
        }

        for (int j = 1; j <= N; j++){
            if (count[j] == N-1){
                ans = j;
            }
        }

        return ans;


    }
    public static void main(String[] args) {
        int N = 4;
        int[][] trust = new int[][] {{1,3},{1,4},{2,3},{2,4},{4,3}};

        FindJudgeSolution s = new FindJudgeSolution();
        int result = s.findJudgeBetter(N, trust); // Display the string.
        System.out.print("result=");
        System.out.println(result);
    }
}
