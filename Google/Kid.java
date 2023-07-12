package Google;

public class Kid {
    private String kidName;
    private  String kidBirthday;

    public Kid(String kidName, String kidBirthday) {
        this.kidName = kidName;
        this.kidBirthday = kidBirthday;
    }
    @Override
    public String toString() {
        return kidName + " " + kidBirthday;
    }
}
