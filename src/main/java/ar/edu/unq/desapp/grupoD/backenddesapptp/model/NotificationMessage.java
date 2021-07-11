package ar.edu.unq.desapp.grupoD.backenddesapptp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NotificationMessage {

    private String imdbId;
    private String message;

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public void setMessage(String s) {
        this.message = s;
    }

    public String getImdbId() {
        return this.imdbId;
    }
}
