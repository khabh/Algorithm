class Solution {
    public String solution(String newId) {
        newId = newId.toLowerCase()
            .replaceAll("[^a-z0-9-_.]", "")
            .replaceAll("\\.{2,}", ".");
        newId = removeInvalidPoint(newId);
        if (newId.isEmpty()) {
            newId = "a";
        }
        if (newId.length() >= 16) {
            newId = removeInvalidPoint(newId.substring(0, 15));
            return newId;
        }
        if (newId.length() <= 2) {
            String last = newId.substring(newId.length() - 1);
            newId += last.repeat(3 - newId.length());
        }
        return newId;
    }
    
    private String removeInvalidPoint(String newId) {
        if (newId.startsWith(".")) {
            newId = newId.substring(1);
        }
        if (newId.endsWith(".")) {
            newId = newId.substring(0, newId.length() - 1);
        }
        return newId;
    }
}