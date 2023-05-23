#include <string>
#include <vector>
#include <tuple>
#include <map>

using namespace std;

int year, mon, date;

tuple<int, int, int> get_ymd(string str) {
    int y = stoi(str.substr(0,4));
    int m = stoi(str.substr(5, 2));
    int d = stoi(str.substr(8, 2));
    
    return make_tuple(y, m, d);
}

bool is_expired(string str, map<char, int> t) {
    int y, m, d, term;
    tie(y, m, d) = get_ymd(str);
    term = t[str[11]];
    
    m += term;
    while (m > 12) {
        y++;
        m -= 12;
    }
    
    if (y != year)
        return y < year;
    if (m != mon)
        return m < mon;
    return date >= d;
}

vector<int> solution(string today, vector<string> terms, vector<string> privacies) {
    map<char, int> t;
    vector<int> answer;
    
    tie(year, mon, date) = get_ymd(today);
    
    for (string term : terms) 
        t[term[0]] = stoi(term.substr(2));
    
    for (int i = 0; i < privacies.size(); i++) {
        if (is_expired(privacies[i], t)) {
            answer.push_back(i + 1);
        }
    }
    
    return answer;
}