#include <bits/stdc++.h>
using namespace std;

int main() {
	int n, k, size;
	list<int> num;
	int prev[5001], next[5001];
	vector<int> v;

	cin >> n >> k;
	size = n;
	
	for (int i = 2; i < n; i++) {
		prev[i] = i - 1;
		next[i] = i + 1;
	}
	prev[1] = n;
	next[1] = 2;
	next[n] = 1;
	prev[n] = n - 1;
	
	int count = 1, cur = 1;
	while (n) {
		if (count == k) {
			prev[next[cur]] = prev[cur];
			next[prev[cur]] = next[cur];
			v.push_back(cur);
			count = 1;
			n--;
		}
		else {
			count++;
		}
		cur = next[cur];
	}
	
	cout << "<";
	for (int i = 0; i < size - 1; i++) {
		cout << v[i] << ", ";
	}
	cout << v[size - 1] << ">";
}
