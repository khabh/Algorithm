#include <iostream>
#include <string>
using namespace std;

string get_quad_tree(string::iterator& it) {
	char current = *(it++);
	if (current != 'x') {
		return string(1, current);
	}
	string left_up = get_quad_tree(it);
	string right_up = get_quad_tree(it);
	string left_down = get_quad_tree(it);
	string right_down = get_quad_tree(it);
	
	return "x" + left_down + right_down + left_up + right_up;
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int c;
	cin >> c;
	while (c--) {
		string input;
		cin >> input;
		string::iterator it;
		it = input.begin();
		cout << get_quad_tree(it) << "\n";	
	}
}