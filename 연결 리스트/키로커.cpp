#include <bits/stdc++.h>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int t;
	cin >> t;
	for (int i = 0; i < t; i++) {
		list<char> password;
		string input;
		list<char>::iterator cursor = password.end();
		cin >> input;
		
		for (char w: input) {
			if (w == '<') {
				if(cursor != password.begin())
					cursor--;
				continue;
			}
			if (w == '>') {
				if (cursor != password.end())
					cursor++;
				continue;
			}
			if (w == '-') {
				if (cursor != password.begin()) {
					cursor--;
					cursor = password.erase(cursor);
				}
				continue;
			}
			password.insert(cursor, w);
		}
		for (char a: password) {
			cout << a;
		}
		cout << "\n";
	} 
}
