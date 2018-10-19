package homework.h1;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.rules.TestWatcher;

public class ForneymonegerieSolutionTests {
    
    // =================================================
    // Test Configuration
    // =================================================
    
    static private int START_SIZE = 16;
    
    // Global timeout to prevent infinite loops from
    // crashing the test suite
    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);
    
    // Each time you pass a test, you get a point! Yay!
    // [!] Requires JUnit 4+ to run
    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void succeeded(Description description) {
            passed++;
        }
    };
    
    // Grade record-keeping
    static int possible = 0, passed = 0;
    
    // Used as the basic empty Forneymonegerie to test; the @Before
    // method is run before every @Test
    Forneymonegerie fm;
    @Before
    public void init () {
        possible++;
        fm = new Forneymonegerie();
    }
    
    // Used for grading, reports the total number of tests
    // passed over the total possible
    @AfterClass
    public static void gradeReport () {
        System.out.println("============================");
        System.out.println("Tests Complete");
        System.out.println(passed + " / " + possible + " passed!");
        if ((1.0 * passed / possible) >= 0.9) {
            System.out.println("[!] Nice job!"); // Automated acclaim!
        }
        System.out.println("============================");
    }
    
    
    // =================================================
    // Unit Tests
    // =================================================
    // For grading purposes, every method has ~3 tests, 
    // weighted equally and totaled for the score.
    // The tests increase in difficulty such that the
    // basics are unlabeled and harder tiers are tagged
    // t1, t2, t3, ... easier -> harder
    
    
    // Initialization Tests
    // -------------------------------------------------
    @Test
    public void testInit() {
        assertTrue(fm.empty());
        assertEquals(0, fm.size());
    }

    // Basic Tests
    // -------------------------------------------------

    @Test
    public void testSize() {
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        assertEquals(fm.size(), 2);
        fm.collect("Dampymon");
        assertEquals(fm.size(), 3);
    }
    @Test
    public void testSize_t1() {
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        assertEquals(fm.size(), 3);
    }
    @Test
    public void testSize_t2() {
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        fm.collect("Zappymon");
        assertEquals(fm.size(), 3);
    }

    @Test
    public void testTypeSize() {
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        assertEquals(fm.typeSize(), 1);
        fm.collect("Dampymon");
        assertEquals(fm.typeSize(), 2);
    }
    public void testTypeSize_t1() {
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        assertEquals(fm.typeSize(), 1);
    }
    @Test
    public void testTypeSize_t2() {
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        fm.collect("Zappymon");
        assertEquals(fm.typeSize(), 3);
    }
    @Test
    public void testTypeSize_t3() {
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        fm.release("Burnymon");
        assertEquals(fm.typeSize(), 1);
        fm.release("Dampymon");
        assertEquals(fm.typeSize(), 0);
    }

    // Forneymonegerie Manipulation Tests
    // -------------------------------------------------
    @Test
    public void testCollect() {
        boolean collected = fm.collect("Burnymon");
        assertTrue(collected);
        fm.collect("Burnymon");
        collected = fm.collect("Burnymon");
        assertFalse(collected);
        fm.collect("Dampymon");
        assertTrue(fm.contains("Burnymon"));
        assertTrue(fm.contains("Dampymon"));
    }
    @Test
    public void testCollect_t1() {
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        assertTrue(fm.contains("Burnymon"));
        assertTrue(fm.contains("Dampymon"));
        assertEquals(fm.size(), 3);
        assertEquals(fm.typeSize(), 2);
    }
    @Test
    public void testCollect_t2() {
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        fm.release("Dampymon");
        assertTrue(fm.contains("Burnymon"));
        assertFalse(fm.contains("Dampymon"));
        assertEquals(fm.size(), 2);
        assertEquals(fm.typeSize(), 1);
    }
    @Test
    public void testCollect_t3() {
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        fm.collect("Zappymon");
        fm.collect("Zappymon");
        fm.release("Dampymon");
        assertEquals(fm.size(), 4);
        assertEquals(fm.typeSize(), 2);
    }
    @Test
    public void testCollect_t4() {
        for (int i = 0; i < 1000; i++) {
            fm.collect("" + i);
        }
        assertEquals(fm.size(), 1000);
        assertEquals(fm.typeSize(), 1000);
    }
    @Test
    public void testCollect_t5() {
        for (int i = 0; i < 1000; i++) {
            fm.collect("SAMESIES");
        }
        assertEquals(fm.size(), 1000);
        assertEquals(fm.typeSize(), 1);
    }

    @Test
    public void testRelease() {
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        assertEquals(fm.size(), 2);
        assertEquals(fm.typeSize(), 1);
        boolean released = fm.release("Burnymon");
        assertEquals(fm.size(), 1);
        assertEquals(fm.typeSize(), 1);
        assertTrue(released);
    }
    @Test
    public void testRelease_t1() {
        fm.collect("Dampymon");
        boolean released = fm.release("Dampymon");
        assertEquals(fm.size(), 0);
        assertTrue(released);
        assertFalse(fm.contains("Dampymon"));
        released = fm.release("Dampymon");
        assertFalse(released);
    }
    @Test
    public void testRelease_t2() {
        fm.release("Dampymon");
        fm.collect("Dampymon");
        fm.release("uni");
        assertEquals(fm.size(), 1);
        assertTrue(fm.contains("Dampymon"));
        fm.collect("Burnymon");
        fm.collect("Zappymon");
        fm.release("Dampymon");
        assertEquals(fm.size(), 2);
        assertFalse(fm.contains("Dampymon"));
    }
    @Test
    public void testRelease_t3() {
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.release("Dampymon");
        assertEquals(fm.size(), 4);
        assertEquals(fm.typeSize(), 2);
        fm.collect("Zappymon");
        fm.collect("Zappymon");
        boolean released = fm.release("Burnymon");
        assertTrue(released);
        released = fm.release("Burnymon");
        assertTrue(released);
        assertEquals(fm.size(), 4);
        assertEquals(fm.typeSize(), 2);
        assertFalse(fm.contains("Burnymon"));
    }

    @Test
    public void testReleaseAll() {
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        assertEquals(fm.size(), 3);
        assertEquals(fm.typeSize(), 2);
        fm.releaseType("Burnymon");
        assertEquals(fm.size(), 1);
        assertEquals(fm.typeSize(), 1);
    }
    @Test
    public void testReleaseAll_t1() {
        fm.releaseType("Dampymon");
        fm.collect("Dampymon");
        fm.releaseType("Dampymon");
        assertEquals(fm.size(), 0);
        assertFalse(fm.contains("Dampymon"));
    }
    @Test
    public void testReleaseAll_t2() {
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        fm.collect("Zappymon");
        fm.releaseType("Dampymon");
        fm.releaseType("Burnymon");
        assertEquals(fm.size(), 1);
        assertFalse(fm.contains("Dampymon"));
    }
    @Test
    public void testReleaseAll_t3() {
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Zappymon");
        fm.releaseType("Burnymon");
        assertEquals(fm.size(), 3);
        assertEquals(fm.typeSize(), 2);
        assertFalse(fm.contains("Burnymon"));
        fm.releaseType("Dampymon");
        assertEquals(fm.size(), 1);
        assertEquals(fm.typeSize(), 1);
        fm.releaseType("Zappymon");
        assertEquals(fm.size(), 0);
        assertEquals(fm.typeSize(), 0);
    }

    @Test
    public void testCount() {
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        assertEquals(fm.countType("Burnymon"), 2);
        assertEquals(fm.countType("Dampymon"), 1);
        assertEquals(fm.countType("forneymon"), 0);
    }
    @Test
    public void testCount_t1() {
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        assertEquals(fm.countType("Burnymon"), 3);
        fm.releaseType("Burnymon");
        assertEquals(fm.countType("Burnymon"), 0);
    }
    @Test
    public void testCount_t2() {
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        assertEquals(fm.countType("Burnymon"), 2);
        assertEquals(fm.countType("Dampymon"), 2);
        fm.releaseType("Burnymon");
        assertEquals(fm.countType("Burnymon"), 0);
        fm.release("Dampymon");
        assertEquals(fm.countType("Dampymon"), 1);
    }

    @Test
    public void testContains() {
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        assertTrue(fm.contains("Burnymon"));
        assertTrue(fm.contains("Dampymon"));
        assertFalse(fm.contains("forneymon"));
    }
    // This is tested pretty much everywhere so...

    @Test
    public void testNth() {
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        fm.collect("Zappymon");
        String[] answers = {
            "Burnymon",
            "Burnymon",
            "Dampymon",
            "Zappymon"
        };
        for (int i = 0; i < fm.size(); i++) {
            String gotten = fm.nth(i);
            assertEquals(answers[i], gotten);
        }
    }
    @Test
    public void testNth_t1() {
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        fm.collect("Zappymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        fm.collect("Zappymon");
        String[] answers = {
            "Dampymon",
            "Dampymon",
            "Burnymon",
            "Burnymon",
            "Zappymon",
            "Zappymon"
        };
        for (int i = 0; i < fm.size(); i++) {
            String gotten = fm.nth(i);
            assertEquals(answers[i], gotten);
        }
    }
    @Test
    public void testNth_t2() {
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        fm.collect("Zappymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        fm.collect("Zappymon");
        fm.release("Dampymon");
        fm.releaseType("Burnymon");
        String[] answers = {
            "Dampymon",
            "Zappymon",
            "Zappymon"
        };
        for (int i = 0; i < fm.size(); i++) {
            String gotten = fm.nth(i);
            assertEquals(answers[i], gotten);
        }
    }

    @Test
    public void testGetRarest() {
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        fm.collect("Zappymon");
        assertEquals(fm.rarestType(), "Zappymon");
        fm.collect("Zappymon");
        assertEquals(fm.rarestType(), "Dampymon");
    }
    @Test
    public void testGetRarest_t1() {
        assertEquals(fm.rarestType(), null);
    }
    @Test
    public void testGetRarest_t2() {
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        fm.collect("Zappymon");
        assertEquals("Zappymon", fm.rarestType());
    }

    // Inter-Forneymonegerie Tests
    // -------------------------------------------------
    @Test
    public void testClone() {
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        Forneymonegerie dolly = fm.clone();
        assertEquals(dolly.countType("Burnymon"), 2);
        assertEquals(dolly.countType("Dampymon"), 1);
        dolly.collect("Zappymon");
        assertFalse(fm.contains("Zappymon"));
    }
    @Test
    public void testClone_t1() {
        Forneymonegerie dolly = fm.clone();
        fm.collect("Dampymon");
        assertFalse(dolly.contains("Dampymon"));
    }
    @Test
    public void testClone_t2() {
        fm.collect("Dampymon");
        Forneymonegerie dolly = fm.clone();
        dolly.collect("Burnymon");
        Forneymonegerie superDolly = dolly.clone();
        superDolly.collect("Zappymon");
        assertTrue(superDolly.contains("Dampymon"));
        assertTrue(superDolly.contains("Burnymon"));
        assertFalse(dolly.contains("Zappymon"));
    }
    @Test
    public void testClone_t3() {
        for (int i = 0; i < START_SIZE; i++) {
            fm.collect("FT" + i);
        }
        Forneymonegerie dolly = fm.clone();
        for (int i = 0; i < START_SIZE; i++) {
            assertTrue(dolly.contains("FT" + i));
        }
    }

    @Test
    public void testTrade() {
        Forneymonegerie fm1 = new Forneymonegerie();
        fm1.collect("Burnymon");
        fm1.collect("Burnymon");
        fm1.collect("Dampymon");
        Forneymonegerie fm2 = new Forneymonegerie();
        fm2.collect("yo");
        fm2.collect("sup");
        fm1.trade(fm2);
        assertTrue(fm1.contains("yo"));
        assertTrue(fm1.contains("sup"));
        assertTrue(fm2.contains("Burnymon"));
        assertTrue(fm2.contains("Dampymon"));
        assertFalse(fm1.contains("Burnymon"));
    }
    @Test
    public void testTrade_t1() {
        Forneymonegerie fm2 = new Forneymonegerie();
        fm.collect("Dampymon");
        fm2.trade(fm);
        assertTrue(fm.empty());
        assertFalse(fm2.empty());
    }
    @Test
    public void testTrade_t2() {
        Forneymonegerie fm2 = new Forneymonegerie();
        Forneymonegerie fm3 = new Forneymonegerie();
        fm2.collect("Dampymon");
        fm.collect("Burnymon");
        fm2.trade(fm);
        fm3.trade(fm2);
        assertTrue(fm2.empty());
        assertTrue(fm.contains("Dampymon"));
        assertTrue(fm3.contains("Burnymon"));
        fm.collect("Zappymon");
        assertFalse(fm2.contains("Zappymon"));
    }
    @Test
    public void testTrade_t3() {
        fm.collect("Dampymon");
        fm.trade(fm);
        assertTrue(fm.contains("Dampymon"));
        assertEquals(fm.size(), 1);
    }
    @Test
    public void testTrade_t4() {
        Forneymonegerie fm2 = new Forneymonegerie();
        fm.trade(fm2);
        assertTrue(fm.empty());
        assertTrue(fm2.empty());
    }

    // Static Method Tests
    // -------------------------------------------------
    @Test
    public void testDiffMon() {
        Forneymonegerie fm1 = new Forneymonegerie();
        fm1.collect("Burnymon");
        fm1.collect("Burnymon");
        fm1.collect("Dampymon");
        Forneymonegerie fm2 = new Forneymonegerie();
        fm2.collect("Burnymon");
        fm2.collect("Zappymon");
        Forneymonegerie fm3 = Forneymonegerie.diffMon(fm1, fm2);
        assertEquals(fm3.countType("Burnymon"), 1);
        assertEquals(fm3.countType("Dampymon"), 1);
        assertFalse(fm3.contains("Zappymon"));
        fm3.collect("Leafymon");
        assertFalse(fm1.contains("Leafymon"));
        assertFalse(fm2.contains("Leafymon"));
    }
    @Test
    public void testDiffMon_t1() {
        Forneymonegerie fm1 = new Forneymonegerie();
        fm1.collect("Dampymon");
        fm1.collect("Burnymon");
        Forneymonegerie fm2 = Forneymonegerie.diffMon(fm, fm1);
        assertEquals(fm2.size(), 0);
        assertFalse(fm2.contains("Dampymon"));
    }
    @Test
    public void testDiffMon_t2() {
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        Forneymonegerie fm1 = new Forneymonegerie();
        fm1.collect("Dampymon");
        fm1.collect("Dampymon");
        Forneymonegerie fm2 = Forneymonegerie.diffMon(fm, fm1);
        assertEquals(fm2.size(), 1);
        assertFalse(fm2.contains("Dampymon"));
    }
    @Test
    public void testDiffMon_t3() {
        Forneymonegerie fm1 = new Forneymonegerie();
        for (int i = 0; i < START_SIZE; i++) {
            fm.collect("FT" + i);
            fm1.collect("FT" + i);
        }
        Forneymonegerie diff = Forneymonegerie.diffMon(fm, fm1);
        assertTrue(diff.empty());
    }
    @Test
    public void testDiffMon_t4() {
        Forneymonegerie fm1 = new Forneymonegerie();
        for (int i = 0; i < START_SIZE; i++) {
            fm.collect("FT" + i);
            fm.collect("FT" + i);
            fm1.collect("FT" + i);
        }
        Forneymonegerie diff = Forneymonegerie.diffMon(fm, fm1);
        assertTrue(Forneymonegerie.sameCollection(diff, fm1));
    }
    @Test
    public void testDiffMon_t5() {
        Forneymonegerie fm1 = new Forneymonegerie();
        Forneymonegerie diff = Forneymonegerie.diffMon(fm, fm1);
        assertTrue(diff.empty());
    }

    @Test
    public void testSameCollection() {
        Forneymonegerie fm1 = new Forneymonegerie();
        fm1.collect("Burnymon");
        fm1.collect("Burnymon");
        fm1.collect("Dampymon");
        Forneymonegerie fm2 = new Forneymonegerie();
        fm2.collect("Dampymon");
        fm2.collect("Burnymon");
        fm2.collect("Burnymon");
        assertTrue(Forneymonegerie.sameCollection(fm1, fm2));
        assertTrue(Forneymonegerie.sameCollection(fm2, fm1));
        fm2.collect("Leafymon");
        assertFalse(Forneymonegerie.sameCollection(fm1, fm2));
    }
    @Test
    public void testSameCollection_t1() {
        Forneymonegerie fm1 = new Forneymonegerie();
        assertTrue(Forneymonegerie.sameCollection(fm, fm1));
        assertTrue(Forneymonegerie.sameCollection(fm1, fm1));
        fm1.collect("Dampymon");
        assertTrue(Forneymonegerie.sameCollection(fm1, fm1));
    }
    @Test
    public void testSameCollection_t2() {
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        Forneymonegerie fm1 = new Forneymonegerie();
        fm1.collect("Burnymon");
        fm1.collect("Dampymon");
        assertFalse(Forneymonegerie.sameCollection(fm, fm1));
        fm.releaseType("Burnymon");
        fm1.releaseType("Burnymon");
        assertTrue(Forneymonegerie.sameCollection(fm, fm1));
    }
    @Test
    public void testSameCollection_t3() {
        assertTrue(Forneymonegerie.sameCollection(fm, new Forneymonegerie()));
    }
    
    // BONUS Method Tests
    // Because there was some skeleton ambiguity over
    // implementing toString, you can earn some bonus
    // points if you did so successfully
    // -------------------------------------------------
    
    @Test
    public void testToString() {
        possible--; // For bonus' sake
        fm.collect("Burnymon");
        assertEquals("[ \"Burnymon\": 1 ]", fm.toString());
    }
    
    @Test
    public void testToString_t1() {
        possible--;
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        fm.collect("Leafymon");
        fm.collect("Zappymon");
        fm.collect("Leafymon");
        assertEquals("[ \"Burnymon\": 2, \"Dampymon\": 1, \"Leafymon\": 2, \"Zappymon\": 1 ]", fm.toString());
    }

}
