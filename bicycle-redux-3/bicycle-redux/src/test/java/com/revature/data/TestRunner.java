//package com.revature.data;
//
//import org.junit.runner.JUnitCore;
//        import org.junit.runner.Result;
//        import org.junit.runner.notification.Failure;
//
//public class TestRunner {
//    public static void main(String[] args) {
//        Result result1 = JUnitCore.runClasses(BicycleDaoTest.class);
//        Result result2 = JUnitCore.runClasses(OfferDaoTest.class);
//        Result result3 = JUnitCore.runClasses(PersonDaoTest.class);
//
//        for (Failure failure : result1.getFailures()) {
//            System.out.println(failure.toString());
//        }
//        for (Failure failure : result2.getFailures()) {
//            System.out.println(failure.toString());
//        }
//        for (Failure failure : result3.getFailures()) {
//            System.out.println(failure.toString());
//        }
//
//        System.out.println(result1.wasSuccessful());
//        System.out.println(result2.wasSuccessful());
//        System.out.println(result3.wasSuccessful());
//    }
//}