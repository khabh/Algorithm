#include <iostream>
#include <algorithm>
#include <string.h>

using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int t, n, m, board[20][20], dp[20][20], result;
	cin >> t;
	while (t--) {
		cin >> n >> m;
		result = 0;
		
		for (int i = 0; i < n; i++) 
			for (int j = 0; j < m; j++) {
				cin >> board[i][j];
				dp[i][j] = board[i][j];
			}
			
				
		for (int j = 0; j < m - 1; j++) {
			for (int i = 0; i < n; i++) {
				if (i > 0)
					dp[i - 1][j + 1] = max(dp[i - 1][j + 1], board[i - 1][j + 1] + dp[i][j]);
				
				dp[i][j + 1] = max(dp[i][j + 1], dp[i][j] + board[i][j + 1]);
				if (i < n - 1)
					dp[i + 1][j + 1] = max(dp[i + 1][j + 1], board[i + 1][j + 1] + dp[i][j]);
			}
		}
		
		for (int i = 0; i < n; i++) {
			result = max(result, dp[i][m - 1]);
		}
		cout << result << "\n";
	}
}