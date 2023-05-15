#include <iostream>
#include <algorithm>

using namespace std;

int soldier[2000], dp[2000];

int main() {
	int n, result;
	
	cin >> n;
	result = n;
	
	for (int i = 0; i < n; i++) {
		cin >> soldier[i];
		dp[i] = i;
	}
	
	for (int i = 0; i < n; i++) {
		for (int j = i + 1; j < n; j++) {
			if (soldier[i] > soldier[j]) {
				dp[j] = min(dp[j], dp[i] + j - i - 1);
			}
		}
		
	}
	
	for (int i = 0; i < n; i++) {
		result = min(result, dp[i] + n - i - 1);
	}
	
	cout << result;
}