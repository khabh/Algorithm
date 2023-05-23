#include <string>
#include <vector>
#include <map>
#include <iostream>

using namespace std;

float price[7][5];
int last;
vector<int> rates, answer(2);

pair<int, int> calc_result(vector<vector<int>> users) {
    int plus = 0, total = 0;
    for (vector<int> user : users) {
        int purchase = 0;
        for (int i = 0; i < last; i++) {
            if (rates[i] * 10 < user[0])
                continue;
            purchase += price[i][rates[i]];
            if (purchase >= user[1]) {
                plus++;
                purchase = 0;
                break;
            }
        }
        total += purchase;
    }
    return {plus, total};
}

void dfs(int index, vector<vector<int>> users) {
    if (index == last) {
        pair<int, int> result = calc_result(users);
        if (rates[0] == 30 && rates[1] == 40) {
                cout << result.first << " " << result.second;
            }
        if (result.first > answer[0]) {
            answer[0] = result.first;
            answer[1] = result.second;
            
            return;
        }
        if (result.first == answer[0] && result.second > answer[1]) {
            answer[1] = result.second;
        }
        return;
    }
    
    for (int i = 1; i <= 4; i++) {
        rates.push_back(i);
        dfs(index + 1, users);
        rates.pop_back();
    }
}

vector<int> solution(vector<vector<int>> users, vector<int> emoticons) {
    last = emoticons.size();
    
    for (int i = 0; i < last; i++) {
        for (int j = 1; j <= 4; j++) {
            price[i][j] = emoticons[i] * (100 - (float)j * 10) / 100;
        }
    }
    
    dfs(0, users);
    
    return answer;
}