#include <string>
#include <vector>
#include <iostream>
using namespace std;
int build[102][102] = {};

void add_build(int x, int y, int type, int n) {
	if (!type) {
		if (!x || build[x - 1][y] % 2 == 1 || (y > 0 && build[x][y - 1] >= 2) || build[x][y] == 2)
			build[x][y] += 1;
	}
	else {
		if (build[x - 1][y] % 2 == 1 || build[x - 1][y + 1] % 2 == 1 || (y > 0 && y < n - 1 && build[x][y - 1] >= 2 && build[x][y + 1] >= 2))
			build[x][y] += 2;
	}	
}

void remove_build(int x, int y, int type, int n) {
	if (!type) { 
		if (build[x][y] % 2 != 1)
			return;
		bool pillar = y < n - 1 && build[x + 1][y] % 2 == 1;
		bool left_fabric = y > 0 && build[x + 1][y - 1] >= 2;
		bool right_fabric = build[x + 1][y] >= 2;
		if (pillar && !left_fabric && !right_fabric)
			return;
		if (left_fabric && build[x][y - 1] % 2 != 1 && (!right_fabric || y < 2 || build[x + 1][y - 2] < 2))
			return;
		if (right_fabric && build[x][y + 1] % 2 != 1 && (!left_fabric || y >= n - 1 || build[x + 1][y + 1] < 2))
			return;	
		build[x][y] -= 1;
	}
	else {
		if (build[x][y] < 2)
			return;
		bool under_pillar = build[x - 1][y] % 2 == 1;
		bool left_fabric = y > 0 && build[x][y - 1] >= 2;
		bool right_fabric = y < n - 1 && build[x][y + 1] >= 2;
		if (build[x][y] == 3 && !under_pillar && !left_fabric)
			return;
		if (build[x][y + 1] % 2 == 1 && !right_fabric && build[x - 1][y + 1] % 2 != 1)
			return;
		if (left_fabric && !under_pillar && build[x - 1][y - 1] % 2 != 1)
			return;
		if (right_fabric && build[x - 1][y + 1] % 2 != 1 && build[x - 1][y + 2] % 2 != 1)
			return;
		build[x][y] -= 2;
	}
}

vector<vector<int>> solution(int n, vector<vector<int>> build_frame) {
	vector<vector<int>> result;
	for (int i = 0; i < build_frame.size(); i++) {
		int x = build_frame[i][1];
		int y = build_frame[i][0];
		int type = build_frame[i][2];
		int add = build_frame[i][3];
		if (add) {
			add_build(x, y, type, n);
		}
		else {
			remove_build(x, y, type, n);
		}
	} 
	for (int i = 0; i <= n; i++) {
		for (int j = 0; j <= n; j++) {
			if (!build[i][j])
				continue;
			if (build[i][j] % 2 == 1) {
				result.push_back({i, j, 0});
			}
			if (build[i][j] >= 2) {
				result.push_back({i, j, 1});
			}
			
		}
	}
	for (int i = 0; i <= n; i++) {
		for (int j = 0; j <= n; j++) {
			cout << build[i][j] << " ";
		}
			cout << "\n";
	}
    return result;
}

int main() {
	solution(5, {{1, 0, 0, 1}, 
	{1, 1, 1, 1}, 
	{2, 1, 0, 1}, 
	{2, 2, 1, 1}, 
	{5, 0, 0, 1}, 
	{5, 1, 0, 1}, 
	{4, 2, 1, 1}, 
	{3, 2, 1, 1}
	});
}