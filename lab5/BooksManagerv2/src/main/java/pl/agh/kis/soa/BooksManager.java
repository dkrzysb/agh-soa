package pl.agh.kis.soa;

import org.json.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@ManagedBean(name = "BooksManager", eager = true)
@SessionScoped
public class BooksManager {
    private String titleFilter;
    private String authorFilter;
    private BookType typeFilter;
    private BigDecimal fromPriceFilter;
    private BigDecimal toPriceFilter;
    private String currencyFilter;
    private int pagesFilter;
    private String[] columnsToDisplay = new String[] { "Title", "Author", "Type", "Price", "Currency", "Pages" };
    private Map<Long, Boolean> orderCheckboxes = new HashMap<>();
    private List<Book> checkedBooks;
    private String currencyDisplay;
    private Book book = new Book();

    @ManagedProperty(value="#{booksService}")
    private BooksService booksService;

    public String getTitleFilter() { return titleFilter; }
    public String getAuthorFilter() { return authorFilter; }
    public BookType getTypeFilter() { return typeFilter; }
    public BookType[] getTypeFilters() { return BookType.values(); }
    public Columns[] getColumns() { return Columns.values(); }
    public BigDecimal getFromPriceFilter() { return fromPriceFilter; }
    public BigDecimal getToPriceFilter() { return toPriceFilter; }
    public String getCurrencyFilter() { return currencyFilter; }
    public int getPagesFilter() { return pagesFilter; }
    public String getCurrencyDisplay() { return currencyDisplay; }
    public Map<Long, Boolean> getOrderCheckboxes() { return orderCheckboxes; }
    public List<Book> getCheckedBooks() { return checkedBooks; }
    public Book getBook() { return book; }
    public BigDecimal getOrderPrice() {
        BigDecimal orderPrice = new BigDecimal(0);

        for(Book book : checkedBooks) {
            if(book.getCurrency().equals("PLN"))
                orderPrice = orderPrice.add(book.getPrice());
            else {
                BigDecimal convertedPrice = getConvertedPrice(book.getPrice(), book.getCurrency());
                if(convertedPrice != null)
                    orderPrice = orderPrice.add(convertedPrice);
            }

        }


        return orderPrice;
    }
    public boolean getShouldDisplayTitle() {
        if(columnsToDisplay == null)
            return true;
        for(String columnToDisplay : columnsToDisplay) {
            if(columnToDisplay.equals("Title"))
                return true;
        }
        return false;
    }
    public boolean getShouldDisplayAuthor() {
        if(columnsToDisplay == null)
            return true;
        for(String columnToDisplay : columnsToDisplay) {
            if(columnToDisplay.equals("Author"))
                return true;
        }
        return false;
    }
    public boolean getShouldDisplayType() {
        if(columnsToDisplay == null)
            return true;
        for(String columnToDisplay : columnsToDisplay) {
            if(columnToDisplay.equals("Type"))
                return true;
        }
        return false;
    }
    public boolean getShouldDisplayPrice() {
        if(columnsToDisplay == null)
            return true;
        for(String columnToDisplay : columnsToDisplay) {
            if(columnToDisplay.equals("Price"))
                return true;
        }
        return false;
    }
    public boolean getShouldDisplayCurrency() {
        if(columnsToDisplay == null)
            return true;
        for(String columnToDisplay : columnsToDisplay) {
            if(columnToDisplay.equals("Currency"))
                return true;
        }
        return false;
    }
    public boolean getShouldDisplayPages() {
        if(columnsToDisplay == null)
            return true;
        for(String columnToDisplay : columnsToDisplay) {
            if(columnToDisplay.equals("Pages"))
                return true;
        }
        return false;
    }
    public String[] getColumnsToDisplay() {
        return columnsToDisplay;
    }
    public Book[] getBooks() {
        Book[] dbBooks = booksService.getBooks();
        if(dbBooks != null) {
            List<Book> filteredBooks = new ArrayList<>(Arrays.asList(Arrays.stream(dbBooks).map(book -> book == null ? null : new Book(book)).toArray(Book[]::new)));
            Book[] books = booksService.getBooks();
            if (titleFilter != null && titleFilter.length() != 0) {
                filteredBooks = filteredBooks.stream()
                        .filter(b -> b.getTitle().startsWith(titleFilter))
                        .collect(Collectors.toList());
            }
            if (authorFilter != null && authorFilter.length() != 0) {
                filteredBooks = filteredBooks.stream()
                        .filter(b -> b.getAuthor().startsWith(authorFilter))
                        .collect(Collectors.toList());
            }
            if (typeFilter != null && typeFilter != BookType.All) {
                filteredBooks = filteredBooks.stream()
                        .filter(b -> b.getType().equals(typeFilter))
                        .collect(Collectors.toList());
            }
            if (fromPriceFilter != null) {
                filteredBooks = filteredBooks.stream()
                        .filter(b -> b.getPrice().compareTo(fromPriceFilter) > 0)
                        .collect(Collectors.toList());
            }
            if (toPriceFilter != null) {
                filteredBooks = filteredBooks.stream()
                        .filter(b -> b.getPrice().compareTo(toPriceFilter) < 0)
                        .collect(Collectors.toList());
            }
            if (currencyFilter != null && currencyFilter.length() != 0) {
                filteredBooks = filteredBooks.stream()
                        .filter(b -> b.getCurrency().startsWith(currencyFilter))
                        .collect(Collectors.toList());
            }
            if (pagesFilter != 0) {
                filteredBooks = filteredBooks.stream()
                        .filter(b -> b.getPages() == pagesFilter)
                        .collect(Collectors.toList());
            }
            if (currencyDisplay != null && currencyDisplay.length() != 0 && currencyDisplay.equals("PLN")) {
                for (Book book : filteredBooks) {
                    if (!book.getCurrency().equals("PLN")) {
                        BigDecimal convertedPrice = getConvertedPrice(book.getPrice(), book.getCurrency());
                        if (convertedPrice != null) {
                            book.setPrice(convertedPrice);
                            book.setCurrency("PLN");
                        }
                    }
                }
            }

            return filteredBooks.toArray(new Book[0]);
        }
        else
            return new Book[0];
    }

    public void setTitleFilter(String titleFilter) { this.titleFilter = titleFilter; }
    public void setAuthorFilter(String authorFilter) { this.authorFilter = authorFilter; }
    public void setTypeFilter(BookType typeFilter) { this.typeFilter = typeFilter; }
    public void setFromPriceFilter(BigDecimal fromPriceFilter) { this.fromPriceFilter = fromPriceFilter; }
    public void setToPriceFilter(BigDecimal toPriceFilter) { this.toPriceFilter = toPriceFilter; }
    public void setCurrencyFilter(String currencyFilter) { this.currencyFilter = currencyFilter; }
    public void setPagesFilter(int pagesFilter) { this.pagesFilter = pagesFilter; }
    public void setCurrencyDisplay(String currencyDisplay) { this.currencyDisplay = currencyDisplay; }
    public void setColumnsToDisplay(String[] columnsToDisplay) { this.columnsToDisplay = columnsToDisplay; }
    public void setBooksService(BooksService booksService) { this.booksService = booksService; }
    public void setBook(Book book) { this.book = book; }

    public String order() {
        checkedBooks = new ArrayList<>();
        Book[] books = booksService.getBooks();
        for(Long id : orderCheckboxes.keySet()) {
            boolean checked = orderCheckboxes.get(id);
            if(checked) {
                for(int i = 0; i < books.length; ++i) {
                    if(books[i].getId() == id) {
                        checkedBooks.add(books[i]);
                        break;
                    }
                }
            }
        }

        return "checkout";
    }

    public String addNewBook() {
        booksService.addBook(book);
        book = new Book();

        return "index";
    }

    public String removeBook(Long id) {
        booksService.removeBook(id);

        return "index";
    }

    public String modifyBook(Long id) {
        Book book = booksService.getBookById(id);
        if(book != null) {
            this.book = book;
            return "modify-book";
        }
        else
            return "index";
    }

    public String modifyBook() {
        booksService.updateBook(book);

        return "index";
    }

    private BigDecimal getConvertedPrice(BigDecimal price, String currency) {
        try {
            URL url = new URL(String.format("https://api.exchangeratesapi.io/latest?base=%s&symbols=%s", currency, "PLN"));
            HttpsURLConnection httpConnection = (HttpsURLConnection) url.openConnection();
            httpConnection.setRequestMethod("GET");
            if (httpConnection.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                httpConnection.disconnect();
                JSONObject obj = new JSONObject(content.toString());
                double rate = Double.parseDouble(obj.getJSONObject("rates").get("PLN").toString());

                return new BigDecimal(price.doubleValue() * rate).setScale(2, RoundingMode.HALF_UP);
            } else {
                httpConnection.disconnect();
                return null;
            }
        }
        catch(Exception ex) {
            return null;
        }
    }
}
