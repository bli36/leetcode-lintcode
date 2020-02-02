
import java.util.*;

public class Solution {
    /*
     * @param timestamp: the current timestamp
     * @param event: the string to distinct different event
     * @param rate: the format is [integer]/[s/m/h/d]
     * @param increment: whether we should increase the counter
     * @return: true or false to indicate the event is limited or not
     */
    Map<String, List<Integer>> eventsLog = new HashMap<>();
    public boolean isRatelimited(int timestamp, String event, String rate, boolean increment) {
        // write your code here
        int unit = 0;
        int hits = Integer.parseInt(rate.split("/")[0]);
        switch (rate.split("/")[1]){
            case "s":
                unit = 1;
                break;
            case "m":
                unit = 60;
                break;
            case "h":
                unit = 60 * 60;
                break;
            case "d":
                unit = 60 * 60 * 24;
                break;
        }
        
        if (eventsLog.containsKey(event)) {
            
            List<Integer> tqCopy = eventsLog.get(event);
            //System.out.println(rate.split("/")[1]);
            int count = bs(tqCopy, timestamp - unit + 1);
            //System.out.println(count);
            if (count >= hits) {
                return true;
            }

        }
        
        if (increment) {
            if (!eventsLog.containsKey(event)) {
                List<Integer> timeListist = new ArrayList<>();
                timeListist.add(timestamp);
                eventsLog.put(event, timeListist);
            }
            else {
            eventsLog.get(event).add(timestamp);
                // List<Integer> timeListist = eventsLog.get(event);
                // timeListist.add(timestamp);
                // eventsLog.replace(event, timeListist);
            }
        }

        //System.out.println(eventsLog.get(event));


        return false;
    }

    public int bs(List<Integer> l, int start_time) {
        //use binary search to find the index of the first timestamp
        //per unit time
        // if (start_time <= 0) {
        //     return l.size();

        // }
        int left = 0;
        int right = l.size() - 1;
        //int mid = (left + right) >> 1;
        if (l.get(right) < start_time) {
            return 0;
        }
        if (right == -1) {
            return 0;
        }
        int ans = 0;
        while(left <= right) {
            int mid = (left + right) >> 1;
            
            if(l.get(mid) < start_time) {
                left = mid + 1;
            }
            //if two consecutive timestamps are identical, it is not supposed to stop at the mid point even if l.get(mid) == target. 
            // We need to look left and find the first one has this value.
            else {
                ans = mid;
                right = mid - 1;
            }
            //mid = (left + right) >> 1;
            //mid = (int)Math.floor((left + right) / 2);
            
        }

        return l.size() - 1 - ans + 1;

    }
}