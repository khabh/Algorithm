#include <string>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

vector<int> solution(vector<int> fees, vector<string> records) {
    map<string, vector<int>> inout;
    vector<string> car_nums;
    vector<int> result;
    for (string record : records) {
        int h = stoi(record.substr(0, 2)), m = stoi(record.substr(3, 2));
        string car_num = record.substr(6, 4);
        inout[car_num].push_back({h * 60 + m});
        if (inout[car_num].size() == 1)
            car_nums.push_back(car_num);
    }
    
    sort(car_nums.begin(), car_nums.end());
    
    for (string car_num : car_nums) {
        vector<int> record = inout[car_num];
        int count = record.size(), total = fees[1];
        int minutes = -fees[0];
        if (record.size() % 2)
            count -= 1;
        
        for (int i = 0; i < count; i += 2) 
            minutes += (record[i + 1] - record[i]);
        
        if (record.size() % 2) 
            minutes += (23 * 60 + 59 - record.back());
        
        if (minutes > 0) {
            total += ((minutes / fees[2]) * fees[3]);
            if (minutes % fees[2] != 0) 
                total += fees[3];
        }
        result.push_back(total);
    }
    
    return result;
}