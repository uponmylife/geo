package geo.yoyo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
public class ReportType {
    private int term;
    private String name;

    public static final List<ReportType> LIST = Arrays.asList(
            new ReportType(3, "3 DAYS"),
            new ReportType(7, "WEEK"),
            new ReportType(15, "HALF MONTH"),
            new ReportType(30, "MONTH"),
            new ReportType(91, "QUARTER"),
            new ReportType(182, "HALF"),
            new ReportType(365, "YEAR")
    );
}
