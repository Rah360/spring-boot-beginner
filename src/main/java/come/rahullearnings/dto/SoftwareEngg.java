package come.rahullearnings.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
public class SoftwareEngg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;
//    1. jakarta.validation.constraints.* (The "Bouncer at the Door")
//    Examples: @NotNull, @NotBlank, @Size, @Min
//
//    What it does: These are part of the Jakarta Bean Validation specification (a standard Java rulebook for verifying data).
//    When to use it: Use this when you are receiving data from the "outside world" (like an API request).
//    How it works with Spring: When you put @Valid on your controller, Spring acts like a bouncer. It looks at the incoming JSON, checks your jakarta.validation annotations on the DTO, and if the data is bad, it stops the request immediately and throws a MethodArgumentNotValidException. Your controller method never even gets executed!

    @NotBlank(message = "name cannot be blank")
    @JsonProperty("name")
    private String name;
    @JsonProperty("tech_stack")
    private String techStack;

    public SoftwareEngg() {

    }

    public SoftwareEngg(Integer id, String name, String techStack) {
        this.id = id;
        this.name = name;
        this.techStack = techStack;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTechStack() {
        return techStack;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTechStack(String techStack) {
        this.techStack = techStack;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        SoftwareEngg that = (SoftwareEngg) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name)
                && Objects.equals(techStack, that.techStack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, techStack);
    }
}
