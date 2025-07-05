package org.example

// 1. กำหนด data class สำหรับเก็บข้อมูลสินค้า
// **แก้ไขแล้ว: ลบ ': Any' ออก เนื่องจากทุกคลาสใน Kotlin สืบทอดจาก Any โดยปริยาย**
data class Product(val name: String, val price: Double, val category: String)

fun main() {
    // 2. สร้างรายการสินค้าตัวอย่าง (List<Product>)
    val products = listOf(
        Product(name = "Laptop", price = 35000.0, category = "Electronics"),
        Product(name = "Smartphone", price = 25000.0, category = "Electronics"),
        Product(name = "T-shirt", price = 450.0, category = "Apparel"),
        Product(name = "Monitor", price = 7500.0, category = "Electronics"),
        Product(name = "Keyboard", price = 499.0, category = "Electronics"), // ราคาไม่เกิน 500
        Product(name = "Jeans", price = 1200.0, category = "Apparel"),
        Product(name = "Headphones", price = 1800.0, category = "Electronics"), // ตรงตามเงื่อนไข
        Product(name = "Mouse", price = 299.0, category = "Electronics"), // สินค้าราคาไม่เกิน 1,000
        Product(name = "Desk Chair", price = 5500.0, category = "Furniture"), // สินค้าระหว่าง 1,000 - 9,999
        Product(name = "Gaming PC", price = 60000.0, category = "Electronics") // สินค้าราคา 10,000 ขึ้นไป
    )

    println("รายการสินค้าทั้งหมด:")
    products.forEach { println(it) }
    println("--------------------------------------------------")

    // --- โจทย์ก่อนหน้า: จงหาผลรวมราคาสินค้าทั้งหมดในหมวด 'Electronics' ที่มีราคามากกว่า 500 บาท ---

    // 3. วิธีที่ 1: การใช้ Chaining กับ List โดยตรง
    val totalElecPriceOver500 = products
        .filter { it.category == "Electronics" } // กรองเฉพาะสินค้าในหมวด "Electronics"
        .filter { it.price > 500.0 }        // กรองเฉพาะสินค้าที่มีราคาสูงกว่า 500 บาท
        .sumOf { it.price }                  // รวมราคาสินค้าที่ผ่านการกรองทั้งหมด

    println("วิธีที่ 1: ใช้ Chaining กับ List")
    println("ผลรวมราคาสินค้า Electronics ที่ราคา > 500 บาท: $totalElecPriceOver500 บาท")
    println("--------------------------------------------------")


    // 4. (ขั้นสูง) วิธีที่ 2: การใช้ .asSequence() เพื่อเพิ่มประสิทธิภาพ
    val totalElecPriceOver500Sequence = products.asSequence()
        .filter { it.category == "Electronics" } // กรองเฉพาะสินค้าในหมวด "Electronics"
        .filter { it.price > 500.0 }        // กรองเฉพาะสินค้าที่มีราคาสูงกว่า 500 บาท
        .sumOf { it.price }                  // รวมราคาสินค้าที่ผ่านการกรองทั้งหมด

    println("วิธีที่ 2: ใช้ .asSequence() (ขั้นสูง)")
    println("ผลรวมราคาสินค้า Electronics ที่ราคา > 500 บาท: $totalElecPriceOver500Sequence บาท")
    println("--------------------------------------------------")


    println("อภิปรายความแตกต่างระหว่าง List และ Sequence:")
    println("1. List Operations (วิธีที่ 1):")
    println("   - ทุกครั้งที่เรียกใช้ operation (เช่น filter, map) จะมีการสร้าง Collection (List) ใหม่ขึ้นมาเพื่อเก็บผลลัพธ์ของขั้นนั้นๆ")
    println("   - ตัวอย่าง: filter ครั้งแรกสร้าง List ใหม่ -> filter ครั้งที่สองสร้าง List ใหม่อีกใบ -> map สร้าง List สุดท้าย -> sum ทำงาน")
    println("   - เหมาะกับข้อมูลขนาดเล็ก เพราะเข้าใจง่าย แต่ถ้าข้อมูลมีขนาดใหญ่มาก (ล้าน records) จะสิ้นเปลืองหน่วยความจำและเวลาในการสร้าง Collection ใหม่ๆ ซ้ำซ้อน")
    println()
    println("2. Sequence Operations (วิธีที่ 2):")
    println("   - ใช้การประมวลผลแบบ 'Lazy' (ทำเมื่อต้องการใช้ผลลัพธ์จริงๆ)")
    println("   - operations ทั้งหมด (filter, map) จะไม่ทำงานทันที แต่จะถูกเรียงต่อกันไว้")
    println("   - ข้อมูลแต่ละชิ้น (each element) จะไหลผ่าน Pipeline ทั้งหมดทีละชิ้น จนจบกระบวนการ")
    println("   - เช่น: 'Laptop' จะถูก filter category -> filter price -> map price จากนั้น 'Smartphone' ถึงจะเริ่มทำกระบวนการเดียวกัน")
    println("   - จะไม่มีการสร้าง Collection กลางทาง ทำให้ประหยัดหน่วยความจำและเร็วกว่ามากสำหรับชุดข้อมูลขนาดใหญ่ เพราะทำงานกับข้อมูลทีละชิ้นและทำทุกขั้นตอนให้เสร็จในรอบเดียว")
    println("   - การคำนวณจะเกิดขึ้นเมื่อมี 'Terminal Operation' มาเรียกใช้เท่านั้น (ในที่นี้คือ .sum())")
    println("--------------------------------------------------")

    // --- โจทย์ใหม่: แบ่งกลุ่มสินค้าตาม range ราคา ---

    // 5. แบ่งกลุ่มสินค้าตามช่วงราคา
    val groupedProducts = products.groupBy { product ->
        when {
            product.price <= 1000.0 -> "ราคาไม่เกิน 1,000 บาท"
            product.price in 1000.0..9999.0 -> "ราคา 1,000 - 9,999 บาท"
            else -> "ราคา 10,000 บาทขึ้นไป"
        }
    }

    // 6. แสดงผลลัพธ์การแบ่งกลุ่ม
    println("ผลลัพธ์การแบ่งกลุ่มสินค้าตามช่วงราคา:")
    groupedProducts.forEach { (range, productList) ->
        println("--- กลุ่ม: $range ---")
        productList.forEach { println(it) }
        println() // เพิ่มบรรทัดว่างเพื่อให้อ่านง่ายขึ้น
    }
}