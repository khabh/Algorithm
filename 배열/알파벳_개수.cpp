#include <iostream>
using namespace std;

int alphabet[26];

int main() {
	string s;
	cin >> s;
	for (auto a : s) {
		alphabet[a - 'a']++;
	}
	for (int i = 0; i< 26; i++) {
		cout << alphabet[i] << " ";
	}
} 
