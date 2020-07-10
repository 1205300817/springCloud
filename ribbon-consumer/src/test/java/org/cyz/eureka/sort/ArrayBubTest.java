package org.cyz.eureka.sort;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ArrayBubTest {

    private int[] arr;
    private Random random = new Random();

    @BeforeEach
    void setUp() {
        // 数组初始化
//        arr = new int[]{3, 2, 4, 9, 5, 7};

//        arr = new int[10000];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = random.nextInt(10000);
//        }
    }

    void init() {
        // 数组初始化
//        arr = new int[]{3, 2, 4, 9, 5, 7};

        arr = new int[10000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10000);
        }
    }

    @Test
    void mainTest() {
        init();
        int[] arr1 = arr.clone();
        int[] arr2 = arr.clone();
        int[] arr3 = arr.clone();

        arr = arr1;
        bubbleSort();

        arr = arr2;
        selectSort();

        arr = arr3;
        insertSort();

    }

    @Test
    void bubbleSort() {
        long start = System.currentTimeMillis();
        log.info("================冒泡排序=============");
        log.info("---------------排序前------------{}", arr);
        ArrayBub arrayBub = new ArrayBub(arr);
        arrayBub.bubbleSort();
        log.info("---------------排序后------------{}", arr);
        long end = System.currentTimeMillis();
        log.info("总耗时：{}", end - start);
    }

    @Test
    void selectSort() {
        log.info("================选择排序=============");
        long start = System.currentTimeMillis();
        log.info("---------------排序前------------{}", arr);
        ArrayBub arrayBub = new ArrayBub(arr);
        arrayBub.selectSort();
        log.info("---------------排序后------------{}", arr);
        long end = System.currentTimeMillis();
        log.info("总耗时：{}", end - start);
    }


    @Test
    void insertSort() {
        log.info("================插入排序=============");
        long start = System.currentTimeMillis();
        log.info("---------------排序前------------{}", arr);
        ArrayBub arrayBub = new ArrayBub(arr);
        arrayBub.insertSort();
        log.info("---------------排序后------------{}", arr);
        long end = System.currentTimeMillis();
        log.info("总耗时：{}", end - start);
    }
}