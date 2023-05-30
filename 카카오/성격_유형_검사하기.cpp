#include <string>
#include <vector>
#include <map>

using namespace std;
char personality[8] = {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};


string solution(vector<string> survey, vector<int> choices) {
    string result = "";
    map<char, int> rate;
    
    for (int i = 0; i < choices.size(); i++) {
        if (choices[i] < 4)
            rate[survey[i][0]] += (4 - choices[i]);
        else 
            rate[survey[i][1]] += (choices[i] - 4);
    }
    
    for (int i = 0; i < 8; i += 2) {
        if (rate[personality[i]] >= rate[personality[i + 1]])
            result += personality[i];
        else
            result += personality[i + 1];
    }
    return result;
}