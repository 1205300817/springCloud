package org.cyz.eureka.jvm;

/**
 * 1. 对象可以在被GC时自救
 * 2. 自救机会只有一次,一个对象的finalize()方法最多被系统自动调用一次
 *
 * @author chengyz
 */
public class FinalizeEscapGC {
    public static FinalizeEscapGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes, i am still alive! ");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed! ");
        FinalizeEscapGC.SAVE_HOOK = this;
    }



    /**
     * 任何对象的finalize()方法都只被系统自动调用一次,
     * 如果对象面临下一次回收,finalize()方法不会被再次执行,
     * 于是下一次自救失败
     *
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {
        SAVE_HOOK = new FinalizeEscapGC();
        // 第一次自救-成功
        SAVE_HOOK = null;
        System.gc();
        // Finalizer方法优先级低,暂停0.5秒,以等待
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead! ");
        }

        // 第二次自救-失败
        SAVE_HOOK = null;
        System.gc();
        // Finalizer方法优先级低,暂停0.5秒,以等待
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead! ");
        }
    }
}
