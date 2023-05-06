#include <iostream>
#include <algorithm>

using namespace std;
int numbers[12];
int n, p, m, t, d, max_result = -1000000001, min_result = 1000000001;

void dfs(int count, int result) {
	if (count > n) {
		max_result = max(max_result, result);
		min_result = min(min_result, result);
		return;
	}
	if (p) {
		p--;
		dfs(count + 1, result + numbers[count]);
		p++;
	}
	if (m) {
		m--;
		dfs(count + 1, result - numbers[count]);
		m++;
	}
	if (t) {
		t--;
		dfs(count + 1, result * numbers[count]);
		t++;
	}
	if (d) {
		d--;
		dfs(count + 1, result / numbers[count]);
		d++;
	}
}

int main() {
	cin.tie();
	ios::sync_with_stdio(0);
	
	cin >> n;
	for (int i = 1; i <= n; i++)
		cin >> numbers[i];
	cin >> p >> m >> t >> d;
	dfs(2, numbers[1]);	
	cout << max_result << "\n" << min_result;
}