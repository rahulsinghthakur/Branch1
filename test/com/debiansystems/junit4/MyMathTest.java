package com.debiansystems.junit4;

import com.debiansystems.junit4.MyMath;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MyMathTest {

    MyMath myMath = new MyMath();
    @Before
    public void before(){
        System.out.println("Before");
    }

    @After
    public void after(){
        System.out.println("After");
    }

    @BeforeClass
    public static void beforeClass(){
        System.out.println("Before Class");
    }

    @AfterClass
    public static void afterClass(){
        System.out.println("After Class");
    }

    @Test
    public void sum_with3numbers(){
        System.out.println("Test 1");
        assertEquals(6, MyMath.sum(new int[]{1,2,3}));
    }

    @Test
    public void sum_with1number(){
        System.out.println("Test 2");
        assertEquals(3, MyMath.sum(new int[]{3}));
    }

}
