#include <iostream>
#include <algorithm>

using namespace std;

int main() {
	cin.tie(0);
	ios::sync_with_stdio(0);
	
	int n, c, house[200000], result;
	cin >> n >> c;
	for (int i = 0; i < n; i++)
		cin >> house[i];
	sort(house, house + n);
	int start = 1, end = house[n -1] - house[0];
	while (start <= end) {
		int mid = (start + end) / 2, pos = house[0], count = 1;
		for (int i = 1; i < n; i++) {
			if (house[i] - pos >= mid) {
				count++;
				if (count > c)
					break;
				pos = house[i];
			}
		}
		if (count >= c) {
			start = mid + 1;
			result = mid;
		}
		else
			end = mid - 1;
	}
	cout << result;
}