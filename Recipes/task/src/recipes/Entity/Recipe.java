package recipes.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.GeneratorType;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id = 1;

    @NotBlank
    @Column(name = "recipe_name")
    private String name;

    @NotBlank
    @Column(name = "recipe_category")
    private String category;

    @Column(name = "date")
    private LocalDateTime date;

    @NotBlank
    @Column(name = "recipe_description")
    private String description;

    @NotEmpty
    @Size(min = 1)
    @ElementCollection
    private List<String> ingredients;

    @NotEmpty
    @Size(min = 1)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> directions;

    @Column(name = "user_name")
    private String userName;

    @JsonIgnore
    public int getId() {
        return id;
    }

    @JsonIgnore
    public String getUserName() {return userName;}
}