package geo.yoyo;

import org.apache.commons.lang.time.DateUtils;

import java.util.*;

import static geo.util.DateUtil.*;

public class ReportCreator {
    private Map<Practice.Pk, Integer> practiceMap = new HashMap<Practice.Pk, Integer>();
    private Date baseDate;

    private static final int[] RANGE_TYPES = {3, 7, 15, 30, 91, 182, 365};
    public static final String[] RANGE_TYPE_NAMES = {"3 DAYS", "WEEK", "HALF MONTH", "MONTH", "QUARTER", "HALF", "YEAR"};
    private List<Integer> rangeTypes = new ArrayList<Integer>();
    private List<int[]> rangeTypeReports = new ArrayList<int[]>();

    private ReportCreator(List<Practice> dayDescOrderedPractices) {
        Date recentDate = dayDescOrderedPractices.get(0).getDate();
        Date oldestDate = dayDescOrderedPractices.get(dayDescOrderedPractices.size() - 1).getDate();
        baseDate = DateUtils.isSameDay(recentDate, today()) ? today() : yesterday();

        int ableDays = diffDay(baseDate, oldestDate);
        for (int rangeType : RANGE_TYPES) if (ableDays >= rangeType) rangeTypes.add(rangeType);
        for (Practice practice : dayDescOrderedPractices) practiceMap.put(practice.getPk(), practice.getScore());

        for (int rangeType : rangeTypes) {
            int sum = 0;
            int len = Practice.TYPE_NAMES.length;
            int[] practiceTypeReports = new int[len + 1];
            for (int practiceType=0; practiceType<len; practiceType++) {
                practiceTypeReports[practiceType] = getAvgScore(rangeType, practiceType);
            }
            practiceTypeReports[len] = sum / len;
            rangeTypeReports.add(practiceTypeReports);
        }
    }

    public static List<Report> create(List<Practice> dayDescOrderedPractices) {
        List<Report> reports = new ArrayList<Report>();
        if (dayDescOrderedPractices.size()<1) return reports;
        int i = 0;
        for (int[] rangeTypeReport : new ReportCreator(dayDescOrderedPractices).rangeTypeReports) {
            reports.add(new Report(RANGE_TYPE_NAMES[i++], rangeTypeReport));
        }
        return reports;
    }

    int getAvgScore(int dayRange, Integer type) {
        int sum = 0;
        for (int dayDelta = 0; dayDelta<dayRange; dayDelta++) {
            Date date = DateUtils.addDays(baseDate, -1 * dayDelta);
            Integer score = practiceMap.get(new Practice.Pk(date, type));
            if (score == null) score = 0;
            sum += score;
        }
        return sum / dayRange;
    }

    // for test
    void setBaseDate(Date baseDate) {
        this.baseDate = baseDate;
    }
}
