package it.unibo.deathnote.api;

public class Death {
    public String deathCause;
    public String deathDetails;

    public Death(String deathCause, String deathDetails) {
        this.deathCause = deathCause;
        this.deathDetails = deathDetails;
    }

    public Death(String deathCause) {
        this.deathCause = deathCause;
        this.deathDetails = null;
    }

    public Death() {
        this.deathCause = "heart attack";
        this.deathDetails = null;
    }
}
