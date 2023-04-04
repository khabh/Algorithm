#include <iostream>
#include <algorithm>

using namespace std;
int height[20000];

int solve(int left, int right) {
	if (left == right)
		return height[left];
	int mid = (left + right) / 2;
	int max_area = max(solve(left, mid), solve(mid + 1, right));
	int low = mid, high = mid + 1;
	int h = min(height[low], height[high]);
	max_area = max(h * 2, max_area);
	
	while (left < low || high < right) {
		if (high < right && (left == low || height[high + 1] > height[low - 1]))
			h = min(height[++high], h);
		else
			h = min(height[--low], h);
		max_area = max(max_area, h * (high - low + 1));
	}
	return max_area;
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int c, n;
	cin >> c;
	while (c--) {
		cin >> n;
		for (int i = 0; i < n; i++) {
			cin >> height[i];
		}
		cout << solve(0, n - 1) << "\n";
	}
}