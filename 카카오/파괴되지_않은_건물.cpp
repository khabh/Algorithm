// https://school.programmers.co.kr/learn/courses/30/lessons/92344
#include <string>
#include <vector>

using namespace std;
int sum[1002][1002];

int solution(vector<vector<int>> board, vector<vector<int>> skills) {
    int n = board.size(), m = board[0].size(), result = 0;
    for (auto skill : skills) {
        int change = skill[0] == 1 ? -skill[5] : skill[5];
        sum[skill[1]][skill[2]] += change;
        sum[skill[3] + 1][skill[2]] -= change;
        sum[skill[1]][skill[4] + 1] -= change;
        sum[skill[3] + 1][skill[4] + 1] += change;
    }
    
    for (int i = 1; i < n; i++) 
        for (int j = 0; j < m; j++) 
            sum[i][j] += sum[i - 1][j];
    
    for (int j = 1; j < m; j++) 
        for (int i = 0; i < n; i++) 
            sum[i][j] += sum[i][j - 1];
    
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (board[i][j] + sum[i][j] > 0)
                result++;
        }
    }
    
    return result;
}