#include <iostream>
#include <algorithm>

using namespace std;
int n, m, dp[5001][5001];
int main() {
	
	
	string a, b;
	cin >> a;
	cin >> b;
	
	n = a.length();
	m = b.length();
	
	for (int i = 0; i < max(n, m); i++) {
		dp[i][0] = i;
		dp[0][i] = i;
	}
	
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= m; j++) {
			if (a[i - 1] == b[j - 1]) 
				dp[i][j] = dp[i - 1][j - 1];
			else
				dp[i][j] = min(dp[i - 1][j], min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;
		}
	}
	
	cout << dp[n][m];
}