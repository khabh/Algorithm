#include <string>
#include <vector>

using namespace std;
int result = 0, count = 0;
vector<vector<int>> graph(18);

void dfs(vector<int> prev, int visit, int sheep, int wolf) {
    vector<int> info;
    info.insert(info.begin(), prev.begin(), prev.end());
    if (info[visit])
        wolf++;
    else
        sheep++;
    if (wolf >= sheep)
        return;
    result = max(sheep, result);
    info[visit] = -1;
    
    for (int i = 0; i < info.size(); i++) {
        if (info[i] != -1)
            continue;
        for (int node : graph[i]) {
            if (info[node] == -1)
                continue;
            dfs(info, node, sheep, wolf);
        }
    }
}

int solution(vector<int> info, vector<vector<int>> edges) {
    for (auto edge : edges) {
        graph[edge[0]].push_back(edge[1]);
    }
    
    dfs(info, 0, 0, 0);
    return result;
}