#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct Student {
	int kor, eng, mat;
	string name;
};

bool cmp(Student a, Student b) {
	if (a.kor != b.kor)	
		return a.kor > b.kor;
	if (a.eng != b.eng)
		return a.eng < b.eng;
	if (a.mat != b.mat)
		return a.mat > b.mat;
	return a.name < b.name;
}

int main() {
	cin.tie(0);
	ios::sync_with_stdio(0);
	vector<Student> students;
	int n;
	cin >> n;
	while (n--) {
		Student s;
		cin >> s.name >> s.kor >> s.eng >> s.mat;
		students.push_back(s);
	}
	sort(students.begin(), students.end(), cmp);
	for (Student s : students) 
		cout << s.name << "\n";
}