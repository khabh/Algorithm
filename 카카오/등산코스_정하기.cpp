// 첫 번째 풀이 
// 산봉우리와 출입구를 두 번 이상 방문하는 경우 예외 처리 불가
#include <string>
#include <vector>
#include <iostream>
#include <queue>
#define INF 10000001

using namespace std;
bool is_gate[50001], is_summit[50001];
vector<vector<int>> board(50001);

vector<int> solution(int n, vector<vector<int>> paths, vector<int> gates, vector<int> summits) {
    vector<vector<int>> graph(50001);
    vector<int> answer;
    answer.push_back(-1);
    answer.push_back(INF);
    
    for (int i = 0; i <= n; i++) {
        for (int j = 0; j <= n; j++) {
            graph[i].push_back(INF);
        }
        graph[i][i] = 0;
    }
    
    for (vector<int> path : paths) {
        board[path[0]].push_back(path[1]);
        board[path[1]].push_back(path[0]);
        graph[path[0]][path[1]] = path[2];
        graph[path[1]][path[0]] = path[2];
    }
    
    for (int gate : gates) {
        is_gate[gate] = true;
    }
    
    for (int summit : summits) {
        is_summit[summit] = true;
    }
    
    for (int k = 1; k <= n; k++) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if ((graph[i][k] != INF) && (graph[k][j] != INF)) {
                    int result = min(min(max(graph[i][k], graph[k][j]), max(graph[j][k], graph[k][i])), graph[i][j]);
                    graph[i][j] = result;
                    graph[j][i] = result;
                }
            }
        }
    }
    
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            cout << graph[i][j] << " ";
        }
        cout << "\n";
    }
    
    
    
    for (int summit : summits) {
        for (int gate : gates) {
            if (graph[gate][summit] < answer[1]) {
                answer[0] = summit;
                answer[1] = graph[gate][summit];
            }
        }
    }
    return answer;
}

// 다익스트라 이용한 두 번째 풀이
#include <string>
#include <vector>
#include <iostream>
#include <queue>
#define INF 10000001
#define MAX 50001

using namespace std;
bool is_summit[MAX];
int intensity[MAX];
vector<vector<pair<int, int>>> graph(MAX);

vector<int> solution(int n, vector<vector<int>> paths, vector<int> gates, vector<int> summits) {  
    int min_value = INF, summit;
    queue<pair<int, int>> q;
    for (int summit : summits)
        is_summit[summit] = true;
    for (int i = 1; i <= n; i++)
        intensity[i] = INF;
    for (int gate : gates) {
        intensity[gate] = -1;
        q.push({0, gate});
    }
    
    for (auto path : paths) {
        graph[path[0]].push_back({path[1], path[2]});
        graph[path[1]].push_back({path[0], path[2]});
    }
    
    
    while (!q.empty()) {
        auto [max_intensity, cur] = q.front();
        q.pop();
        
        if (max_intensity > min_value)
            continue;
        for (auto node : graph[cur]) {
            int next_intensity = max(max_intensity, node.second);
            if (intensity[node.first] <= next_intensity) // 이미 최소값이 업데이트된 경우 스킵
                continue;
            intensity[node.first] = next_intensity;
            if (is_summit[node.first]) { // 만약 이동하려는 노드가 산봉우리면
                if (next_intensity < min_value) {
                    min_value = next_intensity;
                    summit = node.first;
                }
                else if (next_intensity == min_value && node.first < summit)
                    summit = node.first;
                continue; // 큐에 추가하지 않음
            }
            q.push({next_intensity, node.first});
        }
    }
    
    vector<int> answer(2);
    answer[0] = summit;
    answer[1] = min_value;
    return answer;
}