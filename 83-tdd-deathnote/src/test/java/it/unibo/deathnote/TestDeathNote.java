package it.unibo.deathnote;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.api.DeathNoteImpl;

class TestDeathNote {

    static final String WRONGNAME = "Light";
    static final String EMPTYNAME = "";
    DeathNoteImpl myDeathNote;

    @BeforeEach
    private void setUp() {
    this.myDeathNote = new DeathNoteImpl();
    }

    @Test
    void testGetRule() {
        try {
            String myRule = this.myDeathNote.getRule(0);
            String mySecondRule = this.myDeathNote.getRule(-3);
            System.out.println(myRule);
            System.out.println(mySecondRule);
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Unconsistent ruleNumber", e.getMessage());
        }
    }

    @Test
    void checkConsistenceRules() {
        for (String elem : DeathNote.RULES) {
            if (elem == null || elem == "") {
                Assertions.fail();
            }    
        }
    }

    @Test
    void testWriteName() {
        Assertions.assertFalse(this.myDeathNote.isNameWritten("Mario"));
        this.myDeathNote.writeName("Mario");
        Assertions.assertTrue(this.myDeathNote.isNameWritten("Mario"));
        Assertions.assertFalse(this.myDeathNote.isNameWritten(WRONGNAME));
        Assertions.assertFalse(this.myDeathNote.isNameWritten(EMPTYNAME));
    }

    @Test
    void testWriteDeathCause() throws InterruptedException {
        try {
            this.myDeathNote.writeDeathCause("died");
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("You cannot write death cause before writing a name", e.getMessage());
        }
        String currentName;
        this.myDeathNote.writeName("L");
        currentName = "L";
        Assertions.assertEquals("heart attack", this.myDeathNote.getDeathCause(currentName));
        this.myDeathNote.writeName("Sasuke");
        currentName = "Sasuke";
        this.myDeathNote.writeDeathCause("karting accident");
        Assertions.assertEquals("karting accident", this.myDeathNote.getDeathCause(currentName));
        Thread.sleep(100);
        this.myDeathNote.writeDeathCause("suicide");
        Assertions.assertNotEquals("suicide", this.myDeathNote.getDeathCause(currentName));
    }

    
    @Test
    void testWriteDetails() throws InterruptedException {
        try {
            this.myDeathNote.writeDetails("drowned");
        } catch (IllegalStateException e) {
            Assertions.assertEquals("The DeathNote is empty or the details are null.", e.getMessage());
        }
        String currentName;
        this.myDeathNote.writeName("Pietro");
        currentName = "Pietro";
        Assertions.assertTrue(this.myDeathNote.getDeathCause(currentName) == "heart attack"
            && this.myDeathNote.getDeathDetails(currentName) == null);
        Assertions.assertTrue(this.myDeathNote.writeDetails("ran for too long"));
        Assertions.assertEquals("ran for too long", this.myDeathNote.getDeathDetails(currentName));
        this.myDeathNote.writeName("Christian");
        currentName = "Christian";
        Thread.sleep(6100);
        this.myDeathNote.writeDetails("died");
        Assertions.assertNotEquals("died", this.myDeathNote.getDeathDetails(currentName));
    }
    
}