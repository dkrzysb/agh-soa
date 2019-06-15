package pl.agh.kis.soa;

import pl.agh.kis.soa.model.Author;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;

@ManagedBean(name = "AuthorsManager")
@ApplicationScoped
public class AuthorsManager {
    @ManagedProperty(value="#{authorsService}")
    private AuthorsService authorsService;

    public List<Author> getAllAuthors() { return authorsService.getAllAuthors(); }

    public void setAuthorsService(AuthorsService authorsService) { this.authorsService = authorsService; }
}
