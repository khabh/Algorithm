#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool cmp(pair<int, double> a, pair<int, double> b) {
    if (a.second != b.second) 
        return a.second > b.second;
    return a.first < b.first;
}

vector<int> solution(int N, vector<int> stages) {
    int index = 0, count = 0, sum = 0;
    double total = stages.size();
    vector<pair<int, double>> result;
    vector<int> answer;
    
    sort(stages.begin(), stages.end());
    for (int i = 1; i <= N; i++) {
        double fail = 0, rate = 0;
        while (stages[index] == i) {
            index++;
            fail++;
        }
        if (fail) {
            rate = fail / total;
            total -= fail;
        }
        result.push_back({i, rate});
    }
    sort(result.begin(), result.end(), cmp);
    for (auto stage : result) 
        answer.push_back(stage.first);
    return answer;
}