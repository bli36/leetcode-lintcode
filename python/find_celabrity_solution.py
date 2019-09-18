class Solution:
    # @param {int} n a party with n people
    # @return {int} the celebrity's label or -1
    def findCelebrity(self, n):
        # Write your code here
        if (n < 2):
            return n - 1

        ans = 0
        for i in range(1, n):
            if (Celebrity.knows(ans, i)):
                ans = i

        for i in range(n):
            if ans != i and Celebrity.knows(ans, i):
                return -1
            if ans != i and not Celebrity.knows(i, ans):
                return -1
        return ans