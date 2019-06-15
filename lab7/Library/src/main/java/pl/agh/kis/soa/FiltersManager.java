package pl.agh.kis.soa;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "FiltersManager")
@ApplicationScoped
public class FiltersManager {
    private String selectResult;
    private Long authorId;
    private Long bookId;
    private Long clientId;
    private Date startDate;
    private Date endDate;
    private List<String> result;

    @ManagedProperty(value = "#{filtersService}")
    private FiltersService filtersService;

    public String getSelectResult() { return selectResult; }
    public Long getAuthorId() { return authorId; }
    public Long getBookId() { return bookId; }
    public Long getClientId() { return clientId; }
    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }
    public List<String> getResult() { return result; }

    public void setFiltersService(FiltersService filtersService) { this.filtersService = filtersService; }
    public void setSelectResult(String selectResult) { this.selectResult = selectResult; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public void filter() {
        result = filtersService.genericFilter(selectResult, authorId, bookId, clientId, startDate, endDate);
    }
}
