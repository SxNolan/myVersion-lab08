package it.unibo.deathnote.api;

import java.util.HashMap;
import java.util.Map;

public class DeathNoteImpl implements DeathNote{

    public Map<String, Death> myVictims = new HashMap<>();
    private String temp;
    long currentTime = System.currentTimeMillis();

    @Override
    public String getRule(int ruleNumber) {
        if (ruleNumber < 1 || ruleNumber > RULES.size()) {
            throw new IllegalArgumentException("Unconsistent ruleNumber");
        }
        return RULES.get(ruleNumber - 1);
    }

    @Override
    public void writeName(String name) {
        if (name == null) {
            throw new NullPointerException();
        }
        this.myVictims.put(name, new Death());
        this.currentTime = System.currentTimeMillis();
        this.temp = name;
    }

    @Override
    public boolean writeDeathCause(String cause) {
        if (this.myVictims.isEmpty() || cause == null) {
            throw new IllegalArgumentException("You cannot write death cause before writing a name");
        }

        if (System.currentTimeMillis() < currentTime + 40L) {
            Death tempDeath = this.myVictims.get(temp);
            tempDeath.deathCause = cause;
            this.myVictims.put(temp, tempDeath);
            return true;
        }
        return false;
    }

    @Override
    public boolean writeDetails(String details) {
        if (this.myVictims.isEmpty() || details == null) {
            throw new IllegalStateException("The DeathNote is empty or the details are null.");
        }

        if (System.currentTimeMillis() < currentTime + 6040L) {
            Death tempDeath = this.myVictims.get(temp);
            tempDeath.deathDetails = details;
            this.myVictims.put(temp, tempDeath);
            return true;
        }
        return false;
    }

    @Override
    public String getDeathCause(String name) {
        Death tempDeath = this.myVictims.get(name);
        return tempDeath.deathCause;
    }

    @Override
    public String getDeathDetails(String name) {
        Death tempDeath = this.myVictims.get(name);
        return tempDeath.deathDetails;
    }

    @Override
    public boolean isNameWritten(String name) {
        return this.myVictims.containsKey(name);
    }
    
}
