package pl.agh.kis.soa;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.math.BigDecimal;
import java.math.RoundingMode;

@ManagedBean
@ApplicationScoped
public class BooksService {
    private static final Book[] books = new Book[] {
            new Book(1l,"W pustyni i w puszczy", "Henryk Sienkiewicz", BookType.Novel, new BigDecimal(10.75).setScale(2, RoundingMode.HALF_UP), "PLN", 336),
            new Book(2l,"Bohun", "Jacek Komuda", BookType.Fantasy, new BigDecimal(27.93).setScale(2, RoundingMode.HALF_UP), "USD", 338)
    };

    public Book[] getBooks() { return books; }
}
