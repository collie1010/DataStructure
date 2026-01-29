package Cool;

/**
 * 雪花演算法 (Snowflake Algorithm) Java 實作範例
 * 
 * 核心目標：生成全局唯一、時間有序的 64-bit ID
 */
public class SnowflakeIdGenerator {

    // ==============================
    // 1. 基礎配置 (Bit Allocation)
    // ==============================
    
    // 起始時間戳 (例如：2023-01-01 00:00:00)
    // 這是我們計算時間差的基準，一旦系統上線後不應隨意更改
    private final long twepoch = 1672531200000L;

    // 各部分佔用的位元數
    private final long workerIdBits = 5L;      // 工作機器 ID 佔用 5 bits
    private final long datacenterIdBits = 5L;  // 資料中心 ID 佔用 5 bits
    private final long sequenceBits = 12L;     // 序列號佔用 12 bits

    // 各部分的最大值 (透過位移運算計算，避免手動輸入錯誤)
    // -1L ^ (-1L << 5) = 31
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    // -1L ^ (-1L << 12) = 4095
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    // 各部分向左位移的位數 (Shift)
    private final long workerIdShift = sequenceBits; // 12
    private final long datacenterIdShift = sequenceBits + workerIdBits; // 12 + 5 = 17
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits; // 12 + 5 + 5 = 22

    // ==============================
    // 2. 物件屬性
    // ==============================
    
    private long workerId;
    private long datacenterId;
    private long sequence = 0L;
    private long lastTimestamp = -1L; // 上次生成 ID 的時間戳

    // ==============================
    // 3. 建構子 (Constructor)
    // ==============================
    
    public SnowflakeIdGenerator(long workerId, long datacenterId) {
        // 檢查 ID 是否合法
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("Worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("Datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    // ==============================
    // 4. 核心方法：獲取下一個 ID
    // ==============================
    
    // 使用 synchronized 保證執行緒安全
    public synchronized long nextId() {
        long currentTimestamp = timeGen();

        // 檢查時鐘回撥 (Clock Rollback)
        // 如果當前時間小於上一次生成 ID 的時間，說明系統時鐘出了問題
        if (currentTimestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - currentTimestamp));
        }

        // 如果是同一毫秒內生成的
        if (lastTimestamp == currentTimestamp) {
            // 序列號自增，並與掩碼進行 AND 運算，保證不溢出
            sequence = (sequence + 1) & sequenceMask;
            
            // 如果序列號溢出 (變成 0)，代表該毫秒內的 4096 個 ID 已用完
            if (sequence == 0) {
                // 阻塞到下一毫秒
                currentTimestamp = tilNextMillis(lastTimestamp);
            }
        } 
        // 如果是新的毫秒
        else {
            sequence = 0L; // 序列號重置
        }

        // 更新上次生成時間
        lastTimestamp = currentTimestamp;

        // 透過位元運算 (OR) 組合各部分，生成最終的 Long ID
        return ((currentTimestamp - twepoch) << timestampLeftShift) | // 時間戳部分
               (datacenterId << datacenterIdShift) |                  // 資料中心部分
               (workerId << workerIdShift) |                          // 工作機器部分
               sequence;                                              // 序列號部分
    }

    // ==============================
    // 5. 輔助方法
    // ==============================
    
    // 阻塞直到下一毫秒，直到獲得新的時間戳
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    // 獲取當前系統時間毫秒數
    private long timeGen() {
        return System.currentTimeMillis();
    }

    // ==============================
    // 6. 測試 Main 方法
    // ==============================
    public static void main(String[] args) {
        // 假設：工作機器 ID = 1, 資料中心 ID = 1
        SnowflakeIdGenerator idWorker = new SnowflakeIdGenerator(1, 1);

        System.out.println("--- 開始生成 ID ---");
        long start = System.currentTimeMillis();
        
        // 模擬生成 100 個 ID
        for (int i = 0; i < 100; i++) {
            long id = idWorker.nextId();
            System.out.println(Long.toBinaryString(id)); // 印出二進位觀察結構
            System.out.println(id);
        }
        
        System.out.println("--- 生成結束，耗時: " + (System.currentTimeMillis() - start) + "ms ---");
    }
}
