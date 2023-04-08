#include <string>
#include <algorithm>
#include <string>
#include <iostream>

using namespace std;

int calc_length(int unit, string s) {
	int index = 0, result = 0;
	int max_index = s.length() - unit;
	while (index <= max_index) {
		string comp = s.substr(index, unit);
		int count = 1;
		for (int i = index + unit; i <= max_index; i += unit) {
			if (!comp.compare(s.substr(i, unit))) {
				count++;
				continue;
			}
			break;
		}
		if (count > 1) 
			result += to_string(count).length();
		result += unit;
		index += (unit * count);
	}
	result += (s.length() - index);
	return result;
}

int solution(string s) {
	int result = s.length();
	for (int i = 1; i < s.length(); i++) {
		result = min(result, calc_length(i, s));
	}
	return result;
}

int main() {
	cout << solution("aabbaccc") << "\n";
	cout << solution("ababcdcdababcdcd");
}