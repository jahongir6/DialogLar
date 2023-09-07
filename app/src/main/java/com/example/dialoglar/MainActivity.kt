package com.example.dialoglar


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.example.dialoglar.databinding.ActivityMainBinding
import com.example.dialoglar.databinding.ItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.alert.setOnClickListener {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("ogohlantirish")
            alert.setMessage("haqiqatdan ham ochirasmi")
            alert.setPositiveButton("ha", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    Toast.makeText(this@MainActivity, "ochti", Toast.LENGTH_SHORT).show()
                }
            })
            alert.show()
        }
        binding.custom.setOnClickListener {
            val alert = AlertDialog.Builder(this).create()
            val item = ItemBinding.inflate(layoutInflater)
            alert.setView(item.root)
            item.hello.setOnClickListener {
                alert.cancel()
            }
            alert.show()
        }
        binding.fragment.setOnClickListener {
            val dialog = MyDataFragment()
            dialog.show(supportFragmentManager.beginTransaction(), "ixtiyoriy")
        }
        binding.datePicker.setOnClickListener {
            val dateDialog = DatePickerDialog(this)
            dateDialog.setOnDateSetListener(object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    Toast.makeText(
                        this@MainActivity,
                        "${year},${month},${dayOfMonth}",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            })
            dateDialog.show()
        }
        binding.timePicker.setOnClickListener {
            val date = Date()
            val time = TimePickerDialog(
                this, object : TimePickerDialog.OnTimeSetListener {
                    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                        Toast.makeText(
                            this@MainActivity,
                            "${hourOfDay}:${minute}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                date.hours,
                date.minutes,
                true
            )
            time.show()
        }
        binding.bottomSheet.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            val itemBinding = ItemBinding.inflate(layoutInflater)
            dialog.setContentView(itemBinding.root)
            dialog.show()
            itemBinding.hello.setOnClickListener {
                dialog.cancel()
            }
        }
        binding.snackBar.setOnClickListener {
            val snackbar = Snackbar.make(it, "ochirildi", Snackbar.LENGTH_SHORT)
            snackbar.setAction("qaytish", {
                Toast.makeText(this, "qaytarildi", Toast.LENGTH_SHORT).show()
            })
            snackbar.show()
        }
    }
}
