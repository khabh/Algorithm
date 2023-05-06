#include <string>
#include <vector>
#include <queue>

using namespace std;

bool is_correct(string str) {
	queue<char> q;
	for (char p : str) {
		if (p == '(') {
			q.push(p);
			continue;
		}
		if (!q.empty() && q.top() == '(')
			q.pop();
	}
	
	return q.size() == 0;
}

bool is_balanced(string str) {
	int start = 0, end = 0;
	for (char s : str) {
		if (s == '(')
			start++;
		else 
			end++;
	}
	
	return start == end;
}

string reverse_str(string u) {
	string 
	u = u.substr(1, u.length() - 2);
	for (int i = 0; i < u.length(); i++) {
		u[i] = u[i] == '(' ? ')' : '(';
	}
	
	return u;
}

string solution(string str) {
    if (str == "")
		return "";
	string u, v;
	for (int i = 2; i <= str.length(); i += 2) {
		if (is_balanced(str.substr(0, i))) {
			u = str.substr(0, i);
			v = str.substr(i);
			break;
		}
	}
	if (is_correct(u)) {
		return u + solution(v);
	}
	return "(" + solution(v) + ")" + reverse_str(u);
}

