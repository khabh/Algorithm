// 두 번째 풀이 -> 다익스트라
#include <string>
#include <vector>
#include <queue>
#include <tuple>
#include <limits.h>

using namespace std;
int result = INT_MAX, problem_count;

int solution(int alp, int cop, vector<vector<int>> problems) {
    int max_alp = 0, max_cop = 0, dist[151][151];
    
    for (auto problem : problems) {
        max_alp = max(max_alp, problem[0]);
        max_cop = max(max_cop, problem[1]);
    }
    
    fill(&dist[0][0], &dist[150][151], INT_MAX);
    problems.push_back({0, 0, 1, 0, 1});
    problems.push_back({0, 0, 0, 1, 1});
    
    priority_queue<tuple<int, int, int>, vector<tuple<int, int, int>>, greater<>> q; 
    q.push({0, alp, cop});
    dist[alp][cop] = 0;
    
    while (!q.empty()) {
        int t, a, c;
        tie(t, a, c) = q.top();
        q.pop();
        
        if (dist[a][c] < t)
            continue;
        for (auto problem : problems) {
            if (a < problem[0] || c < problem[1])
                continue;
            int next_a = min(a + problem[2], max_alp);
            int next_c = min(c + problem[3], max_cop);
            int next_t = t + problem[4];
            if (dist[next_a][next_c] <= next_t)
                continue;
            dist[next_a][next_c] = next_t;
            q.push({next_t, next_a, next_c});
        }
    }
    
    return dist[max_alp][max_cop];
}

// 첫번째 풀이 -> dfs 효율성 통과 X
#include <string>
#include <vector>
#include <tuple>
#include <limits.h>

using namespace std;
int max_alp = 0, max_cop = 0, result = INT_MAX, problem_count;
vector<vector<int>> problems;

void dfs(int time, int alp, int cop) {
    if (alp >= max_alp && cop >= max_cop) {
        result = min(result, time);
        return;
    }
    if (time >= result)
        return;
    if (alp < max_alp)
        dfs(time + 1, alp + 1, cop);
    if (cop < max_cop)
        dfs(time + 1, alp, cop + 1);
    for (auto problem : problems) {
        if (problem[0] > alp || problem[1] > cop)
            continue;
        dfs(time + problem[4], alp + problem[2], cop + problem[3]);
    }
}

int solution(int alp, int cop, vector<vector<int>> p) {
    problems = p;
    problem_count = p.size();
    
    for (auto problem : problems) {
        max_alp = max(max_alp, problem[0]);
        max_cop = max(max_cop, problem[1]);
    }
    dfs(0, alp, cop);
    
    return result;
}