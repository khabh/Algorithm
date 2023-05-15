#include <iostream>
#include <algorithm>

using namespace std;

int counsel[20][2], dp[20], n, result = 0;

int main() {
	cin >> n;
	
	for (int i = 0; i < n; i++) {
		cin >> counsel[i][0];
		cin >> counsel[i][1];
		if (counsel[i][0] + i <= n)
			dp[i] = counsel[i][1];
		else
			counsel[i][1] = 0;
	}
	
	for (int i = 0; i < n; i++) {
		int next_day = counsel[i][0] + i;
		if (next_day >= n) {
			continue;
		}
		for (int j = next_day; j < n; j++) {
			dp[j] = max(dp[j], counsel[j][1] + dp[i]);
		}
	}
	
	for (int i = 0; i < n; i++) {
		result = max(result, dp[i]);
	}
	cout << result;
}