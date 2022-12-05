package models.lombok.ListUser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

//@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
//public class ListUserLombokModel {
//    List<String> emails;
//}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListUserLombokModel {
    @JsonProperty("data")
    private Users users;
    @JsonProperty("support")
    private Support support;
}