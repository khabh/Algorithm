#include <iostream>
#include <deque>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	
	int N, L;
	deque<pair<int, int>> d;
	cin >> N >> L;
	
	for (int i = 0; i < N; i++) {
		int num;
		cin >> num;
		
		while (!d.empty() && d.back().second >= num) {
			d.pop_back();
		}
		d.push_back({i, num});
		while (d.front().first <= i - L) {
			d.pop_front();
		}
		
		cout << d.front().second << " ";
	}
}
