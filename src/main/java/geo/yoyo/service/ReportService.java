package geo.yoyo.service;

import geo.yoyo.dao.DayRecordDao;
import geo.yoyo.model.Report;
import geo.yoyo.model.ReportSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    private DayRecordDao dayRecordDao;

    public List<Report> getReports() {
//        ReportSource reportSource = new ReportSource();
        // TODO: 하던중....
        return null;
    }
}
