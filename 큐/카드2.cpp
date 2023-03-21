#include <iostream>
#include <queue>
using namespace std;

int main() {
	queue<int> q;
	int n;
	cin >> n;
	for (int i = 1; i <= n; i++) {
		q.push(i);
	}
	while (--n) {
		q.pop();
		int num = q.front();
		q.pop();
		q.push(num);
	}
	cout << q.front();
} 
