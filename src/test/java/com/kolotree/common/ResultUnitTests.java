package com.kolotree.common;

import org.junit.Test;

import static com.kolotree.common.Result.failure;
import static com.kolotree.common.Result.ok;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class ResultUnitTests {

    class TestingError extends Error {
        TestingError(String msg) {
            super(msg);
        }
    }

    @Test
    public void isSuccessShouldBeTrueForSuccessfulResult() {
        Result<String> result = ok("Test");

        assertTrue(result.isSuccess());
        assertFalse(result.isFailure());
    }

    @Test
    public void comparingSameInstanceOfIdenticalResultShouldReturnTrue() {
        Result<String> result = ok("Test");

        assertTrue(result.equals(result));
    }

    @Test
    public void comparingTwoInstancesWithIdenticalValuesShouldReturnTrue() {
        Result<String> result1 = ok("Test");
        Result<String> result2 = ok("Test");

        assertTrue(result1.equals(result2));
    }

    @Test
    public void comparingTwoResultsOfSameTypeDifferentValuesShouldReturnFalse() {
        Result<String> result1 = ok("Test1");
        Result<String> result2 = ok("Test2");

        assertFalse( result1.equals(result2));
    }

    @Test
    public void comparingOneSuccessAndOneFailureShouldReturnFalse() {
        Result<String> result1 = ok("Test1");
        Result<String> result2 = failure(new TestingError("Some error."));

        assertFalse(result1.equals(result2));
        assertFalse(result2.equals(result1));
    }

    @Test
    public void comparingTwoResultsOfDifferentTypeShouldReturnFalse() {
        Result<String> result1 = ok("Test1");
        Result<Integer> result2 = ok(2);

        assertFalse(result1.equals(result2));
        assertFalse(result2.equals(result1));
    }

    @Test(expected = IllegalStateException.class)
    public void gettingValueFromAFailureShouldThrowException() {
        Result<String> result = failure(new TestingError("Some error."));
        result.getValue();
    }

    @Test(expected = IllegalStateException.class)
    public void gettingErrorFromASuccessShouldThrowException() {
        Result<String> result = ok("Test1");
        result.getError();
    }
}
