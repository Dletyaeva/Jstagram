package jstagram;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Post implements PostContentFormatter {
    protected LocalDateTime postTime;
    protected Account postAccount;

    public String tag() {
        String s = postTime.format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));
        return s + " by " + postAccount.getUsername();
    }
}
