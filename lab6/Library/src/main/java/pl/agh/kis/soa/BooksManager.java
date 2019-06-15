package pl.agh.kis.soa;

import pl.agh.kis.soa.model.Author;
import pl.agh.kis.soa.model.Book;
import pl.agh.kis.soa.model.DisplayColumns;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.*;
import java.util.stream.Collectors;

@ManagedBean(name = "BooksManager", eager = true)
@ApplicationScoped
public class BooksManager {
    private String titleFilter;
    private String authorFilter;
    private int pagesFilter;
    private String[] columnsToDisplay = new String[] { "Title", "Author", "Pages" };
    private Book book = new Book();
    private Author author = new Author();
    private boolean dataSeed = false;

    @ManagedProperty(value="#{booksService}")
    private BooksService booksService;

    public String getTitleFilter() { return titleFilter; }
    public String getAuthorFilter() { return authorFilter; }
    public DisplayColumns[] getColumns() { return DisplayColumns.values(); }
    public int getPagesFilter() { return pagesFilter; }
    public Book getBook() { return book; }
    public Author getAuthor() { return author; }
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
        if(!dataSeed) {
            DbInitializer.getInstance().Seed();
            dataSeed = true;
        }
        Book[] dbBooks = booksService.getBooks();
        if(dbBooks != null) {
            List<Book> filteredBooks = new ArrayList<>(Arrays.asList(Arrays.stream(dbBooks).map(book -> book == null ? null : new Book(book)).toArray(Book[]::new)));
            if (titleFilter != null && titleFilter.length() != 0) {
                filteredBooks = filteredBooks.stream()
                        .filter(b -> b.getTitle().startsWith(titleFilter))
                        .collect(Collectors.toList());
            }
            if (authorFilter != null && authorFilter.length() != 0) {
                filteredBooks = filteredBooks.stream()
                        .filter(b -> b.getAuthor().getName().startsWith(authorFilter))
                        .collect(Collectors.toList());
            }
            if (pagesFilter != 0) {
                filteredBooks = filteredBooks.stream()
                        .filter(b -> b.getPages() == pagesFilter)
                        .collect(Collectors.toList());
            }

            return filteredBooks.toArray(new Book[0]);
        }
        else
            return new Book[0];
    }

    public void setTitleFilter(String titleFilter) { this.titleFilter = titleFilter; }
    public void setAuthorFilter(String authorFilter) { this.authorFilter = authorFilter; }
    public void setPagesFilter(int pagesFilter) { this.pagesFilter = pagesFilter; }
    public void setColumnsToDisplay(String[] columnsToDisplay) { this.columnsToDisplay = columnsToDisplay; }
    public void setBooksService(BooksService booksService) { this.booksService = booksService; }
    public void setBook(Book book) { this.book = book; }
    public void setAuthor(Author author) { this.author = author; }

    public String addNewBook() {
        booksService.addBook(book, author);
        book = new Book();
        author = new Author();

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
        book = new Book();

        return "index";
    }
}
