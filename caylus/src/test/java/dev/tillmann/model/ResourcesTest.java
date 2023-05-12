package dev.tillmann.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourcesTest {
    private int allWood;
    private int allFood;
    private int allWorkers;

    @BeforeEach
    public void setup() {
        Resources.provideCamp(new Camp(4));
        allWood = Resources.remainingWood();
        allFood = Resources.remainingFood();
        allWorkers = Resources.remainingWorkers();
    }

    @AfterEach
    public void tearDown() {
        Resources.reset();
        Resources.provideCamp(new Camp(4));
    }

    @Test
    void TakeResourcesCanTakeTest() {
        allWood = Resources.remainingWood();
        Resources resources = Resources.empty().addWood(2).addFood(allFood).addWorkers(5);


        boolean canTake = Resources.tryTake(resources);


        assertTrue(canTake);
        assertEquals(allWood - 2, Resources.remainingWood());
        assertEquals(0, Resources.remainingFood());
        assertEquals(allWorkers - 5, Resources.remainingWorkers());
    }

    @Test
    void TakeResourcesCannnotTakeTest() {
        Resources resources = Resources.empty().addWood(allWood + 1).addFood(3).addWorkers(5);


        boolean canTake = Resources.tryTake(resources);


        assertFalse(canTake);
        assertEquals(allWood, Resources.remainingWood());
        assertEquals(allFood, Resources.remainingFood());
        assertEquals(allWorkers, Resources.remainingWorkers());
    }

    @Test
    void TakeAndReturnTest() {
        Resources resources = Resources.empty().addFabric(1).addFood(2).addWorkers(5);


        Resources.tryTake(resources);
        Resources.returnBack(resources);


        assertEquals(allWood, Resources.remainingWood());
        assertEquals(allFood, Resources.remainingFood());
        assertEquals(allWorkers, Resources.remainingWorkers());
    }
}
