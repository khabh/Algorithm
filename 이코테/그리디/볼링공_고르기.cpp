#include <iostream>

using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int weight[11] = {}, n, m, result = 0;
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		int w;
		cin >> w;
		weight[w]++;
	}
	
	for (int i = 1; i < m; i++) {
		if (!weight[i]) 
			continue;
		n -= weight[i];
		result += weight[i] * n;
	}
	
	cout << result;
}