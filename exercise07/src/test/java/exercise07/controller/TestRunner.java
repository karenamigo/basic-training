package exercise07.controller;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

	public static void main(String[] args) {
		Result testStdeResult = JUnitCore.runClasses(StudentControllerTest.class);

		for (Failure failure : testStdeResult.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println(testStdeResult.wasSuccessful());

	}
}