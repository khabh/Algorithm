#include <iostream>
#include <algorithm>

using namespace std;

int main() {
	int dp[500][500], numbers[500][500], n, result = 0;
	
	cin >> n;
	for (int i = 0; i < n; i++) 
		for (int j = 0; j <= i; j++) 
			cin >> numbers[i][j];
	
	dp[0][0] = numbers[0][0];
	
	for (int i = 0; i < n - 1; i++) {
		for (int j = 0; j <= i; j++) {
			dp[i + 1][j] = max(dp[i + 1][j], dp[i][j] + numbers[i + 1][j]);
			dp[i + 1][j + 1] = max(dp[i + 1][j + 1], dp[i][j] + numbers[i + 1][j + 1]);
		}
	}
	
	for (int i = 0; i < n; i++) {
 		result = max(result, dp[n - 1][i]);
	}
	cout << result;
}