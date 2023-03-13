#include <bits/stdc++.h>
// #include <list>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	string str;
	list<char> L;
	int m;
	cin >> str;
	cin >> m;
	for (char a: str)
		L.push_back(a);
	list<char>::iterator cursor = L.end();

	for (int i = 0; i < m; i++) {
		char command;
		cin >> command;
		if (command == 'P') {
			char add;
			cin >> add;
			L.insert(cursor, add);
			continue;
		}
		if (command == 'L') {
			if (cursor != L.begin())
				cursor--;
			continue;
		}
		if (command == 'D') {
			if (cursor != L.end())
				cursor++;
			continue;
		}
		if (cursor != L.begin()) {
			cursor--;
			cursor = L.erase(cursor);
		}
	}
	for (char a: L) 
		cout << a;
}
