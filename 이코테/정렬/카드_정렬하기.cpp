#include <iostream>
#include <algorithm>
#include <queue>

using namespace std;

int main() {
	cin.tie(0);
	ios::sync_with_stdio(0);
	
	int n; 
	priority_queue<long long> cards;
	long long result = 0;
	
	cin >> n;
	for (int i = 0; i < n; i++) {
		long long card;
		cin >> card;
		cards.push(-card);
	}
	if (n == 1)	{
		cout << 0;
		return 0;
	}
	while (cards.size() >= 2) {
		int a = cards.top();
		cards.pop();
		a += cards.top();
		cards.pop();
		result -= a;
		cards.push(a);
	}
	cout << result;
}