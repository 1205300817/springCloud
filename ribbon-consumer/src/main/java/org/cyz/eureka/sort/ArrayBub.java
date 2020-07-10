package org.cyz.eureka.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * 排序
 *
 * @author chengyz
 */
@Slf4j
public class ArrayBub {
    private int[] arr;

    public ArrayBub(int[] arr) {
        this.arr = arr;
    }

    /**
     * 冒泡排序
     */
    public void bubbleSort() {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }

//            log.info("外循环第{}次：{}", i, arr);
        }
    }

    /**
     * 选择排序
     */
    public void selectSort() {
        for (int i = 0; i < arr.length; i++) {
            // 寻找最小值
            int minIdx = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            // 交换
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;

//            log.info("外循环第{}次：{}", i, arr);
        }
    }

    /**
     * 插入排序
     */
    public void insertSort() {
        // 哨兵
        int guard = arr[0];
        for (int i = 1; i < arr.length; i++) {
            //待排元素小于有序序列的最后一个元素时，向前插入
            if (arr[i] < guard) {
                int temp = arr[i];
                for (int j = i; j >= 0; j--) {
                    if (j > 0 && temp < arr[j - 1]) {
                        arr[j] = arr[j - 1];
                    } else {
                        arr[j] = temp;
                        break;
                    }
                }
            } else {
                guard = arr[i];
            }

//            log.info("第{}次，哨兵：{}，数组：{}", i, guard, arr);
        }
    }


}
