package models.lombok.ListUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Users {
    private List<Integer> id;
    @JsonProperty("email")
    private List<String> emails;
    @JsonProperty("first_name")
    private List<String> firstName;
    @JsonProperty("last_name")
    private List<String> lastName;
}