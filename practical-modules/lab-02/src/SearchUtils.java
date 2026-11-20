public class SearchUtils {

    // Stores search results and inspection counts (Problems 3 & 4)
    public static class SearchResult {
        public boolean found;
        public int index;
        public long inspections;

        public SearchResult(boolean found, int index, long inspections) {
            this.found = found;
            this.index = index;
            this.inspections = inspections;
        }
    }

    // Stores range results for Problem 5
    public static class RangeResult {
        public int first;
        public int last;
        public int count;

        public RangeResult(int first, int last, int count) {
            this.first = first;
            this.last = last;
            this.count = count;
        }
    }

    // Problem 3: Left-to-right conditional linear search (first value >= requirement)
    public static SearchResult findFirstSufficient(int[] charges, int requirement) {
        long inspections = 0;
        for (int i = 0; i < charges.length; i++) {
            inspections++;
            if (charges[i] >= requirement) {
                return new SearchResult(true, i, inspections);
            }
        }
        return new SearchResult(false, -1, inspections);
    }

    // Problem 4 Comparison Baseline: Left-to-right exact first-match linear search
    public static SearchResult linearSearchExact(int[] a, int target) {
        long inspections = 0;
        for (int i = 0; i < a.length; i++) {
            inspections++;
            if (a[i] == target) {
                return new SearchResult(true, i, inspections);
            }
        }
        return new SearchResult(false, -1, inspections);
    }

    // Problem 4: Iterative binary search with inclusive boundaries
    public static SearchResult binarySearchIterative(int[] a, int target, boolean printTrace) {
        long inspections = 0;
        int low = 0;
        int high = a.length - 1;

        if (printTrace) {
            System.out.println("  Trace (low | high | mid | a[mid] | Action):");
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midVal = a[mid];
            inspections++;

            if (midVal == target) {
                if (printTrace) {
                    System.out.println("         " + low + "  |  " + high + "   |  " + mid
                            + "  |  " + midVal + "   | Return " + mid + ".");
                }
                return new SearchResult(true, mid, inspections);
            } else if (midVal < target) {
                if (printTrace) {
                    System.out.println("         " + low + "  |  " + high + "   |  " + mid
                            + "  |  " + midVal + "   | Set low to " + (mid + 1) + ".");
                }
                low = mid + 1;
            } else {
                if (printTrace) {
                    System.out.println("         " + low + "  |  " + high + "   |  " + mid
                            + "  |  " + midVal + "   | Set high to " + (mid - 1) + ".");
                }
                high = mid - 1;
            }
        }

        if (printTrace) {
            System.out.println("         Loop ended (low=" + low + " > high=" + high + ") -> Return -1.");
        }
        return new SearchResult(false, -1, inspections);
    }

    // Problem 5 Helper A: Find first occurrence of target
    public static int findFirstOccurrence(int[] a, int target, boolean printTrace) {
        int low = 0;
        int high = a.length - 1;
        int candidate = -1;

        if (printTrace) {
            System.out.println("  First-Boundary Trace (low | high | mid | a[mid] | candidate | Action):");
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] == target) {
                candidate = mid; // Save match and continue searching left
                if (printTrace) {
                    System.out.println("                        " + low + "  |  " + high + "   |  " + mid
                            + "  |   " + a[mid] + "    |     " + candidate + "     | Match! Save candidate=" + mid + ", set high=" + (mid - 1));
                }
                high = mid - 1;
            } else if (a[mid] < target) {
                if (printTrace) {
                    System.out.println("                        " + low + "  |  " + high + "   |  " + mid
                            + "  |   " + a[mid] + "    |     " + candidate + "     | Set low=" + (mid + 1));
                }
                low = mid + 1;
            } else {
                if (printTrace) {
                    System.out.println("                        " + low + "  |  " + high + "   |  " + mid
                            + "  |   " + a[mid] + "    |     " + candidate + "     | Set high=" + (mid - 1));
                }
                high = mid - 1;
            }
        }
        return candidate;
    }

    // Problem 5 Helper B: Find last occurrence of target
    public static int findLastOccurrence(int[] a, int target, boolean printTrace) {
        int low = 0;
        int high = a.length - 1;
        int candidate = -1;

        if (printTrace) {
            System.out.println("  Last-Boundary Trace  (low | high | mid | a[mid] | candidate | Action):");
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] == target) {
                candidate = mid; // Save match and continue searching right
                if (printTrace) {
                    System.out.println("                        " + low + "  |  " + high + "   |  " + mid
                            + "  |   " + a[mid] + "    |     " + candidate + "     | Match! Save candidate=" + mid + ", set low=" + (mid + 1));
                }
                low = mid + 1;
            } else if (a[mid] < target) {
                if (printTrace) {
                    System.out.println("                        " + low + "  |  " + high + "   |  " + mid
                            + "  |   " + a[mid] + "    |     " + candidate + "     | Set low=" + (mid + 1));
                }
                low = mid + 1;
            } else {
                if (printTrace) {
                    System.out.println("                        " + low + "  |  " + high + "   |  " + mid
                            + "  |   " + a[mid] + "    |     " + candidate + "     | Set high=" + (mid - 1));
                }
                high = mid - 1;
            }
        }
        return candidate;
    }

    // Problem 5: Complete occurrence range query
    public static RangeResult findOccurrenceRange(int[] a, int target, boolean printTrace) {
        int first = findFirstOccurrence(a, target, printTrace);
        if (first == -1) {
            return new RangeResult(-1, -1, 0);
        }
        int last = findLastOccurrence(a, target, printTrace);
        int count = last - first + 1;
        return new RangeResult(first, last, count);
    }

    // Problem 6: Upper bound binary search (first index with a[i] > target, or n if none)
    public static int findFirstStrictlyGreater(int[] a, int target, boolean printTrace) {
        int low = 0;
        int high = a.length - 1;
        int candidate = a.length; // Default to n when no later checkpoint exists

        if (printTrace) {
            System.out.println("  Upper-Bound Trace (low | high | mid | a[mid] | candidate | Action):");
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] > target) {
                candidate = mid; // Qualifying checkpoint found; look for an even earlier one on the left
                if (printTrace) {
                    System.out.println("                     " + low + "  |  " + high + "   |  " + mid
                            + "  |   " + a[mid] + "   |     " + candidate + "     | > target, save candidate=" + mid + ", high=" + (mid - 1));
                }
                high = mid - 1;
            } else {
                // a[mid] <= target: values at most t belong on the discarded left side
                if (printTrace) {
                    System.out.println("                     " + low + "  |  " + high + "   |  " + mid
                            + "  |   " + a[mid] + "   |     " + candidate + "     | <= target, discard left, low=" + (mid + 1));
                }
                low = mid + 1;
            }
        }
        return candidate;
    }
}