package com.example.techtreasure
import android.content.Intent
import android.widget.Spinner
import android.widget.AdapterView
import android.widget.ImageView
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class amdbuild : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amdbuild)

        val spinner1 = findViewById<Spinner>(R.id.spinner1)
        val spinner2 = findViewById<Spinner>(R.id.spinner2)
        val spinner3 = findViewById<Spinner>(R.id.spinner3)
        val spinner4 = findViewById<Spinner>(R.id.spinner4)
        val spinner5 = findViewById<Spinner>(R.id.spinner5)
        val spinner6 = findViewById<Spinner>(R.id.spinner6)
        val spinner7 = findViewById<Spinner>(R.id.spinner7)

        val imageView1 = findViewById<ImageView>(R.id.imageView1)
        val imageView2 = findViewById<ImageView>(R.id.imageView2)
        val imageView3 = findViewById<ImageView>(R.id.imageView3)
        val imageView4 = findViewById<ImageView>(R.id.imageView4)
        val imageView5 = findViewById<ImageView>(R.id.imageView5)
        val imageView6 = findViewById<ImageView>(R.id.imageView6)
        val imageView7 = findViewById<ImageView>(R.id.imageView7)

        val totalPriceTextView = findViewById<TextView>(R.id.totalPriceTextView)

        val completeButton = findViewById<Button>(R.id.completeButton)

        // Arrays for prices
        val cpuPrices = resources.getStringArray(R.array.cpu_prices)
        val gpuPrices = resources.getStringArray(R.array.gpu_prices)
        val ramPrices = resources.getStringArray(R.array.ram_prices)
        val mbPrices = resources.getStringArray(R.array.mb_prices)
        val ssdPrices = resources.getStringArray(R.array.ssd_prices)
        val psuPrices = resources.getStringArray(R.array.psu_prices)
        val pcasePrices = resources.getStringArray(R.array.case_prices)

        var totalPrice = 0

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                if (selectedItem != "Processors") {
                    // Fetch price based on selected item position
                    val price = cpuPrices[position - 1] // Adjust index because of the header item
                    totalPrice += price.substring(1).replace(",", "").toInt()
                    totalPriceTextView.text = "Total Price: ₹$totalPrice"
                    // Display corresponding image
                    if (selectedItem == "Ryzen 5600h") {
                        imageView1.setImageResource(R.drawable.r5)
                    } else if (selectedItem == "Ryzen 7700x") {
                        imageView1.setImageResource(R.drawable.r7)
                    } else if (selectedItem == "Ryzen 9900") {
                        imageView1.setImageResource(R.drawable.r9)
                    }
                    // Show toast with selected item and price
                    Toast.makeText(this@amdbuild, "$selectedItem - Price: $price", Toast.LENGTH_SHORT).show()
                } else if (selectedItem == "Ram") {
                    // Display default image when "Ram" is selected
                    imageView1.setImageResource(R.drawable.ram)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case when nothing is selected
            }
        }

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                if (selectedItem != "Graphics Cards") {
                    // Fetch price based on selected item position
                    val price = gpuPrices[position - 1] // Adjust index because of the header item
                    totalPrice += price.substring(1).replace(",", "").toInt()
                    totalPriceTextView.text = "Total Price: ₹$totalPrice"
                    // Display corresponding image
                    if (selectedItem == "Radeon 5600XT") {
                        imageView2.setImageResource(R.drawable.g1)
                    } else if (selectedItem == "Radeon 6600XT") {
                        imageView2.setImageResource(R.drawable.g2)
                    } else if (selectedItem == "Radeon 7700XT") {
                        imageView2.setImageResource(R.drawable.g3)
                    }
                    // Show toast with selected item and price
                    Toast.makeText(this@amdbuild, "$selectedItem - Price: $price", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case when nothing is selected
            }
        }

        spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                if (selectedItem != "Ram") {
                    // Fetch price based on selected item position
                    val price = ramPrices[position - 1] // Adjust index because of the header item
                    totalPrice += price.substring(1).replace(",", "").toInt()
                    totalPriceTextView.text = "Total Price: ₹$totalPrice"
                    // Display corresponding image
                    if (selectedItem == "8GB") {
                        imageView3.setImageResource(R.drawable.r1)
                    } else if (selectedItem == "16GB") {
                        imageView3.setImageResource(R.drawable.r2)
                    } else if (selectedItem == "32GB") {
                        imageView3.setImageResource(R.drawable.r3)
                    }
                    // Show toast with selected item and price
                    Toast.makeText(this@amdbuild, "$selectedItem - Price: $price", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case when nothing is selected
            }

        }

        spinner4.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                if (selectedItem != "MotherBoard") {
                    // Fetch price based on selected item position
                    val price = mbPrices[position - 1] // Adjust index because of the header item
                    totalPrice += price.substring(1).replace(",", "").toInt()
                    totalPriceTextView.text = "Total Price: ₹$totalPrice"
                    // Display corresponding image
                    if (selectedItem == "Gigabyte") {
                        imageView4.setImageResource(R.drawable.m1)
                    } else if (selectedItem == "ASUS") {
                        imageView4.setImageResource(R.drawable.m2)
                    } else if (selectedItem == "MSI") {
                        imageView4.setImageResource(R.drawable.m3)
                    }
                    // Show toast with selected item and price
                    Toast.makeText(this@amdbuild, "$selectedItem - Price: $price", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case when nothing is selected
            }
        }

        spinner5.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                if (selectedItem != "SSD") {
                    // Fetch price based on selected item position
                    val price = ssdPrices[position - 1] // Adjust index because of the header item
                    totalPrice += price.substring(1).replace(",", "").toInt()
                    totalPriceTextView.text = "Total Price: ₹$totalPrice"
                    // Display corresponding image
                    if (selectedItem == "128GB") {
                        imageView5.setImageResource(R.drawable.s1)
                    } else if (selectedItem == "256GB") {
                        imageView5.setImageResource(R.drawable.s2)
                    } else if (selectedItem == "500GB") {
                        imageView5.setImageResource(R.drawable.s3)
                    }
                    // Show toast with selected item and price
                    Toast.makeText(this@amdbuild, "$selectedItem - Price: $price", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case when nothing is selected
            }
        }

        spinner6.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                if (selectedItem != "Power Supply") {
                    // Fetch price based on selected item position
                    val price = psuPrices[position - 1] // Adjust index because of the header item
                    totalPrice += price.substring(1).replace(",", "").toInt()
                    totalPriceTextView.text = "Total Price: ₹$totalPrice"
                    // Display corresponding image
                    if (selectedItem == "Corsair 750e") {
                        imageView6.setImageResource(R.drawable.p1)
                    } else if (selectedItem == "Zebronics") {
                        imageView6.setImageResource(R.drawable.p2)
                    } else if (selectedItem == "A650 MSI") {
                        imageView6.setImageResource(R.drawable.p3)
                    }
                    // Show toast with selected item and price
                    Toast.makeText(this@amdbuild, "$selectedItem - Price: $price", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case when nothing is selected
            }
        }

        spinner7.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                if (selectedItem != "Cabinet") {
                    // Fetch price based on selected item position
                    val price = pcasePrices[position - 1] // Adjust index because of the header item
                    totalPrice += price.substring(1).replace(",", "").toInt()
                    totalPriceTextView.text = "Total Price: ₹$totalPrice"
                    // Display corresponding image
                    if (selectedItem == "MSI white") {
                        imageView7.setImageResource(R.drawable.c1)
                    } else if (selectedItem == "Zebronics black") {
                        imageView7.setImageResource(R.drawable.c2)
                    } else if (selectedItem == "Deepcool red") {
                        imageView7.setImageResource(R.drawable.c3)
                    }
                    // Show toast with selected item and price
                    Toast.makeText(this@amdbuild, "$selectedItem - Price: $price", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case when nothing is selected
            }
        }

        completeButton.setOnClickListener {
            val selectedProcessor = spinner1.selectedItem.toString()
            val selectedGraphicsCard = spinner2.selectedItem.toString()
            val selectedRAM = spinner3.selectedItem.toString()
            val selectedMotherboard = spinner4.selectedItem.toString()
            val selectedSSD = spinner5.selectedItem.toString()
            val selectedPowerSupply = spinner6.selectedItem.toString()
            val selectedCabinet = spinner7.selectedItem.toString()

            if (selectedProcessor == "Processors" || selectedGraphicsCard == "Graphics Cards" || selectedRAM == "Ram" ||
                selectedMotherboard == "MotherBoard" || selectedSSD == "SSD" || selectedPowerSupply == "Power Supply" ||
                selectedCabinet == "Cabinet") {
                Toast.makeText(this@amdbuild, "Please complete the build by selecting options for all components.", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this@amdbuild, Report::class.java)
                intent.putExtra("Processor", selectedProcessor)
                intent.putExtra("GraphicsCard", selectedGraphicsCard)
                intent.putExtra("RAM", selectedRAM)
                intent.putExtra("Motherboard", selectedMotherboard)
                intent.putExtra("SSD", selectedSSD)
                intent.putExtra("PowerSupply", selectedPowerSupply)
                intent.putExtra("Cabinet", selectedCabinet)
                intent.putExtra("TotalPrice", totalPrice) // Assuming totalPrice is an integer
                startActivity(intent)
            }
        }
    }
}
