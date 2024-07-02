import java.util.*;
import java.io.*;

class Main {

    static int n;
    static List<Team> teams;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.valueOf(br.readLine());
        
        while (t-- > 0) {
            n = Integer.valueOf(br.readLine());
            int[] order = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                order[i] = Integer.valueOf(st.nextToken());
            }

            teams = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                teams.add(new Team(i));
            }

            for (int i = 0; i < n; i++) {
                Team current = teams.get(order[i] - 1);
                for (int j = i + 1; j < n; j++) {
                    teams.get(order[j] - 1).addPrev(current);
                }
            }

            int m = Integer.valueOf(br.readLine());
            boolean isValid = true;
            while (m-- > 0) {
                st = new StringTokenizer(br.readLine());
                Team a = teams.get(Integer.valueOf(st.nextToken()) - 1);
                Team b = teams.get(Integer.valueOf(st.nextToken()) - 1);
                
                if (b.prevs.contains(a)) {
                    b.prevs.remove(a);
                    a.addPrev(b);
                } else if (a.prevs.contains(b)) {
                    a.prevs.remove(b);
                    b.addPrev(a);
                } else {
                    isValid = false;
                }
            }

            teams.sort(Comparator.comparingInt(Team::getOrder));
            
            for (int i = 0; i < n; i++) {
                if (teams.get(i).getOrder() != i) {
                    isValid = false;
                    break;
                }
            }

            if (!isValid) {
                System.out.println("IMPOSSIBLE");
            } else {
                StringJoiner sj = new StringJoiner(" ");
                for (Team team : teams) {
                    sj.add(String.valueOf(team.num));
                }
                System.out.println(sj);
            }
        }
    }

    private static class Team {
        int num;
        Set<Team> prevs = new HashSet<>();

        public Team(int num) {
            this.num = num;
        }

        public void addPrev(Team prev) {
            prevs.add(prev);
        }

        public int getOrder() {
            return prevs.size();
        }
    }
}
