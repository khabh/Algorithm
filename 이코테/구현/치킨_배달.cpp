#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
vector<pair<int, int>> house;
vector<pair<int, int>> chicken;
vector<pair<int, int>> selected_chicken;
int result = 1000000, n, m;

int calc_distance() {
	int sum = 0;
	for (auto h : house) {
		int min_dist = 100000;
		int x1 = h.first, y1 = h.second;
		for (auto c : selected_chicken) {
			min_dist = min(abs(c.first - x1) + abs(c.second - y1), min_dist);
		} 
		sum += min_dist;
	}
	
	return sum;
}

void dfs(int count, int index) {
	if (count == m) {
		result = min(calc_distance(), result);
		return;
	}
	if (index >= chicken.size()) 
		return;
	dfs(count, index + 1);
	selected_chicken.push_back(chicken[index]);
	dfs(count + 1, index + 1);
	selected_chicken.pop_back();
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			int num;
			cin >> num;
			if (!num) 
				continue;
			if (num == 1) {
				house.push_back({i, j});
				continue;
			}
			chicken.push_back({i, j});
		}
	}
	
	dfs(0, 0);
	cout << result;
}