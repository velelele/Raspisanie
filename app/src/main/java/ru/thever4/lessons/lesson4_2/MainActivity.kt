package ru.thever4.lessons.lesson4_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.thever4.lessons.lesson4_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentHost.id, MainFragment.newInstance())
            .commit()
    }
}