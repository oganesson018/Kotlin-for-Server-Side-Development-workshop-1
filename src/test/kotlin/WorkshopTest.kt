import kotlin.test.Test
import kotlin.test.assertEquals

// Assume these functions are defined in your Workshop1.kt file
fun celsiusToFahrenheit(celsius: Double): Double {
    return (celsius * 9 / 5) + 32
}

fun kilometersToMiles(kilometers: Double): Double {
    return kilometers * 0.621371
}

// Assume these are in Workshop2.kt
data class Product(val name: String, val category: String, val price: Double)

fun calculateTotalElectronicsPriceOver500(products: List<Product>): Double {
    return products
        .filter { it.category == "Electronics" && it.price > 500 }
        .sumOf { it.price }
}

fun countElectronicsProductsOver500(products: List<Product>): Int {
    return products
        .count { it.category == "Electronics" && it.price > 500 }
}


class WorkshopTest {

    // --- Tests for Workshop #1: Unit Converter ---

    // celsius input: 20.0
    // expected output: 68.0
    @Test
    fun `test celsiusToFahrenheit with positive value`() {
        // Arrange: ตั้งค่า input และผลลัพธ์ที่คาดหวัง
        val celsiusInput = 20.0
        val expectedFahrenheit = 68.0

        // Act: เรียกใช้ฟังก์ชันที่ต้องการทดสอบ
        val actualFahrenheit = celsiusToFahrenheit(celsiusInput)

        // Assert: ตรวจสอบว่าผลลัพธ์ที่ได้ตรงกับที่คาดหวัง
        assertEquals(expectedFahrenheit, actualFahrenheit, 0.001, "20°C should be 68°F")
    }

    // celsius input: 0.0
    // expected output: 32.0
    @Test
    fun `test celsiusToFahrenheit with zero`() {
        // Arrange: กำหนดค่า 0 องศาเซลเซียส
        val celsiusInput = 0.0
        val expectedFahrenheit = 32.0 // 0°C is 32°F

        // Act: แปลงค่า
        val actualFahrenheit = celsiusToFahrenheit(celsiusInput)

        // Assert: ตรวจสอบผลลัพธ์
        assertEquals(expectedFahrenheit, actualFahrenheit, 0.001, "0°C should be 32°F")
    }

    // celsius input: -10.0
    // expected output: 14.0
    @Test
    fun `test celsiusToFahrenheit with negative value`() {
        // Arrange: กำหนดค่าติดลบ 10 องศาเซลเซียส
        val celsiusInput = -10.0
        val expectedFahrenheit = 14.0 // -10°C is 14°F

        // Act: แปลงค่า
        val actualFahrenheit = celsiusToFahrenheit(celsiusInput)

        // Assert: ตรวจสอบผลลัพธ์
        assertEquals(expectedFahrenheit, actualFahrenheit, 0.001, "-10°C should be 14°F")
    }

    // test for kilometersToMiles function
    // kilometers input: 1.0
    // expected output: 0.621371
    @Test
    fun `test kilometersToMiles with one kilometer`() {
        // Arrange: กำหนด 1 กิโลเมตร
        val kilometersInput = 1.0
        val expectedMiles = 0.621371 // 1 km is approximately 0.621371 miles

        // Act: แปลงค่า
        val actualMiles = kilometersToMiles(kilometersInput)

        // Assert: ตรวจสอบผลลัพธ์
        assertEquals(expectedMiles, actualMiles, 0.000001, "1 km should be 0.621371 miles")
    }

    // --- Tests for Workshop #1: Unit Converter End ---

    // --- Tests for Workshop #2: Data Analysis Pipeline ---
    // ทำการแก้ไขไฟล์ Workshop2.kt ให้มีฟังก์ชันที่ต้องการทดสอบ
    // เช่น ฟังก์ชันที่คำนวณผลรวมราคาสินค้า Electronics ที่ราคา > 500 บาท
    // ในที่นี้จะสมมุติว่ามีฟังก์ชันชื่อ calculateTotalElectronicsPriceOver500 ที่รับ List<Product> และคืนค่า Double
    // จงเขียน test cases สำหรับฟังก์ชันนี้ โดยตรวจสอบผลรวมราคาสินค้า Electronics ที่ราคา > 500 บาท
    // 🚨

    @Test
    fun `test calculateTotalElectronicsPriceOver500`() {
        // Arrange: สร้างรายการสินค้าตัวอย่าง
        val products = listOf(
            Product("Laptop", "Electronics", 1200.0),
            Product("Mouse", "Electronics", 300.0),
            Product("Keyboard", "Electronics", 600.0),
            Product("Chair", "Furniture", 800.0),
            Product("Monitor", "Electronics", 750.0)
        )
        // Expected: Laptop (1200) + Keyboard (600) + Monitor (750) = 2550.0
        val expectedTotal = 2550.0

        // Act: เรียกใช้ฟังก์ชัน
        val actualTotal = calculateTotalElectronicsPriceOver500(products)

        // Assert: ตรวจสอบผลรวม
        assertEquals(expectedTotal, actualTotal, 0.001, "Total electronics price over 500 should be 2550.0")
    }

    @Test
    fun `test calculateTotalElectronicsPriceOver500 with no matching products`() {
        // Arrange: สร้างรายการสินค้าที่ไม่มีสินค้า Electronics ที่ราคาสูงกว่า 500 บาท
        val products = listOf(
            Product("Mousepad", "Electronics", 50.0),
            Product("Desk", "Furniture", 1000.0),
            Product("Headphones", "Electronics", 450.0)
        )
        val expectedTotal = 0.0 // ไม่มีสินค้าที่ตรงเงื่อนไข

        // Act: เรียกใช้ฟังก์ชัน
        val actualTotal = calculateTotalElectronicsPriceOver500(products)

        // Assert: ตรวจสอบผลรวม
        assertEquals(expectedTotal, actualTotal, 0.001, "Total should be 0.0 when no matching products.")
    }

    @Test
    fun `test calculateTotalElectronicsPriceOver500 with empty list`() {
        // Arrange: รายการสินค้าว่างเปล่า
        val products = emptyList<Product>()
        val expectedTotal = 0.0

        // Act: เรียกใช้ฟังก์ชัน
        val actualTotal = calculateTotalElectronicsPriceOver500(products)

        // Assert: ตรวจสอบผลรวม
        assertEquals(expectedTotal, actualTotal, 0.001, "Total should be 0.0 for an empty list.")
    }

    // จงเขียน test cases เช็คจำนวนสินค้าที่อยู่ในหมวด 'Electronics' และมีราคามากกว่า 500 บาท
    // 🚨
    @Test
    fun `test countElectronicsProductsOver500`() {
        // Arrange: สร้างรายการสินค้าตัวอย่าง
        val products = listOf(
            Product("Laptop", "Electronics", 1200.0),
            Product("Mouse", "Electronics", 300.0),
            Product("Keyboard", "Electronics", 600.0),
            Product("Chair", "Furniture", 800.0),
            Product("Monitor", "Electronics", 750.0)
        )
        // Expected: Laptop, Keyboard, Monitor (3 items)
        val expectedCount = 3

        // Act: เรียกใช้ฟังก์ชัน
        val actualCount = countElectronicsProductsOver500(products)

        // Assert: ตรวจสอบจำนวน
        assertEquals(expectedCount, actualCount, "Should count 3 electronics products over 500.")
    }

    @Test
    fun `test countElectronicsProductsOver500 with no matching products`() {
        // Arrange: สร้างรายการสินค้าที่ไม่มีสินค้า Electronics ที่ราคาสูงกว่า 500 บาท
        val products = listOf(
            Product("Mousepad", "Electronics", 50.0),
            Product("Desk", "Furniture", 1000.0),
            Product("Headphones", "Electronics", 450.0)
        )
        val expectedCount = 0

        // Act: เรียกใช้ฟังก์ชัน
        val actualCount = countElectronicsProductsOver500(products)

        // Assert: ตรวจสอบจำนวน
        assertEquals(expectedCount, actualCount, "Should count 0 when no matching products.")
    }

    @Test
    fun `test countElectronicsProductsOver500 with empty list`() {
        // Arrange: รายการสินค้าว่างเปล่า
        val products = emptyList<Product>()
        val expectedCount = 0

        // Act: เรียกใช้ฟังก์ชัน
        val actualCount = countElectronicsProductsOver500(products)

        // Assert: ตรวจสอบจำนวน
        assertEquals(expectedCount, actualCount, "Should count 0 for an empty list.")
    }

    // --- Tests for Workshop #2: Data Analysis Pipeline End ---10.17
}