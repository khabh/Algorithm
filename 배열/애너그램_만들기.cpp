#include <iostream>
using namespace std;

int main() {
	string a, b;
	int result = 0;
	cin >> a >> b;
	int alphabet[26] = {};
	 
	for (auto w: a) {
		alphabet[w - 'a']++;
	}
	for (auto w: b) {
		alphabet[w - 'a']--;
	}
	for (int count: alphabet) {
		if (!count)
			continue;
		if (count > 0)
			result += count;
		else
			result -= count;
	}
	
	cout << result;
}
