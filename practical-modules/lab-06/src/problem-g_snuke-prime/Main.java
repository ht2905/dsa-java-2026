import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem G: Snuke Prime (snuke-prime_v-judge.pdf)
 *
 * Problem Summary:
 * Takahashi will use N services; service i is used on days [a_i, b_i]
 * inclusive at cost c_i per day. Alternatively, on any subset of days he can
 * pay C yen per day for Snuke Prime to cover all services used on that day.
 * Find the minimum total amount Takahashi must pay.
 *
 * Design Decision:
 * - On any single day d, the decision is independent of all other days: pay
 *   min(C, sum of c_i for services active on day d).
 * - Unlike Water Heater (Problem F), day coordinates go up to 10^9, so we
 *   cannot build an array indexed by day.
 * - Instead, we use an Event-Based Sweep Line (difference events on compressed
 *   days):
 *   - Service i adds +c_i starting at day a_i, and -c_i starting at day b_i + 1
 *     (since [a_i, b_i] is inclusive).
 *   - Sort all 2N events by day ascending.
 *   - Between consecutive distinct event days `prevDay` and `currentDay`,
 *     the active daily cost stays constant for `(currentDay - prevDay)` days,
 *     contributing `(currentDay - prevDay) * min(C, activeCost)`.
 * - Use `long` everywhere since costs and day spans can produce totals > 10^14.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N log N) to sort the 2N sweep-line events, followed by
 *   an O(N) linear sweep.
 * - Space Complexity: O(N) to store the 2N events.
 */
public class Main {

    static class Event implements Comparable<Event> {
        final long day;
        final long deltaCost;

        Event(long day, long deltaCost) {
            this.day = day;
            this.deltaCost = deltaCost;
        }

        @Override
        public int compareTo(Event other) {
            return Long.compare(this.day, other.day);
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        int n = in.nextInt();
        long primeCost = in.nextLong();

        Event[] events = new Event[2 * n];
        for (int i = 0; i < n; i++) {
            long startDay = in.nextLong();
            long endDay = in.nextLong();
            long dailyCost = in.nextLong();

            events[2 * i] = new Event(startDay, dailyCost);
            events[2 * i + 1] = new Event(endDay + 1, -dailyCost);
        }

        Arrays.sort(events);

        long minTotalCost = computeMinimumCost(events, primeCost);
        System.out.println(minTotalCost);
    }

    private static long computeMinimumCost(Event[] events, long primeCost) {
        long totalCost = 0;
        long activeDailyCost = 0;
        long prevDay = events[0].day;

        int i = 0;
        while (i < events.length) {
            long currentDay = events[i].day;
            if (currentDay > prevDay) {
                long daysInInterval = currentDay - prevDay;
                totalCost += daysInInterval * Math.min(primeCost, activeDailyCost);
                prevDay = currentDay;
            }

            // Apply all cost changes that occur on currentDay together.
            while (i < events.length && events[i].day == currentDay) {
                activeDailyCost += events[i].deltaCost;
                i++;
            }
        }

        return totalCost;
    }

    static class FastScanner {
        private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer tokenizer = new StringTokenizer("");

        String next() throws IOException {
            while (!tokenizer.hasMoreTokens()) {
                String line = reader.readLine();
                if (line == null) {
                    return null;
                }
                tokenizer = new StringTokenizer(line);
            }
            return tokenizer.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }
}
